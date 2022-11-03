package domain;

import exception.ConsistentCurrencyException;

public class Banker implements Bank {
    @Override
    public Money exchange(Money money, Currency currency) {
        // 동일한 통화로 환전 시도 시 ConsistentCurrencyException
        if (money.isSameCurrency(currency)) {
            throw new ConsistentCurrencyException(currency);
        }

        double exchangedMoney = getExchangedMoney(money, currency);

        // 소수점 아래 둘째 자리까지 반올림하여 표현
        exchangedMoney = roundOffAt3rdDecimalPoint(exchangedMoney);

        // 원화인 경우, 1의 자리 반올림
        if (isKRW(currency)) {
            exchangedMoney = roundAtUnitDigit(exchangedMoney);
        }

        Money resultMoney = new Money(exchangedMoney, currency);
        return resultMoney;
    }

    public double roundOffAt3rdDecimalPoint(double exchangedMoney) {
        return Math.round(exchangedMoney * 100) / 100.0;
    }

    public double getExchangedMoney(Money money, Currency currency) {
        double originRate = money.getCurrency().getExchangeRate();
        double aimRate = currency.getExchangeRate();
        return money.getAmount() * (originRate / aimRate);
    }

    public double roundAtUnitDigit(double money) {
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