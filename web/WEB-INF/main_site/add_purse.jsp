<%--
  Created by IntelliJ IDEA.
  User: Natalie
  Date: 19.01.2015
  Time: 2:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Add Purse</title>
  <style>
    <%@ include file="style_sheet.css"%>
  </style>
</head>
<body>
<form id = "addpurse" method="post" action = "/controller">
  <table class="form_holder1" cellspacing="1">
    <thead>
    <tr>
      <td colspan="2" align="center">Add Purse</td>
    </tr>
    </thead>
    <tbody>
    <tr>
      <td>Choose currency</td>
      <td>
        <select name="list_currencies">
          <option value="UAH">UAH</option>
          <option value="EUR">EUR</option>
          <option value="USD">USD</option>
        </select>
      </td>
    </tr>
    <tr>
      <td colspan="2" align="center"><hr /></td>
    </tr>
    <tr>
      <td colspan="2" align="center">
        <input type="submit" value="Add" name="addpurse_add" />
        <input type="reset" value="Cancel" name="addpurse_cancel"/>
      </td>
    </tr>
    <tr>
      <td colspan="2" align="center">
          <input type="submit" value="Back to operations page" name="transfertooperations" />
      </td>
    </tr>
    <tr>
        <td colspan="2" align="center"><hr /></td>
    </tr>
    <tr>
        <td colspan="2" align="center">
            <input type="submit" value="Log out" name="logouttransfer" />
        </td>
    </tr>
    </tbody>
  </table>
</form>
<%
    if (request.getAttribute("result") != null) {
        String result = (String) request.getAttribute("result");
        response.getWriter().println(result);
    }
%>
</body>
</html>
