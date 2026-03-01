import java.io.*;
import java.util.*;

public class Solution {
	
	static int N;
	static int[][] P;
	static boolean[] visited;
	static double max;
	
	public static void dfs(int idx, double cur) {
		// 가지치기 : 현재 확률이 이미 구한 최댓값보다 작거나 같으면 중단 
		if(cur<=max) {
			return;
		}
		
		if(idx==N) {
			if(cur>max) {
				max = cur;
			}
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				dfs(idx+1, cur*(P[idx][i]*0.01));
				visited[i] = false;
			}
		}
		
	}
	
	public static void main(String args[]) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			P = new int[N][N];
			visited = new boolean[N];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					P[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			max = Double.MIN_VALUE;
			dfs(0, 1.0);
			
			System.out.printf("#%d %.6f\n", t, max*100);
			
		}
	}
}




