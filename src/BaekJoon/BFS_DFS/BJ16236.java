package BaekJoon.BFS_DFS;

import java.util.*;

public class BJ16236 {
    static int N;
    static int[][] map;
    static int sharkX, sharkY, sharkSize = 2, eatCount = 0, minDay = 0;

    static int[] dx = {-1, 1, 0, 0}; // 위 아래 왼 오
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 9) {
                    sharkX = i;
                    sharkY = j;
                    map[i][j] = 0;
                }
            }
        }

        // 아기상어를 계속 움직여서 매순간 최선의 먹이를 향해 감
        while (true) {
            Prey prey = checkPrey();

            if (prey == null) {
                break;
            }

            // 목표 먹이거리 => 걸린 시간 , 먹이 위치 => 최종 위치
            minDay += prey.distance;
            sharkX = prey.x;
            sharkY = prey.y;

            // 먹고나서 빈표시
            map[sharkX][sharkY] = 0;
            eatCount++;

            if (eatCount == sharkSize) {
                sharkSize++;
                eatCount = 0;
            }
        }

        System.out.println(minDay);
    }

    static class Prey {
        int x, y, distance;

        public Prey(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }

    // 현재 아기상어 위치 기준으로 최선의 먹이 찾아감
    private static Prey checkPrey() {
        ArrayDeque<Prey> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];
        ArrayList<Prey> preyList = new ArrayList<>();

        queue.add(new Prey(sharkX, sharkY, 0));
        visited[sharkX][sharkY] = true;


        int tempDistance = 1000;

        while (!queue.isEmpty()) {
            Prey prey = queue.poll();

            // 새로 탐색중인 먹이 거리가 이미 찾은 최단거리 먹이보다 거리가 길면 더 이상 그쪽으로 탐색은 의미 x
            if (prey.distance >= tempDistance) {
               continue;
            }

            for (int i = 0; i < 4; i++) {
                int nextX = prey.x + dx[i];
                int nextY = prey.y + dy[i];

                if (nextX >= 0 && nextY >= 0 && nextX < N  && nextY < N && !visited[nextX][nextY]) {

                    // 아기상어가 먹이보다 크거나 같거나 + 길이거나  일단 지나갈 수 있는 경우
                    if (map[nextX][nextY] <= sharkSize) {
                        visited[nextX][nextY] = true;

                        // 길이 아니면서 아기상어가 먹이보다 커서 먹을 수 있는 경우
                        if (map[nextX][nextY] > 0 && map[nextX][nextY] < sharkSize) {
                            preyList.add(new Prey(nextX, nextY, prey.distance + 1));
                            tempDistance = prey.distance + 1; // 최종 먹이거리
                        }

                        queue.add(new Prey(nextX, nextY, prey.distance + 1));
                    }
                }
            }
        }

        // 먹을 수 있는 먹이가 없으면 null
        if (preyList.isEmpty()) {
            return null;
        }

        Prey goalPrey = new Prey(0, 0, 1000);


        // 이미 최단거리 먹이가 1개 이상인 경우
        for (Prey prey : preyList) {
            if (prey.distance < goalPrey.distance) {// 상어로부터 거리가 더 가까운 경우
                goalPrey = prey;
            } else if (prey.distance == goalPrey.distance) { // 상어로부터의 거리가 같을때
                if (prey.x < goalPrey.x) {// 더 위에있는거
                    goalPrey = prey;
                } else if (prey.x == goalPrey.x && prey.y < goalPrey.y) { // 다 위에있다면? => 더 된쪽에 있는거
                    goalPrey = prey;
                }
            }
        }

        return goalPrey;
    }
}

//N×N 크기의 공간에 물고기 M마리와 아기 상어 1마리가 있다.
// 공간은 1×1 크기의 정사각형 칸으로 나누어져 있다.
// 한 칸에는 물고기가 최대 1마리 존재한다.
//
//아기 상어와 물고기는 모두 크기를 가지고 있고, 이 크기는 자연수이다.
// 가장 처음에 아기 상어의 크기는 2이고,
// 아기 상어는 1초에 상하좌우로 인접한 한 칸씩 이동한다.
//
//아기 상어는 자신의 크기보다 큰 물고기가 있는 칸은 지나갈 수 없고, 나머지 칸은 모두 지나갈 수 있다.
// 아기 상어는 자신의 크기보다 작은 물고기만 먹을 수 있다.
// 따라서, 크기가 같은 물고기는 먹을 수 없지만, 그 물고기가 있는 칸은 지나갈 수 있다.
//
//아기 상어가 어디로 이동할지 결정하는 방법은 아래와 같다.

//더 이상 먹을 수 있는 물고기가 공간에 없다면 아기 상어는 엄마 상어에게 도움을 요청한다.


//먹을 수 있는 물고기가 1마리라면, 그 물고기를 먹으러 간다.

//    먹을 수 있는 물고기가 1마리보다 많다면, 거리가 가장 가까운 물고기를 먹으러 간다.
//           거리는 아기 상어가 있는 칸에서 물고기가 있는 칸으로 이동할 때, 지나야하는 칸의 개수의 최솟값이다.
//                 거리가 가까운 물고기가 많다면,
//                             가장 위에 있는 물고기,
//                                   그러한 물고기가 여러마리라면,
//                                           가장 왼쪽에 있는 물고기를 먹는다.

// => 현재 상어위치에서 최적의 먹이 구하고 => 움직이고 => 상어 마지막위치에서 또 최적의 먹이 구하고
// 먹이구하는거 bfs => 상어위치 먹이위치로 최신화


//아기 상어의 이동은 1초 걸리고, 물고기를 먹는데 걸리는 시간은 없다고 가정한다.
// 즉, 아기 상어가 먹을 수 있는 물고기가 있는 칸으로 이동했다면, 이동과 동시에 물고기를 먹는다. 물고기를 먹으면, 그 칸은 빈 칸이 된다.
//
//아기 상어는 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가한다. 예를 들어, 크기가 2인 아기 상어는 물고기를 2마리 먹으면 크기가 3이 된다.
//
//공간의 상태가 주어졌을 때, 아기 상어가 몇 초 동안 엄마 상어에게 도움을 요청하지 않고 물고기를 잡아먹을 수 있는지 구하는 프로그램을 작성하시오


//입력
//첫째 줄에 공간의 크기 N(2 ≤ N ≤ 20)이 주어진다.
//
//둘째 줄부터 N개의 줄에 공간의 상태가 주어진다. 공간의 상태는 0, 1, 2, 3, 4, 5, 6, 9로 이루어져 있고, 아래와 같은 의미를 가진다.
//
//0: 빈 칸
//1, 2, 3, 4, 5, 6: 칸에 있는 물고기의 크기
//9: 아기 상어의 위치
//아기 상어는 공간에 한 마리 있다.

//출력
//첫째 줄에 아기 상어가 엄마 상어에게 도움을 요청하지 않고 물고기를 잡아먹을 수 있는 시간을 출력한다.

//3
//0 0 0
//0 0 0
//0 9 0

//0

//3
//0 0 1
//0 0 0
//0 9 0

//3