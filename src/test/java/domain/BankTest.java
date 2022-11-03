package domain;

import exception.ConsistentCurrencyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class BankTest {
    private Money krwMoney;
    private Money dollarMoney;
    private Money eurMoney;
    private Bank bank;

    @BeforeEach
    void setUp() {
        krwMoney = mock(Money.class);
        when(krwMoney.getCurrency()).thenReturn(Currency.KRW);

        dollarMoney = mock(Money.class);
        when(dollarMoney.getCurrency()).thenReturn(Currency.DOLLAR);

        eurMoney = mock(Money.class);
        when(eurMoney.getCurrency()).thenReturn(Currency.EUR);

        bank = new Banker();
    }

    @Test
    @DisplayName("통화는 달러화와 원화만이 존재, 환율은 1달러 <-> 1,000원")
    void bank_exchange_dollarToKRW() {
        when(dollarMoney.getAmount()).thenReturn(1.0);

        Money result = bank.exchange(dollarMoney, Currency.KRW);
        assertThat(result.getAmount()).isEqualTo(1000);

        verify(dollarMoney, times(1)).getAmount();
    }

    @Test
    @DisplayName("1,000원 -환전-> 1$")
    void bank_exchange_KRWToDollar() {
        when(krwMoney.getAmount()).thenReturn(1000.0);

        Money result = bank.exchange(krwMoney, Currency.DOLLAR);
        assertThat(result.getAmount()).isEqualTo(1);
        assertThat(result).isEqualTo(new Money(1, Currency.DOLLAR));
    }

    @Test
    @DisplayName("5.25$ -> 5,250원")
    void bank_exchange_decimal_dollarToKRW() {
        when(dollarMoney.getAmount()).thenReturn(5.25);

        Money result = bank.exchange(dollarMoney, Currency.KRW);
        assertThat(result.getAmount()).isEqualTo(5250);
        assertThat(result).isEqualTo(new Money(5250, Currency.KRW));
    }

    @Test
    @DisplayName("달러 -> 원화: 5원 이상 -> 10원으로 반올림")
    void bank_exchange_dollarToKRW_check_roundUp_KRW_AmountUnit() {
        when(dollarMoney.getAmount()).thenReturn(1.005);

        Money result = bank.exchange(dollarMoney, Currency.KRW);
        assertThat(result.getAmount()).isEqualTo(1010);
        assertThat(result).isEqualTo(new Money(1010, Currency.KRW));
    }

    @Test
    @DisplayName("달러 -> 원화: 4원 이상 -> 0원으로 반내림")
    void bank_exchange_dollarToKRW_check_roundDown_KRW_AmountUnit() {
        when(dollarMoney.getAmount()).thenReturn(1.004);

        Money result = bank.exchange(dollarMoney, Currency.KRW);
        assertThat(result.getAmount()).isEqualTo(1000);
        assertThat(result).isEqualTo(new Money(1000, Currency.KRW));
    }

    @Test
    @DisplayName("원화 -> 달러: $0.005 이상 -> $0.01 반올림")
    void bank_exchange_KRWToDollar_check_roundUp_Dollar_AmountUnit() {
        when(krwMoney.getAmount()).thenReturn(5d); //5원

        Money result = bank.exchange(krwMoney, Currency.DOLLAR);
        assertThat(result.getAmount()).isEqualTo(0.01);
        assertThat(result).isEqualTo(new Money(0.01, Currency.DOLLAR));
    }

    @Test
    @DisplayName("1달러 <-> 1.02유료 성공 케이스")
    void bank_exchange_dollarToEUR_success() {
        // 1달러
        when(dollarMoney.getAmount()).thenReturn(1.0);

        Money result = bank.exchange(dollarMoney, Currency.EUR);
        assertThat(result.getAmount()).isEqualTo(1.02);
    }

    @Test
    @DisplayName("동일한 통화로 환전 시도하는 경우 실패")
    void bank_exchange_sameCurrency_then_fail() {
        Money money = new Money(1.0, Currency.DOLLAR);

        assertThatThrownBy(() -> bank.exchange(money, Currency.DOLLAR))
                .isInstanceOf(ConsistentCurrencyException.class)
                .hasMessageContainingAll("Can not exchange");
    }
}