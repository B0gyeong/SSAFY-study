import java.util.*;

public class Main {
	static boolean [] visited;
	static int [][] gears;
    public static void main(String[] args){
    	Scanner sc = new Scanner(System.in);
    	gears = new int [5][8];
    	
    	for(int i=1; i<=4; i++) {
    		String line = sc.next();
    		for(int j=0; j<8; j++) {
    			gears[i][j] = line.charAt(j)-'0';
    		}
    	}
    	
    	int K = sc.nextInt();
    	int [][] rotateArr = new int [K][2];

    	for(int i=0; i<K; i++) {
    		rotateArr[i][0] = sc.nextInt();
    		rotateArr[i][1] = sc.nextInt();
    	}
    	
    	for(int i=0; i<K; i++) {
    		visited = new boolean [5];
    		rotate(rotateArr[i][0], rotateArr[i][1]);
    	}
    	
    	int ans = 0;
    	if(gears[1][0] == 1) ans+=1;
    	if(gears[2][0] == 1) ans+=2;
    	if(gears[3][0] == 1) ans+=4;
    	if(gears[4][0] == 1) ans+=8;
    	
    	System.out.println(ans);
	}

	private static void rotate(int i, int d) {
		if(visited[i]) return;
		visited[i] = true;
		if(1<i) {
			if(gears[i-1][2] != gears[i][6]) {
				rotate(i-1, d*(-1));
			}
		}
		if(4>i) {
			if(gears[i+1][6] != gears[i][2]) {
				rotate(i+1, d*(-1));
			}
		}
		turn(i, d);
	}

	private static void turn(int i, int d) {
		if(d==-1) {
			int temp = gears[i][0];
			for(int j=0; j<7; j++) {
    			gears[i][j] = gears[i][j+1];
    		}
			gears[i][7]=temp;
		} else {
			int temp = gears[i][7];
			for(int j=7; j>=1; j--) {
    			gears[i][j] = gears[i][j-1];
    		}
			gears[i][0]=temp;
		}
	}
}