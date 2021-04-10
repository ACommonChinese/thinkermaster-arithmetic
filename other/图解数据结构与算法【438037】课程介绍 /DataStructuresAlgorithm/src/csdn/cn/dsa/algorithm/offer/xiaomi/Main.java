package csdn.cn.dsa.algorithm.offer.xiaomi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        if (n < 3) {
            System.out.println(n);
            return;
        }

        int result = getMethodLoop(n);
        System.out.println(result);
    }

    public  static int getMethodLoop(int n) {

        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else {
            int m1 = n - 1;
            int m2 = n - 2;
            int result = getMethodLoop(m1) + getMethodLoop(m2);
            return result;
        }
    }
}
