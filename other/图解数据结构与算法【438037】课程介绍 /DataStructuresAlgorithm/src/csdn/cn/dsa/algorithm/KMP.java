package csdn.cn.dsa.algorithm;

import java.lang.reflect.Array;
import java.util.Arrays;

public class KMP {

    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";

        int[] next = kemNext(str2);
        System.out.println("next::"+ Arrays.toString(next));

        int index = kemMatch(str1,str2,next);
        System.out.println("index=="+index);
    }

    private static int kemMatch(String str1, String str2, int[] next) {
        for(int i=0,j=0;i<str1.length();i++){

            while (j>0&&str1.charAt(i)!=str2.charAt(j)){
                j = next[j-1];
            }

            if(str1.charAt(i)==str2.charAt(j)){
                j++;
            }

            if(j == str2.length()){
                return i-j+1;
            }

        }
        return -1;
    }

    //字符串的部分匹配值表
    private static int[] kemNext(String dest) {
        int[] next = new int[dest.length()];
        next[0]=0;
        for(int i=1,j=0;i<dest.length();i++){
            while (j>0&&dest.charAt(i)!=dest.charAt(j)){
                j=next[j-1];
            }
            if(dest.charAt(i)==dest.charAt(j)){
                j++;
            }
            next[i]=j;
        }

        return next;
    }


}
