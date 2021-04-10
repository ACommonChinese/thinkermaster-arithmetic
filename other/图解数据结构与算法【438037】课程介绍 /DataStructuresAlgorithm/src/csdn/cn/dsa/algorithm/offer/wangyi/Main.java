package csdn.cn.dsa.algorithm.offer.wangyi;

import javax.sound.midi.Soundbank;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()){
            int n = in.nextInt();//怪物的总数
            int a = in.nextInt();//能力值
            int b = 0; //怪物的防御值
            for(int i=0;i<n;i++){
                b = in.nextInt();
                if(a<b){
                    a = a + getGCD(a,b);
                }else {
                    a = a+b;
                }
            }
            System.out.println(a);
        }


    }

    private static int getGCD(int a, int b) {
        int minValue = Math.min(a,b);
        int maxValue = Math.max(a,b);
        int tem;
        while (minValue!=0){
            tem = maxValue%minValue;
            maxValue = minValue;
            minValue = tem;
        }
        return maxValue;
    }
}
