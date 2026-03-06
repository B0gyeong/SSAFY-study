import java.util.*;
import java.io.*;

public class SolutionNumGroup {
    static int N; //사람 수  - 입력값이 1부터 시작하는 관계로 배열에 넣기 편하게 1 빼서 0부터 넣음
    static int M; //관계 수
    static int[] parent;
    static int[][] E;
    static int cnt;

    public static void initialize(){
        parent = new int[N];
        E = new int[M][2];

        for(int i = 0; i < N; i++){
            parent[i] = i;
        }
    }

    public static int findSet(int i){
        if(parent[i] != i) parent[i] = findSet(parent[i]);
        return parent[i];
    }

    public static void union(int i, int j){
        int i_rep = findSet(i);
        int j_rep = findSet(j);

        parent[j_rep] = i_rep;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for(int test_case = 1; test_case <= T; test_case++){
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            initialize();

            for(int i = 0; i < M; i++){
                st = new StringTokenizer(br.readLine().trim());
                E[i][0] = Integer.parseInt(st.nextToken()) - 1;
                E[i][1] = Integer.parseInt(st.nextToken()) - 1;
            }

            for(int i = 0; i < M; i++){
                union(E[i][0], E[i][1]);
            }

            HashSet<Integer> distinctSet = new HashSet<>();

            distinctSet.add(findSet(parent[0]));

            for(int i = 0; i < N; i++){
                distinctSet.add(findSet(i));
            }

            cnt = distinctSet.size();

            System.out.println("#" + test_case + " " + cnt);
        }
    }
}
