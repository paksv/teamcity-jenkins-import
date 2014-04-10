/*
 * Copyright 2000-2014 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package jetbrains.buildServer.importJenkins;

import com.intellij.openapi.util.text.StringUtil;
import jetbrains.buildServer.serverSide.BuildTypeOptions;
import jetbrains.buildServer.serverSide.ProjectManager;
import jetbrains.buildServer.serverSide.SBuildType;
import jetbrains.buildServer.serverSide.SProject;
import jetbrains.buildServer.vcs.CheckoutRules;
import jetbrains.buildServer.vcs.SVcsRoot;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Sergey.Pak
 *         Date: 4/2/2014
 *         Time: 7:02 PM
 */
public class JenkinsImport {

  private final Element myRootElement;
  @NotNull
  private final ProjectManager myProjectManager;
  @NotNull
  private final SProject myProject;
  @NotNull
  private final SBuildType myBuildType;
  @NotNull
  private int myRunnerIdx = 0;

  public static void importProject(@NotNull final String projectName, @NotNull final Element rootElement, @NotNull final ProjectManager projectManager) {
    JenkinsImport importer = new JenkinsImport(projectName, rootElement, projectManager);
    final String projectType = rootElement.getName();
//    if (projectType.equals("project")) {
      importer.parseBuilders();
      importer.parseScm();
      importer.parseTriggers();
      importer.persist();
//    }
  }

  private JenkinsImport(@NotNull final String projectName, @NotNull final Element rootElement, @NotNull final ProjectManager projectManager) {
    myRootElement = rootElement;
    myProjectManager = projectManager;

    myProject = myProjectManager.createProject(projectName);
    myBuildType = myProject.createBuildType(projectName + "_bt");
  }

  private void parseScm() {
    final Element scm = myRootElement.getChild("scm");
    final String scmClass = scm.getAttributeValue("class");
    if (scmClass.equals("hudson.scm.SubversionSCM")) {
      final SVcsRoot[] sVcsRoots = parseSubversionScm(scm);
    } else if (scmClass.equals("hudson.plugins.git.GitSCM")) {
      parseGitScm(scm);
    }

  }

