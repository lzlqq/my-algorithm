package com.leo.data.graph;

/**
 * 无权图最小生成树
 */
public class Mst{

    private final int MAX_VERTS = 20;

    private Vertex[] vertexList;

    private int adjMat[][];// adjacency matrix

    private int nVerts;

    private StackX theStack;

    public Mst(){
        vertexList = new Vertex[MAX_VERTS];
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        for (int i = 0; i < MAX_VERTS; i++){
            for (int j = 0; j < MAX_VERTS; j++){
                adjMat[i][j] = 0;
            }
        }
        theStack = new StackX();
    }

    public void addVertex(char label){
        vertexList[nVerts++] = new Vertex(label);
    }

    public void addEdge(int start,int end){
        adjMat[start][end] = 1;
        adjMat[end][start] = 1;
    }

    public void displayVertex(int v){
        System.out.print(vertexList[v].label);
    }

    public void mst(){// depth first
        vertexList[0].wasVisited = true;
        theStack.push(0);

        while (!theStack.isEmpty()){
            int currentVertex = theStack.peek();
            // get next unvisited neighbor
            int v = getAdjUnvisiedVertex(currentVertex);
            if (v == -1){
                theStack.pop();
            }else{
                vertexList[v].wasVisited = true;
                theStack.push(v);

                displayVertex(currentVertex);
                displayVertex(v);
                System.out.print(" ");
            }
        }
        //stack is empty, so we're done
        for (int i = 0; i < nVerts; i++){
            vertexList[i].wasVisited = false;
        }
    }

    private int getAdjUnvisiedVertex(int v){
        for (int i = 0; i < nVerts; i++){
            if (adjMat[v][i] == 1 && vertexList[i].wasVisited == false){
                return i;
            }
        }
        return -1;
    }

    class Vertex{

        public char label;

        public boolean wasVisited;

        public Vertex(char label){
            this.label = label;
            wasVisited = false;
        }
    }

    class StackX{

        private final int SIZE = 20;

        private int[] st;

        private int top;

        public StackX(){
            st = new int[SIZE];
            top = -1;
        }

        public void push(int j){
            st[++top] = j;
        }

        public int pop(){
            return st[top--];
        }

        public int peek(){
            return st[top];
        }

        public boolean isEmpty(){
            return top == -1;
        }
    }
}
