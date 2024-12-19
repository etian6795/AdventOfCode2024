import java.io.*;
import java.util.*;

public class Day19P2 implements AOCInterface {
    public static BufferedReader br;
    public static PrintWriter out = new PrintWriter(System.out);
    public static String file = "Day19Input.txt";

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new FileReader(file));

        String line;
        line = br.readLine();
        String[] arr = line.split(", ");
        br.readLine();

        long ans = 0;
        while((line = br.readLine()) != null) {
            long[] memo = new long[line.length()];
            Arrays.fill(memo, -1);
            ans += rec(line, line.length(), memo, arr, 0);
        }
        
        out.println(ans);
        out.close();
    }

    public static long rec(String s, int n, long[] memo, String[] arr, int idx) {
        if(idx == n) return 1;
        if(memo[idx] != -1) return memo[idx];
        memo[idx] = 0;
        for(String x : arr) {
            if(x.equals(s.substring(idx, Math.min(n, idx + x.length())))) {
                memo[idx] += rec(s, n, memo, arr, idx + x.length());
            }
        }
        return memo[idx];
    }
}