  private SVcsRoot[] parseGitScm(@NotNull final Element scmElement) {
    final List<SVcsRoot> roots = new ArrayList<SVcsRoot>();
    Element remoteConfigs = scmElement.getChild("userRemoteConfigs");
    int idx = 0;

    String local = ".";

    final Map<String, String> extensionParamsMap = new HashMap<String, String>();
    if (scmElement.getChild("extensions") != null) {
      final Element extensions = scmElement.getChild("extensions");
      for (Object extObj : extensions.getChildren()) {
        if (!(extObj instanceof Element)) continue;

        final Element ext = (Element) extObj;
        if (!ext.getName().startsWith("hudson.plugins.git.extensions.impl."))
          continue;
        String extName = ext.getName().substring("hudson.plugins.git.extensions.impl.".length());
        if ("SubmoduleOption".equals(extName)) {
          if ("true".equals(ext.getChildText("disableSubmodules"))) {
            extensionParamsMap.put("submoduleCheckout", "IGNORE");
          }
        } else if ("RelativeTargetDirectory".equals(extName)) {
          if (ext.getChildText("relativeTargetDir") != null) {
            local = ext.getChildText("relativeTargetDir");
          }
        } else if ("CleanCheckout".equals(extName)) {
          extensionParamsMap.put("agentCleanPolicy", "ALWAYS");
          extensionParamsMap.put("agentCleanFilesPolicy", "ALL_UNTRACKED");
        } else if ("PerBuildTag".equals(extName)) {
          //TODO implement
        } else if ("UserIdentity".equals(extName)) {
          // TODO implement
        } else if ("WipeWorkspace".equals(extName)) {
          myBuildType.setOption(BuildTypeOptions.BT_CLEAN_BUILD, true);
        }
      }
    }

    final Element branches = scmElement.getChild("branches");
    StringBuilder branchSpec = new StringBuilder();
    if (branches != null) {
      for (Object branchObj : branches.getChildren()) {
        if (!(branchObj instanceof Element)) continue;

        Element branch = (Element) branchObj;
        String specifier = branch.getChildText("name");
        if (specifier != null) {
          final StringBuilder branchVal = new StringBuilder("/refs/heads");
          if (StringUtil.isEmpty(specifier)) {
            branchVal.append("/*");
          } else {
            final String[] specifierParts = specifier.split("/");
            int specifierIdx = 1;
            if ("remotes".equals(specifierParts[0])) {
              specifierIdx++;
            }
            for (int i = specifierIdx; i < specifierParts.length; i++) {
              branchVal.append("/").append(specifierParts[i]);
            }
          }
          branchSpec.append(branchVal.toString()).append("\n");
        }
      }
    }


    for (Object confObj : remoteConfigs.getChildren()) {
      if (!(confObj instanceof Element)) continue;

      final Element gitRootConfig = (Element) confObj;
      if (!"hudson.plugins.git.UserRemoteConfig".equals(gitRootConfig.getName())) continue;

      final String url = gitRootConfig.getChildText("url").trim();

      String refspec = gitRootConfig.getChildText("refspec");
      if (refspec.startsWith("+")) {
        refspec = refspec.substring(1);
      }
      if (refspec.contains(":")) {
        refspec = refspec.split(":")[0];
      }
      final Map<String, String> paramsMap = new HashMap<String, String>();
      paramsMap.put("url", url);
      if (refspec.contains("*")) {
        if (branchSpec.length() > 0) {
          paramsMap.put("teamcity:branchSpec", branchSpec.toString());
        }
      } else {
        paramsMap.put("branch", refspec);
      }

      paramsMap.putAll(extensionParamsMap);

      final SVcsRoot vcsRoot = myProject.createVcsRoot("jetbrains.git", myProject.getName() + "_vcsroot_" + (++idx), paramsMap);
      myBuildType.addVcsRoot(vcsRoot);
      if (!".".equals(local)) {
        myBuildType.setCheckoutRules(vcsRoot, CheckoutRules.createOn("+:.=>" + local));
      }
      roots.add(vcsRoot);
    }
    return roots.toArray(new SVcsRoot[roots.size()]);
  }

  private SVcsRoot[] parseSubversionScm(@NotNull final Element scmElement) {
    final List<SVcsRoot> roots = new ArrayList<SVcsRoot>();
    Element locations = scmElement.getChild("locations");
    final String updater = scmElement.getChild("workspaceUpdater").getAttributeValue("class");
    int idx = 0;
    for (Object o : locations.getChildren("hudson.scm.SubversionSCM_-ModuleLocation")) {
      if (!(o instanceof Element))
        continue;
      final Element location = (Element) o;
      final String remote = location.getChildText("remote");
      final String local = location.getChildText("local");
      final Map<String, String> paramsMap = new HashMap<String, String>();
      paramsMap.put("url", remote);

      if ("hudson.scm.subversion.UpdateWithRevertUpdater".equals(updater)) {
        // Use 'svn update' as much as possible, with 'svn revert' before update
        paramsMap.put("enforce-revert", "true");
      } else if ("hudson.scm.subversion.UpdateWithCleanUpdater".equals(updater)) {
        // Emulate clean checkout by first deleting unversioned/ignored files, then 'svn update'
        //TODO: add swabra here
      } else if ("hudson.scm.subversion.CheckoutUpdater".equals(updater)) {
        // Always check out a fresh copy
        myBuildType.setOption(BuildTypeOptions.BT_CLEAN_BUILD, true);
      } else if ("hudson.scm.subversion.UpdateUpdater".equals(updater)) {
        // Use 'svn update' as much as possible
        // regular checkout
      }

      final String ignoreExternalsOption = location.getChildText("ignoreExternalsOption");
      paramsMap.put("externals-mode",
              "true".equals(ignoreExternalsOption) ? "externals-none" : "externals-full");
      paramsMap.put("svn-use-default-config-directory", "true");
      final SVcsRoot vcsRoot = myProject.createVcsRoot("svn", myProject.getName() + "_vcsroot_" + (++idx), paramsMap);
      myBuildType.addVcsRoot(vcsRoot);
      roots.add(vcsRoot);
      if (!".".equals(local)) {
        myBuildType.setCheckoutRules(vcsRoot, CheckoutRules.createOn("+:.=>" + local));
      }
    }
    return roots.toArray(new SVcsRoot[roots.size()]);
  }

