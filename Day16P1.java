import java.io.*;
import java.util.*;

public class Day16P1 implements AOCInterface {
    public static BufferedReader br;
    public static PrintWriter out = new PrintWriter(System.out);
    public static String file = "Day16Input.txt";

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new FileReader(file));

        long ans = 0;
        char[][] grid = AOCInterface.readInputAsGrid(file);
        int m = grid.length;
        int n = grid[0].length;
        int r = 0;
        int c = 0;
        int er = 0;
        int ec = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 'S') {
                    r = i;
                    c = j;
                }
                if(grid[i][j] == 'E') {
                    er = i;
                    ec = j;
                }
            }
        }
        grid[r][c] = '.';
        grid[er][ec] = '.';
        
        int[][] dirs = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        long[][][] d = new long[m][n][4];
        for(long[][] x : d) {
            for(long[] y : x) {
                Arrays.fill(y, Long.MAX_VALUE);
            }
        }
        
        boolean[][][] seen = new boolean[m][n][4];
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> (Long.compare(a[3], b[3])));
        pq.add(new long[]{r, c, 1, 0}); // r, c, dir, d
        d[r][c][1] = 0;
        while(!pq.isEmpty()) {
            long[] curr = pq.remove();
            int cr = (int)curr[0];
            int cc = (int)curr[1];
            if(cr == er && cc == ec) {
                out.println(curr[3]);
                out.close();
                return;
            }
            int dir = (int)curr[2];
            if(seen[cr][cc][dir]) continue;
            seen[cr][cc][dir] = true;
            for(int i : new int[]{-1, 0, 1}) {
                int ndir = (dir + i + 4)%4;
                int nr = cr + dirs[ndir][0];
                int nc = cc + dirs[ndir][1];
                if(grid[nr][nc] == '.' && d[cr][cc][dir] + Math.abs(i)*1000 + 1 < d[nr][nc][ndir]) {
                    d[nr][nc][ndir] = d[cr][cc][dir] + Math.abs(i)*1000 + 1;
                    pq.add(new long[]{nr, nc, ndir, d[nr][nc][ndir]});
                }
            }
        }
        
        out.println(ans);
        out.close();
    }
}
