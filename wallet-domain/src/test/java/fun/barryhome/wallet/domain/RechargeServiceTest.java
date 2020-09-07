package fun.barryhome.wallet.domain;

import fun.barryhome.wallet.domain.model.Wallet;
import fun.barryhome.wallet.domain.model.WalletStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created on 2020/9/7 4:33 下午
 *
 * @author barry
 * Description:
 */
class RechargeServiceTest {

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

        BigDecimal tradeAmount = BigDecimal.valueOf(20);
        RechargeService rechargeService = new RechargeService(wallet, tradeAmount);
        rechargeService.exec();

        System.out.println(wallet);
    }
}