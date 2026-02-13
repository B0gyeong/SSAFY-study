import java.util.*;
import java.io.*;

class House{
    int r;
    int c;

    public House(int r, int c){
        this.r = r;
        this.c = c;
    }
}

public class SolutionSecurity{
    static int[][] area;
    static int maxCnt = 0;
    static ArrayList<House> houses;
    static Iterator<House> iterator;
    static int N;
    static int M;
    static int maxEarn;

    public static void service(int r, int c, int k){
        int serviceFee = k * k + (k - 1) * (k - 1);
        if(serviceFee > maxEarn){
            return;
        }
        int cnt = 0;
        int earn;
        iterator = houses.iterator();
        int distance = 0;
        
        while(iterator.hasNext()) {
        	House curr = iterator.next();
        	distance = Math.abs(curr.r - r) + Math.abs(curr.c - c);
        	if(distance < k) {
        		cnt++; 
        	}
        }
        
        earn = cnt * M;
        int profit = earn - serviceFee;
        if(profit >= 0) {
        	maxCnt = Math.max(maxCnt, cnt);
        }

        service(r, c, k + 1);
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine().trim());

        for(int test_case = 1; test_case <= T; test_case++){
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            area = new int[N][N];
            houses = new ArrayList<House>();
            maxCnt = 0;

            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine().trim());
                for(int j = 0; j < N; j++){
                    area[i][j] = Integer.parseInt(st.nextToken());
                    if(area[i][j] == 1){
                        House h = new House(i, j);
                        houses.add(h);
                    }
                }
            }

            maxEarn = houses.size() * M;
            
            for(int i = 0; i < N; i++) {
            	for(int j = 0; j < N; j++) {
            		service(i, j, 1);
            	}
            }

            System.out.println("#" + test_case + " " + maxCnt);
        }
    }
}