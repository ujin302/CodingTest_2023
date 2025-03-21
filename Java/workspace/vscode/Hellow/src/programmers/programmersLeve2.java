import java.util.*;

public class programmersLeve2 {

    public int s1(int k, int[] tangerine) {
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

    public int s3_1(String[][] book_time) {
        System.out.println("다시 풀기1_호텔 대실");
        /*
         * 1. 시간은 분단위
         * 2. String 배열 : 체크인 시간 기준 오름차순 정렬
         * 3. 우선순위 큐 : 체크아웃 시간+10(청소) 기준 오름차순 정렬
         * >> 우선순위 큐에는 현재 방을 사용중인 예약에 대한 정보를 가지고 있음
         * 즉, 큐의 크기는 방의 개수를 의미함.
         * 
         * 4. 현재 예약 체크아웃 시간(방을 사용하고 있는 예약) <= 다음 예약 체크인 시간 (현재 방을 사용하고 있지 않은 예약)
         * >> 해당 경우, 체크아웃 시간이 되었기에 큐에서 현재 예약 제거
         * >> 그 후, 다음 예약이 방금 체크아웃한 방을 사용하기에 큐에 저장
         * 
         * 5. 4번의 경우가 아닐 경우 그냥 큐에 저장
         * >> 새로운 방 부여
         */

        // 1. 체크인 시간 기준 오름차순 정렬
        Arrays.sort(book_time, (o1, o2) -> toSec(o1[0]) - toSec(o2[0]));

        // 2. 우선순위 큐 >> 체크아웃 시간 기준으로 오름차순 정렬
        // >> 현재 방을 사용하고 있는 예약 저장
        PriorityQueue<int[]> book_CheckOut = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

        for (String[] bookStr : book_time) {
            int[] book = new int[2];
            book[0] = toSec(bookStr[0]); // 체크인
            book[1] = toSec(bookStr[1]) + 10; // 체크아웃

            if (!book_CheckOut.isEmpty()) { // 현재 방을 사용중인 예약 존재
                if (book_CheckOut.peek()[1] <= book[0]) {
                    // 현재 사용중인 방의 체크아웃 시간 <= 새로운 에약의 체크인 시간
                    book_CheckOut.poll(); // 체크아웃 시간으로 간주하여 큐에서 제거
                }
            }
            // 새로운 예약 큐에 저장
            book_CheckOut.add(book);
        }

        return book_CheckOut.size();
    }

    public int s3(String[][] book_time) {
        System.out.println("호텔 대실");

        Arrays.sort(book_time, (o1, o2) -> toSec(o1[0]) - toSec(o2[0])); // 체크인 시간 오름차순 정렬
        PriorityQueue<int[]> book_Queue = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]); // 체크아웃 시간 오름차순

        for (String[] bookStr : book_time) {
            int[] book = new int[2];
            book[0] = toSec(bookStr[0]); // 체크인
            book[1] = toSec(bookStr[1]) + 10; // 체크아웃

            if (!book_Queue.isEmpty()) { // 현재 사용중인 방 존재 확인
                int[] temp = book_Queue.peek();

                // 이전 대실 진행 사항 확인
                if (book[0] >= temp[1]) { // 대실 완료
                    /*
                     * 현재 예약 시작 시간 >= 이전 예약 종료 시간
                     * >> 이전 예약 종료되어 해당 방을 사용할 수 있음
                     * >> 따라서 Queue에서 이전 예약을 삭제함.
                     * >> Queue는 현재 사용중인 방에 대한 체크인/체크아웃의 정보만 가지고 있음.
                     */
                    book_Queue.poll();
                }
            }
            book_Queue.add(book); // 현재 예약 저장
        }

