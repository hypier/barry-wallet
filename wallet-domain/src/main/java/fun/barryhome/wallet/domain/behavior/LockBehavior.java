package fun.barryhome.wallet.domain.behavior;

import fun.barryhome.wallet.domain.model.TradeRecord;
import fun.barryhome.wallet.domain.model.enums.WalletStatus;

/**
 * Created on 2020/9/7 10:42 上午
 * 锁定钱包
 *
 * @author barry
 * Description:
 */
public class LockBehavior extends DefaultBehavior {

    public LockBehavior(TradeRecord tradeRecord) {
        super(tradeRecord);
    }

    /**
     * 执行
     */
    @Override
    public void doAction() {
        super.doAction();
        tradeRecord.getWallet().setWalletStatus(WalletStatus.LOCKED);
    }
}
