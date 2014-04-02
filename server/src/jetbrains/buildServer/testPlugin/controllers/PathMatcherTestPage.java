package jetbrains.buildServer.testPlugin.controllers;

import jetbrains.buildServer.controllers.admin.AdminPage;
import jetbrains.buildServer.web.openapi.PagePlaces;
import jetbrains.buildServer.web.openapi.PluginDescriptor;
import jetbrains.buildServer.web.openapi.PositionConstraint;
import org.jetbrains.annotations.NotNull;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;

/**
 * @author Sergey.Pak
 *         Date: 7/2/13
 *         Time: 7:43 PM
 */
public class PathMatcherTestPage extends AdminPage {

  protected PathMatcherTestPage(@NotNull PagePlaces pagePlaces,
                                @NotNull PluginDescriptor pluginDescriptor) {
    super(pagePlaces, "PathMatcherTest", pluginDescriptor.getPluginResourcesPath("testPathMatcher.jsp"),
            "Test Path Matcher");
    setPosition(PositionConstraint.last());
    register();
  }

  @Override
  public void fillModel(@NotNull Map<String, Object> model, @NotNull HttpServletRequest request) {

  }



  @NotNull
  public String getGroup() {
    return SERVER_RELATED_GROUP;
  }
}
