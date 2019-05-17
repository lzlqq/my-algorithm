package com.leo.data.stackqueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by LSH7120 on 2019/2/1.
 */
public class Bracket {

    public static void main(String[] args) throws IOException {
        String input;
        while (true) {
            System.out.print("Enter a string: ");
            System.out.flush();
            input = getString();
            if (input.equals("")) {
                break;
            }
            BracketChecker theChecker = new BracketChecker(input);
            theChecker.check();
        }
    }

    private static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        return br.readLine();
    }

    static class BracketChecker {
        private String input;

        public BracketChecker(String input) {
            this.input = input;
        }

        public void check() {
            int stackSize = input.length();
            StackX theStackX = new StackX(stackSize);
            for (int j = 0; j < input.length(); j++) {
                char ch = input.charAt(j);
                switch (ch) {
                    case '{':
                    case '[':
                    case '(':
                        theStackX.push(ch);
                        break;
                    case '}':
                    case ']':
                    case ')':
                        if (!theStackX.isEmpty()) {
                            char chx = theStackX.pop();
                            if ((ch == '}' && chx != '{') ||
                                    (ch == ']' && chx != '[') ||
                                    (ch == ')' && chx != '(')) {
                                System.out.println("Error: " + ch + " at " + j);
                            }
                        } else {
                            System.out.println("Error: " + ch + " at " + j);
                        }
                        break;
                    default:
                        break;
                }
            }
            if (!theStackX.isEmpty()) {
                System.out.println("Error: missing right delimiter");
            }
        }
    }

    static class StackX {
        private int maxSize;
        private char[] stackArray;
        private int top;

        public StackX(int maxSize) {
            this.maxSize = maxSize;
            stackArray = new char[maxSize];
            top = -1;
        }

        public void push(char j) {
            stackArray[++top] = j;
        }

        public char pop() {
            return stackArray[top--];
        }

        public char peek() {
            return stackArray[top];
        }

        public boolean isEmpty() {
            return top == -1;
        }

        public boolean isFull() {
            return top == maxSize - 1;
        }
    }
}
