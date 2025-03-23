package BackJun;

import java.util.*;

public class bronze1 {
    public void s1032() {
        // 1032.명령 프롬프트
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        String str = sc.next();
        if (n == 1) {
            System.out.println(str);
            return;
        }

        char[][] arr = new char[n][str.length()];
        for (int i = 0; i < n; i++) {
            arr[i] = str.toCharArray();
            if (i < n - 1)
                str = sc.next();
        }

        for (int t = 0; t < arr[0].length; t++) {
            for (int i = 1; i < n; i++) {
                if (arr[0][t] != arr[i][t]) {
                    System.out.print("?");
                    break;
                }

                if (i == n - 1)
                    System.out.print(arr[0][t]);
            }
        }
    }

    private static int[][] aptFill(int[][] apt, int downk, int n) {
        // '2775. 부녀회장이 될테야' 에서 필요함수.
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

    public void s2775() {
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

    public static void main(String[] args) {

    }
}