package lw05.prelab;

import java.util.*;

public class Main {
    static int[][] adjacencyMatrix;
    static int nodeCount;
    static String[] buildingNames;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(Main.class.getResourceAsStream("campus.txt"));

        nodeCount = scanner.nextInt();
        buildingNames = new String[nodeCount];
        adjacencyMatrix = new int[nodeCount][nodeCount];
        for (int index = 0; index < nodeCount; index++) {
            buildingNames[index] = scanner.next();
        }

        int edgeCount = scanner.nextInt();
        while (edgeCount-- > 0) {
            addEdge(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
        }

        int startNode = scanner.nextInt();
        int destNode = scanner.nextInt();

        System.out.println("=== BFS from " + buildingNames[startNode] + " ===");
        printPath(bfs(startNode));

        System.out.println("=== DFS from " + buildingNames[startNode] + " ===");
        dfs(startNode);
        printPath(dfsVisitOrder);

        dijkstraAlg(startNode, destNode);

    }

    static void addEdge(int source, int target, int walkTime) {
        adjacencyMatrix[source][target] = walkTime;
        adjacencyMatrix[target][source] = walkTime;
    }

    static List<Integer> bfs(int startNode) {
        List<Integer> visitOrder = new ArrayList<>();
        Set<Integer> visitedSet = new HashSet<>();
        Queue<Integer> bfsQueue = new LinkedList<>();
        bfsQueue.add(startNode);
        visitedSet.add(startNode);
        while (!bfsQueue.isEmpty()) {
            int currentNode = bfsQueue.poll();
            visitOrder.add(currentNode);
            for (int neighbor = 0; neighbor < nodeCount; neighbor++) {
                if (!visitedSet.contains(neighbor)) {
                    visitedSet.add(neighbor);
                    bfsQueue.add(neighbor);
                }
            }
        }
        return visitOrder;
    }

    static Set<Integer> dfsVisitedSet = new HashSet<>();
    static List<Integer> dfsVisitOrder = new ArrayList<>();

    static void dfs(int currentNode) {
        dfsVisitedSet.add(currentNode);
        dfsVisitOrder.add(currentNode);
        for (int neighbor = 0; neighbor < nodeCount; neighbor++) {
            if (adjacencyMatrix[currentNode][neighbor] > 0 && !dfsVisitedSet.contains(neighbor)) {
                dfs(neighbor);
            }
        }
    }

    static void dijkstraAlg(int startNode, int destNode) {
        int[] shortestDist = new int[nodeCount];
        for (int index = 0; index < nodeCount; index++) {
            shortestDist[index] = Integer.MAX_VALUE;
        }
        shortestDist[startNode] = 0;
        boolean[] visitedFlags = new boolean[nodeCount];
        int[] previousNode = new int[nodeCount];

        for (int round = 0; round < nodeCount; round++) {
            int currentNode = findTheNextNode(visitedFlags, shortestDist);
            if (currentNode == -1)
                break;
            visitedFlags[currentNode] = true;
            for (int neighbor = 0; neighbor < nodeCount; neighbor++) {
                int candidateDist = shortestDist[currentNode] + adjacencyMatrix[currentNode][neighbor];
                if (adjacencyMatrix[currentNode][neighbor] > 0 && candidateDist < shortestDist[neighbor]) {
                    shortestDist[neighbor] = candidateDist;
                    previousNode[neighbor] = currentNode;
                }
            }
        }

        System.out.println("=== Shortest Time from " + buildingNames[startNode] + " ===");
        for (int index = 0; index < nodeCount; index++) {
            System.out.println(buildingNames[index] + " : " + shortestDist[index] + " min");
        }
        System.out.println("=== Shortest Route from " + buildingNames[startNode] + " to " + buildingNames[destNode] + " ===");
        route(destNode, startNode, previousNode);
        System.out.println();
        System.out.println("Total time: " + shortestDist[destNode] + " min");
    }

    static int findTheNextNode(boolean[] visitedFlags, int[] shortestDist) {
        int minDist = Integer.MAX_VALUE;
        int closestNode = -1;
        for (int index = 0; index < nodeCount; index++) {
            if (!visitedFlags[index] && shortestDist[index] < minDist) {
                minDist = shortestDist[index];
                closestNode = index;
            }
        }
        return closestNode;
    }

    static void route(int currentNode, int startNode, int[] previousNode) {
        if (currentNode == startNode) {
            System.out.print(buildingNames[currentNode]);
        } else {
            route(previousNode[currentNode], startNode, previousNode);
            System.out.print(" -> " + buildingNames[currentNode]);
        }
    }

    static void printPath(List<Integer> pathList) {
        for (int index = 0; index < pathList.size(); index++) {
            System.out.print(buildingNames[pathList.get(index)]);
            if (index < pathList.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }
}