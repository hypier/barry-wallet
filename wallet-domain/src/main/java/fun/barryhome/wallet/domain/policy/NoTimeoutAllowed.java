package fun.barryhome.wallet.domain.policy;

import fun.barryhome.wallet.common.BizException;
import fun.barryhome.wallet.common.model.TradeRecord;

import java.util.Calendar;

/**
 * Created on 2020/9/9 10:56 上午
 * 不允许超时
 * @author barry
 * Description:
 */
public class NoTimeoutAllowed implements CheckPolicy {

    private final TradeRecord tradeRecord;

    public NoTimeoutAllowed(TradeRecord tradeRecord) {
        this.tradeRecord = tradeRecord;
    }

    /**
     * 检查
     *
     * @return
     */
    @Override
    public void check() {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.HOUR, -1);

        Calendar upTime = Calendar.getInstance();
        upTime.setTime(tradeRecord.getUpdateTime());

        if (now.compareTo(upTime) > 0){
            throw new BizException("已超过时限");
        }
    }
}
