import java.util.*;

public class programmersLeve2 {

    public static int s1(int k, int[] tangerine) {
        System.out.println("귤 고르기");
        int answer = 0;
        HashMap<Integer, Integer> mappedSize = new HashMap<>();

        // Map<귤 크기, 개수> 초기화
        // getOrDefault 메소드 : 찾는 Key 값이 없을 경우, 두번째 인수값으로 설정됨.
        for (int i = 0; i < tangerine.length; i++) {
            mappedSize.put(tangerine[i], mappedSize.getOrDefault(tangerine[i], 0) + 1);
            // 귤 크기, 0
        }

        // // Map<귤 크기, 0> 초기화
        // for (int i = 0; i < tangerine.length; i++) {
        // mappedSize.put(tangerine[i], 0); // 귤 크기, 0
        // }
        // // Map<귤 크기, 개수>
        // for (int key : mappedSize.keySet()) {
        // for (int i = 0; i < tangerine.length; i++) {
        // if (key == tangerine[i]) {
        // int temp = mappedSize.get(key) + 1;
        // mappedSize.put(key, temp);
        // }
        // }
        // }

        // Value 값들을 ArrayList에 저장
        ArrayList<Integer> valuArrayList = new ArrayList<>(mappedSize.values());
        // 내림차순 정렬
        Collections.sort(valuArrayList, Collections.reverseOrder());
        // 다른 종류 개수 찾아내기
        for (int value : valuArrayList) {
            if (k <= 0) {
                break;
            } else
                answer++;
            k -= value;
        }

        return answer;
    }

    public int toSec(String time) {
        int h = Integer.parseInt(time.split(":")[0]) * 60;
        int m = Integer.parseInt(time.split(":")[1]);

        return h + m;
    }

    public String[] s2_2(String[][] plans) {
        System.out.println("나 혼자 풀이2~ 과제 진행하기");

        /*
         * 1. 시간은 분단휘 기준!
         * 2. 시작 시간대로 오름차순 정렬
         * 3. 과제 중단되는 경우
         * >> 현재과제 시작시간 + paly 시간 > 다음과제 시작 시간 && 마지막 과제 아님
         * 3-1. 과제 중단 O
         * -> 중단된 과제 stack에 저장 (name, 남은 시간)
         * -> 다음 과제 시작
         * -> 현재 시간 = 다음과제 시작 시간
         * 3-2. 과제 중단 X
         * -> answer에 저장
         * -> 다음 과제 시작
         * 
         * 4. 다음과제 시작까지 시간이 남은 경우
         * >> 현재과제 시작시간 + paly 시간 < 다음과제 시작 시간 && 마지막 과제 아님
         * 다음과제까지 남는 시간 파악
         * stack에서 과제 하나 가져와서 남는 시간동안 실행
         * 4-1. 2개 이상의 과제 수행할 상황 판단 필요
         * -> 과제 하나 끝날때마다 answer 과제에 저장
         * 4-2. 1개를 완료하지 못했을 경우 stack에 다시 저장
         * 
         * 5. 주어진 plan이 끝남 && stack에 중단된 과제 남음
         * 중단되 과제 순서대로 완료하여 answer에 저장
         */

        // 1. 변수 설정
        int len = plans.length; // paln 길이
        String[] answer = new String[len]; // 완료된 과제
        Stack<String[]> stopStack = new Stack<>(); // 중단된 과제
        Arrays.sort(plans, (o1, o2) -> toSec(o1[1]) - toSec(o2[1])); // 오름차순

        // 2. 현재 과제
        String name = ""; // 현재 과제
        int startTime = 0; // 현재 과제 시작 시간
        int playTime = 0; // 현재 과제 수행 시간
        int index = 0; // 현재 과제 번호

        // 3. 시간 변수
        int currentTime = 0; // 현재 시간 (현재 과제 끝나는 시간)

        // 4. 다음 과제
        int nextStart = 0; // 다음 과제 시작 시간
        int count = 0; // 완료된 과제 개수

        // 5. 과제 진행
        while (len > index) {
            // 1. 현재 과제 설정
            name = plans[index][0];
            startTime = toSec(plans[index][1]);
            playTime = Integer.parseInt(plans[index][2]);
            currentTime = startTime + playTime;

            // 2. 과제 중단 O (3-1)
            if (index + 1 < len) { // 마지막 과제 아님
                nextStart = toSec(plans[index + 1][1]); // 다음과제 시작 시간
                if (currentTime > nextStart) { // 현재과제 시작시간 + paly 시간 > 다음과제 시작 시간

                    // 2-1. 중단된 과제 저장
                    int remain = playTime - (nextStart - startTime); // 남은 시간
                    stopStack.push(new String[] { name, remain + "" });
                    currentTime = nextStart;
                    // 2-2. 과제 중단으로 인해 다음과제 진행
                    index++;
                    continue;
                }
            }

            // 3. 과제 중단 X (3-2)
            answer[count] = name; // 과제 완료
            count++; // 완료된 과제 개수
            index++; // 다음 과제 설정

            // 4. 다음 과제까지 남는 시간 (4)
            while (!stopStack.empty()) { // 중단된 과제 존재
                String[] stopStrArr = stopStack.pop();
                int remain_time = Integer.parseInt(stopStrArr[1]); // 중단된 과제 남은 시간

                if (currentTime + remain_time <= nextStart) {
                    // 중단된 과제 남은 수행시간 <= 다음과제까지 남은 시간

                    // 주어진 시간 동안 완료 (4-1)
                    answer[count] = stopStrArr[0];
                    count++;
                    currentTime += remain_time;
                } else {
                    // 다시 중단 (4-2)
                    stopStrArr[1] = remain_time - (nextStart - currentTime) + "";
                    stopStack.push(stopStrArr); // 다시 중단됨
                    break;
                }
            }
        }

        // 주어진 plan이 끝남 && stack에 중단된 과제 남음 (5)
        while (!stopStack.empty()) { // 중단된 과제 존재
            String[] stopStrArr = stopStack.pop();
            answer[count] = stopStrArr[0];
            count++;
        }

        return answer;
    }

