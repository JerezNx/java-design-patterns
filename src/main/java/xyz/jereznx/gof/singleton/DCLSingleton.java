package xyz.jereznx.gof.singleton;

/**
 * double check lock (双重锁判断)
 *
 * @author LQL
 * @since Create in 2020/8/17 21:53
 */
public class DCLSingleton {
    private static volatile DCLSingleton instance = null;

    private DCLSingleton() {
    }

    public static DCLSingleton getInstance() {
        if (instance == null) {
//            锁方法中的代码块，减小锁的粒度
            synchronized (DCLSingleton.class) {
                /**
                 * 需要再次判断，否则考虑如下场景：
                 * 当instance为Null时，线程A和B先后走到第一次判断，此时 instance == null 都返回true，于是开始争抢锁
                 * 线程A拿到锁继续往下走，线程B阻塞。然后线程A创建实例释放锁后，线程B拿到锁，此时线程B是在instance == null 的里面，所以其会再次new 对象
                 * 所以如果不双重判断的话，第一次创建的时候，可能会有多个线程创建多次
                 */
                if (instance == null) {
                    instance = new DCLSingleton();
                }
                return instance;
            }
        }
        return instance;
    }
}
