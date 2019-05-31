package com.leo.data.graph;

/**
 * 带权值最短路径算法
 */
public class Path{

    private final int MAX_VERTS = 20;

    private final int INFINITY = 100000;

    private Vertex vertexList[];

    private int adjMat[][];

    private int nVerts;

    private int nTree;

    private DistPar sPath[];

    private int currentVert;

    private int startToCurrent;

    public Path(){
        vertexList = new Vertex[MAX_VERTS];
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        nTree = 0;
        for (int j = 0; j < MAX_VERTS; j++){
            for (int k = 0; k < MAX_VERTS; k++){
                adjMat[j][k] = INFINITY;
            }
        }
        sPath = new DistPar[MAX_VERTS];
    }

    public void addVertex(char label){
        vertexList[nVerts++] = new Vertex(label);
    }

    public void addEdge(int start,int end,int weight){
        adjMat[start][end] = weight;
    }

    public void path(){
        int startTree = 0;
        vertexList[startTree].isInTree = true;
        nTree = 1;

        //transfer row of distance from adjMat to sPath
        for (int j = 0; j < nVerts; j++){
            int tempDist = adjMat[startTree][j];
            sPath[j] = new DistPar(startTree, tempDist);
        }

        //until all vertices are in the tree
        while (nTree < nVerts){
            int indexMin = getMin();
            int minDist = sPath[indexMin].distance;

            if (minDist == INFINITY){
                System.out.println("There are unreachable vertices");
                break;
            }else{
                //reset currentVert to closest vert
                currentVert = indexMin;
                //minimum distance from startTree is to currentVert,and is startToCurrent
                startToCurrent = sPath[indexMin].distance;
            }

            vertexList[currentVert].isInTree = true;
            nTree++;
            adjustSPath();
        }

        displayPaths();

        nTree = 0;
        for (int j = 0; j < nVerts; j++){
            vertexList[j].isInTree = false;
        }
    }

    /**
     * Dijkstra算法的关键：
     * 1）每次派一个代理人到新的城市，用这个代理人提供的新信息修正费用表单，在表中只保留从源点到某各城市现在已知的最低费用
     * 2）不断向某个新城市派代理人，条件是从源点到那个城市的路线费用最低（并不是从某个有代理人的城市出发的费用最低的路线，那是在最小生成树中用到的）
     */
    private void displayPaths(){
        for (int j = 0; j < nVerts; j++){
            System.out.print(vertexList[j].label + "=");
            if (sPath[j].distance == INFINITY){
                System.out.print("inf");
            }else{
                System.out.print(sPath[j].distance);
            }
            char parent = vertexList[sPath[j].parentVert].label;
            System.out.print("{" + parent + "}");
        }
    }

    private void adjustSPath(){
        int column = 1;
        while (column < nVerts){
            if (vertexList[column].isInTree){
                column++;
                continue;
            }
            //calculate distance for one sPath entry
            int currentToFringe = adjMat[currentVert][column];
            int startToFringe = startToCurrent + currentToFringe;
            int sPathDist = sPath[column].distance;

            if (startToFringe < sPathDist){
                sPath[column].parentVert = currentVert;
                sPath[column].distance = startToFringe;
            }

            column++;
        }
    }

    private int getMin(){
        int minDist = INFINITY;
        int indexMin = 0;
        for (int j = 0; j < nVerts; j++){
            if (!vertexList[j].isInTree && sPath[j].distance < minDist){
                minDist = sPath[j].distance;
                indexMin = j;
            }
        }
        return indexMin;
    }

    class Vertex{

        public char label;

        public boolean isInTree;

        public Vertex(char label){
            this.label = label;
            this.isInTree = false;
        }
    }

    class DistPar{

        public int distance;

        public int parentVert;

        public DistPar(int distance, int parentVert){
            this.distance = distance;
            this.parentVert = parentVert;
        }
    }
}
