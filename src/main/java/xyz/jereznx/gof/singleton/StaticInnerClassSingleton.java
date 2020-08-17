package xyz.jereznx.gof.singleton;

/**
 * 静态内部类实现
 * 只有调用getInstance方法，才会触发StaticInnerClassSingleton加载成员变量，实现延迟加载，推荐使用
 *
 * @author LQL
 * @since Create in 2020/8/17 22:01
 */
public class StaticInnerClassSingleton {

    private StaticInnerClassSingleton() {
    }

    private static class SingletonInstance {
        private static final StaticInnerClassSingleton instance = new StaticInnerClassSingleton();
    }

    public static StaticInnerClassSingleton getInstance() {
        return SingletonInstance.instance;
    }
}