  private void parseBuilders(final Element buildersList) {
    for (Object o : buildersList.getChildren()) {
      if (!(o instanceof Element)) continue;

      final Element builder = (Element) o;
      if ("hudson.tasks.Ant".equals(builder.getName())) {
        parseAntBuilder(builder);
      } else if ("hudson.tasks.Maven".equals(builder.getName())) {
        parseMavenBuildStep(builder);
      } else if ("hudson.tasks.Shell".equals(builder.getName()) || "hudson.tasks.BatchFile".equals(builder.getName())) {
        parseCommandLineBuilder(builder);
      }
    }
  }

  private void parseBuilders() {
    if (myRootElement.getChild("prebuilders") != null) {
      parseBuilders(myRootElement.getChild("prebuilders"));
    }
    final Element builders = myRootElement.getChild("builders");
    if (builders != null) {
      parseBuilders(builders);
    }

    parseMavenModule();
    if (myRootElement.getChild("postbuilders") != null) {
      parseBuilders(myRootElement.getChild("postbuilders"));
    }
  }

  private void parseCommandLineBuilder(final Element builderElement) {
    final Map<String, String> paramsMap = new HashMap<String, String>();
    paramsMap.put("use.custom.script", "true");
    paramsMap.put("script.content", builderElement.getChildText("command"));

    myBuildType.addBuildRunner("SimpleRunner_" + ++myRunnerIdx, "simpleRunner", paramsMap);
  }

  private void parseAntBuilder(@NotNull final Element builderElement) {
    final String targets = builderElement.getChildText("targets");
    final String antName = builderElement.getChildText("antName");
    final String antOpts = builderElement.getChildText("antOpts");
    final String buildFile = builderElement.getChildText("buildFile");
    final String properties = builderElement.getChildText("properties");
    final Map<String, String> paramsMap = new HashMap<String, String>();
    paramsMap.put("target", targets);
    paramsMap.put("runnerArgs", properties);
    paramsMap.put("build-file-path", StringUtil.isEmpty(buildFile) ? "build.xml" : buildFile);
    paramsMap.put("jvmArgs", antOpts);

    //TODO: add handling for ant name and version

    myBuildType.addBuildRunner("AntRunner_" + ++myRunnerIdx, "Ant", paramsMap);
  }

  private void parseMavenModule() {
    if (!"maven2-moduleset".equals(myRootElement.getName())) return;

    final String goals = myRootElement.getChildText("goals");
    final String mavenOpts = myRootElement.getChildText("mavenOpts");
    final String incrementalBuild = myRootElement.getChildText("incrementalBuild");
    final Element localRepositoryElem = myRootElement.getChild("localRepository");
    final String runHeadless = myRootElement.getChildText("runHeadless");

    //TODO: add handling for custom maven properties

    createMavenRunner(goals,
            "true".equals(incrementalBuild),
            "true".equals(runHeadless) ? "-Djava.awt.headless=true" : "",
            "pom.xml",
            mavenOpts,
            localRepositoryElem != null
    );

  }

  private void parseMavenBuildStep(@NotNull final Element element) {
    final StringBuilder props = new StringBuilder();
    final String jenkinsProps = element.getChildText("properties");
    if (jenkinsProps != null) {
      final String[] split = jenkinsProps.split("\n");
      for (String s : split) {
        final String[] vals = s.split("=");
        if (vals.length != 2 || StringUtil.isEmpty(vals[0]) || StringUtil.isEmpty(vals[1]))
          continue;
        props.append("-D").append(vals[0].trim()).append("=").append(vals[1].trim());
      }
    }
    createMavenRunner(element.getChildText("targets"),
            false,
            element.getChildText("jvmOptions"),
            StringUtil.isEmpty(element.getChildText("pom"))? "pom.xml" : element.getChildText("pom"),
            props.toString(),
            "true".equals(element.getChildText("usePrivateRepository"))
    );

  }

