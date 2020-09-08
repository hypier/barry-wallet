package fun.barryhome.wallet.service;

import fun.barryhome.wallet.common.enums.WalletStatus;
import fun.barryhome.wallet.common.model.Wallet;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Created on 2020/9/8 5:08 下午
 *
 * @author barry
 * Description:
 */
class LockServiceTest {

    private Wallet initWallet(){
        return Wallet.builder()
                .walletId(UUID.randomUUID().toString())
                .balance(BigDecimal.valueOf(100))
                .walletStatus(WalletStatus.AVAILABLE)
                .build();
    }

    @Test
    void exec() {
        LockService lockService = new LockService(initWallet());
        lockService.done();

        System.out.println(lockService.getTradeRecord());
    }
}