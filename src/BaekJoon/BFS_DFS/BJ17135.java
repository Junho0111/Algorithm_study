package BaekJoon.BFS_DFS;

import java.util.ArrayList;
import java.util.Scanner;

public class BJ17135 {
    static int N;
    static int M;
    static int D;

    static int[][] map;
    static int[][] tempMap;

    static int maxKill = 0;
    static int[] selectedArchers = new int[3];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        D = sc.nextInt();

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        ArcherCaseCreate(0, 0);

        System.out.println(maxKill);
    }

    static class Enemy {
        int x;
        int y;
        int distance;

        public Enemy(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }

    private static void ArcherCaseCreate(int currentArcher, int archerCount) {
        if (archerCount == 3) {
            maxKill = Math.max(maxKill, run());
            return;
        }

        for (int i = currentArcher; i < M; i++) { // 0 1 2 => 0 1 3 => 0 1 4 이런식
            selectedArchers[archerCount] = i;
            ArcherCaseCreate(i + 1, archerCount + 1);
        }
    }

    private static int run() {
        int kill = 0;
        tempMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            tempMap[i] = map[i].clone();
        }

        for (int i = 0; i < N; i++) {
            ArrayList<Enemy> targets = new ArrayList<>();

            for (int j = 0; j < 3; j++) {
                int archer = selectedArchers[j];
                Enemy enemy = findBestEnemy(archer);

                if (enemy != null) {
                    targets.add(enemy);
                }
            }

            for (Enemy target : targets) {
                if (tempMap[target.x][target.y] == 1) {
                    tempMap[target.x][target.y] = 0;
                    kill++;
                }
            }

            downEnemy();
        }

        return kill;
    }

    private static Enemy findBestEnemy(int archer) {
        Enemy goalEnemy = null;
        int minDistance = D + 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tempMap[i][j] == 1) {
                    int currentDist = Math.abs(N - i) + Math.abs(archer - j);

                    if (currentDist <= D) { // 공격사거리이면
                        if (goalEnemy == null) { // 처음이면 무조건 일단 넣고 봄
                            goalEnemy = new Enemy(i, j, currentDist);
                            minDistance = currentDist;
                        } else {
                            if (currentDist < minDistance) { // 거리가 더 짧으면
                                goalEnemy = new Enemy(i, j, currentDist);
                                minDistance = currentDist;
                            } else if (currentDist == minDistance) { // 거리가 같은 경우에
                                if (j < goalEnemy.y) { // 좀 더 왼쪽에 있는게 최종
                                    goalEnemy = new Enemy(i, j, currentDist);
                                }
                            }
                        }
                    }
                }
            }
        }
        return goalEnemy;
    }

    private static void downEnemy() {
        for (int i = N - 1; i > 0; i--) {
            for (int j = 0; j < M; j++) {
                tempMap[i][j] = tempMap[i - 1][j];
            }
        }
        for (int j = 0; j < M; j++) {
            tempMap[0][j] = 0;
        }
    }
}

//캐슬 디펜스는 성을 향해 몰려오는 적을 잡는 턴 방식의 게임이다.
// 게임이 진행되는 곳은 크기가 N×M인 격자판으로 나타낼 수 있다.
// 격자판은 1×1 크기의 칸으로 나누어져 있고, 각 칸에 포함된 적의 수는 최대 하나이다.
// 격자판의 N번행의 바로 아래(N+1번 행)의 모든 칸에는 성이 있다.
//
//성을 적에게서 지키기 위해 궁수 3명을 배치하려고 한다.
// 궁수는 성이 있는 칸에 배치할 수 있고, 하나의 칸에는 최대 1명의 궁수만 있을 수 있다.
// 각각의 턴마다 궁수는 적 하나를 공격할 수 있고, 모든 궁수는 동시에 공격한다.

// 궁수가 공격하는 적은 거리가 D이하인 적 중에서 가장 가까운 적이고,
// 그러한 적이 여럿일 경우에는 가장 왼쪽에 있는 적을 공격한다.
// 같은 적이 여러 궁수에게 공격당할 수 있다. 공격받은 적은 게임에서 제외된다.

// 궁수의 공격이 끝나면, 적이 이동한다.
// 적은 아래로 한 칸 이동하며, 성이 있는 칸으로 이동한 경우에는 게임에서 제외된다.
// 모든 적이 격자판에서 제외되면 게임이 끝난다.
//
//게임 설명에서 보다시피 궁수를 배치한 이후의 게임 진행은 정해져있다.
// 따라서, 이 게임은 궁수의 위치가 중요하다. 격자판의 상태가 주어졌을 때, 궁수의 공격으로 제거할 수 있는 적의 최대 수를 계산해보자.
//
//격자판의 두 위치 (r1, c1), (r2, c2)의 거리는 |r1-r2| + |c1-c2|이다.

//입력
//첫째 줄에 격자판 행의 수 N, 열의 수 M, 궁수의 공격 거리 제한 D가 주어진다. 둘째 줄부터 N개의 줄에는 격자판의 상태가 주어진다.
// 0은 빈 칸, 1은 적이 있는 칸이다.

//출력
//첫째 줄에 궁수의 공격으로 제거할 수 있는 적의 최대 수를 출력한다.

//5 5 1
//0 0 0 0 0
//0 0 0 0 0
//0 0 0 0 0
//0 0 0 0 0
//1 1 1 1 1

//3