package samsung01;

import java.util.*;
import java.io.*;

public class S2112_보호필름 {
	
	static int D;
	static int W;
	static int K;
	static int[][] film;
	static int minInputCnt;
	
	public static boolean testPer() {
		
		int cCnt = 0;
		for(int i=0; i<W; i++) {
			// 매 줄마다 초기화 
			int rCnt = 1;
			boolean success = false;
			
			for(int r=1; r<D; r++) {
				if(film[r][i]==film[r-1][i]){
					rCnt++;
				} else {
					rCnt = 1;
				}
				
				if(rCnt == K) {
					success = true;
					break; // 합격하면 더 볼필요 없음 
				}
			}
			
			// 한줄 실패면 바로 종료 
			if(!success) return false;
			// 성공했으면 성공한 col cnt 증가 -> if(rCnt==K)에서 하면 [A,A,B,A,A]에서 cCnt가 2번 증가
			cCnt++;
		}
		
		if(cCnt == W) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void solve(int idx, int start, int curCnt) {
		
		if(curCnt>minInputCnt) return;
		
		if(testPer()) {
			minInputCnt = Math.min(minInputCnt, curCnt);
			return;
		}
		
		for(int i=start; i<D; i++) {
			int[] tmp = new int[W];
			for(int j=0; j<W; j++) {
				tmp[j] =  film[i][j];
			}
			
			// i행을 전부 A로 만들기 
			for(int j=0; j<W; j++) {
				film[i][j] = 0;
			}
			solve(idx+1, i+1, curCnt+1);
			
			// i행을 전부 B로 만들기
			for(int j=0; j<W; j++) {
				film[i][j] = 1;
			}
			solve(idx+1, i+1, curCnt+1);
			
			for(int j=0; j<W; j++) {
				film[i][j] = tmp[j];
			}
			
		}
	
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		for(int t=1; t<=T; t++) {
			
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			film = new int[D][W];
			
			// 0:A 1:B
			for(int i=0; i<D; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<W; j++) {
					film[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			minInputCnt = Integer.MAX_VALUE;
			// 성능 검사
			if(testPer()) {
				System.out.printf("#%d %d\n", t, 0);
				
			} else {
				solve(0, 0, 0);
				System.out.printf("#%d %d\n", t, minInputCnt);
			}
			
			
		}
		
	}
}
