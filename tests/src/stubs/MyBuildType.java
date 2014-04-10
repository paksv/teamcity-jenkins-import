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

package stubs;

import jetbrains.buildServer.Build;
import jetbrains.buildServer.BuildAgent;
import jetbrains.buildServer.BuildTypeDescriptor;
import jetbrains.buildServer.BuildTypeStatusDescriptor;
import jetbrains.buildServer.buildTriggers.BuildTriggerDescriptor;
import jetbrains.buildServer.messages.Status;
import jetbrains.buildServer.parameters.ParametersProvider;
import jetbrains.buildServer.parameters.ValueResolver;
import jetbrains.buildServer.requirements.Requirement;
import jetbrains.buildServer.responsibility.ResponsibilityEntry;
import jetbrains.buildServer.serverSide.*;
import jetbrains.buildServer.serverSide.artifacts.SArtifactDependency;
import jetbrains.buildServer.serverSide.comments.Comment;
import jetbrains.buildServer.serverSide.dependency.CyclicDependencyFoundException;
import jetbrains.buildServer.serverSide.dependency.Dependency;
import jetbrains.buildServer.serverSide.dependency.Dependent;
import jetbrains.buildServer.serverSide.identifiers.DuplicateExternalIdException;
import jetbrains.buildServer.serverSide.vcs.VcsLabelingSettings;
import jetbrains.buildServer.users.SUser;
import jetbrains.buildServer.users.User;
import jetbrains.buildServer.util.Option;
import jetbrains.buildServer.vcs.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * @author Sergey.Pak
 *         Date: 4/4/2014
 *         Time: 5:12 PM
 */
public class MyBuildType implements SBuildType {
  public List<SFinishedBuild> getHistory() {
    return null;
  }

  public List<SFinishedBuild> getHistoryFull(boolean b) {
    return null;
  }

  public List<SFinishedBuild> getHistory(@Nullable User user, boolean b, boolean b2) {
    return null;
  }

  @Nullable
  public SBuild getLastChangesStartedBuild() {
    return null;
  }

  public List<AgentCompatibility> getAgentCompatibilities() {
    return null;
  }

  public <T extends SBuildAgent> AgentCompatibility getAgentCompatibility(T t) {
    return null;
  }

  public <T extends BuildAgent> List<T> getCanRunAgents() {
    return null;
  }

  public <T extends BuildAgent> List<T> getCanRunAndCompatibleAgents(boolean b) {
    return null;
  }

  public BuildNumbers getBuildNumbers() {
    return null;
  }

  @NotNull
  public List<SRunningBuild> getRunningBuilds(@Nullable User user) {
    return null;
  }

  @NotNull
  public List<SRunningBuild> getRunningBuilds() {
    return null;
  }

  public boolean isInQueue() {
    return false;
  }

  public int getNumberQueued() {
    return 0;
  }

  public Status getStatus() {
    return null;
  }

  @NotNull
  public BuildTypeStatusDescriptor getStatusDescriptor() {
    return null;
  }

  @Nullable
  public SFinishedBuild getLastChangesFinished() {
    return null;
  }

  @Nullable
  public Build getBuildByBuildNumber(@NotNull String s) {
    return null;
  }

  @Nullable
  public SFinishedBuild getLastChangesSuccessfullyFinished() {
    return null;
  }

  public void setPaused(boolean b, User user) {

  }

  public void setPaused(boolean b, @Nullable User user, String s) {

  }

  @Nullable
  public Comment getPauseComment() {
    return null;
  }

  @NotNull
  public SProject getProject() {
    return null;
  }

  public void setName(@NotNull String s) throws DuplicateBuildTypeNameException, BuildTypeRenamingFailedException {

  }

  @NotNull
  public String getFullName() {
    return null;
  }

  public void setDescription(@Nullable String s) {

  }

  public List<SBuildType> getArtifactsReferences() {
    return null;
  }

  public int getNumberOfArtifactReferences() {
    return 0;
  }

  @NotNull
  public File getArtifactsDirectory() {
    return null;
  }

  public List<SVcsModification> getPendingChanges() {
    return null;
  }

  public Collection<SUser> getPendingChangesCommitters() {
    return null;
  }

  public List<SVcsModification> getModificationsSinceLastSuccessful() {
    return null;
  }

  @NotNull
  public ResponsibilityEntry getResponsibilityInfo() {
    return null;
  }

