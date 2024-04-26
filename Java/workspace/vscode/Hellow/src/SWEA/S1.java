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

    public static void main(String args[]) {
        S1 s = new S1();
        s.m5();
    }
}
