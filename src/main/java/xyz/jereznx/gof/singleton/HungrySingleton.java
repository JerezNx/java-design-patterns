package xyz.jereznx.gof.singleton;

/**
 * 饿汉式
 * 饿汉式单例在类创建的同时就已经创建好一个静态的对象供系统使用，以后不再改变，所以是线程安全的
 * 缺点就是，如果只是调用该类的静态方法或者获取其他静态属性，也会进行实例创建，浪费内存
 * 但如果这个类没有静态变量（非final），且没有静态方法， 那其实也是延迟加载的
 *
 * @author LQL
 * @since Create in 2020/8/17 21:50
 */
public class HungrySingleton {
    private static final HungrySingleton instance = new HungrySingleton();

    private HungrySingleton() {
    }

    public static HungrySingleton getInstance() {
        return instance;
    }
}
