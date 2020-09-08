package fun.barryhome.wallet.domain.behavior;

import fun.barryhome.wallet.common.BizException;
import fun.barryhome.wallet.common.enums.InOutFlag;
import fun.barryhome.wallet.common.model.Wallet;

import java.math.BigDecimal;

/**
 * Created on 2020/9/7 2:51 下午
 * 贷记，金额增加
 *
 * @author barry
 * Description:
 */

public class CreditBehavior extends DefaultBehavior {

    /**
     * 金额
     */
    private final BigDecimal tradeAmount;

    public CreditBehavior(Wallet wallet, BigDecimal tradeAmount) {
        super(wallet);
        this.tradeAmount = tradeAmount;
    }

    /**
     * 执行
     */
    @Override
    public void doAction() {
        if (tradeAmount.compareTo(BigDecimal.ZERO) <= 0){
            throw new BizException("交易金额不能小于等于0");
        }

        super.doAction();

        wallet.setBalance(wallet.getBalance().add(tradeAmount));
    }

    /**
     * 进出状态
     *
     * @return
     */
    @Override
    public InOutFlag getInOutFlag() {
        return InOutFlag.IN;
    }
}
