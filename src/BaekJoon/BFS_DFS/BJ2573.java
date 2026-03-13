package BaekJoon.BFS_DFS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;

public class BJ2573 {

    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int time = 0; // 다 녹았을때 시간
    static int result;

    static final int[] dx = { -1, 1, 0, 0}; // 위 아 왼 오
    static final int[] dy = { 0, 0, -1, 1}; // 위 아 왼 오

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt(); //가로
        M = sc.nextInt(); //세로

        map = new int[N][M];

        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                map[n][m] = sc.nextInt();
            }
        }

        while (true){
            int check = countCheck();

            if (check == 0){
                result = check;
                break;
            }

            if (check >= 2){
                result = time;
                break;
            }

            iceMelt();
            time++;
        }

        System.out.println(result);
    }

    private static int countCheck(){
        int count = 0;
        visited = new boolean[N][M];

        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                if (map[n][m] > 0 && !visited[n][m]) {
                    count++;
                    iceCheck(n, m);
                }
            }
        }

        return count;
    }

    private static void iceCheck(int n, int m){ //얼음 기준으로 얼음쭉 연결/ 끊기면 거기까지 덩어리인거
        ArrayDeque<Ice> queue = new ArrayDeque<>();
        queue.add(new Ice(n, m));
        visited[n][m] = true;

        while (!queue.isEmpty()) {
            Ice ice = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = ice.x + dx[i];
                int nextY = ice.y + dy[i];

                if (nextX >= 0 && nextY >= 0 && nextX < N && nextY < M && !visited[nextX][nextY] && map[nextX][nextY] != 0) {
                    visited[nextX][nextY] = true;
                    queue.add(new Ice(nextX, nextY));
                }
            }

        }
    }

    static class Ice {
        int x, y;

        public Ice(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void iceMelt() {
        int[][] temp = new int[N][M];

        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                if(map[n][m] > 0){
                    int seaCount = 0;
                    for (int i = 0; i < 4; i++) {
                        int nextX = n + dx[i];
                        int nextY = m + dy[i];

                        if(nextX >= 0 && nextY >= 0 && nextX < N && nextY < M && map[nextX][nextY] == 0){
                            seaCount++;
                        }
                    }
                    temp[n][m] = seaCount;
                }
            }
        }

        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                if (map[n][m] > 0) {
                    map[n][m] -= temp[n][m];
                    if (map[n][m] < 0) {
                        map[n][m] = 0;
                    }
                }
            }
        }
    }
}

// 입력
// 첫 줄에는 이차원 배열의 행의 개수와 열의 개수를 나타내는 두 정수 N과 M이 한 개의 빈칸을 사이에 두고 주어진다.
// N과 M은 3 이상 300 이하이다. 그 다음 N개의 줄에는 각 줄마다 배열의 각 행을 나타내는 M개의 정수가 한 개의 빈 칸을 사이에 두고 주어진다.
// 각 칸에 들어가는 값은 0 이상 10 이하이다. 배열에서 빙산이 차지하는 칸의 개수, 즉, 1 이상의 정수가 들어가는 칸의 개수는 10,000 개 이하이다.
// 배열의 첫 번째 행과 열, 마지막 행과 열에는 항상 0으로 채워진다.

// 출력
//첫 줄에 빙산이 분리되는 최초의 시간(년)을 출력한다. 만일 빙산이 다 녹을 때까지 분리되지 않으면 0을 출력한다.

//5 7
//0 0 0 0 0 0 0
//0 2 4 5 3 0 0
//0 3 0 2 5 2 0
//0 7 6 2 4 0 0
//0 0 0 0 0 0 0

//2