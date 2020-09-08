package fun.barryhome.wallet.model;

import fun.barryhome.wallet.common.enums.InOutFlag;
import fun.barryhome.wallet.common.enums.TradeStatus;
import fun.barryhome.wallet.common.enums.TradeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Version;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created on 2020/9/8 10:51 下午
 *
 * @author barry
 * Description:
 */
@Data
@Builder
@Entity
@Table(name = "trade")
@AllArgsConstructor
@NoArgsConstructor
public class TradeDo {

    /**
     * 交易号
     */
    @Id
    private String tradeNumber;
    /**
     * 交易金额
     */
    private BigDecimal tradeAmount;
    /**
     * 进出标识
     */
    @Enumerated(EnumType.STRING)
    private InOutFlag inOutFlag;
    /**
     * 交易类型
     */
    @Enumerated(EnumType.STRING)
    private TradeType tradeType;
    /**
     * 交易余额
     */
    private BigDecimal balance;
    /**
     * 钱包ID
     */
    private String walletId;
    /**
     * 备注
     */
    private String remark;
    /**
     * 交易状态
     */
    @Enumerated(EnumType.STRING)
    private TradeStatus tradeStatus;
    /**
     * 原交易号
     */
    private String sourceNumber;

    @Version
    private Long version;
}
