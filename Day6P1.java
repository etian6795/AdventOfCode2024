import java.io.*;
import java.util.*;

public class Day6P1 {
    public static BufferedReader br;
    public static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new FileReader("Day6Input.txt"));

        String line;
        long ans = 0;
        ArrayList<char[]> grid = new ArrayList<>();
        int r = 0;
        int c = 0;
        while((line = br.readLine()) != null) {
            char[] arr = line.toCharArray();
            grid.add(line.toCharArray());
            for(int i = 0; i < arr.length; i++) {
                if(arr[i] == '^') {
                    r = grid.size()-1;
                    c = i;
                }
            }
        }
        int m = grid.size();
        int n = grid.get(0).length;
        boolean[][] seen = new boolean[m][n];
        int dir = 0;
        int[][] dirs = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        while(r >= 0 && r < m && c >= 0 && c < n) {
            if(grid.get(r)[c] == '#') {
                r -= dirs[dir%4][0];
                c -= dirs[dir%4][1];
                dir++;
            } else {
                seen[r][c] = true;
                r += dirs[dir%4][0];
                c += dirs[dir%4][1];
            }
        }
        for(boolean[] x : seen) {
            for(boolean y : x) {
                if(y) ans++;
            }
        }
        
        out.println(ans);
        out.close();
    }
}
