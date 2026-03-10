package samsung01;

import java.util.*;
import java.io.*;

public class S1216_회문2 {
	
	static char[][] wordArr;
	static int maxLen;
	
	public static boolean findPalid(int i, int j, int x, boolean isRow) {
		
		if(isRow) {
			int len = x-j+1;
			for(int c=0; c<len/2; c++) {
				if (wordArr[i][j+c] != wordArr[i][x-c]) return false;
			}
		} else {
			int len = x-i+1;
			for(int c=0; c<len/2; c++) {
				if(wordArr[i+c][j] != wordArr[x-c][j]) return false;
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int tc=1; tc<=10; tc++) {
			
			int T_num = Integer.parseInt(br.readLine().trim());
			wordArr = new char[100][100];
			
			for(int i=0; i<100; i++) {
				String line = br.readLine();
				for(int j=0; j<100; j++) {
					wordArr[i][j] = line.charAt(j);
				}
			}
			
			maxLen = 0;
			//가로
			for(int i=0; i<100; i++) {
				
				for(int j=0; j<100; j++) {
					
					int x = j; //열 시작 번호
					int len = x-j+1; // 1,2,3... 
					
					while(x<100) {
						len = x-j+1;
						
						if(len<=maxLen) {
							x++;
							continue;
						}
						
						
						if(findPalid(i,j,x, true)) {
							if(len>maxLen) {
								maxLen = len;
							}
						} 
						

						x++;
					}
				}
				
			}
			
			// 세로
			for(int j=0; j<100; j++) {
				
				for(int i=0; i<100; i++) {
					
					int x =  i;
					int len = x-i+1;
					
					while(x<100) {
						len = x-i+1;
						if(len<=maxLen) {
							x++;
							continue;
						}
						
			
						if(findPalid(i,j,x,false)) {
							if(len>maxLen) {
								maxLen = len;
							}
						}
						
						
						x++;
					}
				}
			}
			
			System.out.printf("#%d %d\n", T_num, maxLen);
			
			
		}
		
	}
}
