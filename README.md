# java-design-patterns
## 一、单例模式
### 1.概念思想理解
单例（Singleton）模式的定义：指一个类只有一个实例，且该类能自行创建这个实例的一种模式。

单例模式有 3 个特点：
单例类只有一个实例对象；
该单例对象必须由单例类自行创建；
单例类对外提供一个访问该单例的全局访问点；
### 2.java的几种实现方式
#### 2.1 饿汉式
饿汉式单例在类创建的同时就已经创建好一个静态的对象供系统使用，以后不再改变，所以是线程安全的。  
缺点就是，如果只是调用该类的静态方法或者获取其他静态属性，也会进行实例创建，浪费内存。  
但如果这个类没有**静态变量（非final）**，且没有**静态方法**， 那其实也是延迟加载的
```
/**
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
```

#### 2.2 懒汉式
为了解决上面的问题，于是想到等使用的时候再进行实例创建，但是又引入了新的问题：每次访问时都要同步，会影响性能，且消耗更多的资源，这是懒汉式单例的缺点。
```
/**
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
```
#### 2.3 DCL(double check lock) 双重锁判断
由直接锁方法，改成锁方法中的代码块，减小锁的粒度。需要注意的是，锁内还需要进行一次判断。  
考虑如下场景：  
当instance为null时，线程A和B先后走到第一次判断，此时 instance == null 都返回true。  
于是开始争抢锁，线程A拿到锁继续往下走，线程B阻塞。  
然后线程A创建实例释放锁后，线程B拿到锁，此时线程B是在**instance == null** 的里面，所以其会再次new 对象.  
所以如果不双重判断的话，第一次创建的时候，可能会有多个线程创建多次。
```
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
            synchronized (DCLSingleton.class) {
                if (instance == null) {
                    instance = new DCLSingleton();
                }
                return instance;
            }
        }
        return instance;
    }
}
```
#### 2.4 静态内部类实现
双重锁判断比懒汉式减小了锁的粒度，但依然还是有锁。  
饿汉式的问题在于，只有调用了该类的静态方法，就会触发实例创建。  
于是换成静态内部类持有外部单例类的实例，只有当调用获取实例的getInstance方法，才会触发实例创建，完美实现了延迟加载和无锁获取实例。推荐使用。

```
/**
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
```
#### 2.5 枚举实现
枚举本身就是单例的。  
可以添加需要的属性和方法。
```
/**
 * @author LQL
 * @since Create in 2020/8/17 22:10
 */
public enum EnumSingleton {

    INSTANCE;

    private String property;

    public void doSth() {}
}
```
