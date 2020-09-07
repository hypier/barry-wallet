package fun.barryhome.wallet.domain.behavior;

import fun.barryhome.wallet.BizException;
import fun.barryhome.wallet.domain.model.Wallet;

import java.math.BigDecimal;

/**
 * Created on 2020/9/7 10:06 上午
 * 借记，金额减少
 *
 * @author barry
 * Description:
 */
public class DebitBehavior extends DefaultBehavior {

    /**
     * 金额
     */
    private final BigDecimal tradeAmount;

    public DebitBehavior(Wallet wallet, BigDecimal tradeAmount) {
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

        wallet.setBalance(wallet.getBalance().subtract(tradeAmount));
    }
}
