package fun.barryhome.wallet.domain.behavior;

import fun.barryhome.wallet.domain.model.Wallet;

import java.math.BigDecimal;

/**
 * Created on 2020/9/7 2:51 下午
 * 贷记，金额增加
 * @author barry
 * Description:
 */
public class CreditBehavior extends DefaultBehavior {

    /**
     * 金额
     */
    private final BigDecimal amount;

    public CreditBehavior(Wallet wallet, BigDecimal amount) {
        super(wallet);
        this.amount = amount;
    }

    /**
     * 执行
     */
    @Override
    public void doAction() {
        super.doAction();

        wallet.setBalance(wallet.getBalance().add(amount));
    }
}
