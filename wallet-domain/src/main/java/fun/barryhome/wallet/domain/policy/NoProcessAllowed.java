package fun.barryhome.wallet.domain.policy;

import fun.barryhome.wallet.common.BizException;
import fun.barryhome.wallet.common.enums.TradeStatus;

/**
 * Created on 2020/9/9 11:54 上午
 *
 * @author barry
 * Description:
 */
public class NoProcessAllowed implements CheckPolicy {
    private final TradeStatus tradeStatus;

    public NoProcessAllowed(TradeStatus tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    /**
     * 检查
     *
     * @return
     */
    @Override
    public void check() {
        if (tradeStatus != null && !tradeStatus.equals(TradeStatus.PROCESSING)) {
            throw new BizException("此状态不能继续处理");
        }
    }
}
