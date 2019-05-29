package com.leo.algorithm.machine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Knn{

    public static void main(String[] args){

        Object[][] sample = {
                              //id 电影名称 搞笑镜头 拥抱镜头 打斗镜头 电影类型
                              { 1, "宝贝当家", 45, 2, 9, "喜剧片" },
                              { 2, "美人鱼", 21, 17, 5, "喜剧片" },
                              { 3, "澳门风云3", 54, 9, 11, "喜剧片" },
                              { 4, "功夫熊猫3", 39, 0, 31, "喜剧片" },
                              { 5, "谍影重重", 5, 2, 57, "动作片" },
                              { 6, "叶问3", 3, 2, 65, "动作片" },
                              { 7, "伦敦陷落", 2, 3, 55, "动作片" },
                              { 8, "我的特工爷爷", 6, 4, 21, "动作片" },
                              { 9, "奔爱", 7, 46, 4, "爱情片" },
                              { 10, "夜孔雀", 9, 39, 8, "爱情片" },
                              { 11, "代理情人", 9, 38, 2, "爱情片" },
                              { 12, "新步步惊心", 8, 34, 17, "爱情片" }, };
        // 求唐人街办案类型
        Object[] movie = { 13, "唐人街探案", 23, 3, 17, null };
        int length = sample.length;
        System.out.println("序号\t名称\t距离");
        List<MovieDis> movieDisList = new ArrayList<>();
        for (int i = 0; i < length; i++){
            Object[] mv = sample[i];
            double distances = getDistance(mv, movie);
            MovieDis movieDis = new MovieDis((int) mv[0], (String) mv[1], distances, (String) mv[5]);
            System.out.println(String.format("%s\t%s\t%s", mv[0], mv[1], distances));
            movieDisList.add(movieDis);
        }

        Collections.sort(movieDisList, new Comparator<MovieDis>(){

            @Override
            public int compare(MovieDis o1,MovieDis o2){
                double sub = o1.getDistance() - o2.getDistance();
                if (sub == 0){
                    return 0;
                }
                if (sub > 0){
                    return 1;
                }
                return -1;
            }
        });

        int k = 5;
        System.out.println("按照欧式距离排序，取k=5");
        movieDisList = movieDisList.subList(0, k);
        for (MovieDis movieDis : movieDisList){
            System.out.println(movieDis);
        }
    }

    private static double getDistance(Object[] movie1,Object[] movie2){
        double[] ps1 = { (Integer) movie1[2], (Integer) movie1[3], (Integer) movie1[4] };
        double[] ps2 = { (Integer) movie2[2], (Integer) movie2[3], (Integer) movie2[4] };
        return getDistance(ps1, ps2);
    }

    private static double getDistance(double[] ps1,double[] ps2){
        if (ps1.length != ps2.length){
            throw new RuntimeException("属性数量不对应");
        }
        int length = ps1.length;
        double total = 0;
        for (int i = 0; i < length; i++){
            double sub = ps1[i] - ps2[i];
            total += (sub * sub);
        }
        return Math.sqrt(total);
    }
}

class MovieDis{

    private int id;

    private String title;

    private double distance;

    private String type;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public double getDistance(){
        return distance;
    }

    public void setDistance(double distance){
        this.distance = distance;
    }

    public MovieDis(int id, String title, double distance, String type){
        super();
        this.id = id;
        this.title = title;
        this.distance = distance;
        this.type = type;
    }

    @Override
    public String toString(){
        return "MovieDis [id=" + id + ", title=" + title + ", distance=" + distance + ", type=" + type + "]";
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

}
