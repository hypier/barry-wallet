package fun.barryhome.wallet.domain.behavior;

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
    private final BigDecimal amount;

    public DebitBehavior(Wallet wallet, BigDecimal amount) {
        super(wallet);
        this.amount = amount;
    }

    /**
     * 执行
     */
    @Override
    public void exec() {
        super.exec();

        wallet.setBalance(wallet.getBalance().subtract(amount));
    }
}
