import java.io.*;
import java.util.*;

public class B20056_마법사상어와파이어볼 {

	static int N;
	static int M;
	static int K;
	
	static List<Fireball>[][] map;
	
	// 1. 8개인데 7개로 씀 
	static int[] dr = {-1,-1,0,1,1,1,0,-1};
	static int[] dc = {0,1,1,1,0,-1,-1,-1};
	
	static class Fireball {
		int m;
		int s;
		int d;
		
		public Fireball(int m, int s, int d) {
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}
	
	public static boolean isWall(int r, int c) {
		return (r<0||r>=N||c<0||c>=N);
	}
	
	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("res/input_b20056.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new ArrayList[N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j] = new ArrayList<>();
			}
		}
		
		for(int i=0; i<M; i++) {
		
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			map[r][c].add(new Fireball(m,s,d));
			
		}
		
		while(K-->0) {
			
			// 1.이동 
			
			List<Fireball>[][] moved = new ArrayList[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					moved[i][j] = new ArrayList<>();
				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j].isEmpty()) continue;
					
					int count = map[i][j].size();
					
					for(int k=0; k<count; k++) {
						Fireball cur = map[i][j].remove(count-k-1);
						
						// 2. 1번 행은 N번과 연결되어있고 1번 열은 N번 열과 연결 
						int nr = (i + dr[cur.d] * (cur.s%N) + N ) %N;
						int nc = (j + (dc[cur.d] * (cur.s % N)) + N) % N;
						
						moved[nr][nc].add(new Fireball(cur.m, cur.s, cur.d));
						
					}
				}
			}
			
			// 2. 2개 이상의 파이어볼이 있는칸 처리
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(moved[i][j].isEmpty()) continue;
					
					int count = moved[i][j].size();
					
					if(count > 1) {
						
						// 파이어볼 모두 꺼내서 질량,속력 구하기
						List<Integer> dir = new LinkedList<>();
						int sumS =0;
						int sumM =0;
					
						for(int k=0; k<count; k++) {
							
							Fireball cur = moved[i][j].remove(count-1-k);
							sumM += cur.m;
							sumS += cur.s;
							dir.add(cur.d);
							
						}
						
						int nm = sumM/5;
						int ns = sumS/count;
						
						// 방향
						boolean odd = false;
						boolean even = false;
						int dirsize = dir.size();
						int dirfirst = dir.remove(dirsize-1);
						
						if(dirfirst%2 == 0) {
							even = true;
						} else {
							odd = true;
						}
						boolean flag = true;
						for(int k=0; k<dirsize-1; k++) {
							int tmp = dir.remove(dirsize-2-k);
							
							if(even && tmp%2==0) {
								
							} else if(odd && tmp%2 != 0) {
								
							} else {
								flag = false;
							}
						}
						
						if(nm != 0) {
							
							// 3. 하드코딩 했으므로 d 반복 X
								if(flag) {
									moved[i][j].add(new Fireball(nm,ns,0));
									moved[i][j].add(new Fireball(nm,ns,2));
									moved[i][j].add(new Fireball(nm,ns,4));
									moved[i][j].add(new Fireball(nm,ns,6));
								} else {
									moved[i][j].add(new Fireball(nm,ns,1));
									moved[i][j].add(new Fireball(nm,ns,3));
									moved[i][j].add(new Fireball(nm,ns,5));
									moved[i][j].add(new Fireball(nm,ns,7));
								}
							
						}
						
						
					}
				}
			}
			
			map = moved;
		}
		
		int totalM = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				
				if(map[i][j].isEmpty()) continue;
				
				int count = map[i][j].size();
				
				for(int k=0; k<count; k++) {
					Fireball cur = map[i][j].remove(count-1-k);
					
					totalM += cur.m;
				}
			}
		}
		
		System.out.println(totalM);
		
	}
	
	

}
