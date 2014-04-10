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

import jetbrains.buildServer.BuildProject;
import jetbrains.buildServer.BuildTypeDescriptor;
import jetbrains.buildServer.messages.Status;
import jetbrains.buildServer.parameters.ParametersProvider;
import jetbrains.buildServer.parameters.ValueResolver;
import jetbrains.buildServer.serverSide.*;
import jetbrains.buildServer.serverSide.identifiers.DuplicateExternalIdException;
import jetbrains.buildServer.users.User;
import jetbrains.buildServer.vcs.DuplicateVcsRootNameException;
import jetbrains.buildServer.vcs.SVcsRoot;
import jetbrains.buildServer.vcs.UnknownVcsException;
import jetbrains.buildServer.vcs.VcsRootInstance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Sergey.Pak
 *         Date: 4/4/2014
 *         Time: 5:12 PM
 */
public class MySProject implements SProject {

  private final String myName;

  public MySProject(String name) {
    myName = name;
  }

  public void setExternalId(@NotNull String s) throws InvalidIdentifierException, DuplicateExternalIdException {

  }

  public void setName(@NotNull String s) {

  }

  public void setDescription(@Nullable String s) {

  }

  @NotNull
  public String getProjectId() {
    return null;
  }

  @NotNull
  public String getExternalId() {
    return null;
  }

  @Nullable
  public SProject getParentProject() {
    return null;
  }

  @Nullable
  public String getParentProjectId() {
    return null;
  }

  @Nullable
  public String getParentProjectExternalId() {
    return null;
  }

  public boolean isRootProject() {
    return false;
  }

  @NotNull
  public List<SProject> getOwnProjects() {
    return null;
  }

  @NotNull
  public List<SProject> getProjects() {
    return null;
  }

  @NotNull
  public String getName() {
    return myName;
  }

  @NotNull
  public String getFullName() {
    return myName;
  }

  @NotNull
  public String getDescription() {
    return null;
  }

  public Status getStatus() {
    return null;
  }

  @NotNull
  public List<SProject> getProjectPath() {
    return null;
  }

  @NotNull
  public List<SVcsRoot> getOwnVcsRoots() {
    return null;
  }

  @Nullable
  public SProject findProjectByName(@NotNull String s) {
    return null;
  }

  @Nullable
  public SVcsRoot findVcsRootByName(@NotNull String s) {
    return null;
  }

  @NotNull
  public List<SVcsRoot> getVcsRoots() {
    return null;
  }

  @NotNull
  public List<SVcsRoot> getUsedVcsRoots() {
    return null;
  }

  @NotNull
  public List<VcsRootInstance> getVcsRootInstances() {
    return null;
  }

