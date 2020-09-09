package fun.barryhome.wallet.domain;


import fun.barryhome.wallet.common.enums.TradeStatus;
import fun.barryhome.wallet.common.model.TradeRecord;

/**
 * Created on 2020/9/7 10:28 上午
 * 两阶段提交
 * @author barry
 * Description:
 */
public abstract class DefaultTowPCService extends DefaultService implements TwoPCWalletService {

    public DefaultTowPCService(TradeRecord tradeRecord) {
        super(tradeRecord);
    }

    /**
     * 处理中
     */
    @Override
    public void process() {
        check();

        getTradeRecord().setTradeStatus(TradeStatus.PROCESSING);
    }
}
