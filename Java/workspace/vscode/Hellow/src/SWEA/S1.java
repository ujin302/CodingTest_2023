import java.util.*;

public class S1 {
    public void m1() {
        System.out.println("1859. 백만 장자 프로젝트");
        // 1. 변수 설정
        Scanner sc = new Scanner(System.in);
        int tc;
        tc = sc.nextInt();

        for (int i = 0; i < tc; i++) {
            int[] num = new int[sc.nextInt()];

            for (int k = num.length - 1; k >= 0; k--) {
                num[k] = sc.nextInt();
            }

            long sum = 0, max = 0;

            for (int j : num) {
                if (max < j) {
                    max = j;
                }

                if (j < max) {
                    sum += max - j;
                } else
                    continue;
            }
            System.out.println("#" + (i + 1) + " " + sum);
        }

    }

    public void m2() {
        System.out.println("1926. 간단한 369게임");

        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        String result = "";

        for (int i = 1; i < num + 1; i++) {

            String str = i + "";
            String tempStr = "";
            int tempCount = 0;
            for (int k = 0; k < str.length(); k++) {
                int tempNum = Character.getNumericValue(str.charAt(k));
                if (tempNum != 0 && tempNum % 3 == 0) {
                    tempStr += "-";
                    tempCount++;
                }
            }
            if (tempStr.contains("-")) {
                if (tempCount == str.length()) {
                    result += tempStr;
                } else
                    result += "-";
            } else {
                result += str;
            }

            result += " ";
        }

        System.out.println(result);
    }

