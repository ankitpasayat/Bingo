/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo;

import java.util.Scanner;

/**
 *
 * @author Administrator
 */
public class Bingo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        int[] NUM1 = new int[25];
        int[] NUM2 = new int[25];
        int[] used = new int[25];
        for(int i = 0; i <25;){
            int temp = (int)(Math.random()*25) + 1;
            if(!containsVal(NUM1, temp)) {
                NUM1[i++] = temp;
            }
        }
        for(int i = 0; i <25;){
            int temp = (int)(Math.random()*25) + 1;
            if(!containsVal(NUM2, temp)) {
                NUM2[i++] = temp;
            }
        }
        int c = 0;
        System.out.println("MATRIX 1:  ");
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                System.out.print(NUM1[c++] + " ");
            }
            System.out.println();
        }
        System.out.println();
        c = 0;
        System.out.println("MATRIX 2:  ");
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                System.out.print(NUM2[c++] + " ");
            }
            System.out.println();
        }
        System.out.println();
        int u = 0, row_status1, col_status1, row_status2, col_status2, num, index1, index2, curr_row, curr_column;
        int bingo1 = 0, bingo2 = 0;
        int[] row1;
        row1 = new int[5];
        int[] column1;
        column1 = new int[5];
        int[] row2;
        row2 = new int[5];
        int[] column2;
        column2 = new int[5];
        for(int t = 0; t < 25;){
            if(t % 2 == 0){
                System.out.println("Enter your number: ");
                num = sc.nextInt();
                if(num > 0 && num < 26 && !containsVal(used, num)){
                    used[u++] = num;
                    t++;
                }
                else
                    continue;
                index1 = findIndex(NUM1, num);
                System.out.println("INDEX 1: " + index1);
                index2 = findIndex(NUM2, num);
                System.out.println("INDEX 2: " + index2);
                curr_column = index1 % 5;
                curr_row = 0;
                while(index1 > 4){
                    curr_row++;
                    index1 -= 5;
                }
                System.out.println("CURRENT ROW 1: " + curr_row);
                System.out.println("CURRENT COLUMN 1: " + curr_column);
                row1[curr_row]++;
                column1[curr_column]++;
                curr_column = index2 % 5;
                curr_row = 0;
                while(index2 > 4){
                    curr_row++;
                    index2 -= 5;
                }
                System.out.println("CURRENT ROW 2: " + curr_row);
                System.out.println("CURRENT COLUMN 2: " + curr_column);
                row2[curr_row]++;
                column2[curr_column]++;
                for(int k = 0; k < 5; k++){
                    System.out.println("ROW_1[" + k +"]: " + row1[k] + " COL_1[" + k +"]: " + column1[k]);
                }
                System.out.println();
                for(int k = 0; k < 5; k++){
                    System.out.println("ROW_2[" + k +"]: " + row2[k] + " COL_2[" + k +"]: " + column2[k]);
                }
                System.out.println();
                row_status1 = check_row(row1);
                col_status1 = check_col(column1);
                row_status2 = check_row(row2);
                col_status2 = check_col(column2);
                if(row_status1 > 0){
                    bingo1++;
                    row1[row_status1] = 0;
                }
                if(col_status1 > 0){
                    bingo1++;
                    column1[col_status1] = 0;
                }
                if(row_status2 > 0){
                    bingo2++;
                    row2[row_status2] = 0;
                }
                if(col_status2 > 0){
                    bingo2++;
                    column2[col_status2] = 0;
                }
                if(bingo1 == 5){
                    System.out.println("USER WINS!!!");
                    break;
                }
                if(bingo2 == 5){
                    System.out.println("COMPUTER WINS!!!");
                    break;
                }
            }
            else{
                num = (int)(Math.random()*25) + 1;
                System.out.println("Computer's number: " + num);
                if(!containsVal(used, num)){
                    used[u++] = num;
                    t++;
                }
                else
                    continue;
                index1 = findIndex(NUM1, num);
                System.out.println("INDEX 1: " + index1);
                index2 = findIndex(NUM2, num);
                System.out.println("INDEX 2: " + index2);
                curr_column = index1 % 5;
                curr_row = 0;
                while(index1 > 4){
                    curr_row++;
                    index1 -= 5;
                }
                System.out.println("CURRENT ROW 1: " + curr_row);
                System.out.println("CURRENT COLUMN 1: " + curr_column);
                row1[curr_row]++;
                column1[curr_column]++;
                curr_column = index2 % 5;
                curr_row = 0;
                while(index2 > 4){
                    curr_row++;
                    index2 -= 5;
                }
                System.out.println("CURRENT ROW 2: " + curr_row);
                System.out.println("CURRENT COLUMN 2: " + curr_column);
                row2[curr_row]++;
                column2[curr_column]++;
                for(int k = 0; k < 5; k++){
                    System.out.println("ROW_1[" + k +"]: " + row1[k] + " COL_1[" + k +"]: " + column1[k]);
                }
                System.out.println();
                for(int k = 0; k < 5; k++){
                    System.out.println("ROW_2[" + k +"]: " + row2[k] + " COL_2[" + k +"]: " + column2[k]);
                }
                System.out.println();
                row_status1 = check_row(row1);
                col_status1 = check_col(column1);
                row_status2 = check_row(row2);
                col_status2 = check_col(column2);
                if(row_status1 > 0){
                    bingo1++;
                    row1[row_status1] = 0;
                }
                if(col_status1 > 0){
                    bingo1++;
                    column1[col_status1] = 0;
                }
                if(row_status2 > 0){
                    bingo2++;
                    row2[row_status2] = 0;
                }
                if(col_status2 > 0){
                    bingo2++;
                    column2[col_status2] = 0;
                }
                if(bingo1 == 5){
                    System.out.println("USER WINS!!!");
                    break;
                }
                if(bingo2 == 5){
                    System.out.println("COMPUTER WINS!!!");
                    break;
                }
            }
        }
    }
    public static int findIndex(int[] item_set, int temp){
        int i;
        for(i = 0; i < 25; i++){
            if(item_set[i] == temp)
                break;
        }
        return i;
    }
    public static boolean containsVal(int[] item_set, int temp){
        for(int i = 0; i < item_set.length; i++){
            if(item_set[i] == temp)
                return true;
            if(item_set[i] == 0)
                return false;
        }
        return false;
    }

    public static int check_row(int[] row) {
        for(int i = 0; i < 5; i++){
            if(row[i] == 5)
                return i;
        }
        return 0;
    }

    public static int check_col(int[] column) {
        for(int i = 0; i < 5; i++){
            if(column[i] == 5)
                return i;
        }
        return 0;
    }
}