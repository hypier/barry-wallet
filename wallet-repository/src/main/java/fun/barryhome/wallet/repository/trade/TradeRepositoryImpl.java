package fun.barryhome.wallet.repository.trade;

import fun.barryhome.wallet.common.model.TradeRecord;
import fun.barryhome.wallet.convertor.TradeConvertor;
import fun.barryhome.wallet.convertor.WalletConvertor;
import fun.barryhome.wallet.model.TradeDo;
import fun.barryhome.wallet.model.WalletDo;
import fun.barryhome.wallet.repository.TradeRepository;
import fun.barryhome.wallet.repository.wallet.JPAWalletRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

/**
 * Created on 2020/9/8 6:17 下午
 *
 * @author barry
 * Description:
 */
@Slf4j
@Repository
public class TradeRepositoryImpl implements TradeRepository {

    @Autowired
    private JPATradeRepository jpaTradeRepository;

    @Autowired
    private JPAWalletRepository jpaWalletRepository;

    /**
     * 查询交易
     *
     * @param tradeNumber
     * @return
     */
    @Override
    public TradeRecord findByTradeNumber(String tradeNumber) {
        TradeDo trade = TradeDo.builder().tradeNumber(tradeNumber).build();
        Example<TradeDo> example = Example.of(trade);
        TradeDo tradeDo = jpaTradeRepository.findOne(example).orElse(null);

        if (tradeDo == null){
            return null;
        }

        WalletDo walletDo = jpaWalletRepository.findById(tradeDo.getWalletId()).orElse(null);

        return TradeConvertor.toEntity(tradeDo, WalletConvertor.toEntity(walletDo));
    }

    /**
     * 保存交易
     *
     * @param tradeRecord
     */
    @Override
    public TradeRecord save(TradeRecord tradeRecord) {
        TradeDo tradeDo = TradeConvertor.toDto(tradeRecord);
        jpaTradeRepository.save(tradeDo);

        return TradeConvertor.toEntity(tradeDo);
    }

    /**
     * 批量保存
     *
     * @param tradeRecords
     * @return
     */
    @Override
    public void save(Iterable<TradeRecord> tradeRecords) {
        List<TradeDo> tradeDos = TradeConvertor.toDto(tradeRecords);
        jpaTradeRepository.saveAll(tradeDos);
    }
}
