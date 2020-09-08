package fun.barryhome.wallet.repository.wallet;

import fun.barryhome.wallet.common.enums.WalletStatus;
import fun.barryhome.wallet.common.model.Wallet;
import fun.barryhome.wallet.convertor.WalletConvertor;
import fun.barryhome.wallet.model.WalletDo;
import fun.barryhome.wallet.repository.WalletRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Objects;
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

    @Autowired
    private JPAWalletRepository jpaWalletRepository;

    /**
     * 查询钱包
     *
     * @param walletId
     * @return
     */
    @Override
    public Wallet findByWalletId(String walletId) {
        WalletDo walletDo = jpaWalletRepository.getOne(walletId);
        return WalletConvertor.toEntity(walletDo);
    }

    /**
     * 保存钱包
     *
     * @param wallet
     */
    @Override
    public void save(Wallet wallet) {
        WalletDo walletDo = WalletConvertor.toDto(wallet);
        jpaWalletRepository.save(Objects.requireNonNull(walletDo));
    }
}
