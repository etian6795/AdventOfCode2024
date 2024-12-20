import java.io.*;
import java.util.*;

public class Day20P2 implements AOCInterface {
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

        int[][] dist = new int[m][n];
        for(int[] x : dist) Arrays.fill(x, -1);
        int d = bfs1(grid, er, ec, sr, sc, m, n, dist);

        ans = bfs(grid, sr, sc, er, ec, m, n, d, dist);
        
        out.println(ans);
        out.close();
    }

    public static int bfs1(char[][] grid, int sr, int sc, int er, int ec, int m, int n, int[][] dist) {
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
                dist[curr[0]][curr[1]] = d;
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

    public static long bfs(char[][] grid, int sr, int sc, int er, int ec, int m, int n, int qwe, int[][] dist) {
        int d = 0;
        Queue<int[]> q = new LinkedList<>();
        int[] dir = new int[]{0, 1, 0, -1, 0};
        boolean[][] seen = new boolean[m][n];
        q.add(new int[]{sr, sc});
        seen[sr][sc] = true;
        long ans = 0;

        while(!q.isEmpty()) {
            int size = q.size();
            while(size-->0) {
                int[] curr = q.remove();
                if(curr[0] == er && curr[1] == ec) {
                    return ans;
                }
                for(int i = 1; i <= 20; i++) {
                    int r = curr[0];
                    int c = curr[1];
                    for(int j = 0; j <= i; j++) {
                        int nr = r + j;
                        int nc = c - i + j;
                        if(nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] == '.') {
                            int newd = dist[nr][nc] + i + d;
                            if(qwe - newd >= 100) ans++;
                        }
                    }
                    for(int j = 0; j <= i; j++) {
                        int nr = r - i + j;
                        int nc = c + j;
                        if(nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] == '.') {
                            int newd = dist[nr][nc] + i + d;
                            if(qwe - newd >= 100) ans++;
                        }
                    }
                    for(int j = 1; j < i; j++) {
                        int nr = r - j;
                        int nc = c - i + j;
                        if(nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] == '.') {
                            int newd = dist[nr][nc] + i + d;
                            if(qwe - newd >= 100) ans++;
                        }
                    }
                    for(int j = 1; j < i; j++) {
                        int nr = r + i - j;
                        int nc = c + j;
                        if(nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] == '.') {
                            int newd = dist[nr][nc] + i + d;
                            if(qwe - newd >= 100) ans++;
                        }
                    }
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

        return ans;
    }
}
