import java.io.*;
import java.util.*;

public class Day12P2 implements AOCInterface {
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
                    HashSet<Point> set = new HashSet<>();
                    dfs(i, j, seen, m, n, arr, grid, grid[i][j], set);
                    while(!set.isEmpty()) {
                        arr[1]++;
                        Point curr = null;
                        for(Point x : set) {
                            curr = x;
                            break;
                        }
                        Queue<Point> q = new LinkedList<>();
                        q.add(curr);
                        set.remove(curr);
                        boolean found = false;
                        while(!q.isEmpty()) {
                            Point x = q.remove();
                            Point up = new Point(x.x - 3, x.y);
                            Point down = new Point(x.x + 3, x.y);
                            if(set.contains(up)) {
                                q.add(up);
                                set.remove(up);
                                found = true;
                            }
                            if(set.contains(down)) {
                                q.add(down);
                                set.remove(down);
                                found = true;
                            }
                        }
                        if(found) {
                            continue;
                        }
                        q.add(curr);
                        while(!q.isEmpty()) {
                            Point x = q.remove();
                            Point left = new Point(x.x, x.y - 3);
                            Point right = new Point(x.x, x.y + 3);
                            if(set.contains(left)) {
                                q.add(left);
                                set.remove(left);
                            }
                            if(set.contains(right)) {
                                q.add(right);
                                set.remove(right);
                            }
                        }
                    }
                    ans += arr[0] * arr[1];
                }
            }
        }
        
        out.println(ans);
        out.close();
    }

    public static int[] dir = new int[]{0, 1, 0, -1, 0};
    public static void dfs(int r, int c, boolean[][] seen, int m, int n, long[] arr, char[][] grid, char type, HashSet<Point> set) {
        for(int i = 0; i < 4; i++) {
            int nr = r + dir[i];
            int nc = c + dir[i+1];
            if(nr >= 0 && nr < m && nc >= 0 && nc < n) {
                if(!seen[nr][nc] && grid[nr][nc] == type) {
                    seen[nr][nc] = true;
                    arr[0]++;
                    dfs(nr, nc, seen, m, n, arr, grid, type, set);
                } else if(grid[nr][nc] != type) {
                    set.add(new Point(3*r + dir[i], 3*c + dir[i+1]));
                }
            } else {
                set.add(new Point(3*r + dir[i], 3*c + dir[i+1]));
            }
        }
    }
}
