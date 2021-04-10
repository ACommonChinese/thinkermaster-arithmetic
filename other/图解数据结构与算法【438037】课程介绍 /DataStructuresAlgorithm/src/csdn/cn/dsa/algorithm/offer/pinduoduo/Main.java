package csdn.cn.dsa.algorithm.offer.pinduoduo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int HP = Integer.parseInt(br.readLine());
        int normalAttack = Integer.parseInt(br.readLine());
        int buffedAttack = Integer.parseInt(br.readLine());
        int res = 0;
        if(normalAttack  >= (buffedAttack + 1) / 2){
            res = HP / normalAttack;
            if(HP % normalAttack != 0) res += 1;
        }else{
            res = HP / buffedAttack * 2;
            int tmp = HP % buffedAttack;
            if(tmp != 0 && tmp <= normalAttack){
                res +=1;
            }else if(tmp>normalAttack){
                res += 2;
            }
        }
        System.out.println(res);
    }
}
