package fun.barryhome.wallet.domain.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created on 2020/9/7 9:09 上午
 *
 * @author barry
 * Description:
 */
@Data
public class Wallet {

    /**
     * 钱包ID
     */
    private String walletId;
    /**
     * 密码
     */
    private String password;
    /**
     * 状态
     */
    private WalletStatus walletStatus;
    /**
     * 余额
     */
    private BigDecimal balance;

}
