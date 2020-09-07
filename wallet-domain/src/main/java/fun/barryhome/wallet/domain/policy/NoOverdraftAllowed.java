package fun.barryhome.wallet.domain.policy;

import fun.barryhome.wallet.BizException;
import fun.barryhome.wallet.domain.model.Wallet;

import java.math.BigDecimal;

/**
 * Created on 2020/9/7 11:29 上午
 *
 * @author barry
 * Description:
 */
public class NoOverdraftAllowed extends DefaultCheckPolicy {
    /**
     * 金额
     */
    private final BigDecimal tradeAmount;

    public NoOverdraftAllowed(Wallet wallet, BigDecimal tradeAmount) {
        super(wallet);
        this.tradeAmount = tradeAmount;
    }

    /**
     * 检查
     *
     * @return
     */
    @Override
    public void check() {
        if (tradeAmount.compareTo(BigDecimal.ZERO) <= 0){
            throw new BizException("交易金额不能小于等于0");
        }

        if (wallet.getBalance().compareTo(tradeAmount) < 0){
            throw new BizException("余额不足");
        }
    }
}
