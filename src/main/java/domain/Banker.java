package domain;

public class Banker implements Bank {

    @Override
    public Money exchange(Money money, Currency currency) {
        Currency originalCurrency = money.getCurrency();

        double originRate = originalCurrency.getExchangeRate();
        double aimRate = currency.getExchangeRate();

        double exchangedMoney = money.getAmount() * (originRate / aimRate);

        if(currency.equals(Currency.KRW)){
            double tmp = (int) (exchangedMoney % 10);
            if(tmp >= 5){ //6
                exchangedMoney += (10 - tmp); //4
            }
            else{
                exchangedMoney -= tmp;
            }
        }
        Money resultMoney = new Money(exchangedMoney, currency);


        return resultMoney;
    }

}
