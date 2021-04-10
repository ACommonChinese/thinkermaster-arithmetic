package csdn.cn.dsa.heap;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int  n=1000000;
        MaxHeap<Integer> max = new MaxHeap<>();
        Random random = new Random();
        for(int i=0;i<n;i++){
            max.add(random.nextInt(Integer.MAX_VALUE)); //上浮操作
        }

        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i]=max.extractMax();//下沉的操作
        }

        //业务判断
        for(int i=1;i<n;i++){
            if(arr[i-1]<arr[i]){
                throw new RuntimeException("Error");
            }
        }
        System.out.println("yes");
    }
}
