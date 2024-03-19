import java.util.PriorityQueue;
import java.util.Collections;
import java.util.Arrays;

public class programmersLeve3 {
    public static int[] s3(int n, int s) {
        System.out.println("최고의 집합");
        // * 최고의 집합이 존재하지 않는 경우 -1 반환
        if (n > s)
            return new int[] { -1 };

        // 1. 배열, 나머지 값 선언
        int[] answer = new int[n];
        int remain = s % n;

        // 2. 배열 초기화 & 나머지 처리
        for (int i = 0; i < answer.length; i++) {
            if (remain != 0) {
                answer[i]++;
                remain--;
            }
            answer[i] += s / n;
        }

        // 3. 배열 정렬
        Arrays.sort(answer);

        return answer;

        // if (n > s)
        // return new int[] { -1 };

        // // 1. int 배열 초기화 -> s/n
        // int[] answer = new int[n];
        // for (int i = 0; i < answer.length; i++) {
        // answer[i] = s / n;
        // }

        // // 2. 나머지가 있을 경우 +1
        // for (int i = 0; i < s % n; i++) {
        // answer[i]++;
        // }

        // // 3. 정렬
        // Arrays.sort(answer);

        // return answer;
    }

    public static long s2(int n, int[] works) {
        System.out.println("야근 지수");
        // 큰수를 줄이는게 관건인 문제

        long answer = 0;
        // 1. 우선순위 큐 선언 -> 항상 내림차순으로 정렬할 수 있도록 하기위해 사용
        PriorityQueue<Integer> workQueue = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < works.length; i++) {
            workQueue.offer(works[i]);
        }

        // 2. n시간동안 일처리하기. 가장 큰 일(최댓값)에 대해 -1
        // 2-1. 최댓값0이거나 그보다 작을 경우, 일이 끝났기때문에 0 반환
        for (int i = 0; i < n; i++) {
            int num = workQueue.poll();
            if (num <= 0)
                break;
            else
                workQueue.offer(num - 1);
        }

        // 3. 남은일 제곱
        while (!workQueue.isEmpty()) { // 큐가 비어 있을 때까지
            int work = workQueue.poll();
            answer += Math.pow(work, 2);
        }

        return answer;
    }

    public static int s1(int[][] triangle) {
        System.out.println("정수 삼각형");

        int len = triangle.length;
        // 1. dp 초기화
        int[][] dp = new int[len][len];
        dp[0][0] = triangle[0][0];
        // 1-1. 첫번째 요소 초기화 [i][0]
        for (int i = 1; i < len; i++) {
            dp[i][0] = triangle[i][0] + dp[i - 1][0];
        }

        // 2. 동적 계획법
        // 2-2. 첫번째 요소를 제외한 모든 값 초기화
        for (int i = 1; i < len; i++) {
            for (int j = 1; j < i + 1; j++) {
                dp[i][j] = triangle[i][j] + Math.max(dp[i - 1][j - 1], dp[i - 1][j]);
            }
        }

        // 3. 최댓값 구하기
        int max = 0;
        for (int i = 0; i < len; i++) {
            max = Math.max(dp[len - 1][i], max);
        }

        return max;
    }

    public static void main(String[] args) {
        int[] answer = solution(2, 9);
        for (int i : answer) {
            System.out.println(i);
        }

    }
}
