package com.leo.data.graph;

/**
 * 广度优先搜索
 */
public class Bfs {

    private final int MAX_VERTS = 20;
    private Vertex[] vertexList;
    private int adjMat[][];// adjacency matrix
    private int nVerts;
    private Queue theQueue;

    public Bfs() {
        vertexList = new Vertex[MAX_VERTS];
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        for (int i = 0; i < MAX_VERTS; i++) {
            for (int j = 0; j < MAX_VERTS; j++) {
                adjMat[i][j] = 0;
            }
        }
        theQueue = new Queue();
    }

    public void addVertex(char label) {
        vertexList[nVerts++] = new Vertex(label);
    }

    public void addEdge(int start, int end) {
        adjMat[start][end] = 1;
        adjMat[end][start] = 1;
    }

    public void displayVertex(int v) {
        System.out.print(vertexList[v].label);
    }

    /**
     * 规则1：访问下一个未来访问的邻接点（如果存在），这个顶点必须是当前顶点的邻接点，标记它，并把它插入到队列中
     * 规则2：如果因为已经没有未访问顶点而不能执行规则1，那么从队列头取一个顶点（如果存在），并使其成为当前顶点
     * 规则3：如果因为队列为空而不能执行规则2，则搜索结束
     */
    public void bfs() {// depth-first search
        vertexList[0].wasVisited = true;
        displayVertex(0);
        theQueue.insert(0);
        int v2;

        while (!theQueue.isEmpty()) {
            int v1 = theQueue.remove();
            //until it has no unvisited neighbors
            while ((v2 = getAdjUnvisiedVertex(v1)) != -1) {
                vertexList[v2].wasVisited = true;
                displayVertex(v2);
                theQueue.insert(v2);
            }
        }
        //stack is empty, so we're done
        for (int i = 0; i < nVerts; i++) {
            vertexList[i].wasVisited = false;
        }
    }

    private int getAdjUnvisiedVertex(int v) {
        for (int i = 0; i < nVerts; i++) {
            if (adjMat[v][i] == 1 && vertexList[i].wasVisited == false) {
                return i;
            }
        }
        return -1;
    }

    class Vertex {
        public char label;
        public boolean wasVisited;

        public Vertex(char label) {
            this.label = label;
            wasVisited = false;
        }
    }

    class Queue {
        private final int SIZE = 20;
        private int[] queArray;
        private int front;
        private int rear;

        public Queue() {
            queArray = new int[SIZE];
            front = 0;
            rear = -1;
        }

        private void insert(int j) {
            if (rear == SIZE - 1) {
                rear = -1;
            }
            queArray[++rear] = j;
        }

        public int remove() {
            int temp = queArray[front++];
            if (front == SIZE) {
                front = 0;
            }
            return temp;
        }

        public boolean isEmpty() {
            return (rear + 1 == front) || (front + SIZE - 1 == rear);
        }
    }
}
