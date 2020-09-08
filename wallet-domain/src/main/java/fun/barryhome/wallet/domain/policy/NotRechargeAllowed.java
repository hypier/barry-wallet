package fun.barryhome.wallet.domain.policy;

import fun.barryhome.wallet.common.BizException;
import fun.barryhome.wallet.common.enums.InOutFlag;
import fun.barryhome.wallet.common.enums.TradeType;
import fun.barryhome.wallet.common.model.TradeRecord;

/**
 * Created on 2020/9/8 7:40 下午
 *
 * @author barry
 * Description:
 */
public class NotRechargeAllowed implements CheckPolicy {

    private final TradeRecord tradeRecord;

    public NotRechargeAllowed(TradeRecord tradeRecord){
        this.tradeRecord = tradeRecord;
    }

    /**
     * 检查
     *
     * @return
     */
    @Override
    public void check() {
        if (!TradeType.RECHARGE.equals(tradeRecord.getTradeType())){
            throw new BizException("不支持此交易类型");
        }

        if (!InOutFlag.IN.equals(tradeRecord.getInOutFlag())){
            throw new BizException("不支持此出入账类型");
        }
    }
}
