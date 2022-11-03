package domain;

public interface Bank {
    Money exchange(Money money, Currency krw);
}
