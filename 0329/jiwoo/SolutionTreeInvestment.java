import java.io.*;
import java.util.*;

public class SolutionTreeInvestment{ //백준 나무 제테크
    static int[][] A;
    static int[][] land;
    static int N;
    static int M;
    static int K;
    static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
    static Deque<Integer>[][] dq;
    static ArrayList<Integer>[][] al;
    static int result; //# of alive tree

    public static boolean isWall(int r, int c){
        if(r < 0 || c < 0 || r >= N || c >= N) return true;
        return false;
    }
    
    public static void springSummer(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                int nutrition = 0;
                int numTree = dq[i][j].size();
                Deque<Integer> survived = new ArrayDeque<>();
                for(int k = 0; k < numTree; k++){
                    int currAge = dq[i][j].removeFirst();
                    if(currAge <= land[i][j]){
                        land[i][j] -= currAge;
                        currAge++;
                        survived.addLast(currAge);
                    }else{
                        int tmp = currAge / 2;
                        nutrition += tmp;
                    }
                }

                land[i][j] += nutrition;
                dq[i][j] = survived;
            }
        }
    }

    public static void fall(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                for(int currAge : dq[i][j]){
                    if(currAge % 5 == 0){
                        for(int d = 0; d < 8; d++){
                            int nr = i + dr[d];
                            int nc = j + dc[d];
                            if(!isWall(nr, nc)){
                                dq[nr][nc].addFirst(1);
                            }
                        }
                    }
                }
            }
        }
    }

    public static void winter(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                land[i][j] += A[i][j];
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[N][N];
        land = new int[N][N];
        dq = new ArrayDeque[N][N];
        al = new ArrayList[N][N];
        result = 0;

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine().trim());
            for(int j = 0; j < N; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
                land[i][j] = 5;
                dq[i][j] = new ArrayDeque<>();
                al[i][j] = new ArrayList<>();
            }
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine().trim());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());

            al[x][y].add(z);
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                for(int age : al[i][j]){
                    dq[i][j].addLast(age);
                }
            }
        }

        for(int i = 0; i < K; i++){
            springSummer();
            fall();
            winter();
        }

        //count the number of trees
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                result += dq[i][j].size();
            }
        }

        System.out.println(result);
    }
}