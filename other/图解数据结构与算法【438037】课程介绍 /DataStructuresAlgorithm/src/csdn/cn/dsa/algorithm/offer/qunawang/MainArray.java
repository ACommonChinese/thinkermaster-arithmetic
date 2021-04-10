package csdn.cn.dsa.algorithm.offer.qunawang;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MainArray {
    public static void main(String[] args) {
        MainArray mainArry = new MainArray();
        String[] strArry = mainArry.chkBlood("O", "A");
        System.out.println(Arrays.toString(strArry));
    }
    public String[] chkBlood(String father, String mother) {
        String[] blodArray= new String[]{"O", "A", "B", "AB"};

        int fatherIndex=0;
        int motherIndex=0;
        for(int i=0;i<4;i++){
            if(father.equals(blodArray[i])){
                fatherIndex = i;
            }
            if(mother.equals(blodArray[i])){
                motherIndex = i;
            }
        }

        int sum = fatherIndex+motherIndex;

        switch (sum){
            case 0:// OO
                return new String[]{"O"};
            case 1: //OA AO
                return new String[]{"A","O"};
            case 2:
                return fatherIndex == motherIndex ? new String[] {"A", "O"} : new String[] {"B", "O"};
            case 3:
                return (fatherIndex == 0 || motherIndex == 0) ? new String[] {"A", "B"} : new String[] {"A", "AB", "B", "O"};
            case 4:
                return fatherIndex == motherIndex ? new String[] {"B", "O"} : new String[] {"A", "AB", "B"};
            default:
                return new String[] {"A", "AB", "B"};

        }


    }
}
