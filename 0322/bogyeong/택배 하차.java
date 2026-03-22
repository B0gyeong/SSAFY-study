import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int [][] map;
	static Box [] boxArr;
	static PriorityQueue<Box> pq;
	static class Box implements Comparable<Box>{
		int k, r, c, w, h;
		boolean finish;
		Box(int k, int r, int c, int w, int h){
			this.k = k;
			this.r = r;
			this.c = c;
			this.w = w;
			this.h = h;
			this.finish = false;
		}
		
		@Override
		public int compareTo(Box o) {
			return Integer.compare(this.k, o.k);
		}
	}
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st1.nextToken());
        M = Integer.parseInt(st1.nextToken());
        
        map = new int [N+1][N+1];
        boxArr = new Box [M];
        
        for(int i=0; i<M; i++) {
        	StringTokenizer st2 = new StringTokenizer(br.readLine());
        	int k = Integer.parseInt(st2.nextToken());
        	int h = Integer.parseInt(st2.nextToken());
        	int w = Integer.parseInt(st2.nextToken());
        	int c = Integer.parseInt(st2.nextToken());
        	
        	boolean find = false;
        	for(int r=1; r<=N; r++) {
        		for(int col=0; col<w; col++) {
        			if(map[r][c+col] != 0) {
        				find = true;
        				break;
        			}
        		
        		}
        		
        		if(find) {
        			for(int a=0; a<h; a++) {
        				for(int b=0; b<w; b++) {
        					map[r-1-a][c+b] = k;
        				}
        			}
        			
        			boxArr[i] = new Box(k, r-h, c, w, h);
        			
        			break;
        		} else if(r == N) {
        			for(int a=0; a<h; a++) {
        				for(int b=0; b<w; b++) {
        					map[r-a][c+b] = k;
        				}
        			}
        			
        			boxArr[i] = new Box(k, r-h+1, c, w, h);
        		}
        	}
        	
        }
        
        pq = new PriorityQueue<>();
        while(true) {
        	for(int i=0; i<M; i++) {
        		if(!boxArr[i].finish && checkLeft(boxArr[i])) {
        			pq.add(boxArr[i]);
        		}
        	}
        	
        	Box curr = pq.poll();
        	for(int a=0; a<curr.h; a++) {
    			for(int b=0; b<curr.w; b++) {
    				map[curr.r+a][curr.c+b] = 0;
    			}
    		}
        	curr.finish = true;
        	System.out.println(curr.k);
        	 	
        	// 다 나갔는지 확인 
        	if(checkAllFinish()) break;
        	pq.clear();

        	// 중력으로 내리기
        	gravityCheck();
        	
//        	for(int r=1; r<=N; r++) {
//        		for(int c=1; c<=N; c++) {
//        			System.out.print(map[r][c]+" ");
//        		}
//        		System.out.println();
//        	}
        	
        	
        	// 오른쪽 체크 
        	for(int i=0; i<M; i++) {
        		if(!boxArr[i].finish && checkRight(boxArr[i])) {
        			pq.add(boxArr[i]);
        		}
        	}
        	
        	// 하나 빼기
        	curr = pq.poll();
        	for(int a=0; a<curr.h; a++) {
    			for(int b=0; b<curr.w; b++) {
    				map[curr.r+a][curr.c+b] = 0;
    			}
    		}
        	curr.finish = true;
        	System.out.println(curr.k);
        	
        	// 다 나갔는지 확인 
        	if(checkAllFinish()) break;
        	pq.clear();

        	// 중력으로 내리기
        	gravityCheck();
        	
//        	for(int r=1; r<=N; r++) {
//        		for(int c=1; c<=N; c++) {
//        			System.out.print(map[r][c]+" ");
//        		}
//        		System.out.println();
//        	}

        }
        
//        
//        for(int r=1; r<=N; r++) {
//    		for(int c=1; c<=N; c++) {
//    			System.out.print(map[r][c]+" ");
//    		}
//    		System.out.println();
//    	}
//        
//        for(int r=0; r<M; r++) {
//    		System.out.println(boxArr[r].r);
//    	}
        
    }
    
	private static void gravityCheck() {
		while(true) {
			boolean change = false;
			
			for(int i=0; i<M; i++) {
				if(boxArr[i].finish) continue;
				
				Box curr = boxArr[i];
				
				boolean find = false;
				
	        	for(int r=curr.r+curr.h; r<=N; r++) {
	        		for(int col=0; col<curr.w; col++) {
	        			if(map[r][curr.c+col] != 0) {
	        				find = true;
	        				break;
	        			}
	        		
	        		}
	        		
	        		if(find || (!find && r == N)) {
	        			if(find && r == curr.r+curr.h) break;
	        			
	        			// 원래 위치 초기화 
	        			for(int a=0; a<curr.h; a++) {
	        				for(int b=0; b<curr.w; b++) {
	        					map[curr.r+a][curr.c+b] = 0;
	        				}
	        			}
	        			
	        			if(find) {
	            			for(int a=0; a<curr.h; a++) {
	            				for(int b=0; b<curr.w; b++) {
	            					map[r-1-a][curr.c+b] = curr.k;
	            				}
	            			}
	            			
	            			boxArr[i].r = r-curr.h;
	            			change = true;
	            			break;
	            		} else if(r == N) {
	            			for(int a=0; a<curr.h; a++) {
	            				for(int b=0; b<curr.w; b++) {
	            					map[r-a][curr.c+b] = curr.k;
	            				}
	            			}
	            			
	            			boxArr[i].r = r-curr.h+1;
	            			change = true;
	            		}
	        		} 
	        	}
	        	
	        }
			
			if(!change) break;
		}
		
	}

	private static boolean checkLeft(Box boxArr) {
		Box curr = boxArr;
		
		for(int a=0; a<curr.h; a++) {
			for(int b=1; b<curr.c; b++) {
				if(map[curr.r+a][b] != 0) return false;
			}
		}
		return true;
	}
	
	private static boolean checkRight(Box boxArr) {
		Box curr = boxArr;
		
		for(int a=0; a<curr.h; a++) {
			for(int b=curr.c+curr.w; b<=N; b++) {
				if(map[curr.r+a][b] != 0) return false;
			}
		}
		
		return true;
	}
	
	private static boolean checkAllFinish() {
		for(int i=0; i<M; i++) {
			if(!boxArr[i].finish) return false;
		}
		
		return true;
	}
}