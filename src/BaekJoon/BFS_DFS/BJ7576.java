package BaekJoon.BFS_DFS;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

import static java.lang.Math.max;

public class BJ7576 {

    private static int[][] map;
    private static int[][] dayCount;
    private static boolean[][] visited;
    private static final int[] dn = {1, -1, 0, 0}; //오 왼 아 위
    private static final int[] dm = {0, 0, 1, -1};
    private static Queue<Tomato> queue = new ArrayDeque<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int M = sc.nextInt(); // 토마토 상자 가로
        int N = sc.nextInt(); // 토마토 상자 세로

        map = new int[N][M];
        visited = new boolean[N][M];
        dayCount = new int[N][M];

        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                map[n][m] = sc.nextInt();
                if(map[n][m] == 1){
                    visited[n][m] = true;
                    queue.add(new Tomato(n, m));
                }
            }
        }

        bfs(N, M);

        int maxDay = 0; // 처음은 0일부터
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                if(map[n][m] == 0){
                    System.out.println(-1);
                    return;
                }
                maxDay = max(maxDay, dayCount[n][m]);
            }
        }
        System.out.println(maxDay);
    }

    public static class Tomato {
        int n, m;

        public Tomato(int n, int m) {
            this.n = n;
            this.m = m;
        }
    }

    private static void bfs(int N, int M) {
        while (!queue.isEmpty()) {
            Tomato tomato = queue.poll();

            for(int i = 0; i < 4; i++) { // 방향 전부 가보면서 날짜 추가하고 방문 표시해주고 익었다는 표시해주고 <== 이거 다하면 그 익은 토마토가 인접한 토마토 익게하기위해 다시 queue로
                int nextN =  tomato.n + dn[i];
                int nextM =  tomato.m + dm[i];

                if(nextN >= 0 && nextM >= 0 && nextN < N && nextM < M && !visited[nextN][nextM] && map[nextN][nextM] == 0){
                    map[nextN][nextM] = 1;
                    visited[nextN][nextM] = true;
                    dayCount[nextN][nextM] = dayCount[tomato.n][tomato.m] + 1;
                    queue.add(new Tomato(nextN, nextM));
                }
            }
        }

    }
}

//창고에 보관되는 토마토들 중에는 잘 익은 것도 있지만, 아직 익지 않은 토마토들도 있을 수 있다.
// 보관 후 하루가 지나면, 익은 토마토들의 인접한 곳에 있는 익지 않은 토마토들은 익은 토마토의 영향을 받아 익게 된다.
// 하나의 토마토의 인접한 곳은 왼쪽, 오른쪽, 앞, 뒤 네 방향에 있는 토마토를 의미한다.
// 대각선 방향에 있는 토마토들에게는 영향을 주지 못하며, 토마토가 혼자 저절로 익는 경우는 없다고 가정한다.
// 철수는 창고에 보관된 토마토들이 며칠이 지나면 다 익게 되는지, 그 최소 일수를 알고 싶어 한다.

//토마토를 창고에 보관하는 격자모양의 상자들의 크기와 익은 토마토들과 익지 않은 토마토들의 정보가 주어졌을 때, 며칠이 지나면 토마토들이 모두 익는지,
// 그 최소 일수를 구하는 프로그램을 작성하라. 단, 상자의 일부 칸에는 토마토가 들어있지 않을 수도 있다.

//입력
// 첫 줄에는 상자의 크기를 나타내는 두 정수 M,N이 주어진다.
// M은 상자의 가로 칸의 수, N은 상자의 세로 칸의 수를 나타낸다.
// 단, 2 ≤ M,N ≤ 1,000 이다.
// 둘째 줄부터는 하나의 상자에 저장된 토마토들의 정보가 주어진다.
// 즉, 둘째 줄부터 N개의 줄에는 상자에 담긴 토마토의 정보가 주어진다

//하나의 줄에는 상자 가로줄에 들어있는 토마토의 상태가 M개의 정수로 주어진다.
// 정수 1은 익은 토마토,
// 정수 0은 익지 않은 토마토,
// 정수 -1은 토마토가 들어있지 않은 칸을 나타낸다.
//토마토가 하나 이상 있는 경우만 입력으로 주어진다.

//출력
// 여러분은 토마토가 모두 익을 때까지의 최소 날짜를 출력해야 한다.
// 만약, 저장될 때부터 모든 토마토가 익어있는 상태이면 0을 출력해야 하고, => 처음부터 모두 1이면 0을 출력
// 토마토가 모두 익지는 못하는 상황이면 -1을 출력해야 한다.  => 무조건 토마토가 하나 이상은 있음, 따라서 토마토가 영향을 끼치지 못하는곳에 있으면 -1을 출력

//6 4
//0 0 0 0 0 0
//0 0 0 0 0 0
//0 0 0 0 0 0
//0 0 0 0 0 1

//8