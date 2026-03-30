import java.util.*;
import java.io.*;

public class SolutionLunch{
    static int N;
    static int[][] map;
    static List<int[]> people; //[r, c]
    static int[][] stairs; //[stair idx][r, c, length]
    static int[][] distance; //[distance per person][stair idx]
    static int[] selected;
    static final int numStair = 2;

    public static void 

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for(int test_case = 1; test_case <= T; test_case++){
            N = Integer.parseInt(br.readLine().trim());
            map = new int[N][N];
            stairs = new int[2][3];
            stairIdx = 0;

            for(int i = 0; i < N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine().trim());
                for(int j = 0; j < N; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j] == 0) continue;

                    if(map[i][j] == 1){
                        int[] person = {i, j};
                        people.append(person);
                    }else if(map[i][j] > 1){
                        stairs[stairIdx++] = {i, j, map[i][j]};
                    }
                }
            }

            int numPer = people.size();

            selected = new int[numPer];
            distance = new int[numPer][numStair];

            for(int i = 0; i < numPer; i++){
                int[] per = people.get(i);
                for(int j = 0; j < numStair; j++){
                    int[] stair = stairs.get(j);
                    distance[i][j] = Math.abs(per[0] - stair[0]) + Math.abs(per[1] - stair[1]) + 1;
                }
            }
        }
    }
}