  public void setResponsible(@NotNull User user, @Nullable String s, @Nullable User user2) {

  }

  public void removeResponsible(boolean b, @Nullable User user, @Nullable String s, @Nullable User user2) {

  }

  @NotNull
  public String getVcsSettingsHash(@NotNull List<VcsRootInstanceEntry> vcsRootInstanceEntries) {
    return null;
  }

  @NotNull
  public String getVcsSettingsHash() {
    return null;
  }

  public void releaseSources() {

  }

  public void releaseSources(@NotNull SBuildAgent sBuildAgent) {

  }

  @NotNull
  public List<SBuildAgent> getAgentsWhereBuildConfigurationBuilt() {
    return null;
  }

  public List<String> getTags() {
    return null;
  }

  public void forceCheckingForChanges() {

  }

  @Nullable
  public SQueuedBuild addToQueue(@NotNull String s) {
    return null;
  }

  @Nullable
  public SQueuedBuild addToQueue(@NotNull BuildAgent buildAgent, @NotNull String s) {
    return null;
  }

  @NotNull
  public List<SQueuedBuild> getQueuedBuilds(@Nullable User user) {
    return null;
  }

  public boolean isCleanBuild() {
    return false;
  }

  public boolean isShouldFailBuildIfTestsFailed() {
    return false;
  }

  public int getExecutionTimeoutMin() {
    return 0;
  }

  public int getMaximumNumberOfBuilds() {
    return 0;
  }

  public boolean isAllowExternalStatus() {
    return false;
  }

  public void moveToProject(@NotNull SProject sProject, boolean b) throws InvalidVcsRootScopeException {

  }

  public void moveToProject(@NotNull SProject sProject) throws InvalidVcsRootScopeException {

  }

  @Nullable
  public PathMapping mapVcsPath(@NotNull String s) {
    return null;
  }

  @NotNull
  public String getExtendedName() {
    return null;
  }

  @NotNull
  public String getExtendedFullName() {
    return null;
  }

  @NotNull
  public byte[] getFileContent(@NotNull String s) throws VcsException {
    return new byte[0];
  }

  public void attachToTemplate(@NotNull BuildTypeTemplate buildTypeTemplate, boolean b) throws InvalidVcsRootScopeException, CannotAttachToTemplateException {

  }

  public void attachToTemplate(@NotNull BuildTypeTemplate buildTypeTemplate) throws CannotAttachToTemplateException {

  }

  public void detachFromTemplate() {

  }

  public void persist() throws PersistFailedException {

  }

  public boolean replaceInValues(@NotNull String s, @NotNull String s2) throws PatternSyntaxException {
    return false;
  }

  public boolean replaceInValues(@NotNull Pattern pattern, @NotNull String s) {
    return false;
  }

  @NotNull
  public SBuildFeatureDescriptor addBuildFeature(@NotNull String s, @NotNull Map<String, String> stringStringMap) {
    return null;
  }

  public void addBuildFeature(@NotNull SBuildFeatureDescriptor sBuildFeatureDescriptor) {

  }

  @NotNull
  public Collection<SBuildFeatureDescriptor> getBuildFeatures() {
    return null;
  }

  @NotNull
  public Collection<SBuildFeatureDescriptor> getBuildFeaturesOfType(@NotNull String s) {
    return null;
  }

  @Nullable
  public SBuildFeatureDescriptor removeBuildFeature(@NotNull String s) {
    return null;
  }

  public boolean updateBuildFeature(@NotNull String s, @NotNull String s2, @NotNull Map<String, String> stringStringMap) {
    return false;
  }

  @Nullable
  public SBuildFeatureDescriptor findBuildFeatureById(@NotNull String s) {
    return null;
  }

  public void setEnabled(@NotNull String s, boolean b) {

  }

  public boolean isEnabled(@NotNull String s) {
    return false;
  }

  public void remove() {

  }

  @NotNull
  public File getConfigurationFile() {
    return null;
  }

  @Nullable
  public SPersistentEntity getParent() {
    return null;
  }

  @NotNull
  public ResolvedSettings getResolvedSettings() {
    return null;
  }

  @NotNull
  public List<Dependency> getOwnDependencies() {
    return null;
  }

  @NotNull
  public CustomDataStorage getCustomDataStorage(@NotNull String s) {
    return null;
  }

  @Nullable
  public SBuildRunnerDescriptor findBuildRunnerByType(@NotNull String s) {
    return null;
  }

