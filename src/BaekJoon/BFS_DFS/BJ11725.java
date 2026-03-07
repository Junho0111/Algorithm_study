package BaekJoon.BFS_DFS;

import java.util.ArrayList;
import java.util.Scanner;

public class BJ11725 {

    private static ArrayList<Integer>[] tree;
    private static boolean[] visited;
    private static int[] list;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        tree = new ArrayList[n + 1];
        visited = new boolean[n + 1];
        list = new int[n + 1];

        for (int i = 0; i < n + 1; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();

            tree[x].add(y);
            tree[y].add(x);
        }

        dfs(1);
        for (int i = 2; i <= n; i++) {
            System.out.println(list[i]);
        }

    }

    public static void dfs(int v) {
        visited[v] = true;

        for (int next : tree[v]) {
            if (!visited[next]) {
                list[next] = v;
                dfs(next);
            }
        }
    }
}
