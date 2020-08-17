package xyz.jereznx.gof.singleton;

/**
 * 懒汉式
 * 每次访问时都要同步，会影响性能，且消耗更多的资源，这是懒汉式单例的缺点。
 * @author LQL
 * @since Create in 2020/8/17 21:46
 */
public class LazySingleton {

    private static volatile LazySingleton instance = null;

    private LazySingleton(){
        System.out.println("creating");
    }

    public static synchronized LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }

}
