import java.util.*;
import java.io.*;

class Cell{
    int r;
    int c;
    int cnt;
    int dir;

    public Cell(int r, int c, int cnt, int d){
        this.r = r;
        this.c = c;
        this.cnt = cnt;
        dir = d;
    }
}

class BucketCell{
    int sum;
    int maxCnt;
    int maxDir;

    public BucketCell(int cnt, int dir){
        sum = cnt;
        maxCnt = cnt;
        maxDir = dir;
    }

    public void update(int cnt, int dir){
        sum += cnt;
        if(cnt > maxCnt){
            maxCnt = cnt;
            maxDir = dir;
        }
    }
}

public class SolutionMicroOrganism {
    static int N;
    static int M;
    static int K;
    static int[][] info;
    static final int[] dr = {-1, 1, 0, 0}; //top, bottom, left, right
    static final int[] dc = {0, 0, -1, 1};
    static BucketCell[][] bucket; // bucket[next_r][next_c]
    static List<Cell> active;

    public static boolean isMed(int r, int c){
        if(r == 0 || c == 0 || r == N - 1 || c == N - 1) return true;
        return false;
    }

    public static int totalOrg(){
        int ret = 0;

        for(Cell c : active){
            ret += c.cnt;
        }

        return ret;
    }

    public static int opposite(int dir){
        if(dir == 0) return 1; //top -> bottom
        if(dir == 1) return 0; //bottom -> top
        if(dir == 2) return 3; //left -> right
        if(dir == 3) return 2; //right -> left
        return -1; // invalid direction
    }

    public static void merge(){
        active.clear();
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(bucket[i][j] != null){
                    Cell c = new Cell(i, j, bucket[i][j].sum, bucket[i][j].maxDir);
                    active.add(c);
                }
            }
        }
        return;
    }

    public static void move(){
        for(int i = 0; i < M; i++){
            bucket = new BucketCell[N][N];

            for(Cell c : active){
                int nr = c.r + dr[c.dir];
                int nc = c.c + dc[c.dir];
                int cnt = c.cnt;
                int dir = c.dir;

                if(isMed(nr, nc)){
                    cnt = c.cnt/2;
                    dir = opposite(dir);
                }

                if(cnt == 0) continue;
    
                BucketCell b = bucket[nr][nc];
                if(b == null){
                    b = new BucketCell(cnt, dir);
                    bucket[nr][nc] = b;
                }else{
                    b.update(cnt, dir);
                }
            }
    
            merge();
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for(int test_case = 1; test_case <= T; test_case++){
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            active = new ArrayList<Cell>();
            int nr;
            int nc;
            int result = 0;

            info = new int[K][4];
            for(int i = 0; i < K; i++){
                st = new StringTokenizer(br.readLine().trim());
                info[i][0] = Integer.parseInt(st.nextToken()); //세로
                info[i][1] = Integer.parseInt(st.nextToken()); //가로
                info[i][2] = Integer.parseInt(st.nextToken()); //미생물 수
                info[i][3] = Integer.parseInt(st.nextToken()) - 1; //방향
            }

            for(int i = 0; i < K; i++){
                Cell c = new Cell(info[i][0], info[i][1], info[i][2], info[i][3]);
                active.add(c);
            }

            move();

            result = totalOrg();
            System.out.println("#" + test_case + " " + result);
        }
    }
}
