package csdn.cn.dsa.algorithm;

import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.HashSet;

public class Greedy {

    static HashMap<String,HashSet<String>> broadcasts = new HashMap<>();

    static HashSet<String> allAreas = new HashSet<String>();

    public static void main(String[] args) {

        //将各个电台放入到broadcasts
        HashSet<String> hashSet1 = new HashSet<String>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet<String>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");

        HashSet<String> hashSet3 = new HashSet<String>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");


        HashSet<String> hashSet4 = new HashSet<String>();
        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5 = new HashSet<String>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        //加入到map
        broadcasts.put("K1", hashSet1);
        broadcasts.put("K2", hashSet2);
        broadcasts.put("K3", hashSet3);
        broadcasts.put("K4", hashSet4);
        broadcasts.put("K5", hashSet5);

        allAreas.addAll(hashSet1);
        allAreas.addAll(hashSet2);
        allAreas.addAll(hashSet3);
        allAreas.addAll(hashSet4);
        allAreas.addAll(hashSet5);

        //选择的电台
        ArrayList<String> selects = new ArrayList<>();
        while (allAreas.size()!=0){
            String maxKey = null;
            for(String key:broadcasts.keySet()){
                int currSize = getRetainSize(key); //求电台的覆盖区域和所有的还没有被覆盖的区域的交集
                int maxKeySize = getRetainSize(maxKey);

               if(currSize>0&&(maxKey==null || currSize>maxKeySize)){
                   maxKey = key;
               }
            }

            if(maxKey !=null){
                selects.add(maxKey);
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }

        System.out.println("选择结果是：："+selects);

    }

    private static int getRetainSize(String key) {
        if(key==null) return 0;
        HashSet<String> tempSet = new HashSet<>();
        tempSet.addAll(broadcasts.get(key));
        tempSet.retainAll(allAreas);
        return tempSet.size();
    }
}