  @Nullable
  public SBuildRunnerDescriptor findBuildRunnerById(@NotNull String s) {
    return null;
  }

  @Nullable
  public String findRunnerParameter(@NotNull String s) {
    return null;
  }

  public String getBuildParameter(String s) {
    return null;
  }

  @NotNull
  public List<SVcsRoot> getVcsRoots() {
    return null;
  }

  public boolean addBuildTrigger(@NotNull BuildTriggerDescriptor buildTriggerDescriptor) {
    return false;
  }

  @NotNull
  public BuildTriggerDescriptor addBuildTrigger(@NotNull String s, @NotNull Map<String, String> stringStringMap) {
    return null;
  }

  public boolean removeBuildTrigger(@NotNull BuildTriggerDescriptor buildTriggerDescriptor) {
    return false;
  }

  public boolean updateBuildTrigger(@NotNull String s, @NotNull String s2, @NotNull Map<String, String> stringStringMap) {
    return false;
  }

  @NotNull
  public Collection<BuildTriggerDescriptor> getBuildTriggersCollection() {
    return null;
  }

  @Nullable
  public BuildTriggerDescriptor findTriggerById(@NotNull String s) {
    return null;
  }

  public void setCheckoutType(CheckoutType checkoutType) {

  }

  @NotNull
  public Map<String, String> getBuildParameters() {
    return null;
  }

  @NotNull
  public Collection<Parameter> getBuildParametersCollection() {
    return null;
  }

  public void addBuildParameter(Parameter parameter) {

  }

  public void removeBuildParameter(String s) {

  }

  @NotNull
  public Collection<Parameter> getParametersCollection() {
    return null;
  }

  @NotNull
  public Map<String, String> getParameters() {
    return null;
  }

  @NotNull
  public Collection<Parameter> getOwnParametersCollection() {
    return null;
  }

  @NotNull
  public Map<String, String> getOwnParameters() {
    return null;
  }

  @NotNull
  public List<VcsRootInstanceEntry> getVcsRootInstanceEntries() {
    return null;
  }

  @Nullable
  public VcsRootInstance getVcsRootInstanceForParent(@NotNull SVcsRoot sVcsRoot) {
    return null;
  }

  @NotNull
  public List<VcsRootInstance> getVcsRootInstances() {
    return null;
  }

  public boolean belongsTo(@NotNull SProject sProject) {
    return false;
  }

  @NotNull
  public String getInternalId() {
    return null;
  }

  @NotNull
  public String getExternalId() {
    return null;
  }

  public void setExternalId(@NotNull String s) throws InvalidIdentifierException, DuplicateExternalIdException {

  }

  @NotNull
  public String getName() {
    return null;
  }

  @NotNull
  public List<VcsRootEntry> getOwnVcsRootEntries() {
    return null;
  }

  @NotNull
  public List<SBuildType> getDependencyReferences() {
    return null;
  }

  public int getNumberOfDependencyReferences() {
    return 0;
  }

  @NotNull
  public String getBuildTypeId() {
    return null;
  }

  @NotNull
  public String getProjectName() {
    return null;
  }

  public String getDescription() {
    return null;
  }

  public Collection<String> getRunnerTypes() {
    return null;
  }

  @NotNull
  public List<Requirement> getRunTypeRequirements() {
    return null;
  }

  @NotNull
  public SBuildRunnerDescriptor addBuildRunner(@NotNull BuildRunnerDescriptor buildRunnerDescriptor) {
    return null;
  }

  @NotNull
  public SBuildRunnerDescriptor addBuildRunner(@NotNull String s, @NotNull String s2, @NotNull Map<String, String> stringStringMap) {
    return new MyBuildRunnerDescriptor();
  }

  @Nullable
  public SBuildRunnerDescriptor removeBuildRunner(@NotNull String s) {
    return null;
  }

  public boolean updateBuildRunner(@NotNull String s, @NotNull String s2, @NotNull String s3, @NotNull Map<String, String> stringStringMap) {
    return false;
  }

  @NotNull
  public List<SBuildRunnerDescriptor> getBuildRunners() {
    return null;
  }

  public void removeAllBuildRunners() {

  }

  public void applyRunnersOrder(@NotNull String[] strings) {

  }

  public boolean isPersonal() {
    return false;
  }

  public CheckoutType getCheckoutType() {
    return null;
  }

  public void setCheckoutDirectory(@Nullable String s) {

  }

