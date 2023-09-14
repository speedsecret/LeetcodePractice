/*
170. Two Sum - Data Structure Design.java
https://leetcode.com/problems/two-sum-iii-data-structure-design/description/ 

Design a data structure that accepts a stream of integers and checks if it has a pair of integers that sum up to a particular value.

Implement the TwoSum class:

TwoSum() Initializes the TwoSum object, with an empty array initially.
void add(int number) Adds number to the data structure.
boolean find(int value) Returns true if there exists any pair of numbers whose sum is equal to value, otherwise, it returns false.
 

Example 1:

Input
["TwoSum", "add", "add", "add", "find", "find"]
[[], [1], [3], [5], [4], [7]]
Output
[null, null, null, null, true, false]

Explanation
TwoSum twoSum = new TwoSum();
twoSum.add(1);   // [] --> [1]
twoSum.add(3);   // [1] --> [1,3]
twoSum.add(5);   // [1,3] --> [1,3,5]
twoSum.find(4);  // 1 + 3 = 4, return true
twoSum.find(7);  // No two integers sum up to 7, return false
 

Constraints:

-105 <= number <= 105
-231 <= value <= 231 - 1
At most 104 calls will be made to add and find.
*/

class TwoSum {
    Map<Integer, Integer> freqMap;

    public TwoSum() {
        freqMap = new HashMap<>();
    }
    
    // T: O(1)
    public void add(int number) {
        freqMap.put(number, freqMap.getOrDefault(number, 0) + 1);
    }
    
    // T: O(n)
    public boolean find(int value) {
        for (int ele : freqMap.keySet()) {
            int target = value - ele;
            if (freqMap.get(target) != null) {
                // case1:
                // find duplication
                if (target == ele) {
                    if (freqMap.get(ele) >= 2) {
                        return true;
                    }
                } else {
                    if (freqMap.get(target) >= 1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

/**
 * Your TwoSum object will be instantiated and called as such:
 * TwoSum obj = new TwoSum();
 * obj.add(number);
 * boolean param_2 = obj.find(value);
 */
