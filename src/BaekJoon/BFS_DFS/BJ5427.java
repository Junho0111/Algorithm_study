package BaekJoon.BFS_DFS;

import java.util.ArrayDeque;
import java.util.Scanner;

public class BJ5427 {

    static int testCnt;
    static ArrayDeque<String> results = new ArrayDeque<>();
    static int[] dx = {-1, 1, 0, 0}; // 위 아 왼 오
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        testCnt = sc.nextInt();

        for (int i = 1; i <= testCnt; i++) {
            int w, h;
            String[][] map;
            w = sc.nextInt();
            h = sc.nextInt();

            map = new String[h][w];

            for (int H = 0; H < h; H++) {
                String line = sc.next();
                for (int W = 0; W < w; W++) {
                    map[H][W] = String.valueOf(line.charAt(W));
                }
            }
            results.add(bfs(map, w, h));
        }

        for (String result : results) {
            System.out.println(result);
        }
    }

    static class SangGun {
        int x, y;
        int time;

        public SangGun(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    static class Fire {
        int x, y;
        int time;

        public Fire(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    private static String bfs(String[][] map, int w, int h) {
        ArrayDeque<SangGun> queue = new ArrayDeque<>();
        ArrayDeque<Fire> fireQueue = new ArrayDeque<>();
        int[][] fireTime = new int[h][w];
        boolean[][] visited = new boolean[h][w];

        // 받은 지역기반 기반으로 불 확산 시간 기록하고 상근이 출발 준비
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                fireTime[i][j] = Integer.MAX_VALUE;

                if (map[i][j].equals("*")) {
                    fireTime[i][j] = 0;
                    fireQueue.add(new Fire(i, j, 0));
                } else if (map[i][j].equals("@")) {
                    visited[i][j] = true;
                    queue.add(new SangGun(i, j, 0));
                }
            }
        }

        // 벽빼고 시간대로 불내기 -> 나중에 상근이가 불난시간보다 작으면 이동 가능(불나기 전에 왔다는 소리)
        while (!fireQueue.isEmpty()) {
            Fire fire = fireQueue.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = fire.x + dx[i];
                int nextY = fire.y + dy[i];
                int nextTime = fire.time + 1;

                // 나가면 다시
                if (nextX < 0 || nextX >= h || nextY < 0 || nextY >= w) {
                    continue;
                }

                // 벽이면 다시
                if (map[nextX][nextY].equals("#") || fireTime[nextX][nextY] != Integer.MAX_VALUE) {
                    continue;
                }

                fireTime[nextX][nextY] = nextTime;
                fireQueue.add(new Fire(nextX, nextY, nextTime));
            }
        }

        // 상근이 출발/ 불난지역 시간 봐가면서 상근이가 더 빠르게 왔으면 이동
        while (!queue.isEmpty()) {
            SangGun sangGun = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = sangGun.x + dx[i];
                int nextY = sangGun.y + dy[i];
                int nextTime = sangGun.time + 1;

                // 나가면 성공
                if (nextX < 0 || nextX >= h || nextY < 0 || nextY >= w) {
                    return String.valueOf(nextTime);
                }

                // (이미 방문 했거나 / 벽이거나 / 이미 불났거나 / 도착했는데 불나면 )안됨
                if (visited[nextX][nextY] || map[nextX][nextY].equals("#") || nextTime >= fireTime[nextX][nextY]) {
                    continue;
                }

                //1초에 이동했는데 상근이자리에 1초에 불붙는건 가능
                visited[nextX][nextY] = true;
                queue.add(new SangGun(nextX, nextY, nextTime));
            }
        }

        return "IMPOSSIBLE";
    }
}

//불
//상근이는 빈 공간과 벽으로 이루어진 건물에 갇혀있다. 건물의 일부에는 불이 났고, 상근이는 출구를 향해 뛰고 있다.
//
//매 초마다, 불은 동서남북 방향으로 인접한 빈 공간으로 퍼져나간다. 벽에는 불이 붙지 않는다.
//상근이는 동서남북 인접한 칸으로 이동할 수 있으며, 1초가 걸린다.
//상근이는 벽을 통과할 수 없고, 불이 옮겨진 칸 또는 이제 불이 붙으려는 칸으로 이동할 수 없다.
//상근이가 있는 칸에 불이 옮겨옴과 동시에 다른 칸으로 이동할 수 있다.
//
//빌딩의 지도가 주어졌을 때, 얼마나 빨리 빌딩을 탈출할 수 있는지 구하는 프로그램을 작성하시오.

//입력
//첫째 줄에 테스트 케이스의 개수가 주어진다. 테스트 케이스는 최대 100개이다.
//
//각 테스트 케이스의 첫째 줄에는 빌딩 지도의 너비와 높이 w와 h가 주어진다. (1 ≤ w,h ≤ 1000)
//
//다음 h개 줄에는 w개의 문자, 빌딩의 지도가 주어진다.
//
//'.': 빈 공간
//'#': 벽
//'@': 상근이의 시작 위치
//'*': 불
//각 지도에 @의 개수는 하나이다.

//출력
//각 테스트 케이스마다 빌딩을 탈출하는데 가장 빠른 시간을 출력한다. 빌딩을 탈출할 수 없는 경우에는 "IMPOSSIBLE"을 출력한다.

//5

//4 3
//####
//#*@.
//####

//7 6
//###.###
//#*#.#*#
//#.....#
//#.....#
//#..@..#
//#######

//7 4
//###.###
//#....*#
//#@....#
//.######

//5 5
//.....
//.***.
//.*@*.
//.***.
//.....

//3 3
//###
//#@#
//###

//2
//5
//IMPOSSIBLE
//IMPOSSIBLE
//IMPOSSIBLE