package fun.barryhome.wallet.domain.behavior;

import fun.barryhome.wallet.common.enums.InOutFlag;
import fun.barryhome.wallet.common.enums.WalletStatus;
import fun.barryhome.wallet.common.model.Wallet;

/**
 * Created on 2020/9/7 10:42 上午
 * 锁定钱包
 *
 * @author barry
 * Description:
 */
public class LockBehavior extends DefaultBehavior {

    public LockBehavior(Wallet wallet) {
        super(wallet);
    }

    /**
     * 执行
     */
    @Override
    public void doAction() {
        super.doAction();
        wallet.setWalletStatus(WalletStatus.LOCKED);
    }

    /**
     * 进出状态
     *
     * @return
     */
    @Override
    public InOutFlag getInOutFlag() {
        return InOutFlag.NONE;
    }
}