        return book_Queue.size();
    }

    public int s4(String[] want, int[] number, String[] discount) {
        System.out.println("할인 행사");
        int answer = 0;

        int wantLen = want.length; // 항목 개수
        int discountLen = discount.length; // 닐짜별 세일하는 항목
        int dis_Idx = 0; // discount 인덱스 번호 & 날짜

        while (discountLen - dis_Idx >= 10) {
            // 1. 남은 날짜가 10일을 넘은 경우에만 실행
            Boolean isNotCorrect = false; // 원하는 제품을 모두 할인 받을 수 없으면 true

            // 2. 10일 간 할인 품목 설정
            HashMap<String, Integer> tenDayMap = new HashMap<>(); // 10일 간 할인하는 항목과 개수
            for (int i = 0; i < 10; i++) {
                String key = discount[dis_Idx + i];
                tenDayMap.put(key, tenDayMap.getOrDefault(key, 0) + 1);
            }

            // 3. 할인 개수 < 구매하고 싶은 개수
            for (int i = 0; i < wantLen; i++) {
                int getMap = tenDayMap.getOrDefault(want[i], 0);
                if (getMap < number[i]) {
                    isNotCorrect = true;
                    break;
                }
            }

            // 4. 원하는 제품을 모두 할인 확인
            if (!isNotCorrect) {
                answer++;
            }
            dis_Idx++;
        }

        return answer;

    }

    public int s5(int[][] targets) {
        System.out.println("요격 시스템");
        int answer = 0;
        int time = 0; // 현재 시간

        // 미사일 발사 시작, 끝 시간 오름차순 정렬
        PriorityQueue<int[]> bombStartQueue = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        PriorityQueue<int[]> bombEndQueue = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

        for (int[] t : targets) {
            bombStartQueue.add(t);
        }

        while (!bombStartQueue.isEmpty()) {
            int[] currentBomb = bombStartQueue.poll();
            if (time < currentBomb[0]) {
                // 현재시간 < 미사일 시작 시간
                time = currentBomb[0];
                bombStartQueue.add(currentBomb);
            } else {
                bombEndQueue.add(currentBomb);
            }
        }
        return answer;
    }

    public int s6(int[] priorities, int location) {
        System.out.println("프로세스");
        int answer = 0;
        int proCount = priorities.length; // 프로세스 개수수
        Queue<String> proQueue = new LinkedList<>(); // 실행 대기 큐
        for (int i = 0; i < proCount; i++) {
            proQueue.add(i + "P");
        }

        // 최고 우선순위
        int maxPriority = Arrays.stream(priorities).max().orElse(0);

        while (true) {
            String currentProcess = proQueue.poll(); // 현재 프로세스
            int currentIndex = Integer.parseInt(currentProcess.replace("P", "")); // 현재 프로세스 인덱스 번호
            int currentPrioiry = priorities[currentIndex]; // 현재 프로세스 우선순위

            if (currentPrioiry < maxPriority) {
                // 다시 큐에 저장
                proQueue.add(currentProcess);
                System.out.println(proQueue);

            } else {
                answer++; // 실행한 프로세스 개수
                if (currentProcess.equals(location + "P"))
                    return answer;

                // 최고 우선순위 재설정
                if (currentPrioiry == maxPriority) {
                    priorities[currentIndex] = 0;
                    maxPriority = Arrays.stream(priorities).max().orElse(0);
                    Arrays.stream(priorities).min().orElse(0);
                }
            }
        }
    }

    public int solution(int[] scoville, int K) {
        System.out.println("더 맵게");
        int answer = 0;
        return answer;
    }

    public int s7(int[] players, int m, int k) {
        System.out.println("");
        int answer = 0;
        int serverCount = 0; // 서버 개수
        Queue<int[]> timeQueue = new LinkedList<int[]>(); // 삭제할 시간, 삭제할 개수

        for (int i = 0; i < players.length; i++) {
            System.out.println("i = " + i + " & server count = " + serverCount);
            // 서버 삭제 개수 설정
            if (timeQueue.size() > 0 && timeQueue.peek()[0] == i) {
                serverCount -= timeQueue.peek()[1];
                timeQueue.poll();
            }

            // 서버 증설해야 하는 경우
            int c = players[i] / m; // 필요한 서버 수
            if (players[i] >= m && c > serverCount) {
                answer += (c -= serverCount);
                serverCount += c;
                System.out.println(">> c = " + c + " & i+k = " + (i + k));
                timeQueue.add(new int[] { i + k, c });
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println("Lv.2");
        programmersLeve2 p2 = new programmersLeve2();

        int[] targets = {
                1, 1, 9, 1, 1, 1
        };

        int[] players = { 0, 2, 3, 3, 1, 2, 0, 0, 0, 0, 4, 2, 0, 6, 0, 4, 2, 13, 3, 5, 10, 0, 1, 5 };
        p2.s7(players, 3, 5);

        System.out.println(p2.s6(targets, 0));
    }
}

public class Main {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();

        char[][] arr;
        for(int i=0; i<n; i++) {
            String str = sc.next();
            if(i == 0) {
                arr = new char[n][str.length()];
            }
            char[] arr1 = str.toCharArray();
        }
        
        for(int t=0; t<arr[0].length; t++) {
            boolean isBreak = false;
            for(int i=1; i<n; i++) {
                //for(int j=i; j<n; j++) {
                    if(arr[0][t] != arr[i][t]) {
                        System.out.print("?");
                        isBreak = true;
                        break;
                    }
                    
                    if(i == n-1) System.out.print(arr[0][t]);
                //}
                //if(isBreak) break;
            }
            if(isBreak) break;
        }
        
    }
}