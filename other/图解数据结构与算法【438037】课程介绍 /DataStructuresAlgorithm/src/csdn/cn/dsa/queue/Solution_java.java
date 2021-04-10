package csdn.cn.dsa.queue;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution_java {

    private static class Freq implements Comparable<Freq>{
        int e,freq;

      public Freq(int e,int freq){
          this.e=e;
          this.freq=freq;
      }

        @Override
        public int compareTo(Freq o) {
            return o.freq-this.freq;
    }
    }

    public static void main(String[] args) {
         int[] nums={1,1,1,2,2,3};
         int k = 2;
         topKEle(nums,k);
    }

    /**
     * 1、统计数组中每个元素出现的频率
     * 2、将元素和出现的频率加工成一个对象放入到优先队列中
     * 3、获取优先队列中的元素并输出
     * @param nums
     * @param k
     */
    private static void topKEle(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int num:nums){
           if(map.containsKey(num)){
               map.put(num,map.get(num)+1);
           } else {
               map.put(num,1);
           }
        }

        //优先队列
        PriorityQueue<Freq> pq = new PriorityQueue<Freq>();
        for(int key:map.keySet()){
            if(pq.size()<k){
                pq.add(new Freq(key,map.get(key)));
            }else if(map.get(key)>pq.peek().freq){
                pq.remove();
                pq.add(new Freq(key,map.get(key)));
            }
        }

        //输出
        ArrayList<Object> list = new ArrayList<>();
        while (!pq.isEmpty()){
            list.add(pq.remove().e);
        }
        System.out.println(list);


    }
}
