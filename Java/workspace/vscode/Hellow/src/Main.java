class Student {
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

public class Main {
    public static void main(String[] args) {
        Student st = new Student();
        st.name = "st";

        Student st1 = new Student("ujin", 24, 1010);
        System.out.println(st1.name);
    }
}