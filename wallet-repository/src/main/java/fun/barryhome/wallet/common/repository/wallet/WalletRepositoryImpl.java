package fun.barryhome.wallet.common.repository.wallet;

import fun.barryhome.wallet.common.enums.WalletStatus;
import fun.barryhome.wallet.common.model.Wallet;
import fun.barryhome.wallet.common.repository.WalletRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Created on 2020/9/8 6:21 下午
 *
 * @author barry
 * Description:
 */
@Slf4j
@Repository
public class WalletRepositoryImpl implements WalletRepository {
    /**
     * 查询钱包
     *
     * @param walletId
     * @return
     */
    @Override
    public Wallet findByWalletId(String walletId) {
        return Wallet.builder()
                .walletId(UUID.randomUUID().toString())
                .balance(BigDecimal.valueOf(100))
                .walletStatus(WalletStatus.AVAILABLE)
                .build();
    }

    /**
     * 保存钱包
     *
     * @param wallet
     */
    @Override
    public void save(Wallet wallet) {
        log.error("save wallet: {}", wallet);
    }
}