  @Nullable
  public String getCheckoutDirectory() {
    return null;
  }

  public void setArtifactPaths(@Nullable String s) {

  }

  @Nullable
  public String getArtifactPaths() {
    return null;
  }

  @NotNull
  public List<SArtifactDependency> getArtifactDependencies() {
    return null;
  }

  public void setArtifactDependencies(@NotNull List<SArtifactDependency> sArtifactDependencies) {

  }

  public boolean containsVcsRoot(long l) {
    return false;
  }

  @NotNull
  public List<VcsRootEntry> getVcsRootEntries() {
    return null;
  }

  public boolean addVcsRoot(@NotNull SVcsRoot sVcsRoot) throws InvalidVcsRootScopeException, VcsRootNotFoundException {
    return false;
  }

  public boolean removeVcsRoot(@NotNull SVcsRoot sVcsRoot) {
    return false;
  }

  public boolean setCheckoutRules(@NotNull VcsRoot vcsRoot, @NotNull CheckoutRules checkoutRules) {
    return false;
  }

  public CheckoutRules getCheckoutRules(@NotNull VcsRoot vcsRoot) {
    return null;
  }

  public void addConfigParameter(@NotNull Parameter parameter) {

  }

  public void removeConfigParameter(@NotNull String s) {

  }

  @NotNull
  public Collection<Parameter> getConfigParametersCollection() {
    return null;
  }

  @NotNull
  public Map<String, String> getConfigParameters() {
    return null;
  }

  @NotNull
  public List<String> getUndefinedParameters() {
    return null;
  }

  public boolean isTemplateBased() {
    return false;
  }

  @Nullable
  public BuildTypeTemplate getTemplate() {
    return null;
  }

  @Nullable
  public String getTemplateId() {
    return null;
  }

  @NotNull
  public List<Requirement> getRequirements() {
    return null;
  }

  @NotNull
  public List<Requirement> getImplicitRequirements() {
    return null;
  }

  public void addRequirement(@NotNull Requirement requirement) {

  }

  public void removeRequirement(String s) {

  }

  @NotNull
  public String getBuildNumberPattern() {
    return null;
  }

  public void setBuildNumberPattern(@NotNull String s) {

  }

  public boolean isPaused() {
    return false;
  }

  @NotNull
  public String getProjectId() {
    return null;
  }

  @NotNull
  public String getProjectExternalId() {
    return null;
  }

  public int compareTo(BuildTypeDescriptor o) {
    return 0;
  }

  @NotNull
  public Map<SBuildAgent, CompatibilityResult> getCompatibilityMap() {
    return null;
  }

  @NotNull
  public Collection<SBuildAgent> getCompatibleAgents() {
    return null;
  }

  @NotNull
  public CompatibilityResult getAgentCompatibility(@NotNull AgentDescription agentDescription) {
    return null;
  }

  @NotNull
  public Collection<SBuildType> getChildDependencies() {
    return null;
  }

  public boolean intersectsWith(@NotNull Dependent dependent) {
    return false;
  }

  @NotNull
  public List<Dependency> getDependencies() {
    return null;
  }

  public void addDependency(@NotNull Dependency dependency) throws CyclicDependencyFoundException {

  }

  public boolean removeDependency(@NotNull Dependency dependency) {
    return false;
  }

  public <T> void setOption(@NotNull Option<T> tOption, @NotNull T t) {

  }

  @NotNull
  public Collection<Option> getOwnOptions() {
    return null;
  }

  @NotNull
  public Collection<Option> getOptions() {
    return null;
  }

  @NotNull
  public Option[] getChangedOptions() {
    return new Option[0];
  }

  @NotNull
  public <T> T getOption(@NotNull Option<T> tOption) {
    return null;
  }

  @NotNull
  public <T> T getOptionDefaultValue(@NotNull Option<T> tOption) {
    return null;
  }

  @NotNull
  public ParametersProvider getParametersProvider() {
    return null;
  }

  @NotNull
  public ValueResolver getValueResolver() {
    return null;
  }

  public void addParameter(@NotNull Parameter parameter) {

  }

  public void removeParameter(@NotNull String s) {

  }

  @NotNull
  public LabelingType getLabelingType() {
    return null;
  }

  @NotNull
  public String getLabelPattern() {
    return null;
  }

  @NotNull
  public List<VcsRoot> getLabelingRoots() {
    return null;
  }
}
