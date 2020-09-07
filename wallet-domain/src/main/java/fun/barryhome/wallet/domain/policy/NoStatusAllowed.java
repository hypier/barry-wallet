package fun.barryhome.wallet.domain.policy;

import fun.barryhome.wallet.BizException;
import fun.barryhome.wallet.domain.model.Wallet;
import fun.barryhome.wallet.domain.model.enums.WalletStatus;

/**
 * Created on 2020/9/7 11:29 上午
 *
 * @author barry
 * Description:
 */
public class NoStatusAllowed extends DefaultCheckPolicy {

    public NoStatusAllowed(Wallet wallet) {
        super(wallet);
    }

    /**
     * 检查
     *
     * @return
     */
    @Override
    public void check() {

        if (!WalletStatus.AVAILABLE.equals(wallet.getWalletStatus())){
            throw new BizException("钱包状态不可用");
        }
    }
}
