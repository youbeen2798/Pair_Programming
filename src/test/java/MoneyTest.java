import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;

import exception.InvalidMoneyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {
    Money krwMoney;
    Money dollarMoney;

    @BeforeEach
    void setUp() {
        krwMoney = new Money(1000L, Currency.KRW);
        dollarMoney = new Money(5L, Currency.DOLLAR);
    }

    @Test
    @DisplayName("addMoney 원화 성공 테스트")
    void money_KRW_add_success() {
        Money KRW_money2 = new Money(1000L, Currency.KRW);
        Money resultMoney = krwMoney.add(KRW_money2);
        assertThat(resultMoney.getAmount()).isEqualTo(2000L);
    }

    @Test
    @DisplayName("addMoney 달러 성공 테스트")
    void money_DOLLAR_add_success() {
        Money DOLLAR_money2 = new Money(5L, Currency.DOLLAR);
        Money resultMoney = dollarMoney.add(DOLLAR_money2);
        assertThat(resultMoney.getAmount()).isEqualTo(10L);
    }

    @Test
    @DisplayName("equals 원화 성공 테스트")
    void money_KRW_equals_success() {
        Money money2 = new Money(1000L, Currency.KRW);
        assertThat(money2.equals(krwMoney)).isTrue();
    }

    @Test
    @DisplayName("money 음수 실패 테스트")
    void money_KRW_invalid_success() {
        assertThatThrownBy(() -> new Money(-1000L, Currency.KRW))
                .isInstanceOf(InvalidMoneyException.class)
                .hasMessageContainingAll("Invalid");
    }

    @Test
    @DisplayName("5$ - 6$ = 오류")
    void money_DOLLAR_subtract_invalidResult() {
        Money dollarMoney2= new Money(6L, Currency.DOLLAR);

        assertThatThrownBy(() -> dollarMoney.subtract(dollarMoney2))
                .isInstanceOf(InvalidMoneyException.class)
                .hasMessageContainingAll("Invalid");
    }

    @Test
    @DisplayName("5.25$ + 5.25$ = 10.50$ (소숫점 이하 2자리)")
    void money_DOLLAR_decimalPoint_add_success() {
        Money decimalDollarMoney1 = new Money(5.25, Currency.DOLLAR);
        Money decimalDollarMoney2 = new Money(5.25, Currency.DOLLAR);

        Money resultMoney = decimalDollarMoney1.add(decimalDollarMoney2);

        assertThat(resultMoney.getAmount()).isEqualTo(10.50);
    }
}
