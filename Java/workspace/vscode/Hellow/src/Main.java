import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine()); // 영수증의 총 금액
        int[][] arrnum = new int[n][2];
        String result = "";

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < 2; k++) {
                arrnum[i][k] = Integer.parseInt(br.readLine());
            }
            result += (arrnum[i][0] + arrnum[i][1] + "\n");
        }

        System.out.println(result.trim());
    }
}