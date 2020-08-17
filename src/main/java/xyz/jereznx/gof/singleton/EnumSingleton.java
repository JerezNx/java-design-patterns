package xyz.jereznx.gof.singleton;

import lombok.Data;

/**
 * 枚举实现单例
 * enum本身就是单例的，缺点是无法延迟加载
 * @author LQL
 * @since Create in 2020/8/17 22:10
 */
public enum EnumSingleton {

    INSTANCE;

    private String property;

    public void doSth() {}
}
