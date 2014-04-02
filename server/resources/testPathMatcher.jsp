<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" errorPage="/runtimeError.jsp"
        %><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/include.jsp" %>


  <style type="text/css">
    body, form, table td{
      font-family: arial;
      font-size:12;
    }

  </style>
<form method="post" id="aaa">
  <table>
    <tr>
      <td>Basedir:</td>
      <td><input type="text" name="basedir" size="100"/></td>
    </tr>
    <tr>
      <td>Rules:</td>
      <td><textarea rows="20" cols="100" name="rules"></textarea>
      </td>
    </tr>
    <tr>
      <td>Options:</td>
      <td>
        <input type="checkbox" name="allowExternalScan" value="allowExternalScan"/>
               Allow external scan <br/>
        <input type="checkbox" name="prioritizeExcludes" value="prioritizeExcludes"/>
               Prioritize excludes <br/>
        <input type="checkbox" name="useRuleStrictness" value="useRuleStrictness"/>
               Use rule strictness <br/>
        <input type="checkbox" name="includeAllIfNoRules" value="includeAllIfNoRules"/>
               Include all if no rules<br/>
        <input type="checkbox" name="notFollowSymlinks" value="notFollowSymlinks"/>
               Not follow symlinks
      </td>
    </tr>
    <tr>
      <td colspan="2"><input type="button" value="Search" onclick="mySubmitter(); return false;"/> </td>
    </tr>

  </table>
  <hr/>
  <br/>
  <strong>Search results (<span id="resultCount">0</span> files found. Took <span id="tookMs">0</span> ms):</strong>
  <div id="searchResults">
  </div>
</form>

<script>
  function mySubmitter(){
    searchResults.innerHTML = "Searching...";
    tookMs.innerHTML = "";
    resultCount.innerHTML = "";
    var form = BS.Util.serializeForm(document.forms[0]);
    BS.ajaxRequest(window['base_uri'] + "/plugins/PathMatcherTest/testPathMatcher2.jsp", {
      parameters: form,
      onSuccess: function (response){
        var root = BS.Util.documentRoot(response);

        if (!root) return;
        // <response><took>14</took><searchresults><res>D:\Downloads\127.0.0.1 (1).rdp</res></searchresults></response>
        var took = root.getElementsByTagName("took")[0].firstChild.nodeValue;
        document.getElementById("tookMs").innerHTML = took;
        var mapElements = root.getElementsByTagName("res");
        var cnt = mapElements.length;
        document.getElementById("resultCount").innerHTML = cnt;

        searchResults.innerHTML = "";
        var ol = document.createElement("ol");
        for (var i=0; i<cnt; i++){
          var li = document.createElement("li");
          li.appendChild(document.createTextNode(mapElements[i].firstChild.nodeValue + "\n"))
          ol.appendChild(li);
        }
        searchResults.appendChild(ol);
      }.bind(this)
    });

  }
</script>
