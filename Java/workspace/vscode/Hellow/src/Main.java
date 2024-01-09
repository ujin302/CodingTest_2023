import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // 시험장 개수
        int[] coachNum = new int[n]; // 필요한 감독관 수
        int result = 0;
        int[] a = new int[n]; // 각 시험장별 응시자 수

        // 각 시험장별 응시자 수 설정
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        int b = scanner.nextInt(); // 총감독관 한 시험장에서 감시할 수 있는 응시자 수
        int c = scanner.nextInt(); // 부감독관 한 시험장에서 감시할 수 있는 응시자 수

        // 시험장별 총감독관은 오직 1명 부감독관은 1명 이상
        for (int i = 0; i < n; i++) {
            if (a[i] == 1) {
                // 총감독관만 필요한 경우
                coachNum[i] = 1;
            } else {
                coachNum[i]++; // 총감독관

                // 부감독관이 감시해야할 응시자 수
                int studentNum = a[i] - b;
                if (studentNum % c == 0) {
                    coachNum[i] += studentNum / c;
                } else if (studentNum % c > 0) {
                    coachNum[i] += studentNum / c + 1;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            // System.out.println(1 + i + " : " + coachNum[i]);
            result += coachNum[i];
        }

        System.out.println(result);
    }
}