/*
6. ZigZag Conversion
https://leetcode.com/problems/zigzag-conversion/

The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"

Write the code that will take a string and make this conversion given a number of rows:

string convert(string s, int numRows);
 
Example 1:
Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"

Example 2:
Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:
P     I    N
A   L S  I G
Y A   H R
P     I

Example 3:
Input: s = "A", numRows = 1
Output: "A"
*/

class Solution {
    public String convert(String s, int numRows) {

        // Method1(Recommended) String Traversal
        // Iterative the string s once and using StringBuilder to construct a String on the fly,
        // split the matrix into sections, each section would have a certain amount of elements, which depends on the number of rows
        // for row == 0 or nums - 1, we just append the current index in each section
        // otherwise, figure out the secondIndex and charsInBetween so we can append one more element into the StringBuilder, given that the row is in between top and the bottom
        // Start from row 0 to the bottom.
      
        // Method2: fill a new matrix and traverse it later
        // numRows defined the height ---> matrix[0].length of the matrix.
        // using the string s to build up a brand new matrix
        // the hard parts are:
        // 1. find out the numbers of columns
        // 2. how to fill the newly built-up matrix
        // the code is complicated, it easier to make mistakes when filling the matrix


        // Important to add this edge case check, otherwise, it would generate memory limit exceed.
        if (numRows == 1) {
            return s;
        }

        StringBuilder answer = new StringBuilder();
        int n = s.length();
        int charsInSection = 2 * (numRows - 1);

        for (int currRow = 0; currRow < numRows; currRow++) {
            int index = currRow;

            while (index < n) {
                answer.append(s.charAt(index));

                // if currRow is neither the first row nor the end one
                if (currRow != 0 && currRow != numRows - 1) {
                    // the logic is so important as when the numsRow grows, the charsInBetween is diff
                    int charsInBetween = charsInSection - 2 * currRow;
                    int secondIndex = index + charsInBetween;

                    if (secondIndex < n) {
                        answer.append(s.charAt(secondIndex));
                    }
                }
                
                // jump to the first element in the next section
                index += charsInSection;
            }
        }
        return answer.toString();
    }
}

