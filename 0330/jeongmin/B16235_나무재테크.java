import java.io.*;
import java.util.*;

public class B16235_나무재테크 {

    static int N;
    static int M;
    static int K;
    static int[][] arr;
    static int[][] copy;

    // 인접한 8개 칸
    static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

    static class Tree implements Comparable<Tree> {
        int r;
        int c;
        int age;

        public Tree(int r, int c, int age) {
            this.r = r;
            this.c = c;
            this.age = age;
        }

        @Override
        public int compareTo(Tree o) {
            return this.age - o.age;
        }
    }

    public static boolean isWall(int r, int c) {
        return (r<0 || r>=N || c<0 || c>=N);
    }

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N][N];
        copy = new int[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                copy[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                arr[i][j] = 5;
            }
        }

        PriorityQueue<Tree> current = new PriorityQueue<>();
        Queue<Tree> dead = new ArrayDeque<>();
        Queue<Tree> active = new ArrayDeque<>();

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            int age = Integer.parseInt(st.nextToken());

            current.add(new Tree(r, c, age));
        }

        while(K-- > 0) {

            // 봄
            int size = current.size();

            for(int i=0; i<size; i++) {
                Tree tree = current.poll();

                if(arr[tree.r][tree.c] < tree.age) {
                    dead.add(new Tree(tree.r, tree.c, tree.age));
                } else {
                    active.add(new Tree(tree.r, tree.c, tree.age+1));
                    arr[tree.r][tree.c] -= tree.age;
                }
            }

            // 여름
            while(!dead.isEmpty()) {
                Tree tree = dead.poll();

                arr[tree.r][tree.c] += tree.age/2;
            }

            // 봄 지나고 살아남은 애들을 current에 넣음
            size = active.size();

            // 가을
            for(int i=0; i<size; i++) {
                Tree tree = active.poll();

                if((tree.age)%5 == 0) {

                    for(int d=0; d<8; d++) {
                        int nr = tree.r + dr[d];
                        int nc = tree.c + dc[d];

                        if(!isWall(nr,nc)) {
                            current.add(new Tree(nr, nc, 1));
                        }
                    }

                    current.add(new Tree(tree.r, tree.c, tree.age));
                } else {
                    current.add(new Tree(tree.r, tree.c, tree.age));
                }
            }

            // 겨울
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    arr[i][j] += copy[i][j];
                }
            }

        }


        int size = current.size();

        System.out.println(size);

    }
}