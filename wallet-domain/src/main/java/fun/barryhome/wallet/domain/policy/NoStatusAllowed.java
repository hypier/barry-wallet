package fun.barryhome.wallet.domain.policy;

import fun.barryhome.wallet.common.BizException;
import fun.barryhome.wallet.common.enums.WalletStatus;
import fun.barryhome.wallet.common.model.Wallet;

/**
 * Created on 2020/9/7 11:29 上午
 *
 * @author barry
 * Description:
 */
public class NoStatusAllowed implements CheckPolicy {

    /**
     * 钱包
     */
    private final Wallet wallet;

    public NoStatusAllowed(Wallet wallet) {
        this.wallet = wallet;
    }

    /**
     * 检查
     *
     */
    @Override
    public void check() {

        if (!WalletStatus.AVAILABLE.equals(wallet.getWalletStatus())){
            throw new BizException("钱包状态不可用");
        }
    }
}
