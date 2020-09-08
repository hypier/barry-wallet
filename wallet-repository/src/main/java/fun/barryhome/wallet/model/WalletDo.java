package fun.barryhome.wallet.model;

import fun.barryhome.wallet.common.enums.WalletStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created on 2020/9/8 10:56 下午
 *
 * @author barry
 * Description:
 */
@Data
@Builder
@Entity
@Table(name = "wallet")
@AllArgsConstructor
@NoArgsConstructor
public class WalletDo {
    /**
     * 钱包ID
     */
    @Id
    private String walletId;
    /**
     * 密码
     */
    private String password;
    /**
     * 状态
     */
    @Enumerated(EnumType.STRING)
    private WalletStatus walletStatus;
    /**
     * 余额
     */
    private BigDecimal balance;

    /**
     * 版本号
     */
    @Version
    private Long version;
}
