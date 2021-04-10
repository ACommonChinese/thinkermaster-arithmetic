package csdn.cn.dsa.array;

import com.sun.xml.internal.bind.v2.model.core.ID;

import java.util.Arrays;

public class Array<E> {
    private E[] data;
    private int size;
//    private int capactiy = data.length;

    // 改造函数
    public Array(int capacity){
        data = (E[]) new Object[capacity];
        size=0;
    }

    public Array(){
        this(10);
    }

    //获取数组的容量
    public int getCapacity(){
        return data.length;
    }

    //获取数组中元素的个数
    public int getSize(){
        return size;
    }

    //判断数组是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    //向数组的末尾添加元素
    public void addLast(E ele){
//        if(size==data.length){
//            throw new RuntimeException("array is full!");
//        }
//        data[size] = ele;
//        size++;
        add(size,ele);
    }

    public void addFirst(E ele){
        add(0,ele);
    }

    public void  add(int index,E ele){
//        if(size==data.length){
//            throw new RuntimeException("array is full!");
//        }

        if(index<0||index>size){
            throw new RuntimeException("index is not 0<index<size");
        }

        if(size==data.length){
            resize(2*data.length);
        }
        for(int i=size-1;i>=index;i--){
            data[i+1]=data[i];
        }
        data[index] = ele;
        size++;

    }

    private void resize(int newCapacity) {
        E[] newdata = (E[]) new Object[newCapacity];
        for(int i=0;i<size;i++){
            newdata[i] = data[i];
        }

        data = newdata;
    }

    // 获取index索引位置的元素
    public E get(int index){
        if(index<0||index>=size){
            throw  new RuntimeException("index is illegal");
        }
        return data[index];
    }
    // 修改index索引位置的元素为e
    public void set(int index,E ele){
        if(index<0||index>=size){
            throw  new RuntimeException("index is illegal");
        }
        data[index] = ele;
    }

    // 查找数组中是否有元素e
    public boolean contains(E ele){
        for(int i=0;i<size;i++){
            if(data[i].equals(ele)) return true;
        }
        return  false;
    }
    // 查找数组中元素e所在的索引，如果不存在元素e，则返回-1
    public int find(E e){
       for(int i=0;i<size;i++){
           if(data[i].equals(e)) return i;
       }
       return -1;
    }

    // 从数组中删除index位置的元素, 返回删除的元素
    public E remove(int index){
        if(index<0||index>=size){
            throw  new RuntimeException("index is illegal");
        }
        E ret = data[index];
        for(int i=index+1;i<size;i++){
            data[i-1]=data[i];
        }
        size--;
        data[size] = null;

        //判断是否需要缩容
        if(size==data.length/4&&data.length/2!=0){
            resize(data.length/2);
        }
        return ret;
    }
    // 从数组中删除第一个元素, 返回删除的元素
    public E removeFirst(){
        return remove(0);
    }

    // 从数组中删除最后一个元素, 返回删除的元素
    public E removeLast(){
        return remove(size - 1);
    }

    //从数组中删除元素ele
    public void removeEle(E ele){
        int index = find(ele);
        if(index!=-1) remove(index);
    }



    @Override
    public String toString() {
        return Arrays.toString(data);
    }

    public E getLast() {
        return get(size-1);
    }

    public E getFirst(){
        return get(0);
    }
}
