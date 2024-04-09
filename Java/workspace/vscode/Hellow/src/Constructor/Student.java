import java.util.*;

public class Student {
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