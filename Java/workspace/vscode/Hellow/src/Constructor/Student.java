import java.util.*;

public class Student {
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