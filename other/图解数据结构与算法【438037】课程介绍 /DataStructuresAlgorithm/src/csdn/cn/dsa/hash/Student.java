package csdn.cn.dsa.hash;

public class Student {
    private String name;
    private int age;
    private boolean sex;
    private String address;

    public Student(String name, int age, boolean sex, String address) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.address = address;
    }

    public Student(){};

    @Override
    public int hashCode(){

        int B = 666;
        int hash = 0;
        hash = hash*B + name.hashCode();
        hash = hash*B + ((Integer)age).hashCode();
        hash = hash*B + ((Boolean)sex).hashCode();
        hash = hash*B + address.hashCode();

        return hash;

    }

    @Override
    public boolean equals(Object obj) {

        if(this==obj){
          return true;
        }

        if(obj==null||obj.getClass()!=getClass()){
            return false;
        }

        Student stu = (Student) obj;
        return this.name.equals(stu.name)&&
                this.age==stu.age&&
                this.sex==stu.sex&&
                this.address.equals(stu.address);


    }
}
