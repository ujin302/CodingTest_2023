import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.println("백준 2525 오븐시계");
        // Scanner sc = new Scanner(System.in);
        // int h = sc.nextInt();
        // int m = sc.nextInt();
        // int time = sc.nextInt();

        // m += time;
        // if (m >= 60) {
        // h += m / 60;
        // m -= 60 * (m / 60);
        // if (h >= 24) {
        // h -= 24;
        // }
        // }
        // System.out.println(h + " " + m);

        System.out.println("백준 2480 주사위 세개");
        // 같은 눈 3개 > 10000 + a *1000
        // 같은 눈 2개 > 1000 + a *100
        // 모두 다른 눈 > 가장 큰거 * 100
        // Scanner sc = new Scanner(System.in);
        // int a = sc.nextInt();
        // int b = sc.nextInt();
        // int c = sc.nextInt();
        // int result = 0;
        // if (a == b && b == c && c == a) {
        // result = 10000 + a * 1000;
        // } else if (a != b && b != c && c != a) {
        // int max = a;
        // if (a > b) {
        // if (c > a) {
        // max = c;
        // }else {max = a;}
        // } else if (b > c) {
        // if (a > b) {
        // max = a;
        // }else {max = b;}
        // } else if (c > a) {
        // if (b > c) {
        // max = b;
        // }else {max = c;}
        // }
        // result = max * 100;
        // } else {
        // int num = 0;
        // if (a == b && a != c) {
        // num = a;
        // } else if (a == c && a != b) {
        // num = a;
        // } else if (b == c && b != a) {
        // num = b;
        // }
        // result = 1000 + num * 100;
        // }
        // System.out.println(result);

        System.out.println("백준 10950 A+B-3");
        // Scanner sc = new Scanner(System.in);
        // int index = sc.nextInt();
        // int[][] ArrNum = new int[index][2];

        // for (int i = 0; i < index; i++) {
        // for (int j = 0; j < 2; j++) {
        // ArrNum[i][j] = sc.nextInt();
        // }
        // }

        // for (int i = 0; i < index; i++) {
        // System.out.println(ArrNum[i][0] + ArrNum[i][1]);
        // }

        System.out.println("백준 15552 빠른 A+B ");
        // import java.io.BufferedReader;
        // import java.io.BufferedWriter;
        // import java.io.IOException;
        // import java.io.InputStreamReader;
        // import java.io.OutputStreamWriter;
        // import java.util.StringTokenizer;

        // public class Main {
        // public static void main(String[] args) throws IOException {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // int n = Integer.parseInt(br.readLine()); // 영수증의 총 금액
        // String st;

        // for (int i = 0; i < n; i++) {
        // st = br.readLine();
        // int sum = 0;
        // int a = Integer.parseInt(st.split(" ")[0]);
        // int b = Integer.parseInt(st.split(" ")[1]);

        // bw.write(a + b + "\n"); // 버퍼에 작성
        // }

        // bw.flush(); // 출력
        // bw.close();
        // }
        // }
        System.out.println("백준 2439 별 찍기 - 2");
        // import java.io.BufferedReader;
        // import java.io.BufferedWriter;
        // import java.io.IOException;
        // import java.io.InputStreamReader;
        // import java.io.OutputStreamWriter;

        // public class Main {
        // public static void main(String[] args) throws IOException {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // int n = Integer.parseInt(br.readLine()); // 영수증의 총 금액

        // for (int i = 0; i < n; i++) {
        // String st = "";
        // for (int k = 0; k < n - i - 1; k++) {
        // st += " ";
        // }
        // for (int t = 0; t < i + 1; t++) {
        // st += "*";
        // }
        // bw.write(st + "\n"); // 버퍼에 작성
        // }

        // bw.flush(); // 출력
        // bw.close();
        // }
        // }
        System.out.println("백준 10951 A+B-4");
        // import java.io.BufferedReader;
        // import java.io.BufferedWriter;
        // import java.io.IOException;
        // import java.io.InputStreamReader;
        // import java.io.OutputStreamWriter;

        // public class Main {
        // public static void main(String[] args) throws IOException {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // while (true) {
        // String str = br.readLine();
        // int a = Integer.parseInt(str.split(" ")[0]);
        // int b = Integer.parseInt(str.split(" ")[1]);
        // if (a == 0 & b == 0) {
        // break;
        // } else {
        // int sum = a + b;
        // bw.write(sum + "\n");
        // }

        // }
        // bw.flush();
        // bw.close();
        // }
        // }
        System.out.println("백준 10807 개수 세기");
        // import java.io.BufferedReader;
        // import java.io.BufferedWriter;
        // import java.io.IOException;
        // import java.io.InputStreamReader;
        // import java.io.OutputStreamWriter;

        // public class Main {
        // public static void main(String[] args) throws IOException {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // int n = Integer.parseInt(br.readLine()); // 숫자 갯수
        // String str = br.readLine(); // 숫자 입력 받기
        // int num = Integer.parseInt(br.readLine()); // 찾아야 하는 숫자
        // int result = 0; // 숫자 개수

        // for (int i = 0; i < n; i++) {
        // if (num == Integer.parseInt(str.split(" ")[i])) {
        // result++;
        // }
        // }

        // System.out.println(result);
        // }
        // }
    }
}