    public void m3() {
        System.out.println("2001. 파리 퇴치 ");
        Scanner sc = new Scanner(System.in);

        int tc = sc.nextInt();

        for (int i = 0; i < tc; i++) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[][] numArr = new int[n][n];

            for (int a = 0; a < n; a++) {
                for (int b = 0; b < n; b++) {
                    numArr[a][b] = sc.nextInt();
                }
            }

            int count = n - m + 1;
            int[] countArr = new int[count * count];
            int index = 0;
            for (int index_c = 0; index_c < count; index_c++) {
                for (int index_k = 0; index_k < count; index_k++) {
                    for (int c = 0; c < m; c++) {
                        for (int k = 0; k < m; k++) {
                            countArr[index] += numArr[c + index_c][k + index_k];
                        }
                    }
                    index++;
                }
            }

            Arrays.sort(countArr);
            System.out.println("#" + (i + 1) + " " + countArr[countArr.length - 1]);
        }
    }

    public void m4() {
        System.out.println("1989. 초심자의 회문 검사");
        Scanner sc = new Scanner(System.in);

        int tc = sc.nextInt();

        for (int t = 0; t < tc; t++) {
            int result = -1;
            String str = sc.next();

            int a = str.length() / 2; // 가운데 Index
            char[] strChar = str.toCharArray();

            for (int i = 0; i < a; i++) {
                if (result != 0) {
                    if (strChar[i] == strChar[str.length() - (i + 1)]) {
                        result = 1;
                    } else
                        result = 0;
                }
            }
            System.out.println("#" + (t + 1) + " " + result);
        }
    }

    public void m_5() {
        System.out.println("다시 풀어보기~ 1979. 어디에 단어가 들어갈 수 있을까");
        Scanner sc = new Scanner(System.in);

        int tc = sc.nextInt();

        for (int t = 0; t < tc; t++) {
            int result = 0;
            int n = sc.nextInt();
            int k = sc.nextInt();
            int[][] puzzle = new int[n][n];

            // 2차배열 초기화
            for (int a = 0; a < n; a++) {
                for (int b = 0; b < n; b++) {
                    puzzle[a][b] = sc.nextInt();
                }
            }

            // 1 => 흰색, 0 => 검은색
            for (int i = 0; i < n; i++) {
                int sumRow = 0, sumColumn = 0;
                for (int j = 0; j < n; j++) {
                    // row
                    if (puzzle[i][j] == 1) {
                        // 흰색
                        sumRow++;

                    }

                    if (puzzle[i][j] == 0 || j == (n - 1)) {
                        // 검정 or 마지막 index
                        if (sumRow == k) {
                            result++;
                        }
                        sumRow = 0;
                    }

                    // column
                    if (puzzle[j][i] == 1) {
                        // 흰색
                        sumColumn++;

                    }

                    if (puzzle[j][i] == 0 || j == (n - 1)) {
                        // 검정 or 마지막 index
                        if (sumColumn == k) {
                            result++;
                        }
                        sumColumn = 0;
                    }
                }
            }
            System.out.println("#" + (t + 1) + " " + result);
        }
    }

    public void m5() {
        System.out.println("1979. 어디에 단어가 들어갈 수 있을까");
        Scanner sc = new Scanner(System.in);

        int tc = sc.nextInt();

        for (int t = 0; t < tc; t++) {
            int result = 0;
            int n = sc.nextInt();
            int k = sc.nextInt();
            int[][] puzzle = new int[n][n];

            // 2차배열 초기화
            for (int a = 0; a < n; a++) {
                for (int b = 0; b < n; b++) {
                    puzzle[a][b] = sc.nextInt();
                }
            }

            for (int a = 0; a < n; a++) {
                int countRow = 0; // 가로
                int countColumn = 0; // 세로

                for (int b = 0; b < n; b++) {
                    // 가로
                    if (puzzle[a][b] == 1) {
                        countRow++;
                    }
                    if (puzzle[a][b] == 0 || b == n - 1) {
                        if (countRow == k) {
                            result++;
                        }
                        countRow = 0;
                    }

                    // 세로
                    if (puzzle[b][a] == 1) {
                        countColumn++;
                    }
                    if (puzzle[b][a] == 0 || b == n - 1) {
                        if (countColumn == k) {
                            result++;
                        }
                        countColumn = 0;
                    }
                }
            }

            System.out.println("#" + (t + 1) + " " + result);
        }
    }

    public void m6() {
        System.out.println("1983. 조교의 성적 매기기");
        Scanner sc = new Scanner(System.in);

        int tc = sc.nextInt();
        String[] str_finalScore = { "A+", "A0", "A-", "B+", "B0", "B-", "c+", "C0", "C-", "D0" }; // 학점

        for (int t = 0; t < tc; t++) {
            int n = sc.nextInt(); // 학생 수
            int k = sc.nextInt(); // 구해야 하는 학생 Index
            int result = -1;
            int[][] score = new int[n][3]; // 중간(35), 기말(45), 과제(20)
            HashMap<Integer, Double> indexFScoreMap = new HashMap<>(); // Key : index, value : 총점

            // 2차배열 초기화
            for (int a = 0; a < n; a++) {
                double finalScore = 0;
                for (int b = 0; b < 3; b++) {
                    score[a][b] = sc.nextInt();
                    switch (b) {
                        case 0: // 중간 (35)
                            finalScore += score[a][b] * 0.35;
                            break;

                        case 1: // 기말 (45)
                            finalScore += score[a][b] * 0.45;
                            break;

                        case 2: // 과제(20)
                            finalScore += score[a][b] * 0.20;
                            break;
                    }
                }
                indexFScoreMap.put(a + 1, finalScore);
            }

            // 내림차순
            List<Integer> keySort = new ArrayList<>(indexFScoreMap.keySet());
            Collections.sort(keySort, (o1, o2) -> (indexFScoreMap.get(o2).compareTo(indexFScoreMap.get(o1))));

            // key의 index (순위) 구하기
            int i = 0;
            for (Integer key : keySort) {
                if (key == k)
                    break;
                i++;
            }
            result = i / (n / 10);
            System.out.println("#" + (t + 1) + " " + str_finalScore[result]);
        }
    }

    public Boolean m_7Boolean(String str) {
        boolean isTrue = true;
        int a = str.length() / 2; // 가운데 Index
        char[] strChar = str.toCharArray();

        for (int i = 0; i < a; i++) {
            if (isTrue) {
                if (strChar[i] != strChar[str.length() - (i + 1)]) {
                    isTrue = false;
                }
            }
        }
        return isTrue;
    }

    public void m7() {
        System.out.println("20019. 회문의 회문");
        /*
         * 조건
         * 1. S는 회문이다.
         * 2. S의 처음 (N-1)/2글자가 회문이다.
         * 3. S의 마지막 (N-1)/2글자가 회문이다.
         */

        Scanner sc = new Scanner(System.in);
        S1 s = new S1();
        int tc = sc.nextInt();

        for (int t = 0; t < tc; t++) {
            String result = "";
            String str = sc.next();
            int a = str.length() / 2; // 가운데 Index

            Boolean[] isTrue = new Boolean[3];

            // 조건 1
            isTrue[0] = s.m_7Boolean(str);

            // 조건 2
            String frontStr = str.substring(0, a);
            isTrue[1] = s.m_7Boolean(frontStr);

            // 조건 3
            String backStr = str.substring(a + 1, str.length());
            isTrue[2] = s.m_7Boolean(backStr);

            if (isTrue[0] && isTrue[1] && isTrue[2])
                result = "YES";
            else
                result = "NO";

            System.out.println("#" + (t + 1) + " " + result);
        }
    }

    public static void main(String args[]) {
        // Solution s = new Solution();
        // s.m_7Boolean();
        S1 s = new S1();
        s.m7();
    }
}
