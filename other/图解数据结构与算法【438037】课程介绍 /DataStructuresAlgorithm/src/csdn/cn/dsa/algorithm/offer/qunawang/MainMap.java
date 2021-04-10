package csdn.cn.dsa.algorithm.offer.qunawang;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MainMap {
    public static void main(String[] args) {
        MainMap mainMap = new MainMap();
        String[] strArry = mainMap.chkBlood("O", "A");
        System.out.println(Arrays.toString(strArry));
    }
    public String[] chkBlood(String father, String mother) {
        Map<String,String[]> map = new HashMap<>();
        map.put("OO",new String[]{"O"});
        map.put("AO",new String[]{"A","O"});
        map.put("AA",new String[]{"A","O"});
        map.put("AB", new String[] { "A", "AB", "B", "O" });
        map.put("AAB", new String[] { "A", "AB", "B" });
        map.put("BO", new String[] { "B", "O" });
        map.put("BB", new String[] { "B", "O" });
        map.put("BAB", new String[] { "A", "AB", "B" });
        map.put("ABO", new String[] { "A", "B" });
        map.put("ABO", new String[] { "A", "B" });
        map.put("ABAB", new String[] { "A", "AB", "B" });

        if(map.containsKey(father+mother)){
            return map.get(father+mother);
        }else {
            return map.get(mother+father);
        }
    }
}
