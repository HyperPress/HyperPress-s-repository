package cn.information;
public class Student{
    private String name;
    private String id;
    private String address;
    private int age;
    private String academy;
    public Student() {
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAcademy() {
        return academy;
    }

    public Student(String id,String name,  int age, String address, String academy) {
        this.name = name;
        this.id = id;
        this.address = address;
        this.age = age;
        this.academy = academy;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }
}