    public String[] s2_1(String[][] plans) {
        System.out.println("나 혼자 풀이1~ 과제 진행하기");
        /*
         * 시간은 분단위로...!!!
         * 1. 시작시간 기준 오름차순 정렬 [i][1]
         * 2. 과제 진행 중, 새로운 과제가 시작하는지 확인
         * 3. 중단된 과제는 스택에 저장 -> name, 남은 playtime
         * 4. 새로운 과제가 없을 경우, 스택에서 과제 가져옴
         */

        // 1. 오름차순 정렬
        Arrays.sort(plans, (o1, o2) -> toSec(o1[1]) - toSec(o2[1]));

        // 2. 변수 설정
        int len = plans.length;

        // 2-1. 현재 과제
        int index = 0; // 현재 과제 index
        String name = ""; // 현재 과제 이름
        int startTime = 0; // 과제 시작 시간
        int playTime = 0; // 과제 진행 시간
        int currentTime = 0; // 현재 시간

        // 2-2. 다음 과제
        int nextStart = 0; // 다음과제 start time

        // 2-3. 중단된 과제
        Stack<String[]> stopStack = new Stack<>(); // name, 남은 시간

        // 2-4. 완료된 과제
        String[] answer = new String[len]; // 완료된 과제
        int finishCount = 0; // 완료된 과제 수

        // 3. 과제 시작
        while (index < len) {
            // 1. 과제에 대한 변수
            name = plans[index][0];
            startTime = toSec(plans[index][1]);
            playTime = Integer.parseInt(plans[index][2]);
            currentTime = startTime + playTime;

            // 2. 현재 과제 중단여부
            if (len - 1 > index) { // 마지막 과제는 중단될 이유가 없기에
                nextStart = toSec(plans[index + 1][1]);
                // 다음과제 시작 시간 < 현재 과제 종료 시간
                if (nextStart < startTime + playTime) {
                    // 중단된 과제 설정
                    int remainTime = playTime - (nextStart - startTime); // 남은 시간
                    stopStack.push(new String[] { name, remainTime + "" }); // 중단된 과제

                    // 다음 과제
                    index++;
                    currentTime = nextStart; // 현재 시간 설정
                    continue;
                }
            }

            // 3. 완료된 과제
            // 위의 조건문에 해당하지 않을 경우 현재 업무 완료되었음을 의미함.
            answer[finishCount] = name;
            finishCount++;

            // 4. 새로운 과제? 중단된 과제?
            while (!stopStack.empty()) {
                String[] stop = stopStack.pop();
                if (Integer.parseInt(stop[1]) <= nextStart - currentTime) {
                    // 4-1. 멈춘 과제 완료
                    // 해당 과제 남은 시간 <= 다음과제까지 남은 시간
                    answer[finishCount] = stop[0];
                    finishCount++;
                    currentTime += Integer.parseInt(stop[1]);
                } else {
                    // 4-2. 멈춘 과제 또 멈춤
                    stop[1] = Integer.parseInt(stop[1]) - (nextStart - currentTime) + "";
                    stopStack.push(stop);
                    currentTime = nextStart;
                    break;
                }
            }
            index++; // 다음과제 진행을 위해
        }

        // 4. 새롭게 시작될 과제가 없으며 중단된 과제가 남아 있을 경우
        while (!stopStack.empty()) {
            String[] stop = stopStack.pop();
            answer[finishCount] = stop[0];
            finishCount++;
        }

        return answer;
    }

