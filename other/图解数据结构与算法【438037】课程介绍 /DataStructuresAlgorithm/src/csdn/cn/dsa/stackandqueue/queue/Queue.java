package csdn.cn.dsa.stackandqueue.queue;

public interface Queue<E> {

    void enqueue(E e);
    E dequeue();
    E getFront();
    boolean isEmpty();
    int getSize();
}
