import java.util.*;

public class programmersLeve1 {
    public static int[] solution5(String[] park, String[] routes) {
        System.out.println("공원 산책");
        /*
         * S : 시작
         * O : 이동 가능
         * X : 장애물
         * 
         * N : 위
         * S : 아래
         * E : 앞으로
         * W : 뒤로
         */

        int h = park.length, w = park[0].length();
        int x = 0, y = 0;
        String[][] parkArr = new String[h][w];

        // 1. 공원 2차원배열에 저장
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                parkArr[i][j] = park[i].toCharArray()[j] + "";
                if (parkArr[i][j].equals("S")) {
                    x = i;
                    y = j;
                }
            }
        }

        // 2. 이동
        for (String str : routes) {
            int tempX = x, tempY = y; // 현재 위치
            String move = str.split(" ")[0]; // 방향
            int count = Integer.parseInt(str.split(" ")[1]); // 이동 칸 수

            for (int i = 0; i < count; i++) {
                // 방향과 이동 칸 수 확인
                switch (move) {
                    case "N": // 위로
                        tempX--;
                        break;
                    case "S": // 아래로
                        tempX++;
                        break;
                    case "E": // 앞으로
                        tempY++;
                        break;
                    case "W": // 뒤로
                        tempY--;
                        break;
                }

                if ((tempX >= 0 && tempX < h) && (tempY >= 0 && tempY < w)) {
                    // 범위 안에 있음
                    if (parkArr[tempX][tempY].equals("X")) {
                        // 장애물 만남
                        break;
                    }

                    if (i == count - 1) {
                        x = tempX;
                        y = tempY;
                    }
                }

            }
        }

        return new int[] { x, y };
    }

    public static int[] solution4_1(String[] id_list, String[] report, int k) {
        System.out.println("신고 결과 받기");
        int listLen = id_list.length;
        HashMap<String, Integer> indexMap = new HashMap<String, Integer>(); // 식별번호
        HashMap<String, HashSet<String>> reportMap = new HashMap<String, HashSet<String>>(); // 신고당한 사람, 신고한 사람
        int[] mailCountArr = new int[listLen]; // 메일 전송 개수 저장 배열

        // 1. 사람별 index 설정
        for (int i = 0; i < listLen; i++) {
            indexMap.put(id_list[i], i);
        }

        // 2. 신고 정보 설정
        for (int i = 0; i < report.length; i++) {
            String a = report[i].split(" ")[0]; // 신고한 사람
            String b = report[i].split(" ")[1]; // 신고 당한 사람

            // 신고당한 사람 - 신고한 사람
            reportMap.put(b, reportMap.getOrDefault(b, new HashSet<>())); // 초기화
            reportMap.get(b).add(a);
        }

        // 4. 메일 전송
        for (String stopName : reportMap.keySet()) {
            if (reportMap.get(stopName).size() >= k) { // 중단 계정 선별
                for (String name : reportMap.get(stopName)) {
                    mailCountArr[indexMap.get(name)]++;
                }
            }
        }

        return mailCountArr;
    }

    public static int[] solution4(String[] id_list, String[] report, int k) {
        System.out.println("신고 결과 받기");
        /*
         * 사람별 index -> 식별번호
         * 신고한 명단 배열
         * 신고당한 횟수 배열 -> 정지 계정 구하기
         * 메일 전송 개수 저장 배열
         */

        int listLen = id_list.length;
        HashMap<String, Integer> indexMap = new HashMap<String, Integer>(); // 식별번호
        String[][] reportArr = new String[listLen][report.length]; // 신고한 명단 배열
        int[] getReportArr = new int[listLen]; // 신고당한 횟수 배열
        int[] mailCountArr = new int[listLen]; // 메일 전송 개수 저장 배열
        String[] stopNameArr = new String[listLen]; // 정지 당한 명단 배열

        int num = 0;

        // 1. 사람별 index 설정
        for (int i = 0; i < listLen; i++) {
            indexMap.put(id_list[i], i);
        }

        // 2. 신고당한 횟수 배열 && 신고한 명단 배열
        for (int i = 0; i < report.length; i++) {
            String a = report[i].split(" ")[0]; // 신고한 사람
            String b = report[i].split(" ")[1]; // 신고 당한 사람

            for (int t = 0; t < report.length; t++) {
                boolean isOver = false; // 중복 값
                if (reportArr[indexMap.get(a)][t] == null) {
                    // 중복 확인
                    for (String reNum : reportArr[indexMap.get(a)]) {
                        if (reNum == null)
                            break;

                        if (reNum.equals(b)) {
                            isOver = true; // 중복됨
                        }
                    }

                    if (!isOver) {
                        // 신고한 명단 배열
                        reportArr[indexMap.get(a)][t] = b;
                        // 신고당한 횟수 배열
                        getReportArr[indexMap.get(b)]++;
                        break;
                    }
                }
            }
        }

        // 3. 정지 계정 선별하기
        for (int i = 0; i < listLen; i++) {
            if (getReportArr[i] >= k) {
                stopNameArr[num] = id_list[i];
                num++;
            }
        }

        // 4. 메일 전송
        for (int n = 0; n < num; n++) {
            for (int i = 0; i < listLen; i++) {
                for (int t = 0; t < report.length; t++) {
                    if (reportArr[i][t] == null)
                        break;

                    if (reportArr[i][t].equals(stopNameArr[n])) {
                        mailCountArr[i]++;
                    }
                }
            }
        }

        return mailCountArr;
    }

    public static int solution3(String[] friends, String[] gifts) {
        System.out.println("가장 많이 받은 선물");
        int fCount = friends.length;
        int[][] giftArr = new int[fCount][fCount];
        HashMap<String, Integer> indexMap = new HashMap<String, Integer>();
        int[] giftPoint = new int[fCount];
        int[] result = new int[fCount];

        // 1. 사람별 index 설정
        for (int i = 0; i < fCount; i++) {
            indexMap.put(friends[i], i);
        }

        // 2. 선물 주고받은 개수 설정
        for (int i = 0; i < gifts.length; i++) {
            String a = gifts[i].split(" ")[0]; // 준 사람
            String b = gifts[i].split(" ")[1]; // 받은 사람

            int aIndex = indexMap.get(a);
            int bIndex = indexMap.get(b);

            giftArr[aIndex][bIndex]++;
        }

        // 3. 선물 지수 설정
        for (int i = 0; i < fCount; i++) {
            // i번째가 준 선물 : [i][0~n] 합
            // i번째가 받은 선물 : [0~n][i] 합

            int putSum = 0, getSum = 0;
            for (int j = 0; j < fCount; j++) {
                putSum += giftArr[i][j];
                getSum += giftArr[j][i];
            }
            giftPoint[i] = putSum - getSum;
        }

        // 4. 결과값
        for (int i = 0; i < fCount; i++) {
            /*
             * 1. 주고 받은 기록 O
             * 더 많이 준 사람이 +1
             * 
             * 2. 주고 받은 기록 O && 주고 받은 수 동일
             * 선물지수로 판단
             */
            for (int j = i + 1; j < fCount; j++) {
                if ((giftArr[i][j] != 0 || giftArr[j][i] != 0) && (giftArr[i][j] != giftArr[j][i])) {
                    // 1. 주고받음
                    if (giftArr[i][j] > giftArr[j][i]) {
                        result[i]++;
                    } else if (giftArr[i][j] < giftArr[j][i]) {
                        result[j]++;
                    }
                } else {
                    // 2. 주고받지 않음 >> 선물 지수가 큰 사람이 받음
                    if (giftPoint[i] > giftPoint[j]) {
                        result[i]++;
                    } else if (giftPoint[i] < giftPoint[j]) {
                        result[j]++;
                    }
                }
            }
        }

        Arrays.sort(result);
        return result[fCount - 1];
    }

    public static int[] solution2(String[] name, int[] yearning, String[][] photo) {
        System.out.println("추억의 문제");
        int[] answer = {};
        answer = new int[photo.length];
        // name 사진 속 인물
        // yearing 그리움 점수
        // photo 구해야할 각 사진의 인물들

        for (int i = 0; i < photo.length; i++) {
            for (int j = 0; j < photo[i].length; j++) {
                for (int n = 0; n < name.length; n++) {
                    if (photo[i][j].equals(name[n])) {
                        // System.out.print(name[n]);
                        // System.out.print(yearning[n]);
                        answer[i] += yearning[n];
                        // System.out.print(answer[i]);
                    } else
                        continue;
                }
            }
        }
        return answer;

    }

    // 배열을 이중 For문을 사용하여 구현하였으나 시간초과로 실패
    // Map를 사용하여 For문 한번 사용 & 하나씩 확인하는 것이 아닌 필요한 값을 키를 활용하여 가져오기 때문에 시간 단축
    public static String[] solution1(String[] players, String[] callings) {
        System.out.println("달리기 경주");
        HashMap<String, Integer> mappedByPlayer = new HashMap<>();
        // 초기화
        for (int i = 0; i < players.length; i++) {
            mappedByPlayer.put(players[i], i); // 성명, 순위
        }

        for (int i = 0; i < callings.length; i++) {
            int Rank = mappedByPlayer.get(callings[i]); // 추월 전 선수의 순위 저장
            String frontString = players[Rank - 1]; // 추월당한 선수 이름

            // players 배열 업데이트
            players[Rank - 1] = callings[i];
            players[Rank] = frontString;

            // 변경된 순위 기준으로 수행하여야 하기에 map 순위 업데이트
            mappedByPlayer.put(callings[i], Rank - 1);
            mappedByPlayer.put(frontString, Rank);
        }
        return players;
    }

    public static void main(String[] args) {
        // String[] park = { "SOO", "OOO", "OOO" };
        // String[] routes = { "E 2", "S 2", "W 1" };

        // String[] park = { "SOO", "OXX", "OOO" };
        // String[] routes = { "E 2", "S 2", "W 1" };

        String[] park = { "OSO", "OOO", "OXO", "OOO" };
        String[] routes = { "E 2", "S 3", "W 1" };
        solution5(park, routes);
    }
}
