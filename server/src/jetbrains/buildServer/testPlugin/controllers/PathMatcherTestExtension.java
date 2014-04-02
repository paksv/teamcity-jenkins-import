package jetbrains.buildServer.testPlugin.controllers;

import jetbrains.buildServer.util.pathMatcher.AntPatternFileCollector;
import jetbrains.buildServer.web.openapi.PlaceId;
import jetbrains.buildServer.web.openapi.SimplePageExtension;
import jetbrains.buildServer.web.openapi.WebControllerManager;
import org.jetbrains.annotations.NotNull;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Sergey.Pak
 *         Date: 7/2/13
 *         Time: 6:43 PM
 */
public class PathMatcherTestExtension extends SimplePageExtension {

  public PathMatcherTestExtension(@NotNull WebControllerManager manager){
    super(manager);
    setPluginName("PathMatcherTest");
    setIncludeUrl("testPathMatcher.jsp");
    setPlaceId(PlaceId.ALL_PAGES_HEADER);
    register();
  }

  @Override
  public void fillModel(@NotNull Map<String, Object> model, @NotNull HttpServletRequest request) {
    String baseDir = null;
    String rules = null;
    String searchResults = null;
    String searchResultsCount = "0";
    if (request.getParameter("rules") != null && request.getParameter("basedir") != null) {
      rules = request.getParameter("rules");
      List<String> newRules = new ArrayList<String>();
      for (String rule : rules.split("\n")) {
        if (rule.trim().length() > 0){
          newRules.add(rule.trim());
        }
      }
      baseDir = request.getParameter("basedir");

      List<AntPatternFileCollector.ScanOption> options = new ArrayList<AntPatternFileCollector.ScanOption>();

      if (request.getParameter("allowExternalScan") != null){
        options.add(AntPatternFileCollector.ScanOption.ALLOW_EXTERNAL_SCAN);
      }
      if (request.getParameter("prioritizeExcludes") != null){
        options.add(AntPatternFileCollector.ScanOption.PRIORITIZE_EXCLUDES);
      }
      if (request.getParameter("useRuleStrictness") != null){
        options.add(AntPatternFileCollector.ScanOption.USE_RULE_STRICTNESS);
      }
      if (request.getParameter("includeAllIfNoRules") != null){
        options.add(AntPatternFileCollector.ScanOption.INCLUDE_ALL_IF_NO_RULES);
      }
      if (request.getParameter("notFollowSymlinks") != null){
        options.add(AntPatternFileCollector.ScanOption.NOT_FOLLOW_SYMLINK_DIRS);
      }
      long startTime = System.currentTimeMillis();
      final List<File> fileList = AntPatternFileCollector.scanDir(new File(baseDir), newRules.toArray(new String[newRules.size()]),
              options.toArray(new AntPatternFileCollector.ScanOption[options.size()]));
      model.put("took", String.valueOf(System.currentTimeMillis() - startTime));
      StringBuilder searchResultsBuilder = new StringBuilder();
      int index = 0;
      for (File file : fileList) {
        searchResultsBuilder.append(++index).append(". ").append(file.getAbsolutePath()).append("<br/>\n");
      }
      searchResultsCount = String.valueOf(index);
      searchResults= searchResultsBuilder.toString();
    }
    if (request.getParameter("allowExternalScan") != null){
      model.put("allowExternalScan","allowExternalScan");
    }
    if (request.getParameter("prioritizeExcludes") != null){
      model.put("prioritizeExcludes", "prioritizeExcludes");
    }
    if (request.getParameter("useRuleStrictness") != null){
      model.put("useRuleStrictness", "useRuleStrictness");
    }
    if (request.getParameter("includeAllIfNoRules") != null){
      model.put("includeAllIfNoRules", "includeAllIfNoRules");
    }
    if (request.getParameter("notFollowSymlinks") != null){
      model.put("notFollowSymlinks", "notFollowSymlinks");
    }
    model.put("rules", rules);
    model.put("basedir", baseDir);
    model.put("searchResults", searchResults);
    model.put("searchResultsCount", searchResultsCount);
  }
}
