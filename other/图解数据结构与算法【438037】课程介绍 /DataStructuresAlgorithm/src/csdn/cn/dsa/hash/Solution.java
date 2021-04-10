package csdn.cn.dsa.hash;

public class Solution {
    public int firstUniqChar(String s) {

        int[] uniqCharArray = new int[26];
        for(int i=0;i<s.length();i++){
            uniqCharArray[s.charAt(i)-'a']++;
        }
        for(int i=0;i<s.length();i++){
            if(uniqCharArray[s.charAt(i)-'a']==1){
                return i;
            }
        }
        return -1;

    }
}
