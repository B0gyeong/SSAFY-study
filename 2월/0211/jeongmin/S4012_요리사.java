
import java.io.*;
import java.util.*;


public class S4012_요리사 {
	
	static int N;
	static int[][] map;
	static int min;
	static int[] selA;
	static boolean[] visited;
	
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int t=1; t<T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			min = Integer.MAX_VALUE;
			selA = new int[N/2];
			
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// N개 중 N/2개 조합
			comb(0, 0);
			
			// N/2개 중 2개를 고른 순열의합 구하기
			
			System.out.println("#"+t+" "+min);
		}
	}
	
	public static void comb(int m, int start) {
		if(m==N/2) {
			// 시너지 계산 - sel 이용
			cal();
			return;
		}
		
		for(int i=start; i<N; i++) {
			selA[m] = i;
			comb(m+1, i+1);
		}
	}
	
	public static void cal() {
		int sumA =0;
		int[] selB = new int[N/2];
		int bIdx = 0;
		for(int i=0; i<N; i++) {
			boolean isA = false;
			for(int a : selA) {
				if(i == a) { isA = true; break; }
			}
			if(!isA) selB[bIdx++] = i;
		}
		
		for(int i=0; i<N/2; i++) {
			for(int j=0; j<N/2; j++) {
				if(i==j) continue;
				
				int row = selA[i];
				int col = selA[j];
				
				sumA += map[row][col];
			} 
		}
		
		int sumB=0;
		for(int i=0; i<N/2; i++) {
			for(int j=0; j<N/2; j++) {
				if(i==j) continue;
				sumB += map[selB[i]][selB[j]];
			}
		}
		
		min = Math.min(min, Math.abs(sumA-sumB));
		
	}
	
}




