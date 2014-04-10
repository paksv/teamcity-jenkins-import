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

package jetbrains.buildServer.importJenkins.web;

import jetbrains.buildServer.serverSide.ProjectManager;
import jetbrains.buildServer.serverSide.SProject;
import jetbrains.buildServer.web.openapi.*;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Sergey.Pak
 *         Date: 4/4/2014
 *         Time: 12:24 PM
 */
public class JenkinsImportExtension extends SimplePageExtension {

  @NotNull private final ProjectManager myProjectManager;


  public JenkinsImportExtension(@NotNull final PagePlaces pagePlaces,
                                @NotNull final PluginDescriptor desc,
                                @NotNull final ProjectManager projectManager) {
    super(pagePlaces, PlaceId.ADMIN_PROJECT_CREATE_BUILD_TYPE, "JenkinsImport", desc.getPluginResourcesPath("jenkinsImportLink.jsp"));
    myProjectManager = projectManager;

    register();
  }

  @Override
  public boolean isAvailable(@NotNull HttpServletRequest request) {
    return true;
  }

  @Override
  public void fillModel(@NotNull Map<String, Object> model, @NotNull HttpServletRequest request) {
    final String projectExternalId = request.getParameter("projectId");
    if(projectExternalId == null)
      return;
    final SProject project = myProjectManager.findProjectByExternalId(projectExternalId);
    if(project == null)
      return;

    model.put("project", project);
  }
}
