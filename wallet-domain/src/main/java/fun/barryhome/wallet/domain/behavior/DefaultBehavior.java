package fun.barryhome.wallet.domain.behavior;

import fun.barryhome.wallet.BizException;
import fun.barryhome.wallet.domain.model.TradeRecord;
import fun.barryhome.wallet.domain.model.enums.TradeStatus;

/**
 * Created on 2020/9/7 10:40 上午
 *
 * @author barry
 * Description:
 */

public abstract class DefaultBehavior implements Behavior {
    /**
     * 交易
     */
    protected TradeRecord tradeRecord;

    public DefaultBehavior(TradeRecord tradeRecord) {
        this.tradeRecord = tradeRecord;
    }

    /**
     * 执行
     */
    @Override
    public void doAction() {
        if (tradeRecord.getWallet() == null){
            throw new BizException("钱包未初始化");
        }

        tradeRecord.setTradeStatus(TradeStatus.SUCCEED);
    }
}
