package BFS_DFS;

import java.util.*;

public class BJ2667 {

    private static int[][] map;
    private static boolean[][] visited;
    private static int n;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static ArrayList<Integer> list;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        sc.nextLine();

        map = new int[n][n];
        visited = new boolean[n][n];
        list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    list.add(bfs(i, j));
                }
            }
        }

        Collections.sort(list);

        System.out.println(list.size());
        for (int count : list) {
            System.out.println(count);
        }
    }

    public static int bfs(int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{x, y});
        visited[x][y] = true;
        int count = 1;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int cx = curr[0];
            int cy = curr[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                    if (map[nx][ny] == 1 && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        queue.add(new int[]{nx, ny});
                        count++;
                    }
                }
            }
        }
        return count;
    }
}