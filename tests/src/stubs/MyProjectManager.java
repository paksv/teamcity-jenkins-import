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

import jetbrains.buildServer.serverSide.*;
import jetbrains.buildServer.serverSide.auth.AccessDeniedException;
import jetbrains.buildServer.serverSide.identifiers.DuplicateExternalIdException;
import jetbrains.buildServer.users.User;
import jetbrains.buildServer.vcs.SVcsRoot;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.SortedMap;

/**
 * @author Sergey.Pak
 *         Date: 4/4/2014
 *         Time: 5:11 PM
 */
public class MyProjectManager implements ProjectManager {
  @NotNull
  public SProject getRootProject() {
    return null;
  }

  @NotNull
  public List<SProject> getProjects() {
    return null;
  }

  @NotNull
  public List<SProject> getActiveProjects() {
    return null;
  }

  @NotNull
  public List<SProject> getArchivedProjects() {
    return null;
  }

  public int getNumberOfProjects() {
    return 0;
  }

  @NotNull
  public List<SProject> getProjects(@NotNull User user) {
    return null;
  }

  @NotNull
  public SProject createProject(@NotNull String s) throws InvalidIdentifierException, InvalidNameException, DuplicateProjectNameException {
    return new MySProject(s);
  }

  @NotNull
  public SProject copyProject(@NotNull SProject sProject, @NotNull SProject sProject2, @NotNull CopyOptions copyOptions) throws MaxNumberOfBuildTypesReachedException, NotAllIdentifiersMappedException, InvalidNameException, DuplicateExternalIdException {
    return null;
  }

  @NotNull
  public SProject createProject(@NotNull SProject sProject, @NotNull String s, @NotNull CopyOptions copyOptions) throws InvalidIdentifierException, InvalidNameException, MaxNumberOfBuildTypesReachedException {
    return null;
  }

  @Nullable
  public SProject findProjectByName(@NotNull String s) {
    return null;
  }

  @Nullable
  public SProject findProjectById(@Nullable String s) {
    return null;
  }

  @Nullable
  public SProject findProjectByExternalId(@Nullable String s) {
    return null;
  }

  @NotNull
  public Collection<SProject> findProjects(@NotNull Collection<String> strings) {
    return null;
  }

  @NotNull
  public Collection<SProject> findProjectsByExternalIds(@NotNull Collection<String> strings) {
    return null;
  }

  @Nullable
  public SBuildType findBuildTypeById(@Nullable String s) throws AccessDeniedException {
    return null;
  }

  @Nullable
  public SBuildType findBuildTypeByExternalId(@Nullable String s) throws AccessDeniedException {
    return null;
  }

  @NotNull
  public Collection<SBuildType> findBuildTypes(@NotNull Collection<String> strings) {
    return null;
  }

  @NotNull
  public Collection<SBuildType> findBuildTypesByExternalIds(@NotNull Collection<String> strings) {
    return null;
  }

  @NotNull
  public List<SBuildType> getAllBuildTypes() {
    return null;
  }

  @NotNull
  public List<BuildTypeTemplate> getAllTemplates() {
    return null;
  }

  @NotNull
  public List<SBuildType> getActiveBuildTypes() {
    return null;
  }

  @NotNull
  public List<SBuildType> getArchivedBuildTypes() {
    return null;
  }

  public int getNumberOfBuildTypes() {
    return 0;
  }

  @NotNull
  public List<SBuildType> getAllBuildTypes(@NotNull User user) {
    return null;
  }

  public void removeProject(@NotNull String s) throws ProjectRemoveFailedException {

  }

  public List<String> getProjectIds() {
    return null;
  }

  public SortedMap<SProject, List<SBuildType>> getFilteredBuildTypes(@NotNull User user, @Nullable BuildTypeFilter buildTypeFilter) {
    return null;
  }

  @Nullable
  public String findProjectId(@NotNull String s) {
    return null;
  }

  @Nullable
  public String findProjectExternalId(@NotNull String s) {
    return null;
  }

  @Nullable
  public String findProjectIdForTemplate(@NotNull String s) {
    return null;
  }

  public boolean isProjectExists(@NotNull String s) {
    return false;
  }

  public List<SBuildType> getBuildTypesDependingOn(@NotNull SBuildType sBuildType) {
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

  @NotNull
  public SProject getCommonParentProject(@NotNull Collection<SProject> sProjects) {
    return null;
  }

  @Nullable
  public SVcsRoot findVcsRootById(long l) {
    return null;
  }

  @Nullable
  public SVcsRoot findVcsRootByExternalId(@NotNull String s) {
    return null;
  }

  @NotNull
  public Collection<SVcsRoot> findVcsRootsByIds(@NotNull Collection<Long> longs) {
    return null;
  }

  @NotNull
  public List<SVcsRoot> getAllVcsRoots() {
    return null;
  }
}
