package Constructor;

public class Student {
    String name; // 학생 이름
    int age; // 학생 나이
    int number; // 학생 학번

    public Student() {
    }

    public Student(String name, int age, int number) {
        this.name = name;
        this.age = age;
        this.number = number;
    }

}