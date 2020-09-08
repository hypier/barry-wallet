package fun.barryhome.wallet.application;

import fun.barryhome.wallet.common.enums.WalletStatus;
import fun.barryhome.wallet.common.model.Wallet;
import fun.barryhome.wallet.repository.WalletRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Created on 2020/9/8 11:23 下午
 *
 * @author barry
 * Description:
 */
@SpringBootTest
public class WalletTest {
    @Autowired
    private WalletRepository walletRepository;

    @Test
    public void create(){
        Wallet wallet = Wallet.builder()
                .walletId("4bfe7223-ec2e-4788-8921-c1d9f1c7808e")
                .balance(BigDecimal.valueOf(100))
                .walletStatus(WalletStatus.AVAILABLE)
                .build();

        walletRepository.save(wallet);
    }
}
