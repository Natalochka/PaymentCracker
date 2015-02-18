<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.payment_cracker.api.dao.middle_level.middle_entities.CurrencyEntity" %>
<%@ page import="com.payment_cracker.api.dao.middle_level.middle_entities.PurseEntity" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.io.PrintWriter" %>
<%--
  Created by IntelliJ IDEA.
  User: Natalie
  Date: 19.01.2015
  Time: 2:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Operations</title>
  <style>
    <%@ include file="style_sheet.css"%>
  </style>
</head>
<body>
<%--<form id = "operations" method="post" action = "/controller">--%>
  <%--<table class="form_holder1" cellspacing="1">--%>
    <%--<thead>--%>
    <%--<tr>--%>
      <%--<td colspan="2" align="center">Operations</td>--%>
    <%--</tr>--%>
    <%--</thead>--%>
    <%--<tbody>--%>
    <%--<tr>--%>
      <%--<td colspan="2" align="center">--%>
          <%--<input type="submit" value="Add purse" name="operations_addpurse" />--%>
      <%--</td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
      <%--<td colspan="2" align="center">--%>
          <%--<input type="submit" value="Perform transaction" name="operations_performtransaction" />--%>
      <%--</td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
      <%--<td colspan="2" align="center">--%>
          <%--<input type="submit" value="Add money from credit card" name="operations_addmoney" />--%>
      <%--</td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
      <%--<td colspan="2" align="center">--%>
          <%--<input type="submit" value="Put money to credit card" name="operations_withdrawmoney" />--%>
      <%--</td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
        <%--<td colspan="2" align="center"><hr /></td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
        <%--<td>--%>
    <%--<table>--%>
    <%--<tr>--%>
        <%--<td>--%>
        <%--<th align="center">Your Purses List</th>--%>
        <%--</td>--%>
     <%--</tr>--%>
        <%--<tr>--%>
            <%--<td align="center">Purse ID</td>--%>
            <%--<td align="center">Currency</td>--%>
            <%--<td align="center">Balance</td>--%>
        <%--</tr>--%>
        <%
            PrintWriter writer = response.getWriter();
            writer.println("<form id = \"operations\" method=\"post\" action = \"/controller\">\n" +
                    "  <table class=\"form_holder1\" cellspacing=\"1\">\n" +
                    "    <thead>\n" +
                    "    <tr>\n" +
                    "      <td colspan=\"2\" align=\"center\">Operations</td>\n" +
                    "    </tr>\n" +
                    "    </thead>\n" +
                    "    <tbody>\n" +
                    "    <tr>\n" +
                    "      <td colspan=\"2\" align=\"center\">\n" +
                    "          <input type=\"submit\" value=\"Add purse\" name=\"operations_addpurse\" />\n" +
                    "      </td>\n" +
                    "    </tr>\n" +
                    "    <tr>\n" +
                    "      <td colspan=\"2\" align=\"center\">\n" +
                    "          <input type=\"submit\" value=\"Perform money transfer\" name=\"operations_performtransaction\" />\n" +
                    "      </td>\n" +
                    "    </tr>\n" +
                    "    <tr>\n" +
                    "      <td colspan=\"2\" align=\"center\">\n" +
                    "          <input type=\"submit\" value=\"Add money to purse\" name=\"operations_addmoney\" />\n" +
                    "      </td>\n" +
                    "    </tr>\n" +
                    "    <tr>\n" +
                    "      <td colspan=\"2\" align=\"center\">\n" +
                    "          <input type=\"submit\" value=\"Withdraw money from purse\" name=\"operations_withdrawmoney\" />\n" +
                    "      </td>\n" +
                    "    </tr>\n" +
                    "    <tr>\n" +
                    "        <td colspan=\"2\" align=\"center\"><hr /></td>\n" +
                    "    </tr>\n" +
                    "    <tr>\n" +
                    "        <td>\n" +
                    "    <table>\n" +
                    "    <tr>\n" +
                    "        <td>\n" +
                    "        <th align=\"center\">Your Purses List</th>\n" +
                    "        </td>\n" +
                    "     </tr>\n" +
                    "        <tr>\n" +
                    "            <td align=\"center\">Purse ID</td>\n" +
                    "            <td align=\"center\">Currency</td>\n" +
                    "            <td align=\"center\">Balance</td>\n" +
                    "        </tr>");
            if ((HashMap<CurrencyEntity, PurseEntity>) request.getAttribute("pursesInfo") != null) {

                HashMap<CurrencyEntity, PurseEntity> currencyPurseHashMap = (HashMap<CurrencyEntity, PurseEntity>) request.getAttribute("pursesInfo");

                for (Map.Entry<CurrencyEntity, PurseEntity> mapEntry : currencyPurseHashMap.entrySet()) {

                    writer.println("<tr>");
                    writer.println(" <td align=\"center\">");
                    writer.println(mapEntry.getValue().getId());
                    writer.println("</td>");

                    writer.println(" <td align=\"center\">");
                    writer.println(mapEntry.getKey().getCurrencyLabel());
                    writer.println("</td>");

                    writer.println(" <td align=\"center\">");
                    writer.println(mapEntry.getValue().getBalance());
                    writer.println("</td>");
                    writer.println("</tr>");
                }
            }
            writer.println("" +
                    "    </table>\n" +
                    "        </td>\n" +
                    "    </tr>\n" +
                    "    <tr>\n" +
                    "        <td colspan=\"2\" align=\"center\">\n" +
                    "            <hr/>\n" +
                    "        </td>\n" +
                    "    </tr>\n" +
                    "    <tr>\n" +
                    "        <td colspan=\"2\" align=\"center\">\n" +
                    "            <input type=\"submit\" value=\"Log out\" name=\"logouttransfer\" />\n" +
                    "        </td>\n" +
                    "    </tr>\n" +
                    "    </tbody>\n" +
                    "  </table>" +
                    "</form>");
        %>
    <%--<tr>
        <td align="center">***</td>
        <td align="center">UAH</td>
        <td align="center">***</td>
    </tr>
    <tr>
        <td align="center">***</td>
        <td align="center">EUR</td>
        <td align="center">***</td>
    </tr>
    <tr>
        <td align="center">***</td>
        <td align="center">USD</td>
        <td align="center">***</td>
    </tr>--%>
    <%--</table>--%>
        <%--</td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
        <%--<td colspan="2" align="center">--%>
            <%--<hr/>--%>
        <%--</td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
        <%--<td colspan="2" align="center">--%>
            <%--<input type="submit" value="Log out" name="operations_signin" />--%>
        <%--</td>--%>
    <%--</tr>--%>
    <%--</tbody>--%>
  <%--</table>--%>
<%--</form>--%>
</body>
</html>
