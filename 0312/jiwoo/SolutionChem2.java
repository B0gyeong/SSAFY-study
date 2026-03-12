import java.util.*;
import java.io.*;

class Chem{
    int r;
    int c;
    int width;
    int height;

    public Chem(int r, int c, int w, int h){
        this.r = r;
        this.c = c;
        width = w;
        height = h;
    }
}

public class SolutionChem2 {
    static int N;
    static int[][] board;
    static int result;
    static int[][] dp; //chemListSize x chemListSize
    static boolean[][] visited;
    static boolean[] used;
    static int[] dim;
    static List<Chem> chemList;
    static List<Chem> ordered;
    static int chemListSize;

    public static boolean isWall(int r, int c){
        if(r < 0 || c < 0 || r >= N || c >= N) return true;
        return false;
    }

    public static int calcMul(int a, int k, int b){
        return a * k * b;
    }

    public static Chem startChain(){
        Iterator it = chemList.iterator();
        int height[] = new int[chemListSize];
        int width[] = new int[chemListSize];
        int startIdx;
        int i = 0;

        while(it.hasNext()){
            Chem curr = it.next();
            height[i] = curr.height;
            width[i++] = curr.width;
        }

        
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for(int test_case = 1; test_case <= T; test_case++){
            N = Integer.parseInt(br.readLine().trim());
            board = new int[N][N];
            visited = new boolean[N][N];
            result = Integer.MAX_VALUE;
            dp = new int[N][N][N];
            chemList = new ArrayList<Chem>();

            for(int i = 0; i < N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine().trim());
                for(int j = 0; j < N; j++){
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int w, h;

            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    if(board[i][j] > 0 && !visited[i][j]){
                        w = h = 0;
                        while(j + w < N && board[i][j + w] > 0){
                            w++;
                        }
                        while(i + h < N && board[i + h][j] > 0){
                            h++;
                        }
                        Chem curr = new Chem(i, j, w, h);
                        chemList.add(curr);
                        for(int rc = i; rc < i + w; rc++){
                            for(int cc = j; cc < j + h; cc++){
                                visited[rc][cc] = true;
                            }
                        }
                    }
                }
            }

            chemListSize = chemList.size();
            dp = new int[chemListSize][chemListSize];
            dim = new int[chemListSize + 1];

            System.out.println("#" + test_case + " " + result);
        }
    }
}
