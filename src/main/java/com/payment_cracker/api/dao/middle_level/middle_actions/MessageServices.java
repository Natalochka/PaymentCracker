package com.payment_cracker.api.dao.middle_level.middle_actions;



import com.payment_cracker.api.dao.basic_level.basic_dao.BasicDao;

import com.payment_cracker.api.dao.basic_level.basic_entities.*;
import com.payment_cracker.api.dao.basic_level.basic_entities.Objects;
import com.payment_cracker.api.dao.exceptions.DbException;
import com.payment_cracker.api.dao.middle_level.middle_entities.MessageEntity;
import com.payment_cracker.api.dao.utils.Messages;
import com.payment_cracker.api.dao.utils.SessionManager;
import com.payment_cracker.api.dao.utils.TablesInfo;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;

import static com.payment_cracker.api.dao.utils.AttributesInfo.*;

public class MessageServices extends BasicDao {
    private SessionManager sessionManager;
   // private Session session;
    private  Messages messAttribues;


    public MessageServices(SessionManager sessionManager) {
        super(sessionManager.getSession());
        this.sessionManager = sessionManager;
       // this.session = sessionManager.getSession();

    }

    protected void add(MessageEntity entity) throws DbException {
        Objects obj = new Objects();
        Map<Integer,Parameters> parameters =  new MessageMaker().getParams(entity);

        obj.setName(entity.getDate().toString());
        obj.setObjectId(entity.getMessageId());
        obj.setParent(new Objects().setObjectId(entity.getUserId()));
        obj.setObjectType(new ObjType().setObjectTypeId(TablesInfo.OBJ_TYPE_MESSAGE));

        super.addEntity(obj);

        for(Messages mess: messAttribues.values())
        {
            ParametersId parId = new ParametersId(obj,new Attributes()
                    .setAttrId(mess.getAttributeId()));
            Parameters par = parameters.get(mess.getAttributeId());
            par.setParametersId(parId);
            super.addEntity(par);

        }
    }

    protected void update(MessageEntity entity) throws DbException {
        updateConfirmToken(entity);
    }

    private void updateConfirmToken(MessageEntity entity) throws DbException {
        Parameters confirmToken = super.getByIdEntity(new ParametersId
                (new Objects().setObjectId(entity.getMessageId()),
                        new Attributes().setAttrId(
                            messAttribues.CONFIRM_TOKEN.getAttributeId())), Parameters.class);
        confirmToken.setValue(String.valueOf(entity.getConfirmToken()));
        super.updateEntity(confirmToken);
    }

    protected void sendMessage (MessageEntity entity) throws DbException{

//        String sender = "PaymentCraker@gmail.com";
//
//        String receiver = new UserServices(sessionManager)
//                .getById(entity.getUserId()).getEmail();
//
//        String host = "localhost";
//        Properties properties = System.getProperties();
//        properties.setProperty("mail.smtp.host", host);
//
//        Session session = Session.getDefaultInstance(properties);
//
//        try{
//
//            MimeMessage message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(sender));
//
//            message.addRecipient(Message.RecipientType.TO,
//                    new InternetAddress(receiver));
//
//            message.setSubject("First message");
//            message.setText(entity.getText());
//
//            add(entity);
//
//            Transport.send(message);
//            logger.debug("Message send to user with mail " + receiver);
//        }
//        catch (MessagingException e) {
//            logger.error(e.getMessage(), e);
//        }
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("paymentcracker@gmail.com","PaymentCrackerPaymentCracker");
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("paymentcracker@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(new UserServices(sessionManager)
                            .getById(entity.getUserId()).getEmail()));

            message.setSubject("Testing Subject");
            message.setText(entity.getText());

            Transport.send(message);


        } catch (MessagingException e) {
            logger.debug(e.getMessage(), e);
        }


    }

    protected MessageEntity getById(Long id) throws DbException {
        Objects obj = super.getByIdEntity(id, Objects.class);

        List<Parameters> parameterses = super.getParametersList(obj);

        MessageEntity messageEntity = new MessageEntity()
                .setMessageId(id)
                .setUserId(obj.getParent().getObjectId());

        messageEntity = new MessageMaker()
                .makeMessageEntity(parameterses, messageEntity);

        return messageEntity;
    }

    public List<MessageEntity> getAll() throws DbException {
        List<MessageEntity> allMessages = new ArrayList<>();

        List<Parameters> allParameters = super
                .getAllParametersOfEntity(TablesInfo.OBJ_TYPE_MESSAGE);

        int parametersQuantity = messAttribues.values().length;

        for(int i = 0; i < allParameters.size(); i += parametersQuantity)
        {
            MessageEntity messageEntity = new MessageEntity()
                    .setMessageId(allParameters.get(i)
                            .getParametersId().getObject().getObjectId())
                    .setUserId(allParameters.get(i).getParametersId()
                            .getObject().getParent().getObjectId());

            messageEntity = new MessageMaker().makeMessageEntity
                    (allParameters.subList(i, i + parametersQuantity), messageEntity);

            allMessages.add(messageEntity);
        }
        return allMessages;
    }

    private static class MessageMaker {

        private MessageEntity makeMessageEntity(List<Parameters> parameterses,
                                                MessageEntity messageEntity) {
            for (Parameters parameters : parameterses) {
                switch (parameters.getParametersId().getAttribute().getAttrId()) {
                    case ATTR_TEXT_OF_MESSAGE:
                        messageEntity.setText(parameters.getValue());
                        break;
                    case ATTR_DATE_OF_MESSAGE:
                        messageEntity.setDate(parameters.getDateValue());
                        break;
                    case ATTR_CONFIRM_TOKEN_OF_MESSAGE:
                        messageEntity.setConfirmToken(Boolean.parseBoolean(parameters.getValue()));
                        break;
                }
            }
            return messageEntity;
        }

        private Map<Integer, Parameters> getParams(MessageEntity entity) {
            Map<Integer, Parameters> params = new HashMap<>();
            params.put(ATTR_DATE_OF_MESSAGE, new Parameters(null, entity.getDate()));
            params.put(ATTR_TEXT_OF_MESSAGE, new Parameters(entity.getText(), null));
            params.put(ATTR_CONFIRM_TOKEN_OF_MESSAGE,
                    new Parameters(String.valueOf(entity.getConfirmToken()), null));
            return params;
        }

    }
}
