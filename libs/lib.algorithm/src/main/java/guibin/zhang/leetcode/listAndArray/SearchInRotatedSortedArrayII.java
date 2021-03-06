package guibin.zhang.leetcode.listAndArray;

/**
 * 
 * Follow up for "Search in Rotated Sorted Array":
 * What if duplicates are allowed?
 * 
 * Would this affect the run-time complexity? How and why?
 * 
 * Write a function to determine if a given target is in the array.
 * 
 * Run Status: Accepted!
 * Program Runtime: 548 milli secs
 * Progress: 271/271 test cases passed.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class SearchInRotatedSortedArrayII {
    
    public boolean search_v2(int[] A, int target) {
        
        if (A == null || A.length == 0) {
            return false;
        }
        int start = 0;
        int end = A.length - 1;
        
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (A[mid] == target) {
                return true;
            }
            
            //mid is in the left part of rotated array
            if (A[mid] > A[start]) {
                if (target >= A[start] && target < A[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else if (A[mid] < A[start]) {//mid is in the right part of rotated array
                if (target > A[mid] && target <= A[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            } else {//This condition is for resolving the case like: [1,3,1,1,1], 3
                start++;
            }
        }
        return false;
    }
    
    
    public boolean search(int[] A, int target) {
        
        int start = 0;
        int end = A.length - 1;
        
        if (A[start] == target || A[end] == target) {
            return true;
        } 
        
        //Elimit the deplicated head and tail.
        if (A[start] == A[end]) {
            for(int i = 1; i < A.length; i++) {
                if (A[i] == A[0]) {
                    start = i;
                } else {
                    start ++;
                    break;
                }
            }
            for(int j = A.length - 2; j >=0; j--) {
                if (A[j] == A[0]) {
                    end = j;
                } else {
                    end --;
                    break;
                }
            }
        }
        
        int mid = start + (end - start)/2;
        while (start <= end) {
            if (A[mid] == target) {
                return true;
            } else {
                if (A[mid] < A[end]) {
                    if (A[mid] < target && target <= A[end]) {
                        start = mid + 1;
                    } else {
                        end = mid - 1;
                    }
                    mid = start + (end - start) / 2;
                } else if(A[mid] > A[end]) {
                    if (A[mid] > target && target >= A[start]) {
                        end = mid - 1;
                    } else {
                        start = mid + 1;
                    }
                    mid = start + (end - start) / 2;
                    //Note this case
                } else {//A[mid] == A[end]
                    end = mid - 1;
                    mid = start + (end - start) / 2;
                }
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
        
        SearchInRotatedSortedArrayII si = new SearchInRotatedSortedArrayII();
        int[] A = {4,1,2,2,2,3};
        System.out.println(si.search_v2(A,32));
    }
}
