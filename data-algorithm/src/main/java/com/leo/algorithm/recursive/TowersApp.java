package com.leo.algorithm.recursive;

/**
 * 汉诺塔
 */
public class TowersApp {

    static int nDisks = 10;

    public static void main(String[] args) {
        doTowers(nDisks, 'A', 'B', 'C');
    }

    /**
     * 1.从塔座A移动包含上面n-1个盘子的子树到塔座B上
     * 2.从塔座A移动剩余的盘子（最大的盘子）到塔座C上
     * 3.从塔座B移动子树到塔座C上
     * @param topN
     * @param from
     * @param inter
     * @param to
     */
    private static void doTowers(int topN, char from, char inter, char to) {
        if (topN == 1) {
            System.out.println("Disk 1 from " + from + " to " + to);
        } else {
            doTowers(topN - 1, from, to, inter);
            System.out.println("Disk " + topN + " from " + from + " to " + to);
            doTowers(topN - 1, inter, from, to);
        }
    }
}
