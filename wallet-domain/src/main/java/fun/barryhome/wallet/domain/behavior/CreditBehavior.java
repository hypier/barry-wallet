package fun.barryhome.wallet.domain.behavior;

import fun.barryhome.wallet.BizException;
import fun.barryhome.wallet.domain.model.TradeRecord;

import java.math.BigDecimal;

/**
 * Created on 2020/9/7 2:51 下午
 * 贷记，金额增加
 *
 * @author barry
 * Description:
 */
public class CreditBehavior extends DefaultBehavior {

    public CreditBehavior(TradeRecord tradeRecord) {
        super(tradeRecord);
    }

    /**
     * 执行
     */
    @Override
    public void doAction() {
        if (tradeRecord.getTradeAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BizException("交易金额不能小于等于0");
        }

        super.doAction();

        tradeRecord.getWallet().setBalance(tradeRecord.getWallet().getBalance().add(tradeRecord.getTradeAmount()));
    }
}
