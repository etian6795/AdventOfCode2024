import java.io.*;
import java.util.*;

public class Day16P2 implements AOCInterface {
    public static BufferedReader br;
    public static PrintWriter out = new PrintWriter(System.out);
    public static String file = "Day16Input.txt";

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new FileReader(file));

        long minDist = Long.MAX_VALUE;
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
        
        long[][][] d = new long[m][n][4];
        for(long[][] x : d) {
            for(long[] y : x) {
                Arrays.fill(y, Long.MAX_VALUE);
            }
        }

        int[][] dirs = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        boolean[][][] seen = new boolean[m][n][4];
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> (Long.compare(a[3], b[3])));
        pq.add(new long[]{r, c, 1, 0}); // r, c, dir, d
        d[r][c][1] = 0;
        while(!pq.isEmpty()) {
            long[] curr = pq.remove();
            int cr = (int)curr[0];
            int cc = (int)curr[1];
            int dir = (int)curr[2];
            if(seen[cr][cc][dir]) continue;
            seen[cr][cc][dir] = true;
            if(cr == er && cc == ec) {
                minDist = Math.min(minDist, curr[3]);
            }
            for(int i : new int[]{-1, 1}) {
                int ndir = (dir + i + 4)%4;
                int nr = cr;
                int nc = cc;
                if(grid[nr][nc] == '.' && d[cr][cc][dir] + Math.abs(i)*1000 < d[nr][nc][ndir]) {
                    d[nr][nc][ndir] = d[cr][cc][dir] + Math.abs(i)*1000;
                    pq.add(new long[]{nr, nc, ndir, d[nr][nc][ndir]});
                }
            }

            int ndir = dir;
            int nr = cr + dirs[dir][0];
            int nc = cc + dirs[dir][1];
            if(grid[nr][nc] == '.' && d[cr][cc][dir] + 1 < d[nr][nc][ndir]) {
                d[nr][nc][ndir] = d[cr][cc][dir] + 1;
                pq.add(new long[]{nr, nc, ndir, d[nr][nc][ndir]});
            }
        }

        long[][][] dd = new long[m][n][4];
        for(long[][] x : dd) {
            for(long[] y : x) {
                Arrays.fill(y, Long.MAX_VALUE);
            }
        }
        seen = new boolean[m][n][4];
        pq = new PriorityQueue<>((a, b) -> (Long.compare(a[3], b[3])));
        pq.add(new long[]{er, ec, 0, 0}); // r, c, dir, d
        pq.add(new long[]{er, ec, 1, 0});
        pq.add(new long[]{er, ec, 2, 0});
        pq.add(new long[]{er, ec, 3, 0});
        dd[er][ec][0] = 0;
        dd[er][ec][1] = 0;
        dd[er][ec][2] = 0;
        dd[er][ec][3] = 0;
        while(!pq.isEmpty()) {
            long[] curr = pq.remove();
            int cr = (int)curr[0];
            int cc = (int)curr[1];
            int dir = (int)curr[2];
            if(seen[cr][cc][dir]) continue;
            seen[cr][cc][dir] = true;
            for(int i : new int[]{-1, 1}) {
                int ndir = (dir + i + 4)%4;
                int nr = cr;
                int nc = cc;
                if(grid[nr][nc] == '.' && dd[cr][cc][dir] + Math.abs(i)*1000 < dd[nr][nc][ndir]) {
                    dd[nr][nc][ndir] = dd[cr][cc][dir] + Math.abs(i)*1000;
                    pq.add(new long[]{nr, nc, ndir, dd[nr][nc][ndir]});
                }
            }

            int ndir = dir;
            int nr = cr + dirs[dir][0];
            int nc = cc + dirs[dir][1];
            if(grid[nr][nc] == '.' && dd[cr][cc][dir] + 1 < dd[nr][nc][ndir]) {
                dd[nr][nc][ndir] = dd[cr][cc][dir] + 1;
                pq.add(new long[]{nr, nc, ndir, dd[nr][nc][ndir]});
            }
        }

        long ans = 0;
        for(int i = 1; i < m-1; i++) {
            for(int j = 1; j < n-1; j++) {
                for(int k = 0; k < 4; k++) {
                    if(d[i][j][k] + dd[i][j][(k+2)%4] == minDist) {
                        ans++;
                        break;
                    }
                }
            }
        }
        
        out.println(ans);
        out.close();
    }
}
