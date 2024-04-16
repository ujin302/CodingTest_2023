import java.util.*;

public class programmersLeve3 {
    public int s7(int[][] jobs) {
        System.out.println("디스크컨트롤러");
        int jobsindex = 0; // jobs 배열의 인덱스
        int count = 0; // 수행된 요청 개수
        int total = 0; // 총 소요시간
        int end = 0; // 수행되고난 직후의 시간
        // 1. 오름차순 정렬
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]); // 요청 시간 오름차순 (0번째)
        PriorityQueue<int[]> jobTimeQueue = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]); // 처리 시간 오름차순 (1번째)

        while (count < jobs.length) { // 모든 요청 작업 수행

            // 2. 현재 작업이 완료되는 시점까지 들어온 모든 요청을 큐에 저장
            while (jobsindex < jobs.length && jobs[jobsindex][0] <= end) {
                // 현재 인덱스 < 배열의 크기 && 요청 시간 <= 현재 시간
                jobTimeQueue.add(jobs[jobsindex++]);
            }

            // 3. 큐 초기화
            if (jobTimeQueue.isEmpty()) {
                end = jobs[jobsindex][0];
            } else {
                int[] temp = jobTimeQueue.poll();
                total += temp[1] + end - temp[0]; // 현재 작업 소요시간 더하기
                end += temp[1]; // 현재 작업 완료되는 시점 설정
                count++; // 횟수 +1
            }
        }

        return (int) total / jobs.length;
    }

    public long s6(int n, int[] times) {
        System.out.println("입국심사");
        // 1. 변수 설정
        long answer = 0;
        Arrays.sort(times);
        long start = (long) times[0]; // 최소 시간
        long end = (long) times[times.length - 1] * (long) n; // 최대 시간

        // 2. 최소시간 구하기
        while (start <= end) {
            // 2-1. 중간 시간 구하기
            long min = (start + end) / 2; // 중간 시간
            long person = 0; // min 동안 입국심사 완료한 사람

            // 2-2. min 동안 입국심사 완료한 인원 구하기
            for (int i : times) {
                person += min / i;
            }

            // 2-3. 비교 부분 찾기
            // 심사완료 인원 <= 심사해야할 인원
            if (person >= n) {
                // 1) 앞쪽(작은쪽) 부분 비교
                /*
                 * 모든 인원을 완료함..
                 * >> min보다 더 적은 시간을 사용해야할 경우가 있는지 확인
                 * >> start ~ min - 1 부분 확인 필요
                 * 
                 * < 해당 부분에서 min을 저장하는 이유 >
                 * person == n 과 같은 상황일 때 최소 시간을 구하여야 함.
                 * 따라서 작은쪽과 비교할 경우에 min의 값을 저장하여 최소 시간 구함
                 */

                answer = min;
                end = answer - 1;
            } else {
                // 2) 뒤쪽(큰쪽) 부분 비교
                /*
                 * 모든 인원을 완료하지 못함..
                 * >> min보다 더 많은 시간을 사용해야 함
                 * >> min + 1 ~ end 부분 확인 필요
                 */
                start = min + 1;
            }

        }
        return answer;
    }

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
            // 3-2. 3-1의 조건을 만족하면서 이전에 선택했던 아이템의 개수(size)가 항상 크거나 같도록
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
        int[] times = { 10, 7 };
        // int[][] jobs = { { 0, 3 }, { 1, 9 }, { 2, 6 } };
        int[][] jobs = { { 4, 5 }, { 4, 6 }, { 30, 2 }, { 100, 1 } };
        System.out.println(pro.s7(jobs));
        System.out.println(st.s7(jobs));
    }
}
