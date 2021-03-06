package guibin.zhang.leetcode.matrix;

/**
 * 
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 * 
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 * For example,
 * 
 * Consider the following matrix:
 * 
 * [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * Given target = 3, return true.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class SearchA2DMatrix {
    
    /**
     * Run Status: Accepted!
     * Program Runtime: 520 milli secs
     * Progress: 134/134 test cases passed.
     * @param matrix
     * @param target
     * @return 
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        int start = 0;
        int end = rows * cols - 1;
        int mid = start + (end - start)/2;
        while(start <= end) {
            int midValue = valueOf(mid, matrix, cols);
            if(midValue == target) {
                return true;
            } else if (midValue < target) {
                start = mid + 1;
            } else { // mid > target
                end = mid - 1;
            }
            mid = start + (end - start)/2;
        }
        return false;
    }
    
    //Note the method to compute the x,y by the position of the element
    //This is the key point of this question
    private int valueOf(int mid, int[][] matrix, int cols) {
        int x = mid / cols;
        int y = mid % cols;
        return matrix[x][y];
    }
    
    /**
     * In a 2-D matrix, every row is increasingly sorted from left to right, 
     * and every column is increasingly sorted from top to bottom. 
     * Please implement a function to check whether a number is in such a matrix or not. 
     * For example, all rows and columns are increasingly sorted in the following matrix. 
     * It returns true if it tries to find number 7, but it returns false if it tries to find number 5.
     * 1 2 8 9
     * 2 4 9 12
     * 4 7 10 13
     * 6 8 11 15
     * 
     * In this question, the last item of the previous line could be bigger than the first item in current line.
     * 
     * We can start from right top conner of the matrix, and removing a Row or a Column at Each Step
     * 
     * @param matrix
     * @param target
     * @return 
     */
    public boolean searchMatrix_v3(int[][] matrix, int target) {
        
        int row = 0;
        int col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] < target) {
                row ++;
            } else {
                col --;
            }
        }
        return false;
    }
    
    
    /**
     * Run Status: Accepted!
     * Program Runtime: 544 milli secs
     * Progress: 134/134 test cases passed.
     * 
     * @param matrix
     * @param target
     * @return 
     */
    public boolean searchMatrix_v2(int[][] matrix, int target) {
        int rows = matrix.length;
        if (rows == 0) {
            return false;
        }
        int cols = matrix[0].length;
        return searchMatrix(matrix, target, 1, rows * cols);
    }
    
    public boolean searchMatrix(int[][] matrix, int target, int start, int end) {
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        int mid = start + (end - start)/2;

        if(start > end) {
            return false;
        }
        int midValue = valueOf(mid, matrix, cols);
        
        if(midValue == target) {
            return true;
        } else if(midValue < target) {
            start = mid + 1;
            return searchMatrix(matrix, target, start, end);
        } else {
            end = mid - 1;
            return searchMatrix(matrix, target, start, end);
        }
    }
    
    public static void main(String[] args) {
        SearchA2DMatrix s2 = new SearchA2DMatrix();
        int[][] matrix = {{1, 3, 5}};
        System.out.println(s2.searchMatrix_v2(matrix, 3));
    }
}
