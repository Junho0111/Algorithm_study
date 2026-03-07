package BaekJoon.BFS_DFS;

import java.util.ArrayList;
import java.util.Scanner;

import java.util.*;

public class BJ1260 {
    private static ArrayList<Integer>[] graph;
    private static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 정점의 개수
        int m = sc.nextInt(); // 간선의 개수
        int v = sc.nextInt(); // 시작 정점 번호

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int s1 = sc.nextInt();
            int s2 = sc.nextInt();

            graph[s1].add(s2);
            graph[s2].add(s1);
        }

        for (int i = 1; i <= n; i++) {
            Collections.sort(graph[i]);
        }

        visited = new boolean[n + 1];
        dfs(v);
        System.out.println();

        visited = new boolean[n + 1];
        bfs(v);
    }

    public static void dfs(int v) {
        visited[v] = true;
        System.out.print(v + " ");

        for (int next : graph[v]) {
            //방문 안했으면 계속 깊게
            if (!visited[next]) {
                dfs(next);
            }
        }
    }

    public static void bfs(int v) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(v);
        visited[v] = true;

        //큐가 다 빌때까지 반복
        while (!queue.isEmpty()) {
            int value = queue.poll();
            System.out.print(value + " ");

            for (int next : graph[value]) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }
    }
}