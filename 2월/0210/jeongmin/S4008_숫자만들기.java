import java.io.*;
import java.util.*;


public class S4008_숫자만들기
{

	static int[] num;
	static int[] op;
	static int max, min;
	static int N;
	
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st; 
		
		for(int t=1; t<=T; t++) {
			// 테스트 케이스마다 초기화 
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 숫자의 개수  -> 연산자 N-1개 
			num = new int[N];
			op = new int[4]; // 0: + 1: - 2: * 3: /
			
			st = new StringTokenizer(br.readLine()); 
			op[0] = Integer.parseInt(st.nextToken()); 
			op[1] = Integer.parseInt(st.nextToken());
			op[2] = Integer.parseInt(st.nextToken()); 
			op[3] = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				num[i] = Integer.parseInt(st.nextToken());
			}
			
			// 결과가 최대가 되는 수식과 최소가 되는 수식 ( 순서 찾기 )
			// 최대 최소 결과값 차 출력 
			dfs(1, num[0]);
			
			System.out.println("#"+t+" "+(max-min));
			
		}
	}
	
	// 다음 상태로 넘어갈때 변하는 값을 매개로 넘겨줌 
	public static void dfs(int m, int cur) {
		
		// base case
		if(m==N) {
			max = Math.max(max, cur);
			min = Math.min(min, cur);
			return;
		}
		
		for(int i=0; i<4; i++) {
			if(op[i] <= 0) continue;
			op[i]--;
			
			int next = calculate(cur, num[m], i);
			dfs(m+1, next); 
			
			op[i]++;
		}
	}
	
	public static int calculate(int a, int b, int op) {
		if(op==0) return a+b;
		if(op==1) return a-b;
		if(op==2) return a*b;
		return a/b;
	}
}


