import java.io.*;
import java.util.*;

public class Day1P1 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) throws IOException {
        int n = 1000;
        int[][] arr = new int[2][n];
        int ans = 0;
        for(int i = 0; i < n; i++) {
            String s = br.readLine();
            arr[0][i] = Integer.parseInt(s.substring(0, 5));
            arr[1][i] = Integer.parseInt(s.substring(8));
        }
        for(int[] x : arr) Arrays.sort(x);
        for(int i = 0; i < n; i++) ans += Math.abs(arr[0][i] - arr[1][i]);
        out.println(ans);

        out.close();
    }
}
