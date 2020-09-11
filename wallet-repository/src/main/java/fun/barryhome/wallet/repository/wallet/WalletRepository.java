package fun.barryhome.wallet.repository.wallet;


import fun.barryhome.wallet.common.model.Wallet;

/**
 * Created on 2020/9/8 6:03 下午
 *
 * @author barry
 * Description:
 */
public interface WalletRepository {
    /**
     * 查询钱包
     * @param walletId
     * @return
     */
    Wallet findByWalletId(String walletId);

    /**
     * 保存钱包
     * @param wallet
     */
    void save(Wallet wallet);

    /**
     * 批量保存
     * @param wallets
     * @return
     */
    void save(Iterable<Wallet> wallets);
}
