import exception.InvalidMoneyException;

import java.util.Objects;

public class Money {
    private long amount;
    private final Currency currency;

    public Money(long amount, Currency currency) {
        if (amount < 0) {
            throw new InvalidMoneyException();
        }

        this.amount = amount;
        this.currency = currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }

    public Currency getCurrency() {
        return currency;
    }

    public Money add(Money money) {
        checkCurrency(money);

        long amount = this.amount + money.getAmount();
        Money result = new Money(amount, currency);
        return result;
    }

    private void checkCurrency(Money money) {
        if (!currency.equals(money.getCurrency())) {
            throw new InconsistentCurrencyException(this.currency, money.getCurrency());
        }
    }

    public long getAmount() {
        return this.amount;
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

    public Money subtract(Money money) {
        checkCurrency(money);

        long amount = this.amount - money.getAmount();
        Money result = new Money(amount, currency);
        return result;
    }
}
