package fun.barryhome.wallet.domain;

import fun.barryhome.wallet.domain.model.Wallet;
import fun.barryhome.wallet.domain.model.WalletStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created on 2020/9/7 3:30 下午
 *
 * @author barry
 * Description:
 */
class LockServiceTest {

    private Wallet initWallet(){
        return Wallet.builder()
                .walletId(UUID.randomUUID().toString())
                .balance(BigDecimal.valueOf(100))
                .walletStatus(WalletStatus.Available)
                .build();
    }

    @Test
    void exec() {
        Wallet wallet = initWallet();
        LockService lockService = new LockService(wallet);
        lockService.exec();

        System.out.println(wallet);
    }
}