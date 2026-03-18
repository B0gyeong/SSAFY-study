import java.util.*;
import java.io.*;

public class Group{
    int food;
    int repR, repC;
    int repBelief;
    int numMem;
    ArrayList<int[]> members; //[row, col]

    public Group(int f){
        food = f
        repR = -1;
        repC = -1;
        repBelief = -1;
        numMem = 0;
        members = new ArrayList<int[]>();
    }

    public void add(int r, int c, int b){
        members.append([r, c])
        numMem++;

        if(repR == -1){
            repR = r;
            repC = c;
            repBelief = b
        }else{
            if(repBelief < b){
                repR = r;
                repC = c;
                repBelief = b;
            }else if(repBelief == b){
                if(r < repR){
                    repR = r;
                    repC = c;
                }else if(r == repR){
                    if(c < repC){
                        repR = r;
                        repC = c;
                    }
                }
            }
        }
    }

    public updateRepB(int newB){
        repBelief = newB;
    }
}

public class SolutionMintChocoMilk{
    static int N;
    static int T;
    static int[][] F; //신봉음식
    static int[][] B; //신앙심
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static final int[] order = {7, 6, 5, 3, 1, 2, 4}; 
    static int[] result;

    public static boolean isWall(int r, int c){
        if(r < 0 || c < 0 || r >= N || c >= N) return true;
        return false;
    }

    public static Group bfs(int sr, int sc){
        int food = F[sr][sc];

        Queue<int[]> q = new LinkedList<>();
        Group g = new Group(food);
    }

    public static List<Group> grouping(){ 
        List<Group> groups = new ArrayList<>();
        visited = new boolean[N][N];
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(!visited[i][j]){
                    Group g = bfs(i, j);
                    groups.add(g);
                }
            }
        }

        return groups;
    }

    public static void moveBelief(List<Group> groups){
        Iterator it = groups.iterator();

        while(it.hasNext()){
            Group g = it.next();
            int nm = g.numMem;
            int newRB = g.repBelief + nm - 1;
            g.updateRepB(newRB);
            Iterator git = g.members.iterator();
            while(git.hasNext()){
                int[] memIdx = git.next();
                B[memIdx[0]][memIdx[1]]--;
            }
            B[g.repR][g.repC] = newRB;
        }
    }

    public static void morning(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                B[i][j] = B[i][j] + 1;
            }
        }
    }

    public static List<Group> lunch(){
        List<Group> groups = grouping();
        moveBelief(groups);
        return groups;
    }

    public static void dinner(List<Group> groups){
        List<Group> single = new ArrayList<>();
        List<Group> double = new ArrayList<>();
        List<Group> triple = new ArrayList<>();

        Iterator gi = groups.iterator();
        //
    }

    public static void calculate(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                result[order[F[i][j] + 1]]++; //order 손 봐줘야함
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        F = new int[N][N];
        B = new int[N][N];

        for(int i = 0; i < N; i++){
            String f = br.readLine().trim();
            for(int j = 0; j < N; j++){
                char init = f.charAt(j);
                if(init == "T"){
                    F[i][j] = 4;
                }else if(init == "C"){
                    F[i][j] = 2;
                }else{
                    F[i][j] = 1;
                }
            }
        }

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine().trim());
            for(int j = 0; j < N; j++){
                B[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < T; i++){
            result = new int[7];

            morning();
            List<Group> groups = lunch();
            dinner(groups);
            calculate();
            
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < 7; i++){
                sb.append(result[i] + " ");
            }
            System.out.println(sb.toString());
        }
    }
}