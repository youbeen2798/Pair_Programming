package domain;

import java.sql.SQLOutput;

public class Banker implements Bank {

    @Override
    public Money exchange(Money money, Currency currency) {
        Currency originalCurrency = money.getCurrency();

        double originRate = originalCurrency.getExchangeRate();
        double aimRate = currency.getExchangeRate();
        double exchangedMoney = money.getAmount() * (originRate / aimRate);
        System.out.println("exchangeMoney: " + exchangedMoney); //0.98

        // 소수점 아래 둘째 자리까지 반올림하여 표현
        exchangedMoney = Math.round(exchangedMoney * 100) / 100.0;
        System.out.println("exchangeMoney: " + exchangedMoney); //0.98
        if (isKRW(currency)) {
            exchangedMoney = roundAtUnitDigit(exchangedMoney);
        }

        System.out.println("exchangeMoney: " + exchangedMoney);
        Money resultMoney = new Money(exchangedMoney, currency);
        return resultMoney;
    }

    private static double roundAtUnitDigit(double money) {
        double unitDigit = (money % 10);

        if (unitDigit >= 5) {
            money += (10 - unitDigit);
        } else {
            money -= unitDigit;
        }

        return money;
    }

    private static boolean isKRW(Currency currency) {
        return currency.equals(Currency.KRW);
    }

}
