package com.payment_cracker.api.dao.utils;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

import java.util.Locale;

/**
 * Created by Александр on 1/8/2015.
 */
public class SessionManager {
    private static final SessionFactory factory;
    private Session session;
    private Transaction transaction;

    public SessionManager() {
        session = factory.openSession();
        Locale.setDefault(Locale.ENGLISH);
    }

    static {
        Locale.setDefault(Locale.ENGLISH);
        Configuration configuration = new Configuration()
                .setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");

        configuration.configure("hibernate.cfg.xml");

        factory = configuration.configure().buildSessionFactory();    }

    public void startSession() {
        if(session == null) {
            session = startFactory().openSession();
        } else {
            if(!session.isOpen()) {
                session = startFactory().openSession();
            }
        }
    }

    public SessionFactory startFactory() {
        if(factory.isClosed()) {
            return factory;
        }
        return this.factory;
    }

    public Session getSession() {
        return session;
    }

    public void closeSession() {
        if(session != null) {
            if (session.isOpen()) {
                session.close();
            }
        }
    }

    public void closeFactory() {
        factory.close();
    }

    public void startTransaction() {
        if(transaction != null) {
            if (transaction.isActive()) {
            } else if (!transaction.isActive()) {
                transaction = session.beginTransaction();
            }
        } else {
            transaction = session.beginTransaction();
        }
    }

    public void commitTransaction() {
        if(transaction != null) {
            if (transaction.isActive()) {
                transaction.commit();
            } else if (!transaction.isActive()) {
            }
        } else {
        }
    }

    public Transaction getNewTransactionInstance() {
        return session.beginTransaction();
    }

    public void rollbackTransaction() {
        if(transaction.isActive()) {
            transaction.rollback();
        }

    }

}