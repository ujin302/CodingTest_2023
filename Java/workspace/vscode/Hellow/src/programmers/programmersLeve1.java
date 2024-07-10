import java.text.*;
import java.util.*;

public class programmersLeve1 {
    public static String solution8(String[] survey, int[] choices) {
        System.out.println("성격 유형 검사하기");
        /*
         * 1번 지표 라이언형(R), 튜브형(T)
         * 2번 지표 콘형(C), 프로도형(F)
         * 3번 지표 제이지형(J), 무지형(M)
         * 4번 지표 어피치형(A), 네오형(N)
         * 
         * 1 매우 비동의 >> 3
         * 2 비동의 >> 2
         * 3 약간 비동의 >> 1
         * 4 모르겠음 >> 0
         * 5 약간 동의 >> 1
         * 6 동의 >> 2
         * 7 매우 동의 >> 3
         * 
         */
        String answer = "";
        int len = survey.length;
        int[] scoreArr = { 3, 2, 1, 0, 1, 2, 3 };
        String[] surveyArr = { "R", "T", "C", "F", "J", "M", "A", "N" };
        HashMap<String, Integer> resultMap = new HashMap<>();

        // 점수 계산
        for (int i = 0; i < len; i++) {
            // 질문 유형 확인
            String s1 = survey[i].charAt(0) + "";
            String s2 = survey[i].charAt(1) + "";
            int score = scoreArr[choices[i] - 1]; // 대답에 따른 점수

            // 점수 저장
            if (choices[i] < 4) {
                // s1에 점수 ++
                resultMap.put(s1, resultMap.getOrDefault(s1, 0) + score);
            } else {
                // s2에 점수 ++
                resultMap.put(s2, resultMap.getOrDefault(s2, 0) + score);
            }
        }

        // 성격 4가지 유형 확인
        for (int i = 0; i < surveyArr.length; i += 2) {
            int s1 = resultMap.getOrDefault(surveyArr[i], 0);
            int s2 = resultMap.getOrDefault(surveyArr[i + 1], 0);

            if (s1 >= s2) {
                // 앞의 값이 크거나 같을 경우
                // 같이 동일하면 사전순으로 더 빠른 값으로 지정함.
                answer += surveyArr[i];
            } else {
                // 뒤의 값이 클 경우
                answer += surveyArr[i + 1];
            }
        }

        return answer;
    }

    public static Date getDate(String strDate, int term) throws ParseException {
        int[] time = new int[strDate.split("\\.").length]; // yyyy, MM, dd
        String str = "";

        for (int i = 0; i < time.length; i++) {
            time[i] = Integer.parseInt(strDate.split("\\.")[i]);
        }

        // 달(month)가 1년을 넘은 경우
        time[1] += term;
        if (time[1] > 12) {
            time[0]++;
            time[1] -= 12;
        }

        if (time[2] == 1) {
            time[2] = 28;
            time[1]--;
            if (time[1] == 0)
                time[0]--;
        } else
            time[2]--;

        for (int i = 0; i < time.length; i++) {
            str += time[i] + "";
            if (i != 2)
                str += ".";
        }

        return new Date(new SimpleDateFormat("yyyy.MM.dd").parse(str).getTime());
    }

    public static int[] solution7(String today, String[] terms, String[] privacies) throws ParseException {
        System.out.println("개인정보 수입 유효기간");
        /*
         * 예를 들어, A라는 약관의 유효기간이 12 달이고,
         * 2021년 1월 5일에 수집된 개인정보가 A약관으로 수집되었다면
         * 해당 개인정보는 2022년 1월 4일까지 보관 가능하며
         * 2022년 1월 5일부터 파기해야 할 개인정보입니다.
         * 
         * today : 오늘 날짜를 의미하는 문자열
         * terms : 약관의 유효기간을 담은 1차원 문자열 배열
         * privacies : 수집된 개인정보의 정보를 담은 1차원
         * >> privacies[i] = "yyyy.MM.dd 약관"
         * 
         * 이때 파기해야 할 개인정보의 번호를 오름차순으로 1차원 정수 배열에 담아
         * return 하도록 solution 함수를 완성해 주세요.
         */

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        Date toDate = new Date(dateFormat.parse(today).getTime());
        ArrayList<Integer> resultList = new ArrayList<>();
        HashMap<String, Integer> termsMap = new HashMap<>();

        // 약관 별 유효기간 저장
        for (String t : terms) {
            termsMap.put(t.split(" ")[0], Integer.parseInt(t.split(" ")[1]));
        }

        for (int i = 0; i < privacies.length; i++) {
            Date date = getDate(privacies[i].split(" ")[0], termsMap.get(privacies[i].split(" ")[1]));

            // 유효기간 지남
            if (date.before(toDate)) {
                resultList.add(i + 1);
            }
        }

        int[] answer = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            answer[i] = resultList.get(i);
        }

