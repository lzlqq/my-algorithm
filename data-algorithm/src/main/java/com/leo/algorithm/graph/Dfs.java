package com.leo.algorithm.graph;

/**
 * 深度优先搜索
 */
public class Dfs {

    private final int MAX_VERTS = 20;
    private Vertex[] vertexList;
    private int adjMat[][];// adjacency matrix
    private int nVerts;
    private StackX theStack;

    public Dfs() {
        vertexList = new Vertex[MAX_VERTS];
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        for (int i = 0; i < MAX_VERTS; i++) {
            for (int j = 0; j < MAX_VERTS; j++) {
                adjMat[i][j] = 0;
            }
        }
        theStack = new StackX();
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
     * 规则1：如果可能，访问一个邻接的未访问顶点，标记它，并把它放入栈中
     * 规则2：当不能执行规则1时，如果栈不空，就从栈中弹出一个顶点
     * 规则3：如果不能执行规则1和规则2，就完成了整个搜索过程
     */
    public void dfs() {// depth-first search
        vertexList[0].wasVisited = true;
        displayVertex(0);
        theStack.push(0);

        while (!theStack.isEmpty()) {
            //get an unvisited vertex adjacent to stack top
            int v = getAdjUnvisiedVertex(theStack.peek());
            if (v == -1) {
                theStack.pop();
            } else {
                vertexList[v].wasVisited = true;
                displayVertex(v);
                theStack.push(v);
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

    class StackX {
        private final int SIZE = 20;
        private int[] st;
        private int top;

        public StackX() {
            st = new int[SIZE];
            top = -1;
        }

        public void push(int j) {
            st[++top] = j;
        }

        public int pop() {
            return st[top--];
        }

        public int peek() {
            return st[top];
        }

        public boolean isEmpty() {
            return top == -1;
        }
    }
}
