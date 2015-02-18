package com.payment_cracker.api.dao.middle_level.middle_entities;

/**
 * Created by Александр on 2/4/2015.
 */
public interface PaymentEntity extends Entity {
    public Long getId();
    public void setId(Long id);
    public Long getCurrencyId();
    public void setCurrencyId(Long currencyId);
    public double getBalance();
    public void setBalance(double balance);
}
