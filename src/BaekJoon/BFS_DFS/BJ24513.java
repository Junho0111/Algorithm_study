package BaekJoon.BFS_DFS;

import java.util.ArrayDeque;
import java.util.Scanner;

public class BJ24513 {
    static int N; // 세로
    static int M; // 가로
    static int[][] map;
    static int[][] timeMap;
    static ArrayDeque<Home> queue = new ArrayDeque<>();
    static int[] result = new int[3];

    static int[] dx = {-1, 1, 0, 0}; // 위 아 왼 오
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        map = new int[N][M];
        timeMap = new int[N][M];

        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                map[n][m] = sc.nextInt();

                if (map[n][m] == 1 || map[n][m] == 2) {
                    timeMap[n][m] = 1;
                    queue.add(new Home(n, m, map[n][m], 1));
                }
            }
        }

        bfs();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    result[0] += 1;
                } else if (map[i][j] == 2) {
                    result[1] += 1;
                } else if (map[i][j] == 3) {
                    result[2] += 1;
                }
            }
        }

        for (int r : result) {
            System.out.print(r + " ");
        }
    }

    static class Home {
        int x;
        int y;
        int state = 0;
        int time = 0;

        public Home(int x, int y, int state, int time) {
            this.x = x;
            this.y = y;
            this.state = state;
            this.time = time;
        }
    }

    private static void bfs() {
        while (!queue.isEmpty()) {
            Home home = queue.poll();

            if (map[home.x][home.y] == 3) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nextX = home.x + dx[i];
                int nextY = home.y + dy[i];
                int nextTime = home.time + 1;

                if (nextX >= 0 && nextY >=0 && nextX < N && nextY < M) {
                    if (map[nextX][nextY] == 0) {
                        timeMap[nextX][nextY] = nextTime;
                        map[nextX][nextY] = home.state;
                        queue.add(new Home(nextX, nextY, home.state, nextTime));
                    }

                    if (map[nextX][nextY] != home.state && timeMap[nextX][nextY] == nextTime && map[nextX][nextY] != 3) {
                        map[nextX][nextY] = 3;
                    }
                }

            }
        }
    }
}

// -1: 치료제를 가진 마을
// 0: 아직 감염되지 않은 마을
// 1번 바이러스에 감염된 마을
// 2번 바이러스에 감염된 마을

//같은시간에 감염된거는 동시에 퍼지는건 3으로 하면됨 1번시간에 1과 2가 만나면 이건 3
//1번시간에 1로감염된건데 2번시간에 2번이 저기 감염 못시킴
//치료제 마을은 감염 못시킴

//1번 바이러스와
//2번 바이러스에 감염된 마을이 나와버렸다. 바이러스가 퍼질 수 있는 대로 퍼졌을 때
//1번, 2번, 3번 바이러스에 감염된 마을이 각각 몇 개일지 구해보자.

//입력
//1번 바이러스와 2번 바이러스에 감염된 마을은 각각 하나씩만 주어진다.

//출력
// 1번, 2번, 3번 바이러스에 감염된 마을의 수를 공백으로 구분하여 한 줄에 출력한다.




//입력받을때 저렇게 바로 큐에 넣은건 따로 이유가 있어서가 아니라
//문제 잘 안읽고 처음에 1,2번 바이러스가 주어질때 한번에 여러곳에서 여러개 퍼지는줄알아서 바로바로 다 넣은거
//결국 1, 2번 바이러스는 한개씩 주어지니 저렇게 할 필요없음