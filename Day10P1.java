import java.io.*;

public class Day10P1 implements AOCInterface {
    public static BufferedReader br;
    public static PrintWriter out = new PrintWriter(System.out);
    public static String file = "Day10Input.txt";

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new FileReader(file));
        
        long ans = 0;
        char[][] grid = AOCInterface.readInputAsGrid(file);
        int m = grid.length;
        int n = grid[0].length;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == '0') {
                    boolean[][] seen = new boolean[m][n];
                    ans += dfs(i, j, grid, seen);
                }
            }
        }
        
        out.println(ans);
        out.close();
    }

    public static int[] dir = new int[]{0, 1, 0, -1, 0};
    public static long dfs(int r, int c, char[][] grid, boolean[][] seen) {
        if(grid[r][c] == '9') {
            if(!seen[r][c]) {
                seen[r][c] = true;
                return 1;
            }
            return 0;
        }
        long cnt = 0;
        for(int i = 0; i < 4; i++) {
            int nr = r + dir[i];
            int nc = c + dir[i+1];
            if(nr >= 0 && nr < grid.length && nc >= 0 && nc < grid[0].length && grid[nr][nc] - grid[r][c] == 1) {
                cnt += dfs(nr, nc, grid, seen);
            }
        }
        return cnt;
    }
}
