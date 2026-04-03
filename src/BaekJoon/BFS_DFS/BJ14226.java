package BaekJoon.BFS_DFS;

import java.util.ArrayDeque;
import java.util.Scanner;

public class BJ14226 {
    static int S;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        S = sc.nextInt();

        System.out.println(bfs(S));
    }

    static class Emoticon {
        int show;
        int clipBoard;
        int time;

        public Emoticon(int show, int clipBoard, int time) {
            this.show = show;
            this.clipBoard = clipBoard;
            this.time = time;
        }
    }

    private static int bfs(int S) {
        ArrayDeque<Emoticon> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[1001][1001];

        queue.add(new Emoticon(1, 0, 0));
        visited[1][0] = true;

        while (!queue.isEmpty()) {
            Emoticon emoticon = queue.poll();

            if (emoticon.show == S) {
                return emoticon.time;
            }

            // 복사
            if (!visited[emoticon.show][emoticon.show]) {
                visited[emoticon.show][emoticon.show] = true;
                queue.add(new Emoticon(emoticon.show, emoticon.show, emoticon.time + 1));
            }

            // 붙여넣기
            int nextShow = emoticon.show + emoticon.clipBoard;
            if (emoticon.clipBoard > 0 && nextShow <= 1000) {
                if (!visited[nextShow][emoticon.clipBoard]) {
                    visited[nextShow][emoticon.clipBoard] = true;
                    queue.add(new Emoticon(nextShow, emoticon.clipBoard, emoticon.time + 1));
                }
            }

            // 삭제
            if (emoticon.show > 0) {
                if (!visited[emoticon.show - 1][emoticon.clipBoard]) {
                    visited[emoticon.show - 1][emoticon.clipBoard] = true;
                    queue.add(new Emoticon(emoticon.show - 1, emoticon.clipBoard, emoticon.time + 1));
                }
            }
        }
        return -1;
    }
}

//영선이는 매우 기쁘기 때문에, 효빈이에게 스마일 이모티콘을 S개 보내려고 한다.
//
//영선이는 이미 화면에 이모티콘 1개를 입력했다.
// 이제, 다음과 같은 3가지 연산만 사용해서 이모티콘을 S개 만들어 보려고 한다.
//
//화면에 있는 이모티콘을 모두 복사해서 클립보드에 저장한다.
//클립보드에 있는 모든 이모티콘을 화면에 붙여넣기 한다.
//화면에 있는 이모티콘 중 하나를 삭제한다.
//모든 연산은 1초가 걸린다.
// 또, 클립보드에 이모티콘을 복사하면 이전에 클립보드에 있던 내용은 덮어쓰기가 된다.
// 클립보드가 비어있는 상태에는 붙여넣기를 할 수 없으며, 일부만 클립보드에 복사할 수는 없다.
// 또한, 클립보드에 있는 이모티콘 중 일부를 삭제할 수 없다.
// 화면에 이모티콘을 붙여넣기 하면, 클립보드에 있는 이모티콘의 개수가 화면에 추가된다.
//
//영선이가 S개의 이모티콘을 화면에 만드는데 걸리는 시간의 최솟값을 구하는 프로그램을 작성하시오.

//입력
//첫째 줄에 S (2 ≤ S ≤ 1000) 가 주어진다.

//출력
//첫째 줄에 이모티콘을 S개 만들기 위해 필요한 시간의 최솟값을 출력한다.

//2
//2

//18
//8