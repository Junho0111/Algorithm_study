package BFS_DFS;

import java.util.*;

public class BJ7569 {
    private static int N, M, H;
    private static int[][][] map;
    private static int[][][] dayCount;
    private static boolean[][][] visited;
    private static Queue<Tomato> queue = new ArrayDeque<>();
    private static int[] dx = {1, -1, 0, 0, 0, 0};
    private static int[] dy = {0, 0, 1, -1, 0, 0};
    private static int[] dz = {0, 0, 0, 0, 1, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); // 가로
        M = sc.nextInt(); // 세로
        H = sc.nextInt(); // 높이

        map = new int[H][M][N];
        visited = new boolean[H][M][N];
        dayCount = new int[H][M][N];

        for (int h = 0; h < H; h++) {
            for (int m = 0; m < M; m++) {
                for (int n = 0; n < N; n++) {
                    map[h][m][n] = sc.nextInt();
                    if (map[h][m][n] == 1) {
                        visited[h][m][n] = true;
                        queue.add(new Tomato(h, m, n));
                    }
                }
            }
        }

        bfs();

        int maxDay = 0;
        for (int h = 0; h < H; h++) {
            for (int m = 0; m < M; m++) {
                for (int n = 0; n < N; n++) {
                    if (map[h][m][n] == 0) {
                        System.out.println(-1);
                        return;
                    }
                    maxDay = Math.max(maxDay, dayCount[h][m][n]);
                }
            }
        }

        System.out.println(maxDay);
    }

    public static class Tomato {
        int h, m, n;

        public Tomato(int h, int m, int n) {
            this.h = h;
            this.m = m;
            this.n = n;
        }
    }

    public static void bfs() {
        while (!queue.isEmpty()) {
            Tomato tomato = queue.poll();

            for (int i = 0; i < 6; i++) {
                int nh = tomato.h + dz[i];
                int nm = tomato.m + dy[i];
                int nn = tomato.n + dx[i];

                if (nh >= 0 && nm >= 0 && nn >= 0 && nh < H && nm < M && nn < N) {
                    if (map[nh][nm][nn] == 0 && !visited[nh][nm][nn]) {
                        map[nh][nm][nn] = 1;
                        dayCount[nh][nm][nn] = dayCount[tomato.h][tomato.m][tomato.n] + 1;
                        visited[nh][nm][nn] = true;
                        queue.add(new Tomato(nh, nm, nn));
                    }
                }
            }
        }
    }
}