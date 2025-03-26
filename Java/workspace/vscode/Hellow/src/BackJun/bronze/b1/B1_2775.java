package BackJun.bronze.b1;

import java.util.Scanner;

// '2775. 부녀회장이 될테야' 에서 필요함수.
public class B1_2775 {

    private static int[][] aptFill(int[][] apt, int downk, int n) {
        if (apt[downk][n] == 0) {
            apt = aptFill(apt, downk - 1, n);
        }

        for (int i = 2; i <= n; i++) {
            if (apt[downk + 1][i] == 0) {
                for (int j = 1; j <= i; j++) {
                    apt[downk + 1][i] += apt[downk][j];
                }
            }
        }

        return apt;
    }

    public static void main(String[] args) {
        // 2775. 부녀회장이 될테야
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        int[][] apt = new int[15][15];
        for (int i = 0; i < 15; i++) {
            apt[0][i] = i;
            apt[i][1] = 1;
        }

        for (int i = 0; i < tc; i++) {
            int k = sc.nextInt(); // 층
            int n = sc.nextInt(); // 호

            if (apt[k][n] == 0) {
                apt = aptFill(apt, k - 1, n);
            }

            System.out.println(apt[k][n]);
        }
    }
}
