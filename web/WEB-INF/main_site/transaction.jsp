<%--
  Created by IntelliJ IDEA.
  User: Natalie
  Date: 19.01.2015
  Time: 2:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Transaction</title>
  <style>
    <%@ include file="style_sheet.css"%>
  </style>
</head>
<body>
<form id = "transaction" method="post" action = "/controller">
  <table class="form_holder1" cellspacing="1">
    <thead>
    <tr>
      <td colspan="2" align="center">Transaction</td>
    </tr>
    </thead>
    <tbody>
    <tr>
      <td>Purse ID</td>
      <td><input size="26" type="text" name="text_purseid" /></td>
    </tr>
    <tr>
      <td>Receiver's purse ID</td>
      <td><input size="26" type="text" name="text_receiverspurseid" /></td>
    </tr>
    <tr>
      <td>Amount</td>
      <td><input size="26" type="text" name="text_amount" /></td>
    </tr>
    <tr>
      <td colspan="2" align="center"><hr /></td>
    </tr>
    <tr>
      <td colspan="2" align="center">
        <input type="submit" value="Send" name="transaction_send"/>
        <input type="reset" value="Cancel" name="transaction_cancel"/>
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
