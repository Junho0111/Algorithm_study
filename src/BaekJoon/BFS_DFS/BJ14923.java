package BaekJoon.BFS_DFS;

import java.util.ArrayDeque;
import java.util.Scanner;

public class BJ14923 {
    static int N; //세로
    static int M; //가로
    static int[][] map;
    static boolean[][][] visited;

    static int[] dx = {-1, 1, 0, 0}; //위 아 왼 오
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        int x = sc.nextInt() - 1; //출발
        int y = sc.nextInt() - 1;

        int a = sc.nextInt() - 1; //도착지점
        int b = sc.nextInt() - 1;

        map = new int[N][M];
        visited = new boolean[N][M][2];
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                map[n][m] = sc.nextInt();
            }
        }

        System.out.println(bfs(x, y, a, b));
    }

    static class Current {
        int x;
        int y;
        int distance;
        int broken;

        public Current(int x, int y, int distance, int broken) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.broken = broken;
        }

    }

    private static int bfs(int x, int y, int a, int b) {
        ArrayDeque<Current> queue = new ArrayDeque<>();
        queue.add(new Current(x, y, 0, 0));
        visited[x][y][0] = true;

        while (!queue.isEmpty()) {
            Current current = queue.poll();

            if (current.x == a && current.y == b) {
                return current.distance;
            }

            for (int i = 0; i < 4; i++) {
                int nextX = current.x + dx[i];
                int nextY = current.y + dy[i];

                if (nextX >= 0 && nextY >= 0 && nextX < N && nextY < M) {

                    if (map[nextX][nextY] == 0) {
                        if (!visited[nextX][nextY][current.broken]) {
                            visited[nextX][nextY][current.broken] = true;
                            queue.add(new Current(nextX, nextY, current.distance + 1, current.broken));
                        }
                    } else {
                        if (current.broken == 0 && !visited[nextX][nextY][1]) {
                            visited[nextX][nextY][1] = true;
                            queue.add(new Current(nextX, nextY, current.distance + 1, 1));
                        }
                    }
                }
            }
        }
        return -1;
    }
}

//홍익이는 사악한 마법사의 꾐에 속아 N x M 미로 (Hx, Hy) 위치에 떨어졌다.
// 다행히도 홍익이는 마법사가 만든 미로의 탈출 위치(Ex, Ey)를 알고 있다.
// 하지만 미로에는 곳곳에 마법사가 설치한 벽이 있어 홍익이가 탈출하기 어렵게 하고 있다.
//
//홍익이는 마법사의 연구실에서 훔친 지팡이가 있어, 벽을 길로 만들 수 있다.
// 그렇지만, 안타깝게도 마법의 지팡이는 단 한 번만 사용할 수 있다.
//
//이때, 홍익이를 도와 미로에서 탈출할 수 있는지 알아보고, 할 수 있다면 가장 빠른 경로의 거리 D는 얼마인지 알아보자.
//
//인접한 칸으로 이동하는데 똑같은 시간이 들고, 벽을 부수는 데 시간이 걸리지 않는다.


//입력
//N M
//Hx Hy //현재위치
//Ex Ey //도착위치
//N X M 행렬

//2 ≤ N ≤ 1000, 2 ≤ M ≤ 1000
//1 ≤ Hx, Hy, Ex, Ey ≤ 1000
//(Hx, Hy) ≠ (Ex, Ey)
//행렬은 0과 1로만 이루어져 있고, 0이 빈 칸, 1이 벽이다.

//출력
//D (탈출 할 수 없다면, -1을 출력한다.)

//5 6
//1 1
//5 6
//0 1 1 1 0 0
//0 1 1 0 0 0
//0 1 0 0 1 0
//0 1 0 0 1 0
//0 0 0 1 1 0

//11

//힌트
//제일 왼쪽 위 위치에서 제일 오른쪽 아래 위치로 이동하려면 (3,2) 벽을 파괴하고 이동하면 된다.