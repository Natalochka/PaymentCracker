package com.payment_cracker.api.dao.utils;

/**
 * Created by Natalie on 12/7/2014.
 */

import com.payment_cracker.api.dao.middle_level.middle_entities.CurrencyEntity;
import yahoofinance.YahooFinance;
import yahoofinance.quotes.fx.FxQuote;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DAOUtils {

    public static Long generateId() {
        SecureRandom random = new SecureRandom();
        char[] digits = new char[12];
        digits[0] = (char) (random.nextInt(9) + '1');
        for (int i = 1; i < 12; i++) {
            digits[i] = (char) (random.nextInt(10) + '0');
        }
        return Long.parseLong(new String(digits));
    }

    public static List<CurrencyEntity> getCurrencyRate() {
        List<CurrencyEntity> currencyEntityList = new ArrayList<CurrencyEntity>();
        Calendar currentCalendar = Calendar.getInstance();
        Date currentDate = currentCalendar.getTime();

//        FxQuote all = YahooFinance.getFx("ALLUSD=X");
//        CurrencyEntity allEntity = new CurrencyEntity();
//        allEntity.setCurrencyValue(all.getPrice());
//        allEntity.setCurrencyLabel("ALL");
//        allEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(allEntity);

        try {

            FxQuote usd = YahooFinance.getFx("USDUSD=X");
            CurrencyEntity usdEntity = new CurrencyEntity();
            usdEntity.setCurrencyValue(usd.getPrice());
            usdEntity.setCurrencyLabel("USD");
            usdEntity.setCurrencyDate(currentDate);
            currencyEntityList.add(usdEntity);

//        FxQuote ars = YahooFinance.getFx("ARSUSD=X");
//        CurrencyEntity arsEntity = new CurrencyEntity();
//        arsEntity.setCurrencyValue(ars.getPrice());
//        arsEntity.setCurrencyLabel("ARS");
//        arsEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(arsEntity);
//
//        FxQuote aud = YahooFinance.getFx("AUDUSD=X");
//        CurrencyEntity audEntity = new CurrencyEntity();
//        audEntity.setCurrencyValue(aud.getPrice());
//        audEntity.setCurrencyLabel("AUD");
//        audEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(audEntity);
//
//        FxQuote awg = YahooFinance.getFx("AWGUSD=X");
//        CurrencyEntity awgEntity = new CurrencyEntity();
//        awgEntity.setCurrencyValue(awg.getPrice());
//        awgEntity.setCurrencyLabel("AWG");
//        awgEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(awgEntity);
//
//        FxQuote bhd = YahooFinance.getFx("BHDUSD=X");
//        CurrencyEntity bhdEntity = new CurrencyEntity();
//        bhdEntity.setCurrencyValue(bhd.getPrice());
//        bhdEntity.setCurrencyLabel("BHD");
//        bhdEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(bhdEntity);
//
//        FxQuote bbd = YahooFinance.getFx("BBDUSD=X");
//        CurrencyEntity bbdEntity = new CurrencyEntity();
//        bbdEntity.setCurrencyValue(bbd.getPrice());
//        bbdEntity.setCurrencyLabel("BBD");
//        bbdEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(bbdEntity);
//
//        FxQuote bzd = YahooFinance.getFx("BZDUSD=X");
//        CurrencyEntity bzdEntity = new CurrencyEntity();
//        bzdEntity.setCurrencyValue(bzd.getPrice());
//        bzdEntity.setCurrencyLabel("BZD");
//        bzdEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(bzdEntity);
//
//        FxQuote btn = YahooFinance.getFx("BTNUSD=X");
//        CurrencyEntity btnEntity = new CurrencyEntity();
//        btnEntity.setCurrencyValue(btn.getPrice());
//        btnEntity.setCurrencyLabel("BTN");
//        btnEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(btnEntity);
//
//        FxQuote bwp = YahooFinance.getFx("BWPUSD=X");
//        CurrencyEntity bwpEntity = new CurrencyEntity();
//        bwpEntity.setCurrencyValue(bwp.getPrice());
//        bwpEntity.setCurrencyLabel("BWP");
//        bwpEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(bwpEntity);
//
//        FxQuote bnd = YahooFinance.getFx("BNDUSD=X");
//        CurrencyEntity bndEntity = new CurrencyEntity();
//        bndEntity.setCurrencyValue(bnd.getPrice());
//        bndEntity.setCurrencyLabel("BND");
//        bndEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(bndEntity);
//
//        FxQuote bif = YahooFinance.getFx("BIFUSD=X");
//        CurrencyEntity bifEntity = new CurrencyEntity();
//        bifEntity.setCurrencyValue(bif.getPrice());
//        bifEntity.setCurrencyLabel("BIF");
//        bifEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(bifEntity);
//
//        FxQuote bsd = YahooFinance.getFx("BSDUSD=X");
//        CurrencyEntity bsdEntity = new CurrencyEntity();
//        bsdEntity.setCurrencyValue(bsd.getPrice());
//        bsdEntity.setCurrencyLabel("BSD");
//        bsdEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(bsdEntity);
//
//        FxQuote bdt = YahooFinance.getFx("BDTUSD=X");
//        CurrencyEntity bdtEntity = new CurrencyEntity();
//        bdtEntity.setCurrencyValue(bdt.getPrice());
//        bdtEntity.setCurrencyLabel("BDT");
//        bdtEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(bdtEntity);
//
//        FxQuote byr = YahooFinance.getFx("BYRUSD=X");
//        CurrencyEntity byrEntity = new CurrencyEntity();
//        byrEntity.setCurrencyValue(byr.getPrice());
//        byrEntity.setCurrencyLabel("BYR");
//        byrEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(byrEntity);
//
//        FxQuote bmd = YahooFinance.getFx("BMDUSD=X");
//        CurrencyEntity bmdEntity = new CurrencyEntity();
//        bmdEntity.setCurrencyValue(bmd.getPrice());
//        bmdEntity.setCurrencyLabel("BMD");
//        bmdEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(bmdEntity);
//
//        FxQuote bob = YahooFinance.getFx("BOBUSD=X");
//        CurrencyEntity bobEntity = new CurrencyEntity();
//        bobEntity.setCurrencyValue(bob.getPrice());
//        bobEntity.setCurrencyLabel("BOB");
//        bobEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(bobEntity);
//
//        FxQuote brl = YahooFinance.getFx("BRLUSD=X");
//        CurrencyEntity brlEntity = new CurrencyEntity();
//        brlEntity.setCurrencyValue(brl.getPrice());
//        brlEntity.setCurrencyLabel("BRL");
//        brlEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(brlEntity);
//
//        FxQuote bgn = YahooFinance.getFx("BGNUSD=X");
//        CurrencyEntity bgnEntity = new CurrencyEntity();
//        bgnEntity.setCurrencyValue(bgn.getPrice());
//        bgnEntity.setCurrencyLabel("BGN");
//        bgnEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(bgnEntity);
//
//        FxQuote cad = YahooFinance.getFx("CADUSD=X");
//        CurrencyEntity cadEntity = new CurrencyEntity();
//        cadEntity.setCurrencyValue(cad.getPrice());
//        cadEntity.setCurrencyLabel("CAD");
//        cadEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(cadEntity);
//
//        FxQuote khr = YahooFinance.getFx("KHRUSD=X");
//        CurrencyEntity khrEntity = new CurrencyEntity();
//        khrEntity.setCurrencyValue(khr.getPrice());
//        khrEntity.setCurrencyLabel("KHR");
//        khrEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(khrEntity);
//
//        FxQuote kyd = YahooFinance.getFx("KYDUSD=X");
//        CurrencyEntity kydEntity = new CurrencyEntity();
//        kydEntity.setCurrencyValue(kyd.getPrice());
//        kydEntity.setCurrencyLabel("KYD");
//        kydEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(kydEntity);
//
//        FxQuote xaf = YahooFinance.getFx("XAFUSD=X");
//        CurrencyEntity xafEntity = new CurrencyEntity();
//        xafEntity.setCurrencyValue(xaf.getPrice());
//        xafEntity.setCurrencyLabel("XAF");
//        xafEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(xafEntity);
//
//        FxQuote chf = YahooFinance.getFx("CHFUSD=X");
//        CurrencyEntity chfEntity = new CurrencyEntity();
//        chfEntity.setCurrencyValue(chf.getPrice());
//        chfEntity.setCurrencyLabel("CHF");
//        chfEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(chfEntity);
//
//        FxQuote cnh = YahooFinance.getFx("CNHUSD=X");
//        CurrencyEntity cnhEntity = new CurrencyEntity();
//        cnhEntity.setCurrencyValue(cnh.getPrice());
//        cnhEntity.setCurrencyLabel("CNH");
//        cnhEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(cnhEntity);
//
//        FxQuote cop = YahooFinance.getFx("COPUSD=X");
//        CurrencyEntity copEntity = new CurrencyEntity();
//        copEntity.setCurrencyValue(cop.getPrice());
//        copEntity.setCurrencyLabel("COP");
//        copEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(copEntity);
//
//        FxQuote xcp = YahooFinance.getFx("XCPUSD=X");
//        CurrencyEntity xcpEntity = new CurrencyEntity();
//        xcpEntity.setCurrencyValue(xcp.getPrice());
//        xcpEntity.setCurrencyLabel("XCP");
//        xcpEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(xcpEntity);
//
//        FxQuote hrk = YahooFinance.getFx("HRKUSD=X");
//        CurrencyEntity hrkEntity = new CurrencyEntity();
//        hrkEntity.setCurrencyValue(hrk.getPrice());
//        hrkEntity.setCurrencyLabel("HRK");
//        hrkEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(hrkEntity);
//
//        FxQuote czk = YahooFinance.getFx("CZKUSD=X");
//        CurrencyEntity czkEntity = new CurrencyEntity();
//        czkEntity.setCurrencyValue(czk.getPrice());
//        czkEntity.setCurrencyLabel("CZK");
//        czkEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(czkEntity);
//
//        FxQuote cny = YahooFinance.getFx("CNYUSD=X");
//        CurrencyEntity cnyEntity = new CurrencyEntity();
//        cnyEntity.setCurrencyValue(cny.getPrice());
//        cnyEntity.setCurrencyLabel("CNY");
//        cnyEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(cnyEntity);
//
//        FxQuote cve = YahooFinance.getFx("CVEUSD=X");
//        CurrencyEntity cveEntity = new CurrencyEntity();
//        cveEntity.setCurrencyValue(cve.getPrice());
//        cveEntity.setCurrencyLabel("CVE");
//        cveEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(cveEntity);
//
//        FxQuote xof = YahooFinance.getFx("XOFUSD=X");
//        CurrencyEntity xofEntity = new CurrencyEntity();
//        xofEntity.setCurrencyValue(xof.getPrice());
//        xofEntity.setCurrencyLabel("XOF");
//        xofEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(xofEntity);
//
//        FxQuote clp = YahooFinance.getFx("XOFUSD=X");
//        CurrencyEntity clpEntity = new CurrencyEntity();
//        clpEntity.setCurrencyValue(clp.getPrice());
//        clpEntity.setCurrencyLabel("XOF");
//        clpEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(clpEntity);
//
//        FxQuote kmf = YahooFinance.getFx("KMFUSD=X");
//        CurrencyEntity kmfEntity = new CurrencyEntity();
//        kmfEntity.setCurrencyValue(kmf.getPrice());
//        kmfEntity.setCurrencyLabel("KMF");
//        kmfEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(kmfEntity);
//
//        FxQuote crc = YahooFinance.getFx("CRCUSD=X");
//        CurrencyEntity crcEntity = new CurrencyEntity();
//        crcEntity.setCurrencyValue(crc.getPrice());
//        crcEntity.setCurrencyLabel("CRC");
//        crcEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(crcEntity);
//
//        FxQuote cup = YahooFinance.getFx("CUPUSD=X");
//        CurrencyEntity cupEntity = new CurrencyEntity();
//        cupEntity.setCurrencyValue(cup.getPrice());
//        cupEntity.setCurrencyLabel("CUP");
//        cupEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(cupEntity);
//
//        FxQuote djf = YahooFinance.getFx("DJFUSD=X");
//        CurrencyEntity djfEntity = new CurrencyEntity();
//        djfEntity.setCurrencyValue(djf.getPrice());
//        djfEntity.setCurrencyLabel("DJF");
//        djfEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(djfEntity);
//
//        FxQuote xcd = YahooFinance.getFx("XCDUSD=X");
//        CurrencyEntity xcdEntity = new CurrencyEntity();
//        xcdEntity.setCurrencyValue(xcd.getPrice());
//        xcdEntity.setCurrencyLabel("XCD");
//        xcdEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(xcdEntity);
//
//        FxQuote dkk = YahooFinance.getFx("DKKUSD=X");
//        CurrencyEntity dkkEntity = new CurrencyEntity();
//        dkkEntity.setCurrencyValue(dkk.getPrice());
//        dkkEntity.setCurrencyLabel("DKK");
//        dkkEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(dkkEntity);
//
//        FxQuote dzd = YahooFinance.getFx("DZDUSD=X");
//        CurrencyEntity dzdEntity = new CurrencyEntity();
//        dzdEntity.setCurrencyValue(dzd.getPrice());
//        dzdEntity.setCurrencyLabel("DZD");
//        dzdEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(dzdEntity);
//
//        FxQuote egp = YahooFinance.getFx("EGPUSD=X");
//        CurrencyEntity egpEntity = new CurrencyEntity();
//        egpEntity.setCurrencyValue(egp.getPrice());
//        egpEntity.setCurrencyLabel("EGP");
//        egpEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(egpEntity);
//
//        FxQuote ern = YahooFinance.getFx("ERNUSD=X");
//        CurrencyEntity ernEntity = new CurrencyEntity();
//        ernEntity.setCurrencyValue(ern.getPrice());
//        ernEntity.setCurrencyLabel("ERN");
//        ernEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(ernEntity);
//
//        FxQuote etb = YahooFinance.getFx("ETBUSD=X");
//        CurrencyEntity etbEntity = new CurrencyEntity();
//        etbEntity.setCurrencyValue(etb.getPrice());
//        etbEntity.setCurrencyLabel("ETB");
//        etbEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(etbEntity);
//
//        FxQuote fjd = YahooFinance.getFx("FJDUSD=X");
//        CurrencyEntity fjdEntity = new CurrencyEntity();
//        fjdEntity.setCurrencyValue(fjd.getPrice());
//        fjdEntity.setCurrencyLabel("FJD");
//        fjdEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(fjdEntity);
//
//        FxQuote dop = YahooFinance.getFx("DOPUSD=X");
//        CurrencyEntity dopEntity = new CurrencyEntity();
//        dopEntity.setCurrencyValue(dop.getPrice());
//        dopEntity.setCurrencyLabel("DOP");
//        dopEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(dopEntity);
//
//        FxQuote svc = YahooFinance.getFx("SVCUSD=X");
//        CurrencyEntity svcEntity = new CurrencyEntity();
//        svcEntity.setCurrencyValue(svc.getPrice());
//        svcEntity.setCurrencyLabel("SVC");
//        svcEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(svcEntity);
//
//        FxQuote fkp = YahooFinance.getFx("FKPUSD=X");
//        CurrencyEntity fkpEntity = new CurrencyEntity();
//        fkpEntity.setCurrencyValue(fkp.getPrice());
//        fkpEntity.setCurrencyLabel("FKP");
//        fkpEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(fkpEntity);

            FxQuote eur = YahooFinance.getFx("EURUSD=X");
            CurrencyEntity eurEntity = new CurrencyEntity();
            eurEntity.setCurrencyValue(eur.getPrice());
            eurEntity.setCurrencyLabel("EUR");
            eurEntity.setCurrencyDate(currentDate);
            currencyEntityList.add(eurEntity);
//
//        FxQuote gbp = YahooFinance.getFx("GBPUSD=X");
//        CurrencyEntity gbpEntity = new CurrencyEntity();
//        gbpEntity.setCurrencyValue(gbp.getPrice());
//        gbpEntity.setCurrencyLabel("GBP");
//        gbpEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(gbpEntity);
//
//        FxQuote hkd = YahooFinance.getFx("HKDUSD=X");
//        CurrencyEntity hkdEntity = new CurrencyEntity();
//        hkdEntity.setCurrencyValue(hkd.getPrice());
//        hkdEntity.setCurrencyLabel("HKD");
//        hkdEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(hkdEntity);
//
//        FxQuote huf = YahooFinance.getFx("HUFUSD=X");
//        CurrencyEntity hufEntity = new CurrencyEntity();
//        hufEntity.setCurrencyValue(huf.getPrice());
//        hufEntity.setCurrencyLabel("HUF");
//        hufEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(hufEntity);
//
//        FxQuote jpy = YahooFinance.getFx("JPYUSD=X");
//        CurrencyEntity jpyEntity = new CurrencyEntity();
//        jpyEntity.setCurrencyValue(jpy.getPrice());
//        jpyEntity.setCurrencyLabel("JPY");
//        jpyEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(jpyEntity);
//
//        FxQuote mxn = YahooFinance.getFx("MXNUSD=X");
//        CurrencyEntity mxnEntity = new CurrencyEntity();
//        mxnEntity.setCurrencyValue(mxn.getPrice());
//        mxnEntity.setCurrencyLabel("MXN");
//        mxnEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(mxnEntity);
//
//        FxQuote nok = YahooFinance.getFx("NOKUSD=X");
//        CurrencyEntity nokEntity = new CurrencyEntity();
//        nokEntity.setCurrencyValue(nok.getPrice());
//        nokEntity.setCurrencyLabel("NOK");
//        nokEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(nokEntity);
//
//        FxQuote nzd = YahooFinance.getFx("NZDUSD=X");
//        CurrencyEntity nzdEntity = new CurrencyEntity();
//        nzdEntity.setCurrencyValue(nzd.getPrice());
//        nzdEntity.setCurrencyLabel("NZD");
//        nzdEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(nzdEntity);
//
//        FxQuote pln = YahooFinance.getFx("PLNUSD=X");
//        CurrencyEntity plnEntity = new CurrencyEntity();
//        plnEntity.setCurrencyValue(pln.getPrice());
//        plnEntity.setCurrencyLabel("PLN");
//        plnEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(plnEntity);
//
//        FxQuote rub = YahooFinance.getFx("RUBUSD=X");
//        CurrencyEntity rubEntity = new CurrencyEntity();
//        rubEntity.setCurrencyValue(rub.getPrice());
//        rubEntity.setCurrencyLabel("RUB");
//        rubEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(rubEntity);
//
//        FxQuote sek = YahooFinance.getFx("SEKUSD=X");
//        CurrencyEntity sekEntity = new CurrencyEntity();
//        sekEntity.setCurrencyValue(sek.getPrice());
//        sekEntity.setCurrencyLabel("SEK");
//        sekEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(sekEntity);
//
//        FxQuote sgd = YahooFinance.getFx("SGDUSD=X");
//        CurrencyEntity sgdEntity = new CurrencyEntity();
//        sgdEntity.setCurrencyValue(sgd.getPrice());
//        sgdEntity.setCurrencyLabel("SGD");
//        sgdEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(sgdEntity);
//
//        FxQuote TRY = YahooFinance.getFx("TRYUSD=X");
//        CurrencyEntity TRYEntity = new CurrencyEntity();
//        TRYEntity.setCurrencyValue(TRY.getPrice());
//        TRYEntity.setCurrencyLabel("TRY");
//        TRYEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(TRYEntity);
//
//        FxQuote zar = YahooFinance.getFx("ZARUSD=X");
//        CurrencyEntity zarEntity = new CurrencyEntity();
//        zarEntity.setCurrencyValue(zar.getPrice());
//        zarEntity.setCurrencyLabel("ZAR");
//        zarEntity.setCurrencyDate(currentDate);
//        currencyEntityList.add(zarEntity);

            FxQuote uah = YahooFinance.getFx("UAHUSD=X");
            CurrencyEntity uahEntity = new CurrencyEntity();
            uahEntity.setCurrencyValue(uah.getPrice());
            uahEntity.setCurrencyLabel("UAH");
            uahEntity.setCurrencyDate(currentDate);
            currencyEntityList.add(uahEntity);

        } catch (Throwable e) {
            CurrencyEntity usdEntity = new CurrencyEntity();
            usdEntity.setCurrencyValue(1.00);
            usdEntity.setCurrencyLabel("USD");
            usdEntity.setCurrencyDate(currentDate);
            currencyEntityList.add(usdEntity);

            CurrencyEntity eurEntity = new CurrencyEntity();
            eurEntity.setCurrencyValue(1.1315);
            eurEntity.setCurrencyLabel("EUR");
            eurEntity.setCurrencyDate(currentDate);
            currencyEntityList.add(eurEntity);

            CurrencyEntity uahEntity = new CurrencyEntity();
            uahEntity.setCurrencyValue(0.0396);
            uahEntity.setCurrencyLabel("UAH");
            uahEntity.setCurrencyDate(currentDate);
            currencyEntityList.add(uahEntity);
        }

        return currencyEntityList;
    }

    /**
     *@return key for decrypt;
     */
    public static int makeCrypt(String text, int key) {
        return 0;
    }


}
