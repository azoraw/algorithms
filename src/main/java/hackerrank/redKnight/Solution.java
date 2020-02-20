package hackerrank.redKnight;

import java.util.*;

public class Solution {
    public enum Direction {
        UL(-1, -2),
        UR(1, -2),
        R(2, 0),
        LR(1, 2),
        LL(-1, 2),
        L(-2, 0),
        START(0, 0);

        static {
            UL.reversed = LR;
            UR.reversed = LL;
            R.reversed = L;
            LR.reversed = UL;
            LL.reversed = UR;
            L.reversed = R;

        }
        Direction reversed;
        public final int dx;
        public final int dy;

        Direction(int i, int j) {
            dx = i;
            dy = j;
        }
    }

    static void printShortestPath(int n, int yStart, int xStart, int yEnd, int xEnd) {
        Direction[][] visited = new Direction[n][n];
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(xStart, yStart, null));
        visited[xStart][yStart] = Direction.START;

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if (node.isDestination(xEnd, yEnd)) {
                List<String> list = new ArrayList<>();
                int newX = node.x;
                int newY = node.y;
                while (newX != xStart || newY != yStart) {
                    Direction lastDirection = visited[newX][newY];
                    list.add(lastDirection.name());
                    newX += lastDirection.reversed.dx;
                    newY += lastDirection.reversed.dy;
                }

                System.out.println(list.size());
                for (int i = list.size() - 1; i >= 0; i--) {
                    System.out.print(list.get(i) + " ");
                }
                return;
            }

            int newX;
            int newY;
            for (Direction direction : Direction.values()) {
                newX = node.x + direction.dx;
                newY = node.y + direction.dy;

                if (isValid(newX, newY, n) && visited[newX][newY] == null) {
                    queue.add(new Node(newX, newY, direction));
                    visited[newX][newY] = direction;
                }
            }

        }
        System.out.println("Impossible");
    }

    public static boolean isValid(int x, int y, int n) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    private static class Node {
        public Node(int x, int y, Direction lastDirection) {
            this.x = x;
            this.y = y;
            this.lastDirection = lastDirection;
        }

        public int x;
        public int y;
        public Direction lastDirection;

        public boolean isDestination(int iEnd, int jEnd) {
            return x == iEnd && y == jEnd;
        }

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String[] i_startJ_start = scanner.nextLine().split(" ");

        int i_start = Integer.parseInt(i_startJ_start[0]);

        int j_start = Integer.parseInt(i_startJ_start[1]);

        int i_end = Integer.parseInt(i_startJ_start[2]);

        int j_end = Integer.parseInt(i_startJ_start[3]);

        printShortestPath(n, i_start, j_start, i_end, j_end);

        scanner.close();
    }
}
