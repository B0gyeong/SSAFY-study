import java.util.*;
import java.io.*;

public class SolutionSharkTornado{
    static int N;
    static int[][] A;
    static int torR;
    static int torC;
    static int dir;
    static int[] dr = {0, -1, 0, 1};
    static int[] dc = {-1, 0, 1, 0};
    static boolean[] visited;
    static int moveLen; //length to move
    static int moveCnt; //# of times moved with the same moveLen
    static int result; //amount of sand that left the A

    public static boolean isWall(int r, int c){
        if(r < 0 || c < 0 || r >= N || c >= N) return true;
        return false;
    }

    public static void addSand(int r, int c, int sand){
        if(!isWall(r, c)){
            A[r][c] += sand;
        }else result += sand;

        return;
    }

    public static void spread(int r, int c){ // y location
        int per5 = (int) Math.floor(A[r][c] * 0.05);
        int per10 = (int) Math.floor(A[r][c] * 0.1);
        int per7 = (int) Math.floor(A[r][c] * 0.07);
        int per2 = (int) Math.floor(A[r][c] * 0.02);
        int per1 = (int) Math.floor(A[r][c] * 0.01);
        int alpha = (int) Math.floor(A[r][c] - per5 - 2 * (per10 + per7 + per2 + per1));

        int nr = r + 2 * dr[dir];
        int nc = c + 2 * dc[dir];
        addSand(nr, nc, per5);

        nr = r + dr[dir];
        nc = c + dr[dir];
        addSand(nr, nc, alpha);

        nr = r + dr[(dir + 1) % 4];
        nc = c + dc[(dir + 1) % 4];
        addSand(nr, nc, per7);

        nr = r + dr[(dir + 3) % 4];
        nc = c + dr[(dir + 3) % 4];
        addSand(nr, nc, per7);

        nr = r + 2 * dr[(dir + 1) % 4];
        nc = c + 2 * dc[(dir + 1) % 4];
        addSand(nr, nc, per2);

        nr = r + 2 * dr[(dir + 3) % 4];
        nc = c + 2 * dr[(dir + 3) % 4];
        addSand(nr, nc, per2);

        nr = r + dr[dir] + dr[(dir + 1) % 4];
        nc = c + dc[dir] + dc[(dir + 1) % 4];
        addSand(nr, nc, per10);

        nr = r + dr[dir] + dr[(dir + 3) % 4];
        nc = c + dc[dir] + dc[(dir + 3) % 4];
        addSand(nr, nc, per10);

        nr = r + dr[(dir + 1) % 4] + dr[(dir + 2) % 4];
        nc = c + dc[(dir + 1) % 4] + dc[(dir + 2) % 4];
        addSand(nr, nc, per1);

        nr = r + dr[(dir + 2) % 4] + dr[(dir + 3) % 4];
        nc = c + dc[(dir + 2) % 4] + dc[(dir + 3) % 4];
        addSand(nr, nc, per1);

        return;
    }

    public static void move(){
        while(!(torR == 0 && torC == 0)){
            if(moveCnt == 2){
                moveLen++;
                moveCnt = 0;
            }

            for(int i = 0; i < moveLen; i++){
                int nr = torR + dr[dir];
                int nc = torC + dc[dir];
                if(!isWall(nr, nc)){
                    torR = nr;
                    torC = nc;

                    spread(torR, torC);
                    A[torR][torC] = 0;
                    moveCnt++;
                    dir = (dir + 1) % 4;
                }else{
                    System.out.println("wall");
                    return;
                }
            }
            System.out.println("tr: " + torR + ", tc: " + torC);
        }

        return;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());

        A = new int[N][N];
        visited = new boolean[N][N];
        torR = torC = N/2 + 1;
        moveLen = 1;
        moveCnt = 0;
        dir = 0;

        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            for(int j = 0; j < N; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        move();

        System.out.println(result);
    }
}