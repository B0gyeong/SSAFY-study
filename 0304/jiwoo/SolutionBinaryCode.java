import java.util.*;
import java.io.*;

public class SolutionBinaryCode {
    static int N;
    static int M;
    static String[] barcode;
    static Map<String, Integer> map;
    static int result;
    static final int length = 56; //length of the code always 56

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        map = new HashMap<String, Integer>();
		map.put("2:1:1",  0);
		map.put("2:2:1", 1);
		map.put("1:2:2", 2);
		map.put("4:1:1", 3);
		map.put("1:3:2", 4);
		map.put("2:3:1", 5);
		map.put("1:1:4", 6);
		map.put("3:1:2", 7);
		map.put("2:1:3", 8);
		map.put("1:1:2", 9);

        for(int test_case = 1; test_case <= T; test_case++){
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            barcode = new String[N];
            result = 0;
            int start_idx_c = M;
            String line;
            int cnt = 0;
            int[] code = new int[8];

            int left = 0;
            int mid = 0;
            int right = 0;

            for(int i = 0; i < N; i++){
                barcode[i] = br.readLine().trim();
            }

            for(int i = 0; i < N; i++){
                for(int j = M - 1; j >= 0; j--){
                    if(barcode[i].charAt(j) == '1'){
                        start_idx_c = j;
                        line = barcode[i];
                        break;
                    }
                }
                if(start_idx_c != M) break;
            }

            while(cnt < length){
                cnt++;
                if(cnt % 7 == 1){
                    left = 0;
                    mid = 0;
                    right = 1;
                }else{
                    //
                }
            }
        }
    }
}
