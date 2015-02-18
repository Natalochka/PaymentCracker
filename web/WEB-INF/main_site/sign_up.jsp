<%--
  Created by IntelliJ IDEA.
  User: Natalie
  Date: 19.01.2015
  Time: 1:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Sign Up</title>
  <style>
    <%@ include file="style_sheet.css"%>
  </style>
</head>
<body>
<form id = "signup" method="post" action = "/controller">
  <table class="form_holder1" cellspacing="1">
    <thead>
    <tr>
      <td colspan="2" align="center">Sign Up</td>
    </tr>
    </thead>
    <tbody>
    <tr>
      <td>Login (up to 10 symbols)</td>
      <td><input size="26" type="text" name="text_login" /></td>
    </tr>
    <tr>
      <td>Password (up to 20 symbols)</td>
      <td><input size="26" type="password" name="text_password" /></td>
    </tr>
    <tr>
      <td>Name</td>
      <td><input size="26" type="text" name="text_name" /></td>
    </tr>
    <tr>
      <td>Phone Number</td>
      <td><input size="26" type="text" name="text_phonenumber" /></td>
    </tr>
    <tr>
      <td>Email</td>
      <td><input size="26" type="text" name="text_email" /></td>
    </tr>
    <tr>
      <td colspan="2" align="center"><hr /></td>
    </tr>
    <tr>
      <td colspan="2" align="center">
        <input type="submit" value="Confirm" name="signup_confirm"/>
        <input type="reset" value="Cancel" name="signup_cancel"/>
      </td>
    </tr>
    </tbody>
  </table>
</form>
</body>
</html>
