package fun.barryhome.wallet.application;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

/**
 * Created on 2020/9/8 6:30 下午
 *
 * @author barry
 * Description:
 */
@SpringBootTest
class TradeManagerTest {

    @Autowired
    private TradeManager tradeManager;

    @Test
    void recharge() {
        tradeManager.recharge("abc", BigDecimal.valueOf(20));

    }
}