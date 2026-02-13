import java.util.*;
import java.io.*;

public class Parenthesis{
    static int result;

    public static int calc(int a, char op, int b){
        int computed = 0;

        switch(op){
            case '+':
                computed = a + b;
                break;
            case '-':
                computed = a - b;
                break;
            case '*':
                computed = a * b;
                break;
            case '/':
                computed = a / b;
                break;
            default:
                System.out.println("wrong operator");
        }

        return computed;
    }

    public static void dfs(int idx, int computed, char[] ops, int[] num){
        if(idx >= ops.length){
            result = Math.max(computed, result);
            return;
        }

        int xParenComp = calc(computed, ops[idx], num[idx + 1]);

        dfs(idx + 1, xParenComp, ops, num); // X parenthesis

        if(idx + 1 < ops.length){
            int tmp = calc(num[idx + 1], ops[idx + 1], num[idx + 2]);
            int oParenComp = calc(computed, ops[idx], tmp);

            dfs(idx + 2, oParenComp, ops, num); // O parenthesis
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());

        int[] num = new int[N/2 + 1];
        char[] ops = new char[N/2];

        int numIdx = 0;
        int opIdx = 0;

        String expression = br.readLine().trim();

        for(int i = 0; i < expression.length(); i++){
            char c = expression.charAt(i);

            if(i % 2 == 0){
                num[numIdx++] = c - '0';
            }else{
                ops[opIdx++] = c;
            }
        }

        result = Integer.MIN_VALUE;

        dfs(0, num[0], ops, num);

        System.out.println(result);
    }
}