import java.io.*;

public class Day14P1 implements AOCInterface {
    public static BufferedReader br;
    public static PrintWriter out = new PrintWriter(System.out);
    public static String file = "Day14Input.txt";

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new FileReader(file));

        String line;
        long ans = 1;
        // int m = 103;
        // int n = 101;
        int m = 7;
        int n = 11;
        long[][] grid = new long[m][n];
        while((line = br.readLine()) != null) {
            long[] temp = AOCInterface.readLineAsLongs(line);
            long x = (((temp[0] + 100 * temp[2]) % n) + n) % n;
            long y = (((temp[1] + 100 * temp[3]) % m) + m) % m;
            grid[(int)y][(int)x]++;
        }
        long temp = 0;
        for(int i = 0; i < m/2; i++) {
            for(int j = 0; j < n/2; j++) {
                temp += grid[i][j];
            }
        }
        ans *= temp;
        temp = 0;
        for(int i = m/2+1; i < m; i++) {
            for(int j = 0; j < n/2; j++) {
                temp += grid[i][j];
            }
        }
        ans *= temp;
        temp = 0;
        for(int i = 0; i < m/2; i++) {
            for(int j = n/2 + 1; j < n; j++) {
                temp += grid[i][j];
            }
        }
        ans *= temp;
        temp = 0;
        for(int i = m/2+1; i < m; i++) {
            for(int j = n/2 + 1; j < n; j++) {
                temp += grid[i][j];
            }
        }
        ans *= temp;
        
        out.println(ans);
        out.close();
    }
}
