package csdn.cn.dsa.algorithm.offer.baidu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        char[] charArray = bf.readLine().toCharArray();
        //hashSet 不重复的所有字符
        HashSet<Character> hashSet = new HashSet<>();
        StringBuilder builder = new StringBuilder();
        for(int i=0;i<charArray.length;i++){
            char c = charArray[i];
            if(hashSet.contains(c)){
                continue;
            }else {
                hashSet.add(c);
                builder.append(c);
            }
        }
        System.out.println(builder.toString());
    }
}
