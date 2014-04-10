<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" errorPage="/runtimeError.jsp"
        %><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/include.jsp" %>


  <style type="text/css">
    body, form, table td{
      font-family: arial;
      font-size:12;
    }

  </style>
<form method="post" id="jenkinsImportForm">
  <table>
    <tr>
      <td>Jenkins Project URL:</td>
      <td><input type="text" name="jenkinsConfUrl" size="200"></td>
    </tr>
    <tr>
      <td>Project name:</td>
      <td><input type="text" name="projectName" size="200"></td>
    </tr>
    <tr>
      <td colspan="2"><input type="submit" value="Create configuration"></td>
    </tr>
  </table>
</form>

