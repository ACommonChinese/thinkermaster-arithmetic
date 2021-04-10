package csdn.cn.dsa.one;

import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListTest {
    public static void main(String[] args) {
        addArrayList();
        addLinkedList();


    }

    private static void addLinkedList() {

        long begin = System.currentTimeMillis();
        List list = new LinkedList();
        for(int i=0;i<100000000;i++){//10000000
            list.add(i);
        }
        System.out.println("linkedList time=="+(System.currentTimeMillis()-begin)+"ms");
    }

    private static void addArrayList(){
        long begin = System.currentTimeMillis();
        List list = new ArrayList();
        for(int i=0;i<100000000;i++){
            list.add(i);
        }
        System.out.println("arrayList time=="+(System.currentTimeMillis()-begin)+"ms");
    }
}
