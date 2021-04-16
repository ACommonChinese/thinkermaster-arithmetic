# 队列

- 队列是一种特殊的线性数据结构
- 先进先出, FIFO, 队首插入, 队尾出队

面向接口编程, 声明队列操作:

```java
public interface Queue<E> {
    void enqueue(E e); // 入队, 队尾入队
    E dequeue();       // 出队, 队首出队
    E getFront();      // 获取队首
    boolean isEmpty(); // 是否为空
    int getSize();     // 获取队列内元素个数
}
```