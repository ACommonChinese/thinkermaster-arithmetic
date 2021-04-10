package csdn.cn.dsa.heap;

import java.util.ArrayList;
import java.util.Collections;

import static java.util.Collections.swap;

public class MaxHeap<E extends Comparable<E>> {

    private ArrayList<E> data;
    public MaxHeap(){
        data = new ArrayList<>();
    }
    public MaxHeap(int capacity){
        data = new ArrayList<>(capacity);
    }

    public MaxHeap(ArrayList<E> arrayList){

        int index  = parent(arrayList.size()-1);
        for(int i=index;i>=0;i--){
         siftDown(i);
        }

        data = arrayList;
    }

    //返回堆中数组元素的个数
    public int size(){
        return data.size();
    }
    //判断堆中是否为空
    public boolean isEmpty(){
        return data.isEmpty();
    }
    /**
     * 得到父节点的索引值
     */
    public int parent(int index){
        if(index<=0){
            throw new RuntimeException("index 值不合法");
        }
        return (index-1)/2;
    }

    //得到左孩子
    public int leftChild(int index){
        return  index*2+1;
    }

    //得到右孩子
    public  int right(int index){
        return index*2+2;
    }

    /**
     * 堆中添加元素
     * 1、添加到数组中
     * 2、siftUP操作
     * @param e
     */
    public void add(E e){
        data.add(e);
        siftUp(data.size()-1);
    }

    private void siftUp(int k) {
        while (k>0&&data.get(parent(k)).compareTo(data.get(k))<0){
            swap(data,k,parent(k));
            k = parent(k);
        }
    }

    //取出堆中最大元素
    public E extractMax(){
        E ret = findMax();
        //交换根和最后一个叶子节点
        swap(data,0,data.size()-1);
        //删除交换后的根
        data.remove(data.size()-1);
        //siftdown
        siftDown(0);

        return ret;
    }


    private void siftDown(int k) {
        while (leftChild(k)<data.size()){
           int j = leftChild(k);
           if(j+1<data.size()&&data.get(j+1).compareTo(data.get(j))>0){
//               j = right(k);
               j++;
           }
           if(data.get(k).compareTo(data.get(j))>=0){
               break;
           }
           swap(data,k,j);
           k = j;
        }
    }

    public E findMax() {
        if(data.size()==0){
            throw new RuntimeException("this is a empth heap");
        }
        return data.get(0);
    }

    // 取出堆中的最大元素，并且替换成元素e
    public E replace(E e){
        E max = findMax();
        data.add(0,e);
        siftDown(0);
        return max;
    }
}
