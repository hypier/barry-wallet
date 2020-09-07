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
    private final BigDecimal amount;

    public NoOverdraftAllowed(Wallet wallet, BigDecimal amount) {
        super(wallet);
        this.amount = amount;
    }

    /**
     * 检查
     *
     * @return
     */
    @Override
    public void check() {

        if (wallet.getBalance().compareTo(amount) < 0){
            throw new BizException("余额不足");
        }
    }
}
