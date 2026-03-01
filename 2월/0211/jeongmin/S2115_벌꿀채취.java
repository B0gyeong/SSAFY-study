import java.io.*;
import java.util.*;


public class S2115_벌꿀채취 {
	
	static int N;
	static int M;
	static int C;
	static int[][] map;
	static int max;
	static int ans;
	
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 벌통들의 크기
			M = Integer.parseInt(st.nextToken()); // M개까지 선택 가능
			C = Integer.parseInt(st.nextToken()); // 꿀을 채취할수 있는 최대양 
			
			map = new int[N][N];
			ans = Integer.MIN_VALUE;
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
		
			for(int r1=0; r1<N; r1++) {
				for(int c1=0; c1<=N-M; c1++) {
					
					for(int r2=r1; r2<N; r2++) {
						for(int c2=0; c2<=N-M; c2++) {
							if(r1==r2 && c2 < c1+M) continue;
							
							int profit1 = getProfit(r1, c1);
							int profit2 = getProfit(r2, c2);
							
							ans = Math.max(ans, profit1+profit2);
						}
					}
				}
			}
			// M개 배열 중 조건에 만족하고 최대 수익이 되는 부분집합 찾기
			// profit(0);
			// N*N배열에서 M개 배열 자리 2개 찾기
			System.out.println("#"+t+" "+ans);
		}
	}
	
	public static int getProfit(int r, int c) {
		
		int[] honey = new int[M];
		for(int i=0; i<M; i++) {
			honey[i]=map[r][c+i];
		}
		
		max = 0;
		find(0,0,0,honey);
		
		return max;	
	}
	
	public static void find(int idx, int sum, int powSum, int[] honey) {
		if (sum>C) return;
		
		max = Math.max(max, powSum);
		
		if(idx==M) return;
		
		find(idx+1, sum+honey[idx], powSum+(honey[idx]*honey[idx]), honey);
		
		find(idx+1, sum, powSum, honey);
	}

	
}




