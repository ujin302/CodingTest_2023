import java.util.*;

public class programmersLeve3 {
    public int[] s5(String[] gems) {
        System.out.println("보석 쇼핑");

        Map<String, Integer> gemCounthMap = new HashMap<>(); // 어피치가 선택한 보석 종류 및 개수
        Queue<String> selectedGemsQueue = new LinkedList<>(); // 어피치가 선택한 보석
        Set<String> gemSet = new HashSet<>(Arrays.asList(gems)); // 보석 종류

        int start = 0;
        int size = gems.length;
        int result = 0;

        for (String gem : gems) {
            // 1. 보석 선택
            selectedGemsQueue.add(gem);
            gemCounthMap.put(gem, gemCounthMap.getOrDefault(gem, 0) + 1);

            // 2. 1번 보석의 개수가 1개가 될때까지 반복
            while (true) {
                // 2-1. 1번 보석 추출
                String firstgem = selectedGemsQueue.peek();

                // 2-2. 1번 보석 개수 == 1
                if (gemCounthMap.get(firstgem) == 1) {
                    break;
                }

                // 2-3. 1번 보석 개수 > 1
                selectedGemsQueue.poll(); // 1번 보석 삭제
                start++; // 1번 보석 삭제로 인해 2번 보석이 1번으로 변경
                gemCounthMap.put(firstgem, gemCounthMap.get(firstgem) - 1); // 보석의 개수 기록
            }

            // 3. 보석 리스트 확인
            // 3-1. 모든 보석의 종류를 가지고 있는지
            // 3-2. 3-1의 조건을 만족하면서 이전에 선택했던 아이템의 개수(size)가 항상 적도록
            if (gemCounthMap.size() == gemSet.size() && size > selectedGemsQueue.size()) {
                result = start;
                size = selectedGemsQueue.size();
            }
        }

        System.out.println("result + 1 : " + (result + 1));
        System.out.println("result + size : " + (result + size));
        return new int[] { result + 1, result + size };
    }

    public int s4(int[] A, int[] B) {
        System.out.println("숫자게임");
        int answer = 0;
        Arrays.sort(A);
        Arrays.sort(B);

        int a = 0;
        int b = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[a] > B[b] || A[a] == B[b]) {
                b++;
            } else {
                b++;
                a++;
                answer++;
            }
        }

        return answer;
    }

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
        // 객체 생성
        // static이 없는 함수를 호출할 경우 객체 생성 후 접근
        programmersLeve3 pro = new programmersLeve3();
        Student st = new Student();
        // String[] a = { "ZZZ", "YYY", "NNNN", "YYY", "BBB" };
        String[] a = { "DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE",
                "DIA" };
        // String[] a = { "AA", "AB", "AC", "AA", "AC" };
        // String[] a = { "XYZ", "XYZ", "XYZ" };
        System.out.println(pro.s6(a));
        System.out.println(st.s(a));
    }
}
