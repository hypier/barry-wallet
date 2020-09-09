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
        tradeManager.recharge("4bfe7223-ec2e-4788-8921-c1d9f1c7808o", BigDecimal.valueOf(20));
    }

    @Test
    void transfer() {
        tradeManager.transfer("4bfe7223-ec2e-4788-8921-c1d9f1c7808o",
                "4bfe7223-ec2e-4788-8921-c1d9f1c78081", BigDecimal.valueOf(50));
    }
}