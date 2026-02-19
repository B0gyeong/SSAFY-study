import java.util.*;
import java.io.*;

class Main {
    static int[] arr;
    static boolean isDown;
    static int result;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());
        result = 0;
        arr = new int[N];
        String str = br.readLine().trim();
        for(int i = 0; i < N; i++){
            arr[i] = str.charAt(i) - '0';
        }
        
        int curr = arr[0];
        isDown = false;
        
        for(int i = 1; i < N; i++){
            if(arr[i] < curr){
                isDown = true;
            }else if(arr[i] > curr){
                if(isDown){
                    result++;
                }
                isDown = false;
            }
            curr = arr[i];
        }
        
        System.out.println(result);
    }
}