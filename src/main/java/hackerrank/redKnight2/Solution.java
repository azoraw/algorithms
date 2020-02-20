package hackerrank.redKnight2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
    private enum Direction {
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

        public static Direction[] getValues() {
            Direction[] values = Direction.values();
            Direction[] directions = new Direction[values.length - 1];
            for (int i = 0; i < values.length - 1; i++) {
                directions[i] = values[i];
            }
            return directions;
        }

        public static Direction[] getValuesInverted() {
            Direction[] values = Direction.values();
            Direction[] directions = new Direction[values.length - 1];
            for (int i = 0; i < directions.length; i++) {
                directions[i] = values[values.length - i - 2];
            }
            return directions;
        }
    }


    static void printShortestPath(int n, int yStart, int xStart, int yEnd, int xEnd) {
        DirectionContainer[][] visited = new DirectionContainer[n][n];
        Queue<Node> queueFromStart = new ArrayDeque<>();
        queueFromStart.add(new Node(xStart, yStart, Direction.START));
        visited[xStart][yStart] = new DirectionContainer(Direction.START, null);

        Queue<Node> queueFromEnd = new ArrayDeque<>();
        queueFromEnd.add(new Node(xEnd, yEnd, Direction.START));
        visited[xEnd][yEnd] = new DirectionContainer(null, Direction.START);

        while (!queueFromStart.isEmpty() || !queueFromEnd.isEmpty()) {
            Node nodeFromStart = queueFromStart.poll();

            int newXStart;
            int newYStart;
            for (Direction direction : Direction.getValues()) {
                newXStart = nodeFromStart.x + direction.dx;
                newYStart = nodeFromStart.y + direction.dy;

                if (isValid(newXStart, newYStart, n) && (visited[newXStart][newYStart] == null || visited[newXStart][newYStart].lastFromStart == null)) {
                    queueFromStart.add(new Node(newXStart, newYStart, direction));
                    if (visited[newXStart][newYStart] == null) {
                        visited[newXStart][newYStart] = new DirectionContainer(direction, null);
                    } else {
                        visited[newXStart][newYStart].lastFromStart = direction;
                        if (visited[newXStart][newYStart].lastFromStart != null && visited[newXStart][newYStart].lastFromEnd != null) {
                            generateOutput(visited, newXStart, newYStart);
                            return;
                        }
                    }
                }
            }

            Node nodeFromEnd = queueFromEnd.poll();

            int newXEnd;
            int newYEnd;
            for (Direction direction : Direction.getValuesInverted()) {
                newXEnd = nodeFromEnd.x + direction.reversed.dx;
                newYEnd = nodeFromEnd.y + direction.reversed.dy;

                if (isValid(newXEnd, newYEnd, n) && (visited[newXEnd][newYEnd] == null || visited[newXEnd][newYEnd].lastFromEnd == null)) {
                    queueFromEnd.add(new Node(newXEnd, newYEnd, direction));
                    if (visited[newXEnd][newYEnd] == null) {
                        visited[newXEnd][newYEnd] = new DirectionContainer(null, direction);
                    } else {
                        visited[newXEnd][newYEnd].lastFromEnd = direction;
                        if (visited[newXEnd][newYEnd].lastFromStart != null && visited[newXEnd][newYEnd].lastFromEnd != null) {
                            generateOutput(visited, newXEnd, newYEnd);
                            return;
                        }
                    }
                }
            }

        }
        System.out.println("Impossible");
    }

    private static void generateOutput(DirectionContainer[][] visited, int meetX, int meetY) {
        List<String> directionFromStart = new ArrayList<>();
        int newX = meetX;
        int newY = meetY;
        Direction lastDirection = visited[newX][newY].lastFromStart;
        while (lastDirection != Direction.START) {
            directionFromStart.add(lastDirection.name());
            newX += lastDirection.reversed.dx;
            newY += lastDirection.reversed.dy;
            lastDirection = visited[newX][newY].lastFromStart;
        }

        newX = meetX;
        newY = meetY;
        List<String> directionFromEnd = new ArrayList<>();
        lastDirection = visited[newX][newY].lastFromEnd;
        while (lastDirection != Direction.START) {
            directionFromEnd.add(lastDirection.name());
            newX += lastDirection.dx;
            newY += lastDirection.dy;
            lastDirection = visited[newX][newY].lastFromEnd;
        }
        System.out.println(directionFromStart.size() + directionFromEnd.size());
        for (int i = directionFromStart.size() - 1; i >= 0; i--) {
            System.out.print(directionFromStart.get(i) + " ");
        }
        for (int i = 0; i < directionFromEnd.size(); i++) {
            System.out.print(directionFromEnd.get(i) + " ");
        }
    }

    private static boolean isValid(int x, int y, int n) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    private static class DirectionContainer {
        public Direction lastFromStart;
        public Direction lastFromEnd;

        public DirectionContainer(Direction lastFromStart, Direction lastFromEnd) {
            this.lastFromStart = lastFromStart;
            this.lastFromEnd = lastFromEnd;
        }
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
