package BackJun.silver.s2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 1260. DFS와 BFS
public class S2_1260 {
    static int[][] graph; // 노드 저장
    static boolean[] check; // 방문한 노드 확인
    static Queue<Integer> queue = new LinkedList<>(); // bfs 시, 인접 노드 저장

    private static void dfs(int v) {
        check[v] = true;
        System.out.print(v + " ");
        for (int i = 1; i < graph.length; i++) {
            // 노드 v와 인접한 노드 && 미미방문 노드
            if (graph[v][i] == 1 && !check[i]) {
                dfs(i); // i 노드와 인접한 노드 구하기
            }
        }
    }

    private static void bfs(int v) {
        queue.offer(v);
        check[v] = true;
        System.out.print(v + " ");

        while (!queue.isEmpty()) {
            v = queue.poll();
            for (int i = 0; i < graph.length; i++) {
                // 인접한 노드 && 미방문 노드
                if (graph[v][i] == 1 && !check[i]) {
                    queue.offer(i);
                    check[i] = true; // 방문
                    System.out.print(i + " ");
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 정점 개수
        int m = sc.nextInt(); // 간선 개수
        int v = sc.nextInt(); // 시작할 정점
        graph = new int[n + 1][n + 1];
        check = new boolean[n + 1];

        for (int i = 1; i <= m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            // 연결된 노드 표현
            graph[a][b] = graph[b][a] = 1;
        }

        dfs(v);
        System.out.println();

        check = new boolean[n + 1]; // 방문 기록 초기화
        bfs(v);
    }
}
