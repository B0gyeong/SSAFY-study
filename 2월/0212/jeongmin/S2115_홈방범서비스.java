
import java.io.*;
import java.util.*;


public class S2115_홈방범서비스 {
	
	static int[][] city;
	static int K; // 서비스 영역 크기 ( K부터 시작하자 ) 
	static boolean status;
	static int N;
	static int M;
	static int max;
	
	public static void main(String args[]) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int t=1; t<=T; t++) {
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 도시 크기 N 
			M = Integer.parseInt(st.nextToken()); // 집 하나당 돈 
			
			city = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					city[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			if(N%2==0) {
				K = N+1;
			} else {
				K = N;
			}
			
			max = 0;
			status = false;
			
			// 모든 집에서 K를 주고 조사를 시작하자
			while(true) {
				for(int i=0; i<N; i++) {
					for(int j=0; j<N; j++) {
						
							
						getCnt(i, j); // 현재 K와 현재 위치로 최대 집 개수 갱신
							
							
						
					}
				}
				
				if(status) break;
				
				K--;

			}
			
			System.out.println("#"+t+" "+max);

		}
	}
	
	public static void getCnt(int r, int c) {

		int cnt=0;
		
		// 모든 집에서 거리에 닿는지 조사
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int dist = Math.abs(r-i)+Math.abs(c-j);
				if(city[i][j]==1&&dist<K) cnt++;
			}
		}
		
		double profit = cnt*M;
		double cost = Math.pow(K,2)+Math.pow(K-1,2);
		
		if(profit>=cost) {
			max = Math.max(max, cnt);
			status = true;
		}
	}

}




