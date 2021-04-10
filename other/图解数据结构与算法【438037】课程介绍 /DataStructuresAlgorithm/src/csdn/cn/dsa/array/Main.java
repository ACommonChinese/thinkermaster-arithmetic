package csdn.cn.dsa.array;

public class Main {
    public static void main(String[] args) {
        Array array = new Array(5);
        array.addLast("str1"); //int--Integer
        array.addLast(28);
        array.addLast(39);
        array.addLast(65);
        array.add(1,100);
        System.out.println(array);

        array.remove(1);
        System.out.println(array);

        System.out.println(array.contains("str1"));

        System.out.println(array.find(39));

        array.addLast(new Person());
        System.out.println(array);

        array.addLast(8999);
        array.addLast(8999);
        array.addLast(8999);
        System.out.println(array);

        array.removeLast();
        array.removeLast();
        array.removeLast();
        array.removeLast();
        array.removeLast();

        System.out.println(array);


    }
}

class Person{

}
