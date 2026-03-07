package BaekJoon.BFS_DFS;

import java.util.*;

public class BJ13549 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 수빈 위치
        int k = sc.nextInt(); // 동생 위치
        int x = n; // 수빈이 위치 추적 용

        bfs(n, k);
    }

    public static void bfs(int n, int k) {
        int[] time = new int[100_001];
        Arrays.fill(time, -1);

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(n);
        time[n] = 0;

        while (!queue.isEmpty()) {
            int x = queue.poll();
            int tp = x * 2;

            if (x == k) {
                System.out.println(time[x]);
                return;
            }

            //time에서 -1을 통해 방문여부를 알수있고, 이후부턴 -1이 아니면 방문을했다 && 몇초? 동시에 만족
            if (tp <= 100_000 && time[tp] == -1) {
                time[tp] = time[x]; //0초
                queue.addFirst(tp); // 데크에서 0초로 이동한 경우부터 볼수 있도록 addFirst
            }

            int[] walk = {x - 1, x + 1};
            for (int next : walk) {
                if (next >= 0 && next <= 100_000 && time[next] == -1) {
                    time[next] = time[x] + 1; // 1초 ++
                    queue.addLast(next); // 그냥 add하면 addFisrt한이유가 없어져버림 우선순위때문에 addLast
                }
            }
        }
    }
}
//수빈 n 걷거나 순간이동 가능
// x일때 걷는다면 1초후 x-1 또는 x+1
// x일때 순간이동 0초후 2*x
//동생 k

//수빈이가 동생을 찾을 수 있는 최소 시간
//5 17 => 2초
//힌트: 수빈이가 5-10-9-18-17 순으로 가면 2초만에 동생을 찾을 수 있다.