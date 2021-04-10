package csdn.cn.dsa.algorithm.offer.aiqiyi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for(int i=0;i<t;i++){
            int n = Integer.parseInt(br.readLine());
            if(n%5==0||n%5==2){
                System.out.println("yang");
            }else {
                System.out.println("niu");
            }
        }
    }
}
