package fun.barryhome.wallet.domain;

import fun.barryhome.wallet.domain.model.Wallet;
import fun.barryhome.wallet.domain.model.enums.WalletStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Created on 2020/9/7 3:11 下午
 *
 * @author barry
 * Description:
 */
@SpringBootTest
class TransferServiceTest {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    private Wallet initWallet() {
        return Wallet.builder()
                .walletId(UUID.randomUUID().toString())
                .balance(BigDecimal.valueOf(100))
                .walletStatus(WalletStatus.AVAILABLE)
                .build();
    }

    @Test
    void exec() {
        Wallet fromWallet = initWallet();
        Wallet toWallet = initWallet();
        BigDecimal tradeAmount = BigDecimal.valueOf(70.9);

        TransferService transferService = new TransferService(fromWallet, toWallet, tradeAmount);
        transferService.done();
        transferService.sendEvent(applicationEventPublisher);

        System.out.println(fromWallet);
        System.out.println(toWallet);
    }
}