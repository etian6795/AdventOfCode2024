import java.io.*;
import java.util.*;

public class Day12P1 implements AOCInterface {
    public static BufferedReader br;
    public static PrintWriter out = new PrintWriter(System.out);
    public static String file = "Day12Input.txt";

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new FileReader(file));

        long ans = 0;
        char[][] grid = AOCInterface.readInputAsGrid(file);
        int m = grid.length;
        int n = grid[0].length;

        boolean[][] seen = new boolean[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(!seen[i][j]) {
                    long[] arr = new long[2];
                    seen[i][j] = true;
                    arr[0]++;
                    dfs(i, j, seen, m, n, arr, grid, grid[i][j]);
                    out.println(Arrays.toString(arr));
                    ans += arr[0] * arr[1];
                }
            }
        }
        
        out.println(ans);
        out.close();
    }

    public static int[] dir = new int[]{0, 1, 0, -1, 0};
    public static void dfs(int r, int c, boolean[][] seen, int m, int n, long[] arr, char[][] grid, char type) {
        for(int i = 0; i < 4; i++) {
            int nr = r + dir[i];
            int nc = c + dir[i+1];
            if(nr >= 0 && nr < m && nc >= 0 && nc < n) {
                if(!seen[nr][nc] && grid[nr][nc] == type) {
                    seen[nr][nc] = true;
                    arr[0]++;
                    dfs(nr, nc, seen, m, n, arr, grid, type);
                } else if(grid[nr][nc] != type) {
                    arr[1]++;
                }
            } else {
                arr[1]++;
            }
        }
    }
}
