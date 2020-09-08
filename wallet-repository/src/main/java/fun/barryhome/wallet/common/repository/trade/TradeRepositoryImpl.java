package fun.barryhome.wallet.common.repository.trade;

import fun.barryhome.wallet.common.enums.InOutFlag;
import fun.barryhome.wallet.common.enums.TradeType;
import fun.barryhome.wallet.common.enums.WalletStatus;
import fun.barryhome.wallet.common.model.TradeRecord;
import fun.barryhome.wallet.common.model.Wallet;
import fun.barryhome.wallet.common.repository.TradeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Created on 2020/9/8 6:17 下午
 *
 * @author barry
 * Description:
 */
@Slf4j
@Repository
public class TradeRepositoryImpl implements TradeRepository {
    /**
     * 查询交易
     *
     * @param tradeNumber
     * @return
     */
    @Override
    public TradeRecord findByTradeNumber(String tradeNumber) {
        return TradeRecord.builder()
                .tradeNumber(UUID.randomUUID().toString())
                .wallet(Wallet.builder()
                        .walletId(UUID.randomUUID().toString())
                        .balance(BigDecimal.valueOf(100))
                        .walletStatus(WalletStatus.AVAILABLE)
                        .version(0L)
                        .build())
                .tradeAmount(BigDecimal.valueOf(20))
                .inOutFlag(InOutFlag.IN)
                .tradeType(TradeType.RECHARGE)
                .build();
    }

    /**
     * 保存交易
     *
     * @param tradeRecord
     */
    @Override
    public void save(TradeRecord tradeRecord) {
        log.error("save trade: {}", tradeRecord);
    }
}
