import java.util.*;

public class Student {
    public int s7(int[][] jobs) {
        // 1. 변수 설정

        int total = 0; // 결과값
        int count = 0; // 주어진 모든 일을 완료했는지
        int index = 0; // 현재 진행중인 일의 번호
        int endTime = 0; // 현재 시점

        // 1-1. 오름차순
        // 가장 작은 수부터 업무 진행 시작을 위해
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]); // 0번째 오름차순
        PriorityQueue<int[]> jobsPriorityQueue = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]); // 1번째 오름차순
        /*
         * (o1, o2) -> o1[0] - o2[0]
         * 양수일 경우에만 두 원소 자리 변경
         * 
         * 예시 : [[0,1], [1, 3]]
         * 1. (o1, o2) -> o1[0] - o2[0] -> 오름차순
         * ([0,1], [1, 3]) -> 0 - 1
         * => [[0,1], [1, 3]]
         * 
         * 2. (o1, o2) -> o2[0] - o1[0] -> 내림차순
         * ([0,1], [1, 3]) -> 1 - 0
         * => [[1, 3], [0,1]]
         */

        // 2. 업무 진행
        while (count < jobs.length) {

            // 2-1. 현재 작업이 완료되는 시점까지 들어온 모든 요청을 큐에 저장
            while (index < jobs.length && jobs[index][0] <= endTime) {
                // 1. 현재 인덱스번호가 jobs 배열에 해당하는지
                // 2. 현재 인덱스 업무의 대기시간이 다 지났는지

                // 요청시간이 끝난 업무를 큐에 저장
                jobsPriorityQueue.add(jobs[index]);
                index++;
            }

            // 2-2. 요청시간이 끝난 업무의 수행시간 확인하기
            if (jobsPriorityQueue.isEmpty()) {
                // 요청시간이 끝난 업무가 없을 경우
                endTime = jobs[index][0]; // 현재 인덱스의 대기시간 값으로 설정
            } else {
                // 요청시간이 끝난 업무가 있을 경우
                int[] temp = jobsPriorityQueue.poll(); // 현재 진행중인 업무
                total += endTime + temp[1] - temp[0]; // 각 업무의 수행시간
                endTime += temp[1];
                count++;
            }

        }

        return (int) total / jobs.length;
    }

    public long solution(int n, int[] times) {
        // 1. 변수 설정
        long answer = 0;
        Arrays.sort(times);
        long start = (long) times[0]; // 최소시간
        long end = (long) times[times.length - 1] * n; // 최대시간

        while (start <= end) {
            long min = (start + end) / 2; // 중간 시간
            long person = 0; // 검사 완료된 인원

            // min시간동안 검사완료 인원 구하기
            for (int i : times) {
                person += min / i;
            }

            // 큰쪽? 작은쪽? 비교 대상 확인
            if (person >= n) {
                // 작은쪽
                answer = min;
                // 최소 시간을 구하기에 이 경우에만 min 값 저장
                end = min - 1;
            } else {
                // 큰쪽
                start = min + 1;
            }
        }
        return answer;
    }

    public int[] s(String[] gems) {
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

    public static void main(String[] args) {
        Student st = new Student();
        String[] a = { "XYZ", "XYZ", "XYZ" };
        System.out.println(st.s(a));
    }
}