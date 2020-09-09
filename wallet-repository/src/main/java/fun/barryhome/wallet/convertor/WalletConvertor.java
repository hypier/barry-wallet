package fun.barryhome.wallet.convertor;

import fun.barryhome.wallet.common.model.Wallet;
import fun.barryhome.wallet.model.WalletDo;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2020/9/8 11:08 下午
 *
 * @author barry
 * Description:
 */
public class WalletConvertor {

    public static WalletDo toDto(Wallet wallet){
        if (wallet == null){
            return null;
        }

        WalletDo walletDo = WalletDo.builder().build();
        BeanUtils.copyProperties(wallet, walletDo);
        return walletDo;
    }

    public static List<WalletDo> toDto(Iterable<Wallet> wallets) {
        if (wallets == null){
            return null;
        }

        List<WalletDo> list = new ArrayList<>();
        wallets.forEach(t -> list.add(toDto(t)));

        return list;
    }

    public static Wallet toEntity(WalletDo walletDo){
        if (walletDo == null){
            return null;
        }

        Wallet wallet = Wallet.builder().build();
        BeanUtils.copyProperties(walletDo, wallet);
        return wallet;
    }
}
