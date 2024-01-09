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
    }
}