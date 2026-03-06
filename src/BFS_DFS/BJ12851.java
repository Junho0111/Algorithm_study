package BFS_DFS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BJ12851 {
    private static ArrayList<Integer> times;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 수빈 위치
        int k = sc.nextInt(); // 동생 위치

        bfs(n, k);
    }

    public static void bfs(int n, int k) {
        int[] time = new int[100_001];
        Arrays.fill(time, -1);

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(n);
        time[n] = 0;

        int minTime = 100_001;
        int cnt = 0;

        while (!queue.isEmpty()) {
            int x = queue.poll(); // 현재 위치 추적용

            if (minTime < time[x]) {
                continue;
            }

            if (x == k) {
                minTime = time[x];
                cnt++;
                continue;
            }

            int[] walkAndTp = {x - 1, x + 1, x * 2};
            for (int next : walkAndTp) {
                if (next >= 0 && next <= 100_000) {
                    if (time[next] == -1 || time[next] == time[x] + 1) { // 첫 방문 또는 중복방문(이미 최단시간으로 방문함)이지만 이것이 똑같이 최단시간일떄
                        time[next] = time[x] + 1;
                        queue.add(next);
                    }
                }
            }
        }
        System.out.println(minTime);
        System.out.println(cnt);
    }
}
//수빈 n 걷거나 순간이동 가능
// x일때 걷는다면 1초후 x-1 또는 x+1
// x일때 순간이동 1초후 2*x
//동생 k

//수빈이가 동생을 찾을 수 있는 최소 시간
//5 17 => 4 2
//가장 빠른 시간으로 찾는 방법이 몇 가지인지

//첫째 줄에 수빈이가 동생을 찾는 가장 빠른 시간을 출력한다.
//둘째 줄에는 가장 빠른 시간으로 수빈이가 동생을 찾는 방법의 수를 출력한다.