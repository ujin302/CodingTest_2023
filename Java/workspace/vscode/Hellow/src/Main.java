import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // 시험장 개수
        // ... int 범위 생각해보기... 데이터 저장할 때 타입 고려하기...!
        long result = n; // 총감독관 각 시험장에 한명 씩 배정
        int[] a = new int[n]; // 각 시험장별 응시자 수

        // 각 시험장별 응시자 수 설정
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        int b = scanner.nextInt(); // 총감독관 한 시험장에서 감시할 수 있는 응시자 수
        int c = scanner.nextInt(); // 부감독관 한 시험장에서 감시할 수 있는 응시자 수

        // 시험장별 총감독관은 오직 1명 부감독관은 1명 이상
        for (int i = 0; i < n; i++) {
            a[i] -= b; // 총감독관이 감시하는 응시자 제외하기
            if (a[i] > 0) {
                result += a[i] / c;
                if (a[i] % c != 0) {
                    result++;
                }
            }
        }
        System.out.println(result);
    }
}