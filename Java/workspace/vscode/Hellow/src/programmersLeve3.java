public class programmersLeve3 {
    public long solution(int n, int[] works) {
        long answer = 0;
        return answer;
    }

    public int s1(int[][] triangle) {
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

    public void main(String[] args) {
        int[] work = { 4, 2, 2 };
        System.out.println(solution(1, work));
    }
}
