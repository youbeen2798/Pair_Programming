package domain;

import exception.InconsistentCurrencyException;
import exception.InvalidMoneyException;

import java.util.Objects;

public class Money {
    private final double amount;
    private final Currency currency;

    public Money(double amount, Currency currency) {
        if (amount < 0) {
            throw new InvalidMoneyException();
        }

        // 소수점 아래 두자리까지 반올림하여 표현
        this.amount = Math.round(amount * 100) / 100.0;
        this.currency = currency;
    }

    public Currency getCurrency() {
        return currency;
    }

    public double getAmount() {
        return this.amount;
    }

    public Money add(Money money) {
        checkCurrency(money);

        double amount = this.amount + money.getAmount();
        Money result = new Money(amount, currency);
        return result;
    }

    public Money subtract(Money money) {
        checkCurrency(money);

        double amount = this.amount - money.getAmount();
        Money result = new Money(amount, currency);
        return result;
    }

    private void checkCurrency(Money money) {
        if (!currency.equals(money.getCurrency())) {
            throw new InconsistentCurrencyException(this.currency, money.getCurrency());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money money = (Money) o;
        return amount == money.amount && currency == money.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }
}
