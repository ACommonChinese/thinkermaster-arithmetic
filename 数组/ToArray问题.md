# ToArray问题

在ArrayList源码的`toArray()`方法中有这么一句：c.toArray might (incorrectly) not return Object[] (see 6260652)

java.util.ArrayList.java 源码:  
```java
public ArrayList(Collection<? extends E> c) {
        elementData = c.toArray();
    if ((size = elementData.length) != 0) {
        // c.toArray might (incorrectly) not return Object[] (see 6260652)
        if (elementData.getClass() != Object[].class)
            elementData = Arrays.copyOf(elementData, size, Object[].class);
    } else {
        // replace with empty array.
        this.elementData = EMPTY_ELEMENTDATA;
    }
}
```

也就是说c.toArray()不一定返回的是Object[], 也可能返回的Student[], Cat[], (? extends Object)[], 假如返回的是Student[], 那么往这个数组中插入Object对象时就会因向下转型而Crash(子类指针Student指向父类Object对象, 不允许)  

### 分析

因为由于继承的原因，我们父类实例的具体类型，实际上是取决于在 new 时，我们所使用的子类类型, 看一个示例:  

```java
public class Father {}
public class Son extends Father {}

public class Test {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        Son[] sons = new Son[]{new Son(), new Son()};
        System.out.println(sons.getClass()); // class [LSon;
        Father[] fathers = sons;
        System.out.println(fathers.getClass()); // class [LSon;
        fathers[0] = new Father();
        // Crash: java.lang.ArrayStoreException: Father
        // 因为fathers实际类型是Son[], 数组中元素类型都是Son类型, 因而出现向下转型
        // 子类指针指向父类对象不可以
        // 那么假如有一个Object[]数组, 并不代表我们可以将Object对象存进去, 这取决于数组中元素实际的类型
    }
}
```

ArrayList的源码, `toArray()`的实现如下:  

```java
public Object[] toArray() {
    return Arrays.copyOf(elementData, size);
}
```

Arrays.copyOf的意思是把elementData这个数组中的数据, copy size个元素, 生成一个新的数组, 这里的size是elementData数组的length, 即Arrays.copyOf把整个elementData数组拷贝了一份新的, 并返回, 但这里的新的数组可能是Object[], 也可能是Student[], Cat[], 只要是(? extends Object)[]即可  

因此, java为了返回Object[], 这样处理:  

```java
 if (elementData.getClass() != Object[].class)
            elementData = Arrays.copyOf(elementData, size, Object[].class);
```

来看一下Arrays.copyOf的源代码:  

```java
public static <T,U> T[] copyOf(U[] original, int newLength, Class<? extends T[]> newType) {
        @SuppressWarnings("unchecked")
    T[] copy = ((Object)newType == (Object)Object[].class)
        ? (T[]) new Object[newLength]
        : (T[]) Array.newInstance(newType.getComponentType(), newLength);
    System.arraycopy(original, 0, copy, 0,
                     Math.min(original.length, newLength));
    return copy;
}
```

因此, 由于 `elementData = Arrays.copyOf(elementData, size, Object[].class)`, 第三个参数传的是Object[].class, 这就保证了返回的一定是Object[], 而不是(? extends Object)[]  
