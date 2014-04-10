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

import com.intellij.openapi.util.io.StreamUtil;
import jetbrains.buildServer.controllers.BaseFormXmlController;
import jetbrains.buildServer.http.HttpUtil;
import jetbrains.buildServer.importJenkins.JenkinsImport;
import jetbrains.buildServer.serverSide.ProjectManager;
import jetbrains.buildServer.util.FileUtil;
import jetbrains.buildServer.web.openapi.PluginDescriptor;
import jetbrains.buildServer.web.openapi.WebControllerManager;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Sergey.Pak
 *         Date: 4/3/2014
 *         Time: 4:14 PM
 */
public class JenkinsImportController extends BaseFormXmlController {

  public static final String PAGE_URL = "jenkinsImport.jsp";
  private final String myPagePath;

  @NotNull
  private final PluginDescriptor myPluginDescriptor;
  @NotNull
  private final ProjectManager myMyProjectManager;
  @NotNull
  private final WebControllerManager myControllerManager;

  public JenkinsImportController(@NotNull final PluginDescriptor pluginDescriptor,
                                 @NotNull final WebControllerManager controllerManager,
                                 @NotNull final ProjectManager myProjectManager) {
    myPluginDescriptor = pluginDescriptor;
    myMyProjectManager = myProjectManager;
    myPagePath = myPluginDescriptor.getPluginResourcesPath(PAGE_URL);
    myControllerManager = controllerManager;
    myControllerManager.registerController("/admin/importFromJenkins.html", this);
  }

  @Override
  protected ModelAndView doGet(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
    ModelAndView mv = new ModelAndView(myPagePath);
    mv.getModel().put("myPagePath", myPagePath);
    return mv;
  }

  @Override
  protected void doPost(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Element xmlResponse) {
    final String jenkinsConfURL = request.getParameter("jenkinsConfUrl");
    final String projectName = request.getParameter("projectName");
    HttpClient httpClient = HttpUtil.createHttpClient(120);  // 2 minutes
    final GetMethod get = new GetMethod(jenkinsConfURL);
    try {
      httpClient.executeMethod(get);
      final Element rootElem = FileUtil.parseDocument(get.getResponseBodyAsStream(), false);
      JenkinsImport.importProject(projectName, rootElem, myMyProjectManager);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (JDOMException e) {
      e.printStackTrace();
    }
  }
}
