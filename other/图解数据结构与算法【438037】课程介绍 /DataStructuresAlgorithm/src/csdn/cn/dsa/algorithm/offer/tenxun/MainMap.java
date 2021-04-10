package csdn.cn.dsa.algorithm.offer.tenxun;

import java.util.HashMap;

public class MainMap {
    public static void main(String[] args) {
        MainMap mainMap = new MainMap();
        int[] gifts = new int[]{1,2,3,2,2};
        int value = mainMap.getValue(gifts, 5);
        System.out.println(value);


    }

    public int getValue(int[] gifts,int n){

        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<n;i++){
            int gift = gifts[i];
            int count = 0;
            if(map.containsKey(gift)){
                count = map.get(gift)+1;
                map.put(gift,count);
            }else {
                count = 1;
                map.put(gift,count);
            }
            if(count>n/2){
                return gift;
            }
        }
        return  0;

    }
}
