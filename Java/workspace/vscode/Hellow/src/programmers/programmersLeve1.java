import java.util.HashMap;

// test
public class programmersLeve1 {
    public static int solution(String[] friends, String[] gifts) {
        System.out.println("가장 많이 받은 선물");
        int answer = 0;
        int[][] giftGrap = new int[friends.length][friends.length];
        // 선물 주고 받은 기록 저장
        // int[][] friendInfo = new int[friends.length][4];
        // 0 : 준 선물 1 : 받은 선물 3 : 선물 지수 4 : 다음달 받을 선물
        HashMap<String, Integer> map = new HashMap<>();
        // 친구와 해당 Index 저장

        for (int i = 0; i < friends.length; i++) {
            map.put(friends[i], i);
        }

        for (String temp : gifts) {
            String gift1 = temp.split(" ")[0]; // 선물 준 사람
            String gift2 = temp.split(" ")[1]; // 선물 받은 사람

            // 2차원 배열 설정
            giftGrap[map.get(gift1)][map.get(gift2)]++;
        }

        // for (int i = 0; i < friends.length; i++) {
        // friendInfo[i][0] = 0;
        // friendInfo[i][1] = 0;
        // for (int t = 0; t < gifts.length; t++) {
        // String gift1 = gifts[t].split(" ")[0]; // 선물 준 사람
        // String gift2 = gifts[t].split(" ")[1]; // 선물 받은 사람
        // // 선물 준 개수 설정
        // if (friends[i].equals(gift1)) {
        // friendInfo[i][0]++;
        // }

        // // 선물 받은 개수 설정
        // if (friends[i].equals(gift2)) {
        // friendInfo[i][1]++;
        // }
        // }
        // }

        // // 선물 지수 설정
        // for (int i = 0; i < friends.length; i++) {
        // friendInfo[i][2] = friendInfo[i][0] - friendInfo[i][1];
        // }

        return answer;
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
        // String[] friends = { "a", "b", "c" };
        // String[] gifts = { "a b", "b a", "c a", "a c", "a c", "c a" };

        String[] friends = { "joy", "brad", "alessandro", "conan", "david" };
        String[] gifts = { "alessandro brad", "alessandro joy", "alessandro conan",
                "david alessandro", "alessandro david" };

        solution(friends, gifts);
    }
}