  private void createMavenRunner(final String goals,
                                 final boolean isIncremental,
                                 final String jvmArgs,
                                 final String pomLocation,
                                 final String runnerArgs,
                                 final boolean useOwnLocalRepo) {
    final Map<String, String> paramsMap = new HashMap<String, String>() {{
      put("goals", goals);
      put("isIncremental", String.valueOf(isIncremental));
      put("jvmArgs", jvmArgs);
      put("pomLocation", pomLocation);
      put("runnerArgs", runnerArgs);
      put("useOwnLocalRepo", String.valueOf(useOwnLocalRepo));
    }};

    myBuildType.addBuildRunner("MavenRunner_" + ++myRunnerIdx, "Maven2", paramsMap);
  }

  private void parseTriggers() {
    final Element triggers = myRootElement.getChild("triggers");
    for (Object o : triggers.getChildren()) {
      if (!(o instanceof Element)) continue;

      final Element trigger = (Element) o;
      if ("hudson.triggers.SCMTrigger".equals(trigger.getName()) || "hudson.triggers.TimerTrigger".equals(trigger.getName())) {
        String specs = trigger.getChildText("spec");
        boolean scmTrigger = "hudson.triggers.SCMTrigger".equals(trigger.getName());
        if (scmTrigger) {
          // simplify to VCS trigger:
          final Map<String, String> triggerParams = new HashMap<String, String>();
          if (StringUtil.isEmpty(specs)) {
            triggerParams.put("quietPeriodMode", "USE_DEFAULT");
            addVcsTriggerRules(triggerParams);
            myBuildType.addBuildTrigger("vcsTrigger", triggerParams);
            return;
          }
        }
        final String[] specLines = specs.split("\n");
        for (String specLine : specLines) {
          if (specLine.trim().startsWith("#")) continue; //skip comments

          final Map<String, String> triggerParams = new HashMap<String, String>();

          triggerParams.put("cronExpression_year", "*");
          triggerParams.put("cronExpression_month", "*");
          triggerParams.put("cronExpression_dm", "*");
          triggerParams.put("cronExpression_dw", "*");
          triggerParams.put("cronExpression_hour", "*");
          triggerParams.put("cronExpression_min", "*");
          triggerParams.put("cronExpression_sec", "0");
          if ("@hourly".equals(specLine)) {
            triggerParams.put("cronExpression_hour", "*");
            triggerParams.put("cronExpression_min", "0");
          } else if ("@daily".equals(specLine) || "@midnight".equals(specLine)) {
            triggerParams.put("cronExpression_hour", "0");
            triggerParams.put("cronExpression_min", "0");
          } else if ("@weekly".equals(specLine)) {
            triggerParams.put("cronExpression_dw", "1");
            triggerParams.put("cronExpression_hour", "0");
            triggerParams.put("cronExpression_min", "0");
          } else if ("@monthly".equals(specLine)) {
            triggerParams.put("cronExpression_dm", "1");
            triggerParams.put("cronExpression_hour", "0");
            triggerParams.put("cronExpression_min", "0");
          } else if ("@annually".equals(specLine) || "@yearly".equals(specLine)) {
            triggerParams.put("cronExpression_hour", "0");
            triggerParams.put("cronExpression_min", "0");
            triggerParams.put("cronExpression_dm", "1");
            triggerParams.put("cronExpression_month", "1");
          } else {
            // assuming cron-view
            String[] values = specLine.split("\\s");
            triggerParams.put("cronExpression_month", values[3]);
            triggerParams.put("cronExpression_dm", values[2]);
            triggerParams.put("cronExpression_dw", values[4]);
            triggerParams.put("cronExpression_hour", values[1]);
            triggerParams.put("cronExpression_min", values[0]);
          }
          if (scmTrigger) {
            triggerParams.put("triggerBuildWithPendingChangesOnly", "true");
            addVcsTriggerRules(triggerParams);
          }
          myBuildType.addBuildTrigger("schedulingTrigger", triggerParams);
        }
      }
    }
  }

  private void addVcsTriggerRules(final Map<String, String> triggerParams) {
    //TODO add
  }


  private void persist() {
    myProject.persist();
    myBuildType.persist();
    final List<SVcsRoot> vcsRoots = myProject.getVcsRoots();
    for (SVcsRoot vcsRoot : vcsRoots) {
      vcsRoot.persist();
    }
  }
}
