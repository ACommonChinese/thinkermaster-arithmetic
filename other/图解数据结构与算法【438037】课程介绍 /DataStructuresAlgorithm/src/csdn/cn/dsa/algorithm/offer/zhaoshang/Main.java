package csdn.cn.dsa.algorithm.offer.zhaoshang;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader((new InputStreamReader(System.in)));
        int x = Integer.parseInt(br.readLine());
        if(x < 0){
            x = -x;
        }
        if(x==0){
            System.out.println(x);
            return;
        }

        int[] step = new int[x+1];
        step[0]=0;
        step[1]=1;
        for(int i=2;i<x+1;i++){
            if(i%2==0){
                step[i] = step[i/2]+1;
            }else {
                step[i] = Math.min(step[i -1], step[(i+1)/2]+ 1) + 1;
            }
        }
        System.out.println(step[x]);
    }
}
