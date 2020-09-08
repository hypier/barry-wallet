package fun.barryhome.wallet.common.model;

import fun.barryhome.wallet.common.enums.WalletStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created on 2020/9/7 9:09 上午
 *
 * @author barry
 * Description:
 */
@Data
@Builder
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

    /**
     * 版本号
     */
    private Long version;
}
