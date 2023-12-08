import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 학생 30명 중에 28명만 과제 제출
        // 과제 제출하지 않은 학생 2명의 학번은?

        // int[] Submit = new int[28]; // 제출한 28명 학생
        String[] sutdent = new String[30]; // 30명 학생 확인
        String output = "";
        for (int i = 0; i < 28; i++) {
            int sutdentNum = Integer.parseInt(sc.nextLine());
            sutdent[sutdentNum - 1] = "O";
        }
        for (int i = 0; i < 30; i++) {
            if (sutdent[i] != "O") {
                output += (i + 1) + "\n";
            }
        }
        System.out.println(output);
    }
}