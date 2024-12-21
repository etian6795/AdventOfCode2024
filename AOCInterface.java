import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface AOCInterface {
    public static long[] readLineAsLongs(String s) {
        ArrayList<Long> arr = new ArrayList<>();
        
        Pattern pattern = Pattern.compile("-?\\d+");
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            arr.add(Long.valueOf(matcher.group()));
        }

        long[] res = new long[arr.size()];
        for(int i = 0; i < res.length; i++) {
            res[i] = arr.get(i);
        }
        return res;
    }

    public static int[] readLineAsInts(String s) {
        ArrayList<Integer> arr = new ArrayList<>();
        
        Pattern pattern = Pattern.compile("-?\\d+");
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            arr.add(Integer.valueOf(matcher.group()));
        }

        int[] res = new int[arr.size()];
        for(int i = 0; i < res.length; i++) {
            res[i] = arr.get(i);
        }
        return res;
    }

    public static char[][] readInputAsGrid(String file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        ArrayList<char[]> arr = new ArrayList<>();
        String line;
        while((line = br.readLine()) != null) {
            arr.add(line.toCharArray());
        }
        char[][] res = new char[arr.size()][arr.get(0).length];
        for(int i = 0; i < res.length; i++) {
            for(int j = 0; j < res[0].length; j++) {
                res[i][j] = arr.get(i)[j];
            }
        }
        return res;
    }

    public static String[] readLineSplitBySpaces(String line) {
        return line.split("\\s+");
    }

    public static double[] sys2eq2varSolver(double a, double b, double c, double d, double e, double f) {
        double det = (a*d - b*c);
        if(det == 0) {
            return null;
        }
        double x = ((d) * (e) - (b) * (f)) / det;
        double y = ((a) * (f) - (c) * (e)) / det;
        return new double[]{x, y};
    }

    public static class Point {
        long x;
        long y;
        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object other) {
            if(this == other) {
                return true;
            }
            if(other == null || getClass() != other.getClass()) {
                return false;
            }
            return this.x == ((Point)other).x && this.y == ((Point)other).y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return x+" "+y;
        }
    }

    public static int bfs(char[][] grid, int m, int n, int sr, int sc, int er, int ec, String good, int[][] dirs) {
        int d = 0;
        Queue<int[]> q = new LinkedList<>();
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
                for(int[] dir : dirs) {
                    int nr = curr[0] + dir[0];
                    int nc = curr[1] + dir[1];
                    if(nr >= 0 && nr < m && nc >= 0 && nc < n && !seen[nr][nc] && good.contains(grid[nr][nc]+"")) {
                        q.add(new int[]{nr, nc});
                        seen[nr][nc] = true;
                    }
                }
            }
            d++;
        }

        return -1;
    }
}
