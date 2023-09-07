/*
307. Range Sum Query - Mutable
https://leetcode.com/problems/range-sum-query-mutable/

Given an integer array nums, handle multiple queries of the following types:

Update the value of an element in nums.
Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
Implement the NumArray class:

NumArray(int[] nums) Initializes the object with the integer array nums.
void update(int index, int val) Updates the value of nums[index] to be val.
int sumRange(int left, int right) Returns the sum of the elements of nums between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).
 

Example 1:

Input
["NumArray", "sumRange", "update", "sumRange"]
[[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
Output
[null, 9, null, 8]

Explanation
NumArray numArray = new NumArray([1, 3, 5]);
numArray.sumRange(0, 2); // return 1 + 3 + 5 = 9
numArray.update(1, 2);   // nums = [1, 2, 5]
numArray.sumRange(0, 2); // return 1 + 2 + 5 = 8
 

Constraints:

1 <= nums.length <= 3 * 104
-100 <= nums[i] <= 100
0 <= index < nums.length
-100 <= val <= 100
0 <= left <= right < nums.length
At most 3 * 104 calls will be made to update and sumRange.

*/

class SegmentTreeNode {
    int start, end;
    SegmentTreeNode left, right;
    int sum;

    public SegmentTreeNode(int start, int end) {
        this.start = start;
        this.end = end;
        this.left = null;
        this.right = null;
        this.sum = 0;
    }
}

public class NumArray {
    SegmentTreeNode root = null;
   
    public NumArray(int[] nums) {
        // Build the initial segment tree when initializing NumArray
        root = buildTree(nums, 0, nums.length - 1);
    }

    // Helper function to build the segment tree
    private SegmentTreeNode buildTree(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        } else {
            SegmentTreeNode newNode = new SegmentTreeNode(start, end);
            if (start == end) {
                newNode.sum = nums[start];
            } else {
                // calculate two sides separately
                int mid = start + (end - start) / 2;             
                newNode.left = buildTree(nums, start, mid);
                newNode.right = buildTree(nums, mid + 1, end);
                newNode.sum = newNode.left.sum + newNode.right.sum;
            }         
            return newNode;
        }
    }
   
    // Update a value in the array and the segment tree
    void update(int i, int val) {
        update(root, i, val);
    }
   
    // Helper function to update the segment tree
    void update(SegmentTreeNode root, int pos, int val) {
        if (root.start == root.end) {
            // If the node represents a single element, update its sum
            root.sum = val;
        } else {
            int mid = root.start + (root.end - root.start) / 2;
            if (pos <= mid) {
                // Recursively update the left subtree
                update(root.left, pos, val);
            } else {
                // Recursively update the right subtree
                update(root.right, pos, val);
            }
            // Update the sum for the current node
            root.sum = root.left.sum + root.right.sum;
        }
    }

    // Query the sum of a range in the array
    public int sumRange(int i, int j) {
        return sumRange(root, i, j);
    }
    
    // Helper function to query the sum of a range in the segment tree
    public int sumRange(SegmentTreeNode root, int start, int end) {
        if (root.end == end && root.start == start) {
            // If the current node's range matches the query range, return its sum
            return root.sum;
        } else {
            int mid = root.start + (root.end - root.start) / 2;
            if (end <= mid) {
                // Query lies entirely in the left subtree
                return sumRange(root.left, start, end);
            } else if (start >= mid+1) {
                // Query lies entirely in the right subtree
                return sumRange(root.right, start, end);
            }  else {    
                // Query spans both left and right subtrees
                return sumRange(root.right, mid+1, end) + sumRange(root.left, start, mid);
            }
        }
    }
}
