package BaekJoon.BFS_DFS;

import java.util.*;

public class BJ2251 {
    static int[] maxBottle = new int[3]; // A, B, C의 최대 용량
    static ArrayList<Integer> A_BottleZero = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        maxBottle[0] = sc.nextInt(); //a
        maxBottle[1] = sc.nextInt(); //b
        maxBottle[2] = sc.nextInt(); //c

        bfs();

        Collections.sort(A_BottleZero);

        for (int A : A_BottleZero) {
            System.out.print(A + " ");
        }
    }

    static class Bottle{
        int a;
        int b;
        int c;

        public Bottle(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    static void bfs() {
        ArrayDeque<Bottle> bottleCaseList = new ArrayDeque<>();
        boolean[][] visited = new boolean[1000][1000];

        bottleCaseList.add(new Bottle(0, 0, maxBottle[2]));
        visited[0][0] = true;


        while (!bottleCaseList.isEmpty()) {
            Bottle currCase = bottleCaseList.poll();
            int a = currCase.a;
            int b = currCase.b;
            int c = currCase.c;

            if (a == 0) {
                A_BottleZero.add(c);
            }

            // 병이 3개이니 자기자신한테 붓는거제외 6가지 경우
            //i가 자기 제외한 나머지 2개 (1, 2)에 한번씩 붓고 다음 병으로 이동하고 이거 반복
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // 물을 붓는 병과 받는병이 같지 않을때만
                    if (i != j) {
                        int[] bottles = {a, b, c};

                        // i쪽이 작으면 붓는쪽이 남김없이 붓는거고 반대이면 받는쪽이 풀잔 받고 i가 조금 남은 상태
                        int water = Math.min(bottles[i], maxBottle[j] - bottles[j]);
                        bottles[i] -= water; // 붓는쪽 물 제거
                        bottles[j] += water; // 받는쪽 물 증가

                        //한번 부어진 경우 다시 방문X
                        if (!visited[bottles[0]][bottles[1]]) {
                            visited[bottles[0]][bottles[1]] = true;
                            bottleCaseList.add(new Bottle(bottles[0], bottles[1], bottles[2]));
                        }
                    }
                }
            }
        }
    }
}

//각각 부피가 A, B, C(1≤A, B, C≤200) 리터인 세 개의 물통이 있다.
// 처음에는 앞의 두 물통은 비어 있고, 세 번째 물통은 가득(C 리터) 차 있다.
// 이제 어떤 물통에 들어있는 물을 다른 물통으로 쏟아 부을 수 있는데, 이때에는 한 물통이 비거나, 다른 한 물통이 가득 찰 때까지 물을 부을 수 있다.
// 이 과정에서 손실되는 물은 없다고 가정한다.
//
//이와 같은 과정을 거치다보면 세 번째 물통(용량이 C인)에 담겨있는 물의 양이 변할 수도 있다.
// 첫 번째 물통(용량이 A인)이 비어 있을 때,
// 세 번째 물통(용량이 C인)에 담겨있을 수 있는 물의 양을 모두 구해내는 프로그램을 작성하시오.

//입력
//첫째 줄에 세 정수 A, B, C가 주어진다.

//출력
//첫째 줄에 공백으로 구분하여 답을 출력한다. 각 용량은 오름차순으로 정렬한다.

//8 9 10

//1 2 8 9 10