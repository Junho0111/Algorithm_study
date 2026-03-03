package BFS_DFS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BJ2606 {

    private static ArrayList<Integer>[] graph;
    private static boolean[] visited;
    private static int count = 0;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 정점의 개수
        int m = sc.nextInt(); // 간선의 개수

        graph = new ArrayList[n + 1];
        visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= m; i++) {
            int s1 = sc.nextInt();
            int s2 = sc.nextInt();

            graph[s1].add(s2);
            graph[s2].add(s1);
        }

        for (int i = 1; i <= n; i++) {
            Collections.sort(graph[i]);
        }

        dfs(1);
        System.out.println(count-1);
    }

    public static void dfs(int v) {
        visited[v] = true;
        count++;

        for (int next : graph[v]) {
            if (!visited[next]) {
                dfs(next);
            }
        }
    }
}
