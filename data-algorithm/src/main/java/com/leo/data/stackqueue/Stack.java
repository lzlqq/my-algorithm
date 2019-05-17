package com.leo.data.stackqueue;

/**
 * Created by LSH7120 on 2019/2/1.
 */
public class Stack {
    /**
     * LIFO 后进先出
     * @param args
     */
    public static void main(String[] args) {
        StackX theStack= new StackX(10);
        theStack.push(20);
        theStack.push(40);
        theStack.push(60);
        theStack.push(80);

        while (!theStack.isEmpty()){
            System.out.print(theStack.pop()+" ");
        }
    }

   static class StackX {
        private int maxSize;
        private long[] stackArray;
        private int top;

        public StackX(int maxSize) {
            this.maxSize = maxSize;
            stackArray=new long[maxSize];
            top=-1;
        }
        public void push(long j){
            stackArray[++top]=j;
        }
        public  long pop(){
            return stackArray[top--];
        }
        public long peek(){
            return stackArray[top];
        }
        public boolean isEmpty(){
            return top==-1;
        }
        public boolean isFull(){
            return top==maxSize-1;
        }
    }
}
