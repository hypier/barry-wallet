package fun.barryhome.wallet.domain.model;

/**
 * Created by heyong on 2017/2/8 18:25.
 */
public enum WalletStatus {
    /**
     * 未激活的
     */
    Unactivated,
    /**
     * 锁定的
     */
    Locked,
    /**
     * 可用的
     */
    Available,
    /**
     * 挂失的
     */
    Lost,
    /**
     * 注销的
     */
    Destroyed,
    /**
     * 异常的
     */
    Abnormal,

}