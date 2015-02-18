package com.utils.ui.site_files;

import com.payment_cracker.api.dao.exceptions.DbException;
import com.payment_cracker.api.dao.high_level.access_actions.AccessPoint;
import com.payment_cracker.api.dao.utils.CurrencyTypes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;

/**
 * Created by Александр on 1/19/2015.
 */
public class ActionsControllerServlet extends HttpServlet {
    AccessPoint user;

    @Override
    public void init() {
        try {
            Locale.setDefault(Locale.ENGLISH);
            user = new AccessPoint();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (request.getParameter("signin_login") != null) {
                String login = request.getParameter("text_login");
                String password = request.getParameter("text_password");
                if (user.connect(login, password)) {
                    request.setAttribute("pursesInfo", user.getPursesInfo());
                    request.getRequestDispatcher("/WEB-INF/main_site/operations.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("/WEB-INF/main_site/error.jsp").forward(request, response);
                }

            } else if (request.getParameter("signin_signup") != null) {
                request.getRequestDispatcher("/WEB-INF/main_site/sign_up.jsp").forward(request, response);

            } else if (request.getParameter("logouttransfer") != null) {
                //user.close();
                request.getRequestDispatcher("WEB-INF/main_site/sign_in.jsp").forward(request, response);

            } else if (request.getParameter("operations_addpurse") != null) {
                request.getRequestDispatcher("/WEB-INF/main_site/add_purse.jsp").forward(request, response);
            } else if (request.getParameter("operations_performtransaction") != null) {
                request.getRequestDispatcher("WEB-INF/main_site/transaction.jsp").forward(request, response);
            } else if (request.getParameter("operations_addmoney") != null) {
                request.getRequestDispatcher("WEB-INF/main_site/add_money.jsp").forward(request, response);
            } else if (request.getParameter("operations_withdrawmoney") != null) {
                request.getRequestDispatcher("WEB-INF/main_site/withdraw_money.jsp").forward(request, response);

            } else if (request.getParameter("addmoney_sumbit") != null) {
                Long purseId = Long.valueOf(request.getParameter("text_purseid"));
                Long creditCardId = Long.valueOf(request.getParameter("text_creditcardnumber"));
                Double moneyAmount = Double.valueOf(request.getParameter("text_amount"));
                request.setAttribute("result", user.putMoneyOnPurseFromCreditCard(creditCardId, purseId, moneyAmount));
                request.getRequestDispatcher("WEB-INF/main_site/add_money.jsp").forward(request, response);

            } else if (request.getParameter("transfertooperations") != null) {
                request.setAttribute("pursesInfo", user.getPursesInfo());
                request.getRequestDispatcher("WEB-INF/main_site/operations.jsp").forward(request, response);

            } else if (request.getParameter("addpurse_add") != null) {
                String currencyLabel = request.getParameter("list_currencies");
                if (CurrencyTypes.UAH.getCurrencyName().equals(currencyLabel)) {
                    user.createPurse(CurrencyTypes.UAH);
                } else if (CurrencyTypes.USD.getCurrencyName().equals(currencyLabel)) {
                    user.createPurse(CurrencyTypes.USD);
                } else if (CurrencyTypes.EUR.getCurrencyName().equals(currencyLabel)) {
                    user.createPurse(CurrencyTypes.EUR);
                }
                request.getRequestDispatcher("WEB-INF/main_site/add_purse.jsp").forward(request, response);
            } else if (request.getParameter("transaction_send") != null) {
                Long fromPurseId = Long.valueOf(request.getParameter("text_purseid"));
                Long wherePurseId = Long.valueOf(request.getParameter("text_receiverspurseid"));
                Double moneyAmount = Double.valueOf(request.getParameter("text_amount"));
                request.setAttribute("result", user.makeTransactionFromPurseToPurse(fromPurseId, wherePurseId, moneyAmount));
                request.getRequestDispatcher("WEB-INF/main_site/transaction.jsp").forward(request, response);
            } else if (request.getParameter("withdrawmoney_withdraw") != null) {
                Long creditCardId = Long.valueOf(request.getParameter("text_creditcardnumber"));
                Long purseId = Long.valueOf(request.getParameter("text_purseid"));
                Double moneyAmount = Double.valueOf(request.getParameter("text_amount"));
                request.setAttribute("result", user.putMoneyOnCreditCardFromPurse(purseId, creditCardId, moneyAmount));
                request.getRequestDispatcher("WEB-INF/main_site/withdraw_money.jsp").forward(request, response);
            } else if (request.getParameter("signup_confirm") != null) {
                String login = request.getParameter("text_login");
                String password = request.getParameter("text_password");
                String fio = request.getParameter("text_name");
                String phoneNumber = request.getParameter("text_phonenumber");
                String email = request.getParameter("text_email");
                user.createUser(login, password, fio, phoneNumber, email);
                request.getRequestDispatcher("WEB-INF/main_site/sign_in.jsp").forward(request, response);
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    public void destroy() {
        user.close();
        super.destroy();
    }
}
