package BackJun.silver.s1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 2178. 미로 탐색
public class S1_2178 {
    static int[][] miro;
    static boolean[][] check;

    private static void find() {
        // 상하좌우 x, y 좌표 이동
        // 앞으로, 위위로, 뒤로, 아래로
        int[] moveX = { 0, -1, 0, 1 };
        int[] moveY = { 1, 0, -1, 0 };
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0, 0));

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            for (int i = 0; i < 4; i++) {
                int x = node.x + moveX[i];
                int y = node.y + moveY[i];
                // 인덱스 범위 벗어나면 작업 X
                if (x < 0 || y < 0 || x > miro.length - 1 || y > miro[0].length - 1) {
                    continue;
                }
                // 값이 0 이거나 방문 기록이 있으면 작업 X
                if (miro[x][y] == 0 || check[x][y]) {
                    continue;
                }

                // 인덱스 범위 안에서 값이 1이며 방문 기록이 없는 경우
                queue.offer(new Node(x, y));
                check[x][y] = true;
                miro[x][y] = miro[node.x][node.y] + 1;
            }
        }

        System.out.println(miro[miro.length - 1][miro[0].length - 1]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        miro = new int[n][m];
        check = new boolean[n][m];
        check[0][0] = true;

        for (int i = 0; i < n; i++) {
            String str = sc.next();
            char[] strArr = str.toCharArray();
            for (int j = 0; j < m; j++) {
                miro[i][j] = Integer.parseInt(strArr[j] + "");
            }
        }

        find();
    }

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}