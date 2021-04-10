package csdn.cn.dsa.hash;

import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        int a = 100;
        System.out.println(((Integer) a).hashCode());

        int b = -99;
        System.out.println(((Integer)b).hashCode());

        Double c = 3.14;
        System.out.println(c.hashCode());

        String d = "samuel";
        System.out.println(d.hashCode());

        Student student = new Student("samuel",20,true,"bj");
        System.out.println(student.hashCode());

        Student student2 = new Student("samuel",20,true,"bj");
        System.out.println(student2.hashCode());

        //hashset hashMap
        HashSet hashSet = new HashSet();
        hashSet.add(student);
        hashSet.add(student2);
        System.out.println(hashSet.size());


    }
}
