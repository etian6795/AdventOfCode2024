import java.io.*;
import java.util.*;

public class Day14P2 implements AOCInterface {
    public static BufferedReader br;
    public static PrintWriter out = new PrintWriter(System.out);
    public static String file = "Day14Input.txt";

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new FileReader(file));

        String line;
        int m = 103;
        int n = 101;
        // int m = 7;
        // int n = 11;
        ArrayList<long[]> arr = new ArrayList<>();
        while((line = br.readLine()) != null) {
            long[] temp = AOCInterface.readLineAsLongs(line);
            arr.add(temp);
        }

        for(int i = 0; i < 10000; i++) {
            long[][] grid = new long[m][n];
            for(long[] temp : arr) {
                long x = (((temp[0] + i * temp[2]) % n) + n) % n;
                long y = (((temp[1] + i * temp[3]) % m) + m) % m;
                grid[(int)y][(int)x]++;
            }
            int max = 0;
            for(int r = 0; r < m; r++) {
                int cnt = 0;
                for(int c = 0; c < n; c++) {
                    if(grid[r][c] == 0) cnt = 0;
                    else {
                        cnt++;
                        max = Math.max(max, cnt);
                    }
                }
            }
            if(max >= 20) {
                out.println(i);
                for(long[] x : grid) {
                    for(long y : x) {
                        out.print(y);
                    }
                    out.println();
                }
            }
        }
        
        out.close();
    }
}
