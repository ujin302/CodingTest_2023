import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String str = br.readLine(); // 숫자 개수 와 비교숫자
        int n = Integer.parseInt(str.split(" ")[0]);
        int num = Integer.parseInt(str.split(" ")[1]);

        int[] arrnum = new int[n];
        String numberStr = br.readLine();

        for (int i = 0; i < n; i++) {
            arrnum[i] = Integer.parseInt(numberStr.split(" ")[i]);
            if (num > arrnum[i]) {
                bw.write(arrnum[i] + " ");
            }
        }

        bw.flush();
        bw.close();
    }
}