package csdn.cn.dsa.algorithm.offer.qunawang;

import java.util.Arrays;

public class MainSwitch {
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        MainSwitch mainSwitch = new MainSwitch();
        String[] strArry = mainSwitch.chkBlood("A", "AB");
        System.out.println(Arrays.toString(strArry));

    }
    public String[] chkBlood(String father, String mother) {
        String[] result={""};
        switch (father){
            case "A":
                switch (mother){
                    case "A":
                        String[] result1 = {"A","O"};
                        return result1;
                    case "B":
                        String[] result2 = {"A","AB","B","O"};
                        return result2;
                    case "AB":
                        String[] result3 = {"A","AB","B"};
                        return result3;
                    case "O":
                        String[] result4 = {"A","O"};
                        return result4;
                }
             break;
            case "B":
                switch(mother){
                    case "A":
                        String[] result5 = {"A","AB","B","O"};
                        return result5;

                    case "B":
                        String[] result6 = {"B","O"};
                        return result6;

                    case "AB":
                        String[] result7 = {"A","AB","B"};
                        return result7;

                    case "O":
                        String[] result8 = {"B","O"};
                        return result8;

                }

            case "AB":
                switch(mother){
                    case "A":
                        String[] result9 = {"A","AB","B"};
                        return result9;

                    case "B":
                        String[] result10 = {"A","AB","B"};
                        return result10;

                    case "AB":
                        String[] result11 = {"A","AB","B"};
                        return result11;

                    case "O":
                        String[] result12 = {"A","B"};
                        return result12;

                }

            case "O":
                switch(mother) {
                    case "A":
                        String[] result13 = {"A", "O"};
                        return result13;

                    case "B":
                        String[] result14 = {"B", "O"};
                        return result14;

                    case "AB":
                        String[] result15 = {"A", "B"};
                        return result15;

                    case "O":
                        String[] result16 = {"O"};
                        return result16;
                }
        }
        return result;
    }
}
