import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int index = sc.nextInt();
        int[][] ArrNum = new int[index][2];

        for (int i = 0; i < index; i++) {
            for (int j = 0; j < 2; j++) {
                ArrNum[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < index; i++) {
            System.out.println(ArrNum[i][0] + ArrNum[i][1]);
        }
    }
}