  @NotNull
  public File getConfigDirectory() {
    return null;
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
  public File getPluginSettingsFile() {
    return null;
  }

  @NotNull
  public File getArtifactsDirectory() {
    return null;
  }

  @NotNull
  public File getPluginDataDirectory(@NotNull String s) {
    return null;
  }

  @NotNull
  public SBuildType createBuildType(@NotNull String s, @NotNull String s2) throws DuplicateExternalIdException, DuplicateBuildTypeNameException, MaxNumberOfBuildTypesReachedException {
    return null;
  }

  @NotNull
  public SBuildType createBuildType(@NotNull String s) throws DuplicateBuildTypeNameException, MaxNumberOfBuildTypesReachedException {
    return new MyBuildType();
  }

  @NotNull
  public SBuildType createBuildType(@NotNull String s, @NotNull String s2, int i, BuildTypeDescriptor.CheckoutType checkoutType) throws DuplicateBuildTypeNameException, MaxNumberOfBuildTypesReachedException {
    return null;
  }

  @NotNull
  public BuildTypeTemplate createBuildTypeTemplate(@NotNull String s, @NotNull String s2) throws DuplicateTemplateNameException, DuplicateExternalIdException {
    return null;
  }

  @NotNull
  public BuildTypeTemplate createBuildTypeTemplate(@NotNull String s) throws DuplicateTemplateNameException {
    return null;
  }

  @NotNull
  public BuildTypeTemplate copyBuildTypeTemplate(@NotNull BuildTypeTemplate buildTypeTemplate, @NotNull String s, @NotNull String s2) throws InvalidVcsRootScopeException {
    return null;
  }

  @NotNull
  public BuildTypeTemplate createBuildTypeTemplate(@NotNull BuildTypeTemplate buildTypeTemplate, @NotNull String s, @NotNull CopyOptions copyOptions) throws InvalidVcsRootScopeException {
    return null;
  }

  @NotNull
  public BuildTypeTemplate extractBuildTypeTemplate(@NotNull SBuildType sBuildType, @NotNull String s, @NotNull String s2) throws InvalidVcsRootScopeException, InvalidIdentifierException, DuplicateExternalIdException {
    return null;
  }

  @NotNull
  public BuildTypeTemplate createBuildTypeTemplate(@NotNull SBuildType sBuildType, @NotNull String s, @NotNull CopyOptions copyOptions) throws InvalidVcsRootScopeException {
    return null;
  }

  @NotNull
  public SBuildType copyBuildType(@NotNull SBuildType sBuildType, @NotNull String s, @NotNull String s2, @NotNull CopyOptions copyOptions) throws MaxNumberOfBuildTypesReachedException, InvalidVcsRootScopeException, DuplicateExternalIdException {
    return null;
  }

  @NotNull
  public SBuildType createBuildType(@NotNull SBuildType sBuildType, @NotNull String s, @NotNull CopyOptions copyOptions) throws MaxNumberOfBuildTypesReachedException, InvalidVcsRootScopeException {
    return null;
  }

  @NotNull
  public SBuildType createBuildTypeFromTemplate(@NotNull BuildTypeTemplate buildTypeTemplate, @NotNull String s, @NotNull String s2) throws MaxNumberOfBuildTypesReachedException, InvalidVcsRootScopeException, DuplicateExternalIdException {
    return null;
  }

  @NotNull
  public SBuildType createBuildTypeFromTemplate(@NotNull BuildTypeTemplate buildTypeTemplate, @NotNull String s, @NotNull CopyOptions copyOptions) throws MaxNumberOfBuildTypesReachedException, InvalidVcsRootScopeException {
    return null;
  }

  @NotNull
  public SBuildType createBuildType(SBuildType sBuildType, String s, boolean b, boolean b2) throws MaxNumberOfBuildTypesReachedException, InvalidVcsRootScopeException {
    return null;
  }

  @Nullable
  public BuildTypeIdentity findBuildTypeIdentityByName(@NotNull String s) {
    return null;
  }

  @Nullable
  public SBuildType findBuildTypeById(@Nullable String s) {
    return null;
  }

  @Nullable
  public SBuildType findBuildTypeByExternalId(@Nullable String s) {
    return null;
  }

  @Nullable
  public SBuildType findBuildTypeByName(@NotNull String s) {
    return null;
  }

  public void removeBuildTypes() {

  }

  public void removeBuildType(String s) {

  }

  @NotNull
  public List<SBuildType> getOwnBuildTypes() {
    return null;
  }

  @NotNull
  public List<SBuildType> getBuildTypes() {
    return null;
  }

  public boolean hasBuildTypes() {
    return false;
  }

  public boolean containsBuildType(@NotNull String s) {
    return false;
  }

  public void updateProjectInTransaction(@NotNull ProjectUpdater projectUpdater) throws PersistFailedException {

  }

  public boolean isInModel() {
    return false;
  }

  public List<User> getPotentiallyResponsibleUsers() {
    return null;
  }

  public void persist() throws PersistFailedException {

  }

  public void remove() {

  }

  public boolean isArchived() {
    return false;
  }

  public void setArchived(boolean b, @Nullable User user) {

  }

  @Nullable
  public User getArchivingUser() {
    return null;
  }

  @Nullable
  public Date getArchivingTime() {
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
  public List<BuildTypeTemplate> getOwnBuildTypeTemplates() {
    return null;
  }

  @NotNull
  public List<BuildTypeTemplate> getBuildTypeTemplates() {
    return null;
  }

  @NotNull
  public List<BuildTypeTemplate> getAvailableTemplates() {
    return null;
  }

  @NotNull
  public List<SVcsRoot> getAvailableVcsRoots() {
    return null;
  }

  @Nullable
  public BuildTypeTemplate findBuildTypeTemplateById(@NotNull String s) {
    return null;
  }

  @Nullable
  public BuildTypeTemplate findBuildTypeTemplateByExternalId(@NotNull String s) {
    return null;
  }

  @Nullable
  public BuildTypeTemplate findBuildTypeTemplateByName(@NotNull String s) {
    return null;
  }

  public void removeBuildTypeTemplate(@NotNull String s) throws TemplateCannotBeRemovedException {

  }

  public boolean belongsTo(@NotNull SProject sProject) {
    return false;
  }

  public void moveToProject(@NotNull SProject sProject) throws CyclicDependencyException, InvalidVcsRootScopeException {

  }

  @NotNull
  public SVcsRoot copyVcsRoot(@NotNull SVcsRoot sVcsRoot, @Nullable String s) {
    return null;
  }

  @NotNull
  public SVcsRoot createVcsRoot(@NotNull String s, @Nullable String s2, @NotNull Map<String, String> stringStringMap) throws UnknownVcsException, DuplicateVcsRootNameException {
    return null;
  }

  @NotNull
  public SVcsRoot createVcsRoot(@NotNull String s, @NotNull String s2, @NotNull String s3) throws DuplicateExternalIdException, DuplicateVcsRootNameException {
    return null;
  }

  @NotNull
  public SProject createProject(@NotNull String s, @NotNull String s2) throws InvalidIdentifierException, InvalidNameException, DuplicateProjectNameException, DuplicateExternalIdException {
    return null;
  }

  public int compareTo(BuildProject o) {
    return 0;
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
  public String describe(boolean b) {
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
}
