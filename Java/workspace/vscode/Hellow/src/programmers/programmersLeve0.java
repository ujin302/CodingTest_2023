public class programmersLeve0 {
    public int[][] solution1(int n) {
        System.out.println("정수를 나선형으로 배치하기");
        int[][] answer = new int[n][n];
        int row = 0;
        int col = 0;
        int num = 1;
        int d = 0; // 이동 방향 0: 오른쪽, 1: 아래, 2: 왼쪽, 3: 위

        while (num <= n * n) {
            answer[row][col] = num++;

            // 다음 이동할 위치 계산
            switch (d) {
                case 0:
                    // 오른쪽으로로 끝까지 이동한 경우 || 다음 칸의 값이 이미 존재할 경우
                    if (col == n - 1 || 0 != answer[row][col + 1]) {
                        d = 1;
                        row++;
                    } else
                        col++;
                    break;
                case 1:
                    // 아래로 끝까지 이동한 경우 || 다음 칸의 값이 이미 존재할 경우
                    if (row == n - 1 || 0 != answer[row + 1][col]) {
                        d = 2;
                        col--;
                    } else
                        row++;
                    break;
                case 2:
                    // 왼쪽으로 끝까지 이동한 경우 || 다음 칸의 값이 이미 존재할 경우
                    if (col == 0 || 0 != answer[row][col - 1]) {
                        d = 3;
                        row--;
                    } else
                        col--;
                    break;
                case 3:
                    // 위로 끝까지 이동한 경우 || 다음 칸의 값이 이미 존재할 경우
                    if (row == 0 || 0 != answer[row - 1][col]) {
                        d = 0;
                        col++;
                    } else
                        row--;
                    break;
                default:
                    break;
            }

        }

        return answer;
    }

    public static void main(String[] args) {
        programmersLeve0 leve0 = new programmersLeve0();
        System.out.println(leve0.solution1(4));
    }
}
