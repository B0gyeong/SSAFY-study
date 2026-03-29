import java.io.*;
import java.util.*;

public class B16235_나무재테크 {
    static int N, M, K;
    static int[][] A, food;
    static Deque<Integer>[][] trees;
    static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

    static class Tree {
        int r, c, age;
        Tree(int r, int c, int age) {
            this.r = r;
            this.c = c;
            this.age = age;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/b_16235.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[N + 1][N + 1];
        food = new int[N + 1][N + 1];
        trees = new ArrayDeque[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                food[i][j] = 5; // 초기 양분은 모두 5
                trees[i][j] = new ArrayDeque<>();
            }
        }

        // 초기 나무 입력
        List<Tree> initialList = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());
            initialList.add(new Tree(r, c, age));
        }

        // 초기 나무들은 나이순으로 정렬해서 넣어줘야 함 (처음 한 번만)
        Collections.sort(initialList, (t1, t2) -> t1.age - t2.age);
        for (Tree t : initialList) {
            trees[t.r][t.c].add(t.age);
        }

        // K년 동안 시뮬레이션 진행
        for (int i = 0; i < K; i++) {
            springAndSummer();
            autumn();
            winter();
        }

        System.out.println(countTrees());
    }

    static void springAndSummer() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                Deque<Integer> dq = trees[i][j];
                Deque<Integer> alive = new ArrayDeque<>();
                int deadToFood = 0;

                // 봄: 어린 나무부터 양분 섭취
                while (!dq.isEmpty()) {
                    int age = dq.pollFirst();
                    if (food[i][j] >= age) {
                        food[i][j] -= age;
                        alive.addLast(age + 1);
                    } else {
                        // 여름: 죽은 나무가 양분으로 변함 (나이 / 2)
                        deadToFood += age / 2;
                    }
                }
                trees[i][j] = alive;
                food[i][j] += deadToFood;
            }
        }
    }

    static void autumn() {
        // 번식할 나무들을 미리 파악 (순회 중 추가하면 에러 발생 가능)
        List<Tree> breeders = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                for (int age : trees[i][j]) {
                    if (age % 5 == 0) {
                        breeders.add(new Tree(i, j, age));
                    }
                }
            }
        }

        // 번식 진행
        for (Tree t : breeders) {
            for (int d = 0; d < 8; d++) {
                int nr = t.r + dr[d];
                int nc = t.c + dc[d];

                if (nr >= 1 && nr <= N && nc >= 1 && nc <= N) {
                    // 핵심: 나이가 1인 나무를 가장 앞에 넣어서 정렬 상태 유지!
                    trees[nr][nc].addFirst(1);
                }
            }
        }
    }

    static void winter() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                food[i][j] += A[i][j];
            }
        }
    }

    static int countTrees() {
        int count = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                count += trees[i][j].size();
            }
        }
        return count;
    }
}