import java.util.Scanner;

public class bootcampprepare {
    public static void main(String[] args) {
        System.out.println("백준 문제집 SSAFY 기출 문제");
        System.out.println(3 % 20);

        System.out.println("백준 14501 퇴사");
        // import java.util.Scanner;

        // public class Main {
        // public static void main(String[] args) {
        // // 백준 14501 중도 포기
        // // dp 라는걸 알아야 한다구 함... 나는 몰랑...

        // Scanner scanner = new Scanner(System.in);
        // // n일 동안 근무 n+1 퇴사
        // int n = scanner.nextInt(); // 퇴사전까지 남은 근무 기간
        // int[][] TPwork = new int[n][2]; // [n][0] == 상담 기간 , [n][1] == 상담별 수익
        // int[] result = new int[n];

        // // 기본 설정
        // for (int i = 0; i < n; i++) {
        // TPwork[i][0] = scanner.nextInt(); // 상담 기간
        // TPwork[i][1] = scanner.nextInt(); // 상담별 수익
        // }

        // for (int i = 0; i < n; i++) {
        // for (int t = i; t < n - i; t++) {
        // if (TPwork[t][0] <= n - t) {
        // // 상담 기간 <= 남은 근무일수
        // // 해당 조건에 해당할 경우에만 상담 진행
        // result[i] += TPwork[t][1];
        // t = t + TPwork[t][0] - 1; // 상담 시작 날로부터 상담기간까지
        // }
        // }
        // }

        // for (int i = 0; i < n; i++) {
        // System.out.println((i + 1) + "번 : " + result[i]);
        // }
        // }
        // }

        System.out.println("백준 13458 시험 감독");
        // import java.util.Scanner;

        // public class Main {
        // public static void main(String[] args) {
        // Scanner scanner = new Scanner(System.in);
        // int n = scanner.nextInt(); // 시험장 개수
        // // ... int 범위 생각해보기... 데이터 저장할 때 타입 고려하기...!
        // long result = n; // 총감독관 각 시험장에 한명 씩 배정
        // int[] a = new int[n]; // 각 시험장별 응시자 수

        // // 각 시험장별 응시자 수 설정
        // for (int i = 0; i < n; i++) {
        // a[i] = scanner.nextInt();
        // }

        // int b = scanner.nextInt(); // 총감독관 한 시험장에서 감시할 수 있는 응시자 수
        // int c = scanner.nextInt(); // 부감독관 한 시험장에서 감시할 수 있는 응시자 수

        // // 시험장별 총감독관은 오직 1명 부감독관은 1명 이상
        // for (int i = 0; i < n; i++) {
        // a[i] -= b; // 총감독관이 감시하는 응시자 제외하기
        // if (a[i] > 0) {
        // result += a[i] / c;
        // if (a[i] % c != 0) {
        // result++;
        // }
        // }
        // }
        // System.out.println(result);
        // }
        // }
    }
}