    public String[] s2(String[][] plans) {
        System.out.println("과제 진행하기");
        /*
         * 1. 시작시간 기준 오름차순 정렬 [i][1]
         * 2. 과제 진행 중, 새로운 과제가 시작하는지 확인
         * 3. 중단된 과제는 스택에 저장 -> name, playtime
         * 4. 새로운 과제가 없을 경우, 스택에서 과제 가져옴
         */

        int size = plans.length;
        // 1. 시작시간 기준 오름차순 정렬
        Arrays.sort(plans, (a, b) -> toSec(a[1]) - (toSec(b[1])));

        String[] answer = new String[size]; // 완료된 과제
        Stack<String[]> stop = new Stack<>(); // 중단된 과제

        // 2. 과제 수행
        int z = 0; // 완료된 과제 index
        int idx = 0; // 완료된 index
        int currentTime = 0; // 현재 시간
        String name = "";
        int starttime = 0; // 현재 과제 start time
        int playtime = 0;
        int nextStart = 0; // 다음과제 start time
        while (size != idx) {

            // 1.현재 과제에 필요한 정보 설정
            name = plans[idx][0]; // 과제이름
            starttime = toSec(plans[idx][1]); // 과제 start 시간
            playtime = Integer.parseInt(plans[idx][2]); // 과제 play 시간
            currentTime = starttime + playtime;

            // 2. 현재 과제 진행 중, 새로운 과제가 시작하는지 체크
            if (size - 1 != idx) {
                nextStart = toSec(plans[idx + 1][1]); // 다음과제 start 시간
                if (currentTime > nextStart) {
                    // 종료예정 시간 > 다음과제 시작 시간
                    stop.push(new String[] { name, currentTime - nextStart + "" }); // 이름, 남은 시간 저장
                    currentTime = nextStart; // 종료시간(현재시간) 초기화
                    idx++; // 새로운과제 index
                    continue;
                }
            }

            // 지금 과제 완료
            answer[z++] = name;

            // 3. 다음과제까지 남은시간동안 멈춘 과제를 할수있을까?
            while (!stop.isEmpty()) { // 중단된 과제 없음 X
                // 3-1. 다음과제 세팅
                int remain = nextStart - currentTime; // 다음과제까지 남은 시간
                String[] work = stop.pop(); // 가장 최근에 멈춰있는 과제
                int re_p = Integer.parseInt(work[1]); // 남은 시간

                // 3-2. 멈춘과제 진행
                if (remain >= re_p) { // 과제 완료
                    answer[z++] = work[0];
                    currentTime += re_p;
                } else {
                    // 과제 또 멈춤
                    stop.push(new String[] { work[0], re_p - remain + "" });
                    break;
                }
            }
            idx++;
        }

        // 모든 과제를 탐색했다. 이젠 남은 멈춘과제 마저하자
        while (!stop.isEmpty()) {
            answer[z++] = stop.pop()[0];
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println("Lv.2");
        programmersLeve2 p2 = new programmersLeve2();
        // String[][] strArr = { { "korean", "11:40", "30" }, { "english", "12:10", "20"
        // }, { "math", "12:30", "40" } };
        // String[][] strArr = { { "science", "12:40", "50" }, { "music", "12:20", "40"
        // }, { "history", "14:00", "30" },
        // { "computer", "12:30", "100" }, { "a", "14:40", "10" } };
        // String[][] strArr = { { "korean", "11:40", "20" }, { "english", "12:10", "30"
        // }, { "math", "12:30", "40" } };
        // String[][] strArr = { { "a", "09:00", "30" }, { "b", "09:10", "20" }, { "c",
        // "09:15", "20" },
        // { "d", "09:55", "10" }, { "e", "10:50", "5" } };

        String[][] strArr = { { "a", "09:00", "30" }, { "b", "09:20", "10" }, { "c", "09:40", "10" } };

        p2.s2_2(strArr);
    }
}