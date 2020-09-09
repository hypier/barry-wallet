package fun.barryhome.wallet.service;

import fun.barryhome.wallet.common.enums.WalletStatus;
import fun.barryhome.wallet.common.model.Wallet;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Created on 2020/9/8 4:59 下午
 *
 * @author barry
 * Description:
 */
@SpringBootTest
class RechargeRollbackServiceTest {

    private Wallet initWallet(){
        return Wallet.builder()
                .walletId("4bfe7223-ec2e-4788-8921-c1d9f1c78081")
                .balance(BigDecimal.valueOf(100))
                .walletStatus(WalletStatus.AVAILABLE)
                .build();
    }

    @Test
    void exec() {
        RechargeService rechargeService = new RechargeService(initWallet(), BigDecimal.valueOf(20));
        rechargeService.done();
        System.out.println(rechargeService.getTradeRecord());

        RechargeRollbackService rollbackService = new RechargeRollbackService(rechargeService.getTradeRecord());
        rollbackService.done();
        System.out.println(rollbackService.getTradeRecord());
    }
}