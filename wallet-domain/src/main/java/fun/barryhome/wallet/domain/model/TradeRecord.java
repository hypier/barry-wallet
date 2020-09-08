package fun.barryhome.wallet.domain.model;

import fun.barryhome.wallet.domain.model.enums.InOutFlag;
import fun.barryhome.wallet.domain.model.enums.TradeStatus;
import fun.barryhome.wallet.domain.model.enums.TradeType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created on 2020/9/7 4:44 下午
 *
 * @author barry
 * Description:
 */
@Data
@Builder
public class TradeRecord {
    /**
     * 交易号
     */
    private String tradeNumber;
    /**
     * 交易金额
     */
    private BigDecimal tradeAmount;
    /**
     * 进出标识
     */
    private InOutFlag inOutFlag;
    /**
     * 交易类型
     */
    private TradeType tradeType;
    /**
     * 交易余额
     */
    private BigDecimal balance;
    /**
     * 钱包ID
     */
    private Wallet wallet;
    /**
     * 备注
     */
    private String remark;
    /**
     * 交易状态
     */
    private TradeStatus tradeStatus;
    /**
     * 原交易号
     */
    private String sourceNumber;
}
