package fun.barryhome.wallet.convertor;

import fun.barryhome.wallet.common.model.Wallet;
import fun.barryhome.wallet.model.WalletDo;
import org.springframework.beans.BeanUtils;

/**
 * Created on 2020/9/8 11:08 下午
 *
 * @author barry
 * Description:
 */
public class WalletConvertor {

    public static WalletDo toDto(Wallet wallet){
        WalletDo walletDo = WalletDo.builder().build();
        BeanUtils.copyProperties(wallet, walletDo);
        return walletDo;
    }

    public static Wallet toEntity(WalletDo walletDo){
        Wallet wallet = Wallet.builder().build();
        BeanUtils.copyProperties(walletDo, wallet);
        return wallet;
    }
}
