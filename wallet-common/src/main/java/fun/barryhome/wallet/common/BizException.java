package fun.barryhome.wallet.common;

/**
 * Created on 2020/9/7 11:32 上午
 * 业务异常
 * @author barry
 * Description:
 */
public class BizException extends RuntimeException {

    public BizException(String msg){
        super(msg);
    }
}
