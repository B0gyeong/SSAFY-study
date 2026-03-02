import java.io.*;
import java.util.*;


public class S1952_수영장
{
	static int day;
	static int mon1;
	static int mon3;
	static int year;
	static int[] table;
	static int cost; // 출력할 비용
	
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st; 
		
		for(int t=1; t<=T; t++) {
			cost = Integer.MAX_VALUE; // 최소비용 찾기 
			
			// 이용권 가격 
			st = new StringTokenizer(br.readLine());
			day = Integer.parseInt(st.nextToken());
			mon1 = Integer.parseInt(st.nextToken());
			mon3 = Integer.parseInt(st.nextToken());
			year = Integer.parseInt(st.nextToken());
			
			// 12달 이용계획 
			table = new int[13];
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=12; i++) {
				table[i] = Integer.parseInt(st.nextToken());
			}
			
			//매달 어떤 이용권을 선택할 것인가? 
			dfs(1, 0);
			
			int answer = Math.min(cost, year);
			System.out.println("#"+t+" "+answer);
			
		}
	}
	
	// ( 현재 달, 현재가지 누적 비용 )
	public static void dfs(int m, int sum) {
		// base case
		if(m>12) {
			cost = Math.min(cost, sum);
			return;
		}
		
		// 현재 달에 수영장 이용 계획이 없는 경우 
		if(table[m] == 0) {
			dfs(m+1, sum);
		} else {
			dfs(m+1, sum + table[m]*day); // 1일권
			
			dfs(m+1, sum + mon1); // 2일권
			
			dfs(m+3, sum + mon3); // 3일권 
		}
	}
	
}



