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

    static int n, n2;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        startGame();
        int[] NUM1 = new int[n2];
        int[] NUM2 = new int[n2];
        int[] used = new int[n2];
        System.out.println("Do You Want To Fill The Board Yourlself (Y/N)? ");
        char choice = sc.nextLine().charAt(0);
        switch(choice){
            case 'Y':
                System.out.println("ENTER THE NUMBERS: ");
                for(int i = 0; i <n2;){
                    int temp = Integer.parseInt(sc.nextLine());
                    if(!containsVal(NUM1, temp)) {
                        NUM1[i++] = temp;
                }
            }
                break;
            case 'N':
                for(int i = 0; i <n2;){
                    int temp = (int)(Math.random()*n2) + 1;
                    if(!containsVal(NUM1, temp)) {
                        NUM1[i++] = temp;
                }
            }
                break;
        }
        for(int i = 0; i <n2;){
            int temp = (int)(Math.random()*n2) + 1;
            if(!containsVal(NUM2, temp)) {
                NUM2[i++] = temp;
            }
        }
        System.out.println("MATRIX 1:  ");
        print_matrix(NUM1);
        System.out.println("MATRIX 2:  ");
        print_matrix(NUM2);
        int u = 0, row_status1, col_status1, row_status2, col_status2, num, index1, index2, curr_row, curr_column;
        int bingo1 = 0, bingo2 = 0;
        int[] row1;
        row1 = new int[n];
        int[] column1;
        column1 = new int[n];
        int[] row2;
        row2 = new int[n];
        int[] column2;
        column2 = new int[n];
        int[] diag1;
        diag1 = new int[n];
        int[] diag2;
        diag2 = new int[n];
        initialize_diagonal(diag1);
        initialize_diagonal(diag2);
        for(int t = 0; t < n2;){
            if(t % 2 == 0){
                System.out.println("Enter your number: ");
                num = sc.nextInt();
                if(num > 0 && num < n2 + 1 && !containsVal(used, num)){
                    used[u++] = num;
                    t++;
                }
                else
                    continue;
                index1 = findIndex(NUM1, num);
                NUM1[index1] = 0;
                System.out.println("INDEX 1: " + index1);
                index2 = findIndex(NUM2, num);
                NUM2[index2] = 0;
                System.out.println("INDEX 2: " + index2);
                curr_column = index1 % n;
                curr_row = 0;
                while(index1 > n - 1){
                    curr_row++;
                    index1 -= n;
                }
                System.out.println("CURRENT ROW 1: " + curr_row);
                System.out.println("CURRENT COLUMN 1: " + curr_column);
                row1[curr_row]++;
                column1[curr_column]++;
                curr_column = index2 % n;
                curr_row = 0;
                while(index2 > n - 1){
                    curr_row++;
                    index2 -= n;
                }
                System.out.println("CURRENT ROW 2: " + curr_row);
                System.out.println("CURRENT COLUMN 2: " + curr_column);
                row2[curr_row]++;
                column2[curr_column]++;
                System.out.println("MATRIX 1:  ");
                print_matrix(NUM1);
                System.out.println("MATRIX 2:  ");
                print_matrix(NUM2);
                row_status1 = check_row(row1);
                col_status1 = check_col(column1);
                row_status2 = check_row(row2);
                col_status2 = check_col(column2);
                if(row_status1 < n + 1){
                    bingo1++;
                    row1[row_status1] = 0;
                }
                if(col_status1 < n + 1){
                    bingo1++;
                    column1[col_status1] = 0;
                }
                if(row_status2 < n + 1){
                    bingo2++;
                    row2[row_status2] = 0;
                }
                if(col_status2 < n + 1){
                    bingo2++;
                    column2[col_status2] = 0;
                }
                if(diag1[0] == 0 || diag1[1] == 0){
                    int lf = diag1[0] == 1 ? 0 : checkLeftDiag(NUM1);
                    int rt = diag1[1] == 1 ? 0 : checkRightDiag(NUM1);
                    if(lf == 1){
                        diag1[0] = 1;
                        bingo1++;
                    }
                    if(rt == 1){
                        diag1[1] = 1;
                        bingo1++;
                    }
                }
                if(diag2[0] == 0 || diag2[1] == 0){
                    int lf = diag2[0] == 1 ? 0 : checkLeftDiag(NUM2);
                    int rt = diag2[1] == 1 ? 0 : checkRightDiag(NUM2);
                    if(lf == 1){
                        diag2[0] = 1;
                        bingo2++;
                    }
                    if(rt == 1){
                        diag2[1] = 1;
                        bingo2++;
                    }
                }
                System.out.println("BINGO 1: " + bingo1);
                System.out.println("BINGO 2: " + bingo2);
                System.out.println();
                if(check_bingo(t, bingo1, bingo2)){
                    break;
                }
            }
            else{
                num = (int)(Math.random()*n2) + 1;
                if(!containsVal(used, num)){
                    used[u++] = num;
                    t++;
                }
                else
                    continue;
                System.out.println("Computer's number: " + num);
                index1 = findIndex(NUM1, num);
                NUM1[index1] = 0;
                System.out.println("INDEX 1: " + index1);
                index2 = findIndex(NUM2, num);
                NUM2[index2] = 0;
                System.out.println("INDEX 2: " + index2);
                curr_column = index1 % n;
                curr_row = 0;
                while(index1 > n - 1){
                    curr_row++;
                    index1 -= n;
                }
                System.out.println("CURRENT ROW 1: " + curr_row);
                System.out.println("CURRENT COLUMN 1: " + curr_column);
                row1[curr_row]++;
                column1[curr_column]++;
                curr_column = index2 % n;
                curr_row = 0;
                while(index2 > n - 1){
                    curr_row++;
                    index2 -= n;
                }
                System.out.println("CURRENT ROW 2: " + curr_row);
                System.out.println("CURRENT COLUMN 2: " + curr_column);
                row2[curr_row]++;
                column2[curr_column]++;
                System.out.println("MATRIX 1:  ");
                print_matrix(NUM1);
                System.out.println("MATRIX 2:  ");
                print_matrix(NUM2);
                row_status1 = check_row(row1);
                col_status1 = check_col(column1);
                row_status2 = check_row(row2);
                col_status2 = check_col(column2);
                if(row_status1 < n + 1){
                    bingo1++;
                    row1[row_status1] = 0;
                }
                if(col_status1 < n + 1){
                    bingo1++;
                    column1[col_status1] = 0;
                }
                if(row_status2 < n + 1){
                    bingo2++;
                    row2[row_status2] = 0;
                }
                if(col_status2 < n + 1){
                    bingo2++;
                    column2[col_status2] = 0;
                }
                if(diag1[0] == 0 || diag1[1] == 0){
                    int lf = diag1[0] == 1 ? 0 : checkLeftDiag(NUM1);
                    int rt = diag1[1] == 1 ? 0 : checkRightDiag(NUM1);
                    if(lf == 1){
                        diag1[0] = 1;
                        bingo1++;
                    }
                    if(rt == 1){
                        diag1[1] = 1;
                        bingo1++;
                    }
                }
                if(diag2[0] == 0 || diag2[1] == 0){
                    int lf = diag2[0] == 1 ? 0 : checkLeftDiag(NUM2);
                    int rt = diag2[1] == 1 ? 0 : checkRightDiag(NUM2);
                    if(lf == 1){
                        diag2[0] = 1;
                        bingo2++;
                    }
                    if(rt == 1){
                        diag2[1] = 1;
                        bingo2++;
                    }
                }
                System.out.println("BINGO 1: " + bingo1);
                System.out.println("BINGO 2: " + bingo2);
                System.out.println();
                if(check_bingo(t, bingo2, bingo1)){
                    break;
                }
            }
        }
    }
    public static int findIndex(int[] item_set, int temp){
        int i;
        for(i = 0; i < n2; i++){
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
        for(int i = 0; i < n; i++){
            if(row[i] == n)
                return i;
        }
        return n + 1;
    }

    public static int check_col(int[] column) {
        for(int i = 0; i < n; i++){
            if(column[i] == n)
                return i;
        }
        return n + 1;
    }

    private static int checkLeftDiag(int[] NUM) {
        for(int i = 0; i < n2; i += (n + 1)){
            if(NUM[i] != 0)
                return 0;
        }
        return 1;
    }

    private static int checkRightDiag(int[] NUM) {
        for(int i = n - 1; i < n2; i += (n - 1)){
            if(NUM[i] != 0)
                return 0;
        }
        return 1;
    }

    private static void initialize_diagonal(int[] diag) {
        diag[0] = 0;
        diag[1] = 0;
    }

    private static void print_matrix(int[] NUM) {
        int c = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(NUM[c] == 0){
                    System.out.print("X ");
                    c++;
                    continue;
                }
                System.out.print(NUM[c++] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static boolean check_bingo(int t, int bingo1, int bingo2) {
        if(bingo1 == n){
            if(t%2 == 0){
                System.out.println("COMPUTER WINS!!!");
                return true;
            }
            System.out.println("USER WINS!!!");
            return true;
        }
        if(bingo2 == n){
            if(t % 2 == 0){
                System.out.println("USER WINS!!!");
                return true;
            }
            System.out.println("COMPUTER WINS!!!");
            return true;
        }
        return false;
    }

    private static void startGame() {
        System.out.println("ENTER BOARD LENGTH: ");
        Scanner sc = new Scanner(System.in);
        n = Integer.parseInt(sc.nextLine());
        n2 = n * n;
    }
}
