package BaekJoon.BFS_DFS;

import java.util.ArrayDeque;
import java.util.Scanner;

public class BJ12869 {

    static int N; //일꾼 수
    static int[] HP = new int[3];

    static int[][] mutalAttackCases = { {9, 3, 1}, {9, 1, 3}, {3, 9, 1}, {3, 1, 9}, {1, 9, 3}, {1, 3, 9}} ;
    static int minCount;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        for (int i = 0; i < N; i++) {
            HP[i] = sc.nextInt();
        }

        bfs();

        System.out.println(minCount);
    }

    static class ScvStatus {
        int SCV1;
        int SCV2;
        int SCV3;
        int attackCount;

        public ScvStatus(int SCV1, int SCV2, int SCV3, int attackCount) {
            this.SCV1 = SCV1;
            this.SCV2 = SCV2;
            this.SCV3 = SCV3;
            this.attackCount = attackCount;
        }
    }

    private static void bfs() {
        ArrayDeque<ScvStatus> queue = new ArrayDeque<>();
        boolean[][][] visited = new boolean[100][100][100];

        queue.add(new ScvStatus(HP[0], HP[1], HP[2], 0));
        visited[HP[0]][HP[1]][HP[2]] = true;

        while (!queue.isEmpty()) {
            ScvStatus scvStatus = queue.poll();

            if (scvStatus.SCV1 == 0 && scvStatus.SCV2 == 0 && scvStatus.SCV3 == 0) {
                minCount = scvStatus.attackCount;
                break;
            }

            for (int i = 0; i < 6; i++) {
                int nextSCV1 = Math.max(0, scvStatus.SCV1 - mutalAttackCases[i][0]);
                int nextSCV2 = Math.max(0, scvStatus.SCV2 - mutalAttackCases[i][1]);
                int nextSCV3 = Math.max(0, scvStatus.SCV3 - mutalAttackCases[i][2]);

                if (!visited[nextSCV1][nextSCV2][nextSCV3]) {
                    visited[nextSCV1][nextSCV2][nextSCV3] = true;
                    queue.add(new ScvStatus(nextSCV1, nextSCV2, nextSCV3, scvStatus.attackCount + 1));
                }

            }
        }
    }
}

//수빈이는 강호와 함께 스타크래프트 게임을 하고 있다. 수빈이는 뮤탈리스크 1개가 남아있고, 강호는 SCV N개가 남아있다.
//
//각각의 SCV는 남아있는 체력이 주어져있으며, 뮤탈리스크를 공격할 수는 없다. 즉, 이 게임은 수빈이가 이겼다는 것이다.
//
//뮤탈리스크가 공격을 할 때, 한 번에 세 개의 SCV를 공격할 수 있다.
//
//첫 번째로 공격받는 SCV는 체력 9를 잃는다.
//두 번째로 공격받는 SCV는 체력 3을 잃는다.
//세 번째로 공격받는 SCV는 체력 1을 잃는다.
//SCV의 체력이 0 또는 그 이하가 되어버리면, SCV는 그 즉시 파괴된다. 한 번의 공격에서 같은 SCV를 여러 번 공격할 수는 없다.
//
//남아있는 SCV의 체력이 주어졌을 때, 모든 SCV를 파괴하기 위해 공격해야 하는 횟수의 최솟값을 구하는 프로그램을 작성하시오.


//입력
//첫째 줄에 SCV의 수 N (1 ≤ N ≤ 3)이 주어진다. 둘째 줄에는 SCV N개의 체력이 주어진다. 체력은 60보다 작거나 같은 자연수이다.


//출력
//첫째 줄에 모든 SCV를 파괴하기 위한 공격 횟수의 최솟값을 출력한다.

//3
//12 10 4

//2

//1, 3, 2 순서대로 공격을 하면, 남은 체력은 (12-9, 10-1, 4-3) = (3, 9, 1)이다. 2, 1, 3 순서대로 공격을 하면, 남은 체력은 (0, 0, 0)이다.