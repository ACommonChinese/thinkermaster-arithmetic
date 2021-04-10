package csdn.cn.dsa.algorithm.offer.tenxun;

import java.util.Arrays;

public class MianArray {
    public static void main(String[] args) {
        MianArray mainArry = new MianArray();
        int[] gifts = new int[]{1,2,3,2,2};
        int value = mainArry.getValue(gifts, 5);
        System.out.println(value);
    }

    public int getValue(int[] gifts,int n) {

        if(n==0||gifts==null){
            return 0;
        }

        Arrays.sort(gifts);
        int mid = gifts[n/2];
        int count = 0;
        for(int i=0;i<n;i++){
            if(gifts[i]==mid){
                count++;
            }
        }
        if(count>n/2){
            return mid;
        }else {
            return 0;
        }
    }

}
