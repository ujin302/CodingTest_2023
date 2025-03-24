package BackJun;

import java.util.Arrays;
import java.util.Scanner;

public class Silver2 {
    public void s1912() {
        // 1912. 연속합
        // 이 중 연속된 몇 개의 수를 선택해서 구할 수 있는 합 중 가장 큰 합을 구하려고 한다. (1개 이상 선택)
        // 예를 들어서 10, -4, 3, 1, 5, 6, -35, 12, 21, -1 이라는 수열이 주어졌다고 하자.
        // 여기서 정답은 12+21인 33이 정답이 된다.

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        for (int i = 1; i < n; i++) {
            arr[i] += (arr[i - 1] + arr[i] < arr[i] ? 0 : arr[i - 1]);
        }

        Arrays.sort(arr);
        System.out.println(arr[n - 1]);
    }

    public static void main(String[] args) {

    }
}