        return answer;
    }

    public static int[][] solution6_1(int[][] data, String ext, int val_ext, String sort_by) {
        System.out.println("데이터 분석");
        // 다른 사람 풀이 참고하여 다시 품

        int[][] answer = {};
        List<String> valeList = Arrays.asList(new String[] { "code", "date", "maximum", "remain" });
        ArrayList<int[]> resultList = new ArrayList<>();
        final int sortIndex = valeList.indexOf(sort_by);

        for (int[] d : data) {
            if (d[valeList.indexOf(ext)] < val_ext) {
                resultList.add(d);
            }
        }

        answer = resultList.toArray(new int[resultList.size()][data[0].length]);
        Arrays.sort(answer, (o1, o2) -> o1[sortIndex] - o2[sortIndex]);

        return answer;
    }

    public static int[][] solution6(int[][] data, String ext, int val_ext, String sort_by) {
        System.out.println("데이터 분석");
        /*
         * data : 정렬한 데이터들이 담긴 이차원 정수 리스트
         * data[i] : 코드 번호(code), 제조일(date), 최대 수량(maximum), 현재 수량(remain)
         * data[i][1] : 제조일은 yyyyMMdd로 표현함.
         * ext : 어떤 정보를 기준으로 데이터를 뽑아낼지를 의미하는 문자열
         * val_ext : 뽑아낼 정보의 기준값을 나타내는 정수
         * sort_by : 정보를 정렬할 기준이 되는 문자열
         * 
         * data에서 ext 값이 val_ext보다 작은 데이터만 뽑은 후,
         * sort_by에 해당하는 값을 기준으로 오름차순으로 정렬하여
         * return 하도록 solution 함수를 완성해 주세요.
         */
        int[][] answer = new int[data.length][data[0].length];
        int indexExt = 0;
        int count = 0;

        switch (ext) {
            case "code":
                indexExt = 0;
                break;
            case "date":
                indexExt = 1;
                break;
            case "maximum":
                indexExt = 2;
                break;
            case "remain":
                indexExt = 3;
                break;
        }

        for (int i = 0; i < data.length; i++) {
            if (data[i][indexExt] < val_ext) {
                answer[count] = data[i];
                count++;
            }
        }
        answer = Arrays.copyOf(answer, count);

        // 정렬
        switch (sort_by) {
            case "code":
                Arrays.sort(answer, (o1, o2) -> o1[0] - o2[0]);
                break;
            case "date":
                Arrays.sort(answer, (o1, o2) -> o1[1] - o2[1]);
                break;
            case "maximum":
                Arrays.sort(answer, (o1, o2) -> o1[2] - o2[2]);
                break;
            case "remain":
                Arrays.sort(answer, (o1, o2) -> o1[3] - o2[3]);
                break;
        }

        return answer;
    }

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

        // 2. 신고 정보 저장
        for (int i = 0; i < report.length; i++) {
            String a = report[i].split(" ")[0]; // 신고한 사람
            String b = report[i].split(" ")[1]; // 신고 당한 사람

            // 신고당한 사람 - 신고한 사람
            reportMap.put(b, reportMap.getOrDefault(b, new HashSet<>())); // 초기화
            reportMap.get(b).add(a);
        }

        // 3. 메일 전송 대상 선별
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

    public static void main(String[] args) throws ParseException {
        // String[] survey = { "AN", "CF", "MJ", "RT", "NA" };
        // int[] choices = { 5, 3, 2, 7, 5 };

        String[] survey = { "AN" };
        int[] choices = { 7 };

        solution8(survey, choices);
    }
}
