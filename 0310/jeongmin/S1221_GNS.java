package samsung01;


import java.util.*;
import java.io.*;

public class S1221_GNS {
	
	
	static int[] count;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		for(int t=1; t<=T; t++) {
			
			st = new StringTokenizer(br.readLine());
			String tcNum = st.nextToken();
			int N = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			count = new int[10]; // 0~9
			for(int i=0; i<N; i++) {
				String word = st.nextToken();
				
				char c1 = word.charAt(0);
				char c2 = word.charAt(1);
				char c3 = word.charAt(2);
				
				switch(c1) {
					case 'Z': count[0]++; break;
					case 'O': count[1]++; break;
					case 'T':
						if(c3=='O') count[2]++;
						else count[3]++;
						break;
					case 'F':
						if(c3=='R') count[4]++;
						else count[5]++;
						break;
					case 'S':
						if(c3=='X') count[6]++;
						else count[7]++;
						break;
					case 'E': count[8]++; break;
					case 'N': count[9]++; break;
				}
				
				
			}
			
			System.out.printf("#%d", t);
			System.out.println();
			for(int i=0; i<=9; i++) {
				while(count[i]!=0) {
					
					switch(i) {
						case 0:
							System.out.printf("ZRO ");
							break;
						case 1:
							System.out.printf("ONE ");
							break;
						case 2:
							System.out.printf("TWO ");
							break;
						case 3:
							System.out.printf("THR ");
							break;
						case 4:
							System.out.printf("FOR ");
							break;
						case 5:
							System.out.printf("FIV ");
							break;
						case 6:
							System.out.printf("SIX ");
							break;
						case 7:
							System.out.printf("SVN ");
							break;
						case 8:
							System.out.printf("EGT ");
							break;
						case 9:
							System.out.printf("NIN ");
							break;
							
					}
					
					count[i]--;
				}
			}
			
			
		}
		
	}
}
