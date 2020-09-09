package fun.barryhome.wallet.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import fun.barryhome.wallet.common.enums.InOutFlag;
import fun.barryhome.wallet.common.enums.TradeStatus;
import fun.barryhome.wallet.common.enums.TradeType;
import fun.barryhome.wallet.common.model.TradeEvent;
import fun.barryhome.wallet.convertor.TradeConvertor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.DomainEvents;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tradeId;
    /**
     * 交易号
     */
    @Column(unique = true)
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

    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(nullable = false, updatable = false)
    private Date createTime;


    /**
     * 更新时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(nullable = false)
    private Date updateTime = new Date();

    /**
     * 版本号
     */
    @Version
    private Long version;

    @PrePersist
    void prePersist() {
        createTime = new Date();
        updateTime = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updateTime = new Date();
    }

    @DomainEvents
    public List<Object> domainEvents(){
        return Collections.singletonList(new TradeEvent(TradeConvertor.toEntity(this)));
    }

}
