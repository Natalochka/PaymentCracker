<%--
  Created by IntelliJ IDEA.
  User: Natalie
  Date: 19.01.2015
  Time: 1:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Sign In</title>
  <style>
    <%@ include file="style_sheet.css"%>
  </style>
</head>
<body>
<form id = "signin" method="post" action = "/controller">
  <table class="form_holder1" cellspacing="1">
    <thead>
    <tr>
      <td colspan="2" align="center">Sign In</td>
    </tr>
    </thead>
    <tbody>
    <tr>
      <td>Login</td>
      <td><input size="26" type="text" name="text_login" /></td>
    </tr>
    <tr>
      <td>Password</td>
      <td><input size="26" type="password" name="text_password" /></td>
    </tr>
    <tr>
      <td colspan="2" align="center"><hr /></td>
    </tr>
    <tr>
      <td colspan="2" align="center">
        <input type="submit" value="Sign In" name="signin_login"/>
        <input type="reset" value="Cancel" name="signin_cancel"/>
      </td>
    </tr>
    <tr>
      <td colspan="2" align="center">
          <input type="submit" value="Sign Up" name="signin_signup" />
      </td>
    </tr>
    </tbody>
  </table>
</form>
</body>
</html>
