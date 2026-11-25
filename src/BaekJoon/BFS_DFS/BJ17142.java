package BaekJoon.BFS_DFS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BJ17142 {

    static int[][] map;
    static int[] dx = new int[]{-1, 1, 0, 0}; // 위 아 왼 오
    static int[] dy = new int[]{0, 0, -1, 1};
    static int N;
    static int M;
    static int emptyCount = 0;
    static int minTime = Integer.MAX_VALUE;
    static List<Viruse> viruses = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 0) {
                    emptyCount++;
                } else if (map[i][j] == 2) {
                    viruses.add(new  Viruse(i, j));
                }
            }
        }

        if (emptyCount == 0) {
            System.out.println(0);
            return;
        }

        int[] viruseSelected = new int[M];
        mixture(0, 0, viruseSelected);

        System.out.println(minTime == Integer.MAX_VALUE ? -1 : minTime);
    }

    static class Cur {
        int x, y, time;

        public Cur(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    static class Viruse {
        int x, y;

        public Viruse(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // 전체 바이러스 후보 중 M개를 고르는 조합
    private static void mixture(int idx, int depth, int[] viruseSelected) {
        // 돌다가 바이러스 다 고르면 bfs로 시간
        if (depth == M) {
            int result = bfs(viruseSelected);
            if (result != -1) {
                minTime = Math.min(minTime, result);
            }
            return;
        }

        // 바이러스 하나 고르고 그떄의 경우의수 돌리고, 결국 바이러스 선택의 모든 경우의 수
        for (int i = idx; i < viruses.size(); i++) {
            viruseSelected[depth] = i;
            mixture(i + 1, depth + 1, viruseSelected);
        }
    }

    private static int bfs(int[] selected) {
        ArrayDeque<Cur> queue = new ArrayDeque<>();
        int[][] visitTime = new int[N][N];
        int resultTime = 0;
        int maxTime = 0;

        // 방문 x => -1 , 방문 o => 해당 시간
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                visitTime[i][j] = -1;
            }
        }

        // 선택한 M개 바이러스 동시에 활성화 시키기
        for (int idx : selected) {
            Viruse viruse = viruses.get(idx);
            int viruseX = viruse.x;
            int viruseY = viruse.y;
            visitTime[viruseX][viruseY] = 0;
            queue.add(new Cur(viruseX, viruseY, 0));
        }


        while (!queue.isEmpty()) {
            Cur cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = cur.x + dx[i];
                int nextY = cur.y + dy[i];
                int nextTime = cur.time + 1;

                if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) {
                    continue;
                }

                // 벽 = 1 아니고 아직 방문하지 않은 칸(빈 칸 0 또는 비활성 바이러스 2)
                if (map[nextX][nextY] != 1 && visitTime[nextX][nextY] == -1) {
                    visitTime[nextX][nextY] = nextTime;
                    queue.add(new Cur(nextX, nextY, nextTime));

                    // 빈 칸 = 0 이 감염된 경우만 카운트 및 시간 갱신
                    if (map[nextX][nextY] == 0) {
                        resultTime++;
                        maxTime = nextTime;
                    }
                }
            }
        }

        // 모든 빈 칸을 감염시켰을 때만 시간 반환 , 감염 못시키면 -1
        return resultTime == emptyCount ? maxTime : -1;
    }
}

// 연구소3
//인체에 치명적인 바이러스를 연구하던 연구소에 승원이가 침입했고, 바이러스를 유출하려고 한다. 바이러스는 활성 상태와 비활성 상태가 있다.
//가장 처음에 모든 바이러스는 비활성 상태이고, 활성 상태인 바이러스는 상하좌우로 인접한 모든 빈 칸으로 동시에 복제되며, 1초가 걸린다.
//승원이는 연구소의 바이러스 M개를 활성 상태로 변경하려고 한다.
//
//연구소는 크기가 N×N인 정사각형으로 나타낼 수 있으며, 정사각형은 1×1 크기의 정사각형으로 나누어져 있다.
//연구소는 빈 칸, 벽, 바이러스로 이루어져 있으며, 벽은 칸 하나를 가득 차지한다.
//활성 바이러스가 비활성 바이러스가 있는 칸으로 가면 비활성 바이러스가 활성으로 변한다.
//
//예를 들어, 아래와 같이 연구소가 생긴 경우를 살펴보자. 0은 빈 칸, 1은 벽, 2는 바이러스의 위치이다.

//2 0 0 0 1 1 0
//0 0 1 0 1 2 0
//0 1 1 0 1 0 0
//0 1 0 0 0 0 0
//0 0 0 2 0 1 1
//0 1 0 0 0 0 0
//2 1 0 0 0 0 2


//M = 3이고, 바이러스를 아래와 같이 활성 상태로 변경한 경우 6초면 모든 칸에 바이러스를 퍼뜨릴 수 있다.
//벽은 -, 비활성 바이러스는 *, 활성 바이러스는 0, 빈 칸은 바이러스가 퍼지는 시간으로 표시했다.

//* 6 5 4 - - 2
//5 6 - 3 - 0 1
//4 - - 2 - 1 2
//3 - 2 1 2 2 3
//2 2 1 0 1 - -
//1 - 2 1 2 3 4
//0 - 3 2 3 4 *


//시간이 최소가 되는 방법은 아래와 같고, 4초만에 모든 칸에 바이러스를 퍼뜨릴 수 있다.

//0 1 2 3 - - 2
//1 2 - 3 - 0 1
//2 - - 2 - 1 2
//3 - 2 1 2 2 3
//3 2 1 0 1 - -
//4 - 2 1 2 3 4
//* - 3 2 3 4 *

//입력
//첫째 줄에 연구소의 크기 N(4 ≤ N ≤ 50), 놓을 수 있는 바이러스의 개수 M(1 ≤ M ≤ 10)이 주어진다.
//
//둘째 줄부터 N개의 줄에 연구소의 상태가 주어진다. 0은 빈 칸, 1은 벽, 2는 비활성 바이러스의 위치이다.
//2의 개수는 M보다 크거나 같고, 10보다 작거나 같은 자연수이다.

//출력
//연구소의 모든 빈 칸에 바이러스가 있게 되는 최소 시간을 출력한다. 바이러스를 어떻게 놓아도 모든 빈 칸에 바이러스를 퍼뜨릴 수 없는 경우에는 -1을 출력한다.

//7 3
//2 0 0 0 1 1 0
//0 0 1 0 1 2 0
//0 1 1 0 1 0 0
//0 1 0 0 0 0 0
//0 0 0 2 0 1 1
//0 1 0 0 0 0 0
//2 1 0 0 0 0 2

//4