import java.io.*;
import java.util.*;

public class Day20P1 implements AOCInterface {
    public static BufferedReader br;
    public static PrintWriter out = new PrintWriter(System.out);
    public static String file = "Day20Input.txt";

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new FileReader(file));

        long ans = 0;
        char[][] grid = AOCInterface.readInputAsGrid(file);
        int m = grid.length;
        int n = grid[0].length;
        int sr = -1;
        int sc = -1;
        int er = -1;
        int ec = -1;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 'S') {
                    sr = i;
                    sc = j;
                    grid[i][j] = '.';
                }
                if(grid[i][j] == 'E') {
                    er = i;
                    ec = j;
                    grid[i][j] = '.';
                }
            }
        }

        int d = bfs(grid, sr, sc, er, ec, m, n);

        for(int i = 1; i < m-1; i++) {
            for(int j = 1; j < n-1; j++) {
                if(grid[i][j] == '#') {
                    grid[i][j] = '.';
                    
                    int newd = bfs(grid, sr, sc, er, ec, m, n);
                    if(d - newd >= 100) {
                        ans++;
                    }

                    grid[i][j] = '#';
                }
            }
        }
        
        out.println(ans);
        out.close();
    }

    public static int bfs(char[][] grid, int sr, int sc, int er, int ec, int m, int n) {
        int d = 0;
        Queue<int[]> q = new LinkedList<>();
        int[] dir = new int[]{0, 1, 0, -1, 0};
        boolean[][] seen = new boolean[m][n];
        q.add(new int[]{sr, sc});
        seen[sr][sc] = true;

        while(!q.isEmpty()) {
            int size = q.size();
            while(size-->0) {
                int[] curr = q.remove();
                if(curr[0] == er && curr[1] == ec) {
                    return d;
                }
                for(int k = 0; k < 4; k++) {
                    int nr = curr[0] + dir[k];
                    int nc = curr[1] + dir[k + 1];
                    if(nr >= 0 && nr < m && nc >= 0 && nc < n && !seen[nr][nc] && grid[nr][nc] == '.') {
                        seen[nr][nc] = true;
                        q.add(new int[]{nr, nc});
                    }
                }
            }
            d++;
        }

        return -1;
    }
}
