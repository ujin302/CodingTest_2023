import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;

public class programmersLeve2 {
    public int solution(int[][] triangle) {
        int answer = 0;
        // 1. dp 초기화
        int[][] dp = new int[triangle.length][triangle.length];
        dp[0][0] = triangle[0][0];

        // 2. 동적 계획법
        for (int i = 1; i < triangle.length; i++) {
            for (int j = i; j < triangle.length; j++) {

            }
        }

        return answer;
    }

    // public static int s1(int k, int[] tangerine) {
    // System.out.println("귤 고르기");
    // int answer = 0;
    // HashMap<Integer, Integer> mappedSize = new HashMap<>();

    // // Map<귤 크기, 개수> 초기화
    // // getOrDefault 메소드 : 찾는 Key 값이 없을 경우, 두번째 인수값으로 설정됨.
    // for (int i = 0; i < tangerine.length; i++) {
    // mappedSize.put(tangerine[i], mappedSize.getOrDefault(tangerine[i], 0) + 1);
    // // 귤 크기, 0
    // }

    // // // Map<귤 크기, 0> 초기화
    // // for (int i = 0; i < tangerine.length; i++) {
    // // mappedSize.put(tangerine[i], 0); // 귤 크기, 0
    // // }
    // // // Map<귤 크기, 개수>
    // // for (int key : mappedSize.keySet()) {
    // // for (int i = 0; i < tangerine.length; i++) {
    // // if (key == tangerine[i]) {
    // // int temp = mappedSize.get(key) + 1;
    // // mappedSize.put(key, temp);
    // // }
    // // }
    // // }

    // // Value 값들을 ArrayList에 저장
    // ArrayList<Integer> valuArrayList = new ArrayList<>(mappedSize.values());
    // // 내림차순 정렬
    // Collections.sort(valuArrayList, Collections.reverseOrder());
    // // 다른 종류 개수 찾아내기
    // for (int value : valuArrayList) {
    // if (k <= 0) {
    // break;
    // } else
    // answer++;
    // k -= value;
    // }

    // return answer;
    // }

    public static void main(String[] args) {
        int[] tangerine1 = { 1, 3, 2, 5, 4, 5, 2, 3 };
        System.out.println(solution(1, tangerine1));
    }
}