import java.util.*;
import java.io.*;

public class SolutionBalance { //swea 1245
    static int N;
    static double[] coordinate;
    static double[] mass;
    static double[] balance;

    public static double force(double low, double high, int idx){ //idx of the point(coordinate, mass)
        double mid = (high + low) / 2;
        double leftF = 0;
        double rightF = 0;

        if(high - low < 1e-12) return mid;

        for(int i = 0; i <= idx; i++){
            leftF += mass[i] / ((mid - coordinate[i])*(mid - coordinate[i]));
        }
        for(int i = idx + 1; i < N; i++){
            rightF += mass[i] / ((coordinate[i] - mid)*(coordinate[i] - mid));
        }
        //if(Math.abs(leftF - rightF) < 1e-12) return mid; -> 문제에선 오차범위를 좌표값으로 정의 했고, force가 같아 보여도 좌표값은 그 오차범위를 벗어날 수 있음
        
        if(leftF > rightF){
            return force(mid, high, idx);
        }else{
            return force(low, mid, idx);
        }
    }


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for(int test_case = 1; test_case <= T; test_case++){
            N = Integer.parseInt(br.readLine().trim());
            coordinate = new double[N];
            mass = new double[N];
            balance = new double[N - 1];

            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            for(int i = 0; i < N; i++){
                coordinate[i] = Double.parseDouble(st.nextToken());
            }
            for(int i = 0; i < N; i++){
                mass[i] = Double.parseDouble(st.nextToken());
            }

            for(int i = 0; i < balance.length; i++){
                balance[i] = force(coordinate[i], coordinate[i + 1], i);
            }

            StringBuilder sb = new StringBuilder();
            sb.append("#" + test_case);

            for(int i = 0; i < balance.length; i++){
                String formatting = String.format(" %.10f", balance[i]);
                sb.append(formatting);
            }

            System.out.println(sb.toString());
        }
    }
}
