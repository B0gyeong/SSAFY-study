import java.util.*;
import java.io.*;

public class SolutionPalindrome2 {
    static int N = 100;
    static char[][] map;
    static int maxLength;
    // 각 줄마다 max length -> 1일때까지 sliding window로 palindrome 찾기. palindrome 찾은 거 있으면 그 max length로 가지치기

    public static void main(String[] args) throws Exception{
        int T = 10;
        for(int test_case = 1; test_case <= T; test_case++){
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int tc = Integer.parseInt(br.readLine().trim());

            for(int i = 0; i < N; i++){
                String line = br.readLine().trim();
                for(int j = 0; j < N; j++){
                    map[i][j] = line.charAt(j);
                }
            }

            for(int i = 0; i < N; i++){
                
            }

            System.out.println("#" + tc + " " + maxLength);
        }
    }
}
