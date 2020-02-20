package hackerrank.hermionaAndRon;

import java.io.*;
import java.util.*;

public class Solution {

    private static final String IMPRESSED = "Impressed";
    private static final String OOPS = "Oops!";
    private static int M;
    private static int N;
    static Stack<Node> stack = new Stack<>();
    static List<Node> visitedNodes = new ArrayList<>();
    static String[] MATRIX;

    public static String countLuck(String[] matrix, int k) {
        M = matrix[0].length();
        N = matrix.length;
        MATRIX = matrix;
        Node currentNode = null;
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (matrix[y].charAt(x) == 'M') {
                    Node startingNode = new Node(x, y, 'M', 0);
                    currentNode = startingNode;
                    stack.push(currentNode);
                }
            }
        }

        while (!currentNode.isDestination()) {
            currentNode = stack.pop();
            List<Node> notVisitedNeighbours = getNotVisitedNeighbours(currentNode);
            if (notVisitedNeighbours.size() > 0) {
                visitedNodes.add(currentNode);
                currentNode = notVisitedNeighbours.get(0);
                for (Node notVisitedNeighbour : notVisitedNeighbours) {
                    stack.push(notVisitedNeighbour);
                }

            }
        }
        visitedNodes.clear();
        stack.clear();
        return currentNode.numberOfJunction == k ? IMPRESSED : OOPS;
    }

    private static List<Node> getNotVisitedNeighbours(Node node) {
        int x = node.x;
        int y = node.y;
        List<Node> neighbours = new ArrayList<>();
        addNeighbour(x - 1, y, neighbours, node.numberOfJunction);
        addNeighbour(x + 1, y, neighbours, node.numberOfJunction);
        addNeighbour(x, y - 1, neighbours, node.numberOfJunction);
        addNeighbour(x, y + 1, neighbours, node.numberOfJunction);

        if (neighbours.size() > 1) {
            for (Node notVisitedNeighbour : neighbours) {
                notVisitedNeighbour.numberOfJunction++;
            }
        }
        return neighbours;
    }

    private static void addNeighbour(int x, int y, List<Node> neighbours, int numberOfJunction) {
        if (isInsideMatrix(x, y)) {
            char character = MATRIX[y].charAt(x);
            if (character == '.' || character == '*') {
                Node node = new Node(x, y, character, numberOfJunction);
                if (!visitedNodes.contains(node)) {
                    neighbours.add(node);
                }
            }
        }
    }

    private static class Node {

        public final int x;
        public final int y;
        private boolean destination = false;
        public int numberOfJunction;

        public Node(int x, int y, char type, int numberOfJunction) {
            this.x = x;
            this.y = y;
            this.numberOfJunction = numberOfJunction;
            if (type == '*') {
                destination = true;
            }
        }

        public boolean isDestination() {
            return destination;
        }

        @Override
        public boolean equals(Object o) {
            Node node = (Node) o;
            return x == node.x &&
                    y == node.y;
        }
    }

    private static boolean isInsideMatrix(int x, int y) {
        return x >= 0 && x < M && y >= 0 && y < N;
    }


    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            String[] nm = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nm[0]);

            int m = Integer.parseInt(nm[1]);

            String[] matrix = new String[n];

            for (int i = 0; i < n; i++) {
                String matrixItem = scanner.nextLine();
                matrix[i] = matrixItem;
            }

            int k = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            String result = countLuck(matrix, k);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
