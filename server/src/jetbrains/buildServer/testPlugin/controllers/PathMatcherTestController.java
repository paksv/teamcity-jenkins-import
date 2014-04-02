package jetbrains.buildServer.testPlugin.controllers;

import jetbrains.buildServer.controllers.BaseFormXmlController;
import jetbrains.buildServer.util.pathMatcher.AntPatternFileCollector;
import jetbrains.buildServer.web.openapi.PluginDescriptor;
import jetbrains.buildServer.web.openapi.WebControllerManager;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Sergey.Pak
 *         Date: 7/3/13
 *         Time: 7:06 PM
 */
public class PathMatcherTestController extends BaseFormXmlController {

  private static final String PAGE_URL = "testPathMatcher2.jsp";
  private final String myPagePath;

  public PathMatcherTestController(@NotNull PluginDescriptor pluginDescriptor,
                                   @NotNull WebControllerManager webControllerManager){
    myPagePath = pluginDescriptor.getPluginResourcesPath(PAGE_URL);
    webControllerManager.registerController(myPagePath, this);
  }

  @Override
  protected ModelAndView doGet(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
    ModelAndView mv = new ModelAndView(myPagePath);
    mv.getModel().put("myPagePath", myPagePath);
    return mv;
  }

  @Override
  protected void doPost(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Element xmlResponse) {
    final String basedir = request.getParameter("basedir");
    final String[] rules = request.getParameter("rules").split("\n");
    List<String> newRules = new ArrayList<String>();
    for (String rule : rules) {
      if (rule.trim().length() > 0){
        newRules.add(rule.trim());
      }
    }

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
    final List<File> fileList = AntPatternFileCollector.scanDir(new File(basedir), newRules.toArray(new String[newRules.size()]),
            options.toArray(new AntPatternFileCollector.ScanOption[options.size()]));
    long took = System.currentTimeMillis() - startTime;
    Element tookElem = new Element("took");
    tookElem.addContent(String.valueOf(took));
    final Element searchResults = new Element("searchResults");
    for (File file : fileList) {
      Element res = new Element("res");
      res.addContent(file.getAbsolutePath());
      searchResults.addContent(res);
    }
    xmlResponse.addContent(tookElem);
    xmlResponse.addContent(searchResults);
  }
}
