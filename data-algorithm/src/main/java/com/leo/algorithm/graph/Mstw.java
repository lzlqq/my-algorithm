package com.leo.algorithm.graph;

/**
 * 带权图最小生成树
 */
public class Mstw {

    private final int MAX_VERTS = 20;
    private final int INFINITY = 100000;
    private Vertex vertexList[];
    private int adjMat[][];
    private int nVerts;
    private int currentVert;
    private PriorityQ thePQ;
    private int nTree;

    public Mstw() {
        vertexList = new Vertex[MAX_VERTS];
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        for (int j = 0; j < MAX_VERTS; j++) {
            for (int k = 0; k < MAX_VERTS; k++) {
                adjMat[j][k] = INFINITY;
            }
        }
        thePQ = new PriorityQ();
    }

    public void addVertex(char lab) {
        vertexList[nVerts++] = new Vertex(lab);
    }

    public void addEdge(int start, int end, int weight) {
        adjMat[start][end] = weight;
        adjMat[end][start] = weight;
    }

    public void displayVertex(int v) {
        System.out.println(vertexList[v].label);
    }

    /**
     * 1.优先级队列
     * 用优先级队列来实现这个用于反复选择最小造价值的表，而不用链表或数组
     * 2.算法要点
     * 从一个顶点开始，把它放入树的集合中。然后重复做下面的事情：
     * 1）找到从最新的顶点到其他顶点的所有边，这些顶点不能再树的集合中。把这些边放入优先级队列
     * 2）找出权值最小的边，把它和它所到达的顶点放入树的集合中。
     * 重复这些步骤，直到所有顶点都在树的集合中。
     * 3.无用边
     * 优先级队列中应该只包含一条到达某个第二类顶点的边
     * 4.在优先级队列中寻找目的地重复的边
     * 在每次向树中增加边的时候，一定要确保没有其他边也到达同样的顶点。如果有，只保留最小权值的边。
     * 这使得在优先级队列中逐步查找成为必要的一步操作，必须违反一下优先级队列的设计思想。
     * 4.代码
     * while循环结束条件是所有顶点都已在树中。循环中完成下面的操作：
     * 1）当前顶点放在树中
     * 2）连接这个顶点的边放到优先级队列中（如果合适）
     * 3）从优先级队列中删除权值最小的边。这条边的目的顶点变成当前顶点
     */
    public void mstw() {
        currentVert = 0;
        while (nTree < nVerts - 1) {
            vertexList[currentVert].isInTree = true;
            nTree++;
            // insert edges adjacent to currentVert int PQ
            for (int j = 0; j < nVerts; j++) {
                if (j == currentVert) {
                    continue;
                }
                if (vertexList[j].isInTree) {
                    continue;
                }
                int distance = adjMat[currentVert][j];
                if (distance == INFINITY) {
                    continue;
                }
                putInPQ(j, distance);
            }
            if (thePQ.size() == 0) {
                System.out.println("GRAPH NOT CONNECTED");
                return;
            }
            Edge theEdge = thePQ.removeMin();
            int sourceVert = theEdge.srcVert;
            currentVert = theEdge.destVert;

            System.out.print(vertexList[sourceVert].label);
            System.out.print(vertexList[currentVert].label);
            System.out.print(" ");
        }
        for (int j = 0; j < nVerts; j++) {
            vertexList[j].isInTree = false;
        }
    }

    private void putInPQ(int newVert, int newDist) {
        int queueIndex = thePQ.find(newVert);
        if (queueIndex != -1) {
            Edge tempEdge = thePQ.peekN(queueIndex);
            int oldDist = tempEdge.distance;
            if (oldDist > newDist) {
                thePQ.removeN(queueIndex);
                Edge theEdge = new Edge(currentVert, newVert, newDist);
                thePQ.insert(theEdge);
            }
        } else {
            Edge theEdge = new Edge(currentVert, newVert, newDist);
            thePQ.insert(theEdge);
        }
    }

    class Vertex {
        public char label;
        public boolean isInTree;

        public Vertex(char label) {
            this.label = label;
            this.isInTree = false;
        }
    }

    class PriorityQ {
        private final int SIZE = 20;
        private Edge[] queArray;
        private int size;

        public PriorityQ() {
            this.queArray = new Edge[SIZE];
            this.size = 0;
        }

        public void insert(Edge item) {
            int j;

            for (j = 0; j < size; j++) {
                if (item.distance >= queArray[j].distance) {
                    break;
                }
            }

            for (int k = size - 1; k >= j; k--) {
                queArray[k + 1] = queArray[k];
            }

            queArray[j] = item;
            size++;
        }

        public Edge removeMin() {
            return queArray[--size];
        }

        public void removeN(int n) {
            for (int j = n; j < size - 1; j++) {
                queArray[j] = queArray[j + 1];
            }
            size--;
        }

        public Edge peekMin() {
            return queArray[size - 1];
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public Edge peekN(int n) {
            return queArray[n];
        }

        public int find(int findDex) {
            for (int j = 0; j < size; j++) {
                if (queArray[j].destVert == findDex) {
                    return j;
                }
            }
            return -1;
        }
    }

    class Edge {
        public int srcVert;
        public int destVert;
        public int distance;

        public Edge(int srcVert, int destVert, int distance) {
            this.srcVert = srcVert;
            this.destVert = destVert;
            this.distance = distance;
        }
    }
}
