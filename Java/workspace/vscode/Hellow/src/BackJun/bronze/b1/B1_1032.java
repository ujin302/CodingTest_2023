package BackJun.bronze.b1;

import java.util.Scanner;

// 1032.명령 프롬프트
public class B1_1032 {

    public static void main(String[] args) {
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
}
