package BaekJoon.BFS_DFS;

import java.util.ArrayDeque;
import java.util.Scanner;

public class BJ2206 {

    static int N; // 세로
    static int M; // 가로
    static int[][] map;
    static boolean[][][] visited; //[][] 크기 [] 벽 뚫은거 공유x
    static int minDistance;
    static int BROKEN_Layer = 1;
    static int NOT_BROKEN_Layer = 0;


    static int[] dx = {-1, 1, 0, 0}; // 위, 아, 왼, 오
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        map = new int[N][M];
        for (int n = 0; n < N; n++) {
            String str = sc.next();
            for (int m = 0; m < M; m++) {
                map[n][m] = str.charAt(m) - '0';
            }
        }

         minDistance = bfs();

        //제일 먼저 도착하는것이 최단거리
        System.out.println(minDistance);
    }

    static class Mover {
        int x;
        int y;
        int distance;
        boolean brokenCheck = false;

        public Mover(int x, int y, int distance, boolean brokenCheck) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.brokenCheck = brokenCheck;
        }
    }

    private static int bfs() {
        ArrayDeque<Mover> queue = new ArrayDeque<>();
        visited = new boolean[N][M][2]; // 0은기본 1은 뚫은거

        queue.add(new Mover(0, 0, 1, false));
        visited[0][0][NOT_BROKEN_Layer] = true;

        while (!queue.isEmpty()) {
            Mover mover = queue.poll();

            if (mover.x == N - 1 && mover.y == M - 1) {
                return mover.distance;
            }

            for (int i = 0; i < 4; i++) {
                int nextX = mover.x + dx[i];
                int nextY = mover.y + dy[i];

                if (nextX >= 0 && nextY >= 0 && nextX < N && nextY < M) {

                    //벽만났을때 (아직 부수기 사용x, 벽부순 계층 방문 x)
                    if(map[nextX][nextY] == 1 && !visited[nextX][nextY][BROKEN_Layer] && !mover.brokenCheck) {
                        visited[nextX][nextY][BROKEN_Layer] = true;
                        queue.add(new Mover(nextX, nextY, mover.distance+1, true));
                    }

                    // 길 만났을때 (부수기 사용 x)
                    if(map[nextX][nextY] == 0 && !visited[nextX][nextY][NOT_BROKEN_Layer] &&!mover.brokenCheck) {
                        visited[nextX][nextY][NOT_BROKEN_Layer] = true;
                        queue.add(new Mover(nextX, nextY, mover.distance+1, false));
                    }

                    // 길 만났을때 (부수기 사용 했을때)
                    if(map[nextX][nextY] == 0 && !visited[nextX][nextY][BROKEN_Layer] && mover.brokenCheck) {
                        visited[nextX][nextY][BROKEN_Layer] = true;
                        queue.add(new Mover(nextX, nextY, mover.distance+1, true));
                    }
                }
            }
        }
        return -1;
    }
}

// N×M의 행렬로 표현되는 맵이 있다.
// 맵에서 0은 이동할 수 있는 곳을 나타내고, - 길
// 1은 이동할 수 없는 벽이 있는 곳을 나타낸다. - 벽
// 당신은 (1, 1)에서 (N, M)의 위치까지 이동하려 하는데, 이때 최단 경로로 이동하려 한다.
// 최단경로는 맵에서 가장 적은 개수의 칸을 지나는 경로를 말하는데,
// 이때 시작하는 칸과 끝나는 칸도 포함해서 센다.
//
//만약에 이동하는 도중에 한 개의 벽을 부수고 이동하는 것이 좀 더 경로가 짧아진다면, 벽을 한 개 까지 부수고 이동하여도 된다.
//
//한 칸에서 이동할 수 있는 칸은 상하좌우로 인접한 칸이다.
//
//맵이 주어졌을 때, 최단 경로를 구해 내는 프로그램을 작성하시오.

//입력
// 첫째 줄에 N(1 ≤ N ≤ 1,000), M(1 ≤ M ≤ 1,000)이 주어진다.
// 다음 N개의 줄에 M개의 숫자로 맵이 주어진다. (1, 1)과 (N, M)은 항상 0이라고 가정하자.

// 출력
// 첫째 줄에 최단 거리를 출력한다. 불가능할 때는 -1을 출력한다.

//6 4
//0100
//1110
//1000
//0000
//0111
//0000

//15