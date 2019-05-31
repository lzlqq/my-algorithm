package com.leo.data.graph;

/**
 * 无权值拓扑排序
 */
public class Topo{

    private final int MAX_VERTS = 20;

    private Vertex[] vertexList;

    private int adjMat[][];// adjacency matrix

    private int nVerts;

    private char[] sortedArray;

    public Topo(){
        vertexList = new Vertex[MAX_VERTS];
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        for (int i = 0; i < MAX_VERTS; i++){
            for (int j = 0; j < MAX_VERTS; j++){
                adjMat[i][j] = 0;
            }
        }
        sortedArray = new char[MAX_VERTS];
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
    
    /**
     * 1.调用noSuccessors（）找到任意一个没有后继的顶点
     * 2.如果找到一个这样的顶点，把顶点放入数组sortedArray[],并且从图中删除顶点
     * 3.如果没有这样的顶点，则图必然存在环
     */
    public void topo(){
        int origNVerts = nVerts;// remember how many verts
        while (nVerts > 0){// while vertices remain,get a vertex with no successors,or -1
            int currentVertex = noSuccessors();
            if (currentVertex == -1){
                System.out.println("ERROR:Graph has cycles");
                return;
            }

            sortedArray[nVerts - 1] = vertexList[currentVertex].label;
            deleteVertex(currentVertex);
        }

        System.out.print("Topologically sorted order: ");
        for (int i = 0; i < origNVerts; i++){
            System.out.print(sortedArray[i]);
        }
        System.out.println();
    }

    private int noSuccessors(){
        boolean isEdge;
        for (int row = 0; row < nVerts; row++){
            isEdge = false;
            for (int col = 0; col < nVerts; col++){
                if (adjMat[row][col] > 0){
                    isEdge = true;
                    break;
                }
            }
            if (!isEdge){
                return row;
            }
        }
        return -1;
    }

    private void deleteVertex(int delVert){
        if (delVert != nVerts - 1){
            for (int j = delVert; j < nVerts - 1; j++){
                vertexList[j] = vertexList[j + 1];// delete from vertexList
            }
            for (int row = delVert; row < nVerts - 1; row++){
                // 上移的时候，列数还是nVerts
                moveRowUp(row, nVerts);// delete row from adjMat
            }
            for (int col = delVert; col < nVerts - 1; col++){
                // 由于先上移了，所以再左移的时候，只有nVerts-1行
                moveColLeft(col, nVerts - 1);// delete col from adjMat
            }
        }
        nVerts--;
    }

    private void moveRowUp(int row,int length){
        for (int col = 0; col < length; col++){
            adjMat[row][col] = adjMat[row + 1][col];
        }
    }

    private void moveColLeft(int col,int length){
        for (int row = 0; row < length; row++){
            adjMat[row][col] = adjMat[row][col + 1];
        }
    }

    class Vertex{

        public char label;

        public Vertex(char label){
            this.label = label;
        }
    }
}
