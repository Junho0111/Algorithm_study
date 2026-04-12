package BaekJoon.Two_Pointer;

import java.util.ArrayList;
import java.util.Scanner;

public class BJ1644 {
    static int N;
    static int MaxNum = 4_000_000;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        ArrayList<Integer> primeList = Eratosthenes(MaxNum);

        int start = 0;
        int end = 0;
        int sum = 0;
        int count = 0;

        while (true) {
            if (sum >= N) {
                if (sum == N) {
                    count++;
                }
                sum -= primeList.get(start);
                start++;
            } else if (end == primeList.size()) {
                break;
            } else  {
                sum += primeList.get(end);
                end++;
            }
        }

        System.out.println(count);
    }

    public static ArrayList<Integer> Eratosthenes(int n) { //에라토스테네스의 체 <== 안쓰면 시간초과 O(Nlog(logN)) 거의 O(N)이라고 보면됨
        boolean[] list = new boolean[n + 1];

        for (int i = 2; i <= n; i++) {
            list[i] = true;
        }

        for(int i = 2; i * i <= n; i++) {
            if (list[i]) {
                for (int j = i * i; j <= n; j += i) {
                    list[j] = false;
                }
            }
        }

        ArrayList<Integer> primeList = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (list[i]) {
                primeList.add(i);
            }
        }
        return primeList;
    }
}

// 하나 이상의 연속된 소수의 합으로 나타낼 수 있는 자연수들이 있다. 몇 가지 자연수의 예를 들어 보면 다음과 같다.
//
//  3 : 3 (한 가지)
//  41 : 2+3+5+7+11+13 = 11+13+17 = 41 (세 가지)
//  53 : 5+7+11+13+17 = 53 (두 가지)
// 하지만 연속된 소수의 합으로 나타낼 수 없는 자연수들도 있는데, 20이 그 예이다.
// 7+13을 계산하면 20이 되기는 하나 7과 13이 연속이 아니기에 적합한 표현이 아니다.
// 또한 한 소수는 반드시 한 번만 덧셈에 사용될 수 있기 때문에, 3+5+5+7과 같은 표현도 적합하지 않다.
//
//자연수가 주어졌을 때, 이 자연수를 연속된 소수의 합으로 나타낼 수 있는 경우의 수를 구하는 프로그램을 작성하시오.

//입력
//첫째 줄에 자연수 N이 주어진다. (1 ≤ N ≤ 4,000,000)

//출력
//첫째 줄에 자연수 N을 연속된 소수의 합으로 나타낼 수 있는 경우의 수를 출력한다.

//20

//0