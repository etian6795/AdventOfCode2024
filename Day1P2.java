import java.io.*;

public class Day1P2 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) throws IOException {
        int n = 1000;
        int[][] arr = new int[2][n];
        long ans = 0;
        for(int i = 0; i < n; i++) {
            String s = br.readLine();
            arr[0][i] = Integer.parseInt(s.substring(0, 5));
            arr[1][i] = Integer.parseInt(s.substring(8));
        }
        long[] f = new long[1000000];
        for(int i = 0; i < n; i++) f[arr[1][i]]++;
        for(int i = 0; i < n; i++) ans += arr[0][i] * f[arr[0][i]];
        out.println(ans);

        out.close();
    }
}
