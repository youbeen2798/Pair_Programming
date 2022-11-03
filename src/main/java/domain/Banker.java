package domain;

public class Banker implements Bank {

    @Override
    public Money exchange(Money money, Currency currency) {
        Currency originalCurrency = money.getCurrency();

        double originRate = originalCurrency.getExchangeRate();
        double aimRate = currency.getExchangeRate();

        double exchangedMoney = money.getAmount() * (originRate / aimRate);

        Money resultMoney = new Money(exchangedMoney, currency);

        return resultMoney;
    }
}
