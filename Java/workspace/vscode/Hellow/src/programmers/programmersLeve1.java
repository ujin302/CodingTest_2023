import java.util.*;

// test
public class programmersLeve1 {
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

        // 1. 사람별 index 설정
        for (int i = 0; i < listLen; i++) {
            indexMap.put(id_list[i], i);
        }

        // 2. 신고당한 횟수 배열 && 신고한 명단 배열
        for (int i = 0; i < report.length; i++) {
            String a = report[i].split(" ")[0]; // 신고한 사람
            String b = report[i].split(" ")[1]; // 신고 당한 사람

            // 1) 신고당한 횟수 배열
            getReportArr[indexMap.get(b)]++;

            // 2) 신고한 명단 배열 (중복 X)
            for (int t = 0; t < report.length; t++) {
                if (reportArr[indexMap.get(a)][t] == null) {
                    reportArr[indexMap.get(a)][t] = b;
                    break;
                }
            }
        }

        // 3. 정지 계정 선별하기
        int num = 0;
        for (int i = 0; i < listLen; i++) {
            if (getReportArr[i] >= k) {
                stopNameArr[num] = id_list[i];
                num++;
            }
        }

        // 4. 메일 전송
        for (int i = 0; i < listLen; i++) {
            for (int t = 0; t < report.length; t++) {
                if (reportArr[i][t] != null) {
                    for (String name : stopNameArr) {
                        if (reportArr[i][t].equals(name)) {
                            mailCountArr[i]++;
                        }
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
        String[] id_list = { "muzi", "frodo", "apeach", "neo" };
        String[] report = { "muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi" };
        int k = 2;

        solution4(id_list, report, k);
    }
}
