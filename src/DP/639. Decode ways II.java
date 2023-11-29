/*
639. Decode ways II.java
A message containing letters from A-Z can be encoded into numbers using the following mapping:

'A' -> "1"
'B' -> "2"
...
'Z' -> "26"
To decode an encoded message, all the digits must be grouped then mapped back into letters using the reverse of the mapping above (there may be multiple ways). For example, "11106" can be mapped into:

"AAJF" with the grouping (1 1 10 6)
"KJF" with the grouping (11 10 6)
Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".

In addition to the mapping above, an encoded message may contain the '*' character, which can represent any digit from '1' to '9' ('0' is excluded). For example, the encoded message "1*" may represent any of the encoded messages "11", "12", "13", "14", "15", "16", "17", "18", or "19". Decoding "1*" is equivalent to decoding any of the encoded messages it can represent.

Given a string s consisting of digits and '*' characters, return the number of ways to decode it.

Since the answer may be very large, return it modulo 109 + 7.

 

Example 1:

Input: s = "*"
Output: 9
Explanation: The encoded message can represent any of the encoded messages "1", "2", "3", "4", "5", "6", "7", "8", or "9".
Each of these can be decoded to the strings "A", "B", "C", "D", "E", "F", "G", "H", and "I" respectively.
Hence, there are a total of 9 ways to decode "*".
Example 2:

Input: s = "1*"
Output: 18
Explanation: The encoded message can represent any of the encoded messages "11", "12", "13", "14", "15", "16", "17", "18", or "19".
Each of these encoded messages have 2 ways to be decoded (e.g. "11" can be decoded to "AA" or "K").
Hence, there are a total of 9 * 2 = 18 ways to decode "1*".
Example 3:

Input: s = "2*"
Output: 15
Explanation: The encoded message can represent any of the encoded messages "21", "22", "23", "24", "25", "26", "27", "28", or "29".
"21", "22", "23", "24", "25", and "26" have 2 ways of being decoded, but "27", "28", and "29" only have 1 way.
Hence, there are a total of (6 * 2) + (3 * 1) = 12 + 3 = 15 ways to decode "2*".
 

Constraints:

1 <= s.length <= 105
s[i] is a digit or '*'.
*/
// Methodology
// Use DP and start the bottom up from index == 2 to s.length()(included)

class Solution {
    public int numDecodings(String s) {
        if(s.length() == 0 || s.charAt(0) == '0')
            return 0;
        
        long dp[] = new long[s.length() + 1];
		//you can decode empty string in 1 way that is empty string ""
        dp[0] = 1;
        dp[1] = s.charAt(0) == '*' ? 9 : 1;
        
        // we need to check the dp[i - 2] hence we started from index == 2;
        for (int i = 2; i < dp.length; i++) {
            // the current char is actually the index from 1;
            char currChar = s.charAt(i - 1);
            
        //1. Considering only currChar, check how many decoding ways
            //check how many ways are possible if we consider only currChar
            long withoutPrev = 0;
            //it means currChar could be anything 1-9, so it will add 9 times more ways to decode to previos string from (0 to i - 1)
            if(currChar == '*'){
                withoutPrev = dp[i - 1] * 9;
            }
            //currChar can be decoded individually if it is between 1- 9, or in other words if currChar is 0 means it cannot make a valid decoing by itself so let withOutPrev remain 0
            else if(currChar != '0'){
                withoutPrev = dp[i - 1];
            }
            
        //2. Now check how many decoing ways it adds when we consider prevChar. There are 3 cases based on the value of prevChar
            char prevChar = s.charAt(i - 2);
            long withPrev = 0;
            
            if(prevChar == '1'){
                //if prevChar == 1 and currChar == '*', means we can have 9 times more ways in addition to dp[i - 2], which are 11-19
                if(currChar == '*'){
                    withPrev = dp[i - 2] * 9;
                }
                //if prevChar == 1 and currentChar is in range (0 - 9), means it cannot add anything to previous decoding ways hence its value will be dp[i - 2]
                else{
                    withPrev = dp[i - 2];
                }
            }
            
            else if(prevChar == '2'){
                //if prevChar == 2 and currChar == '*', means we can have 6 times more ways in addition to dp[i - 2], which are 21-26
                if(currChar == '*'){
                    withPrev = dp[i - 2] * 6;
                }
                //if prevChar == 2 and currentChar is in range (0 - 6), means it cannot add anything to previous decoding ways hence its value will be dp[i - 2]
                else if('0' <= currChar && currChar <= '6'){
                    withPrev = dp[i - 2];
                }
            }
            
            else if(prevChar == '*'){
                //if prevChar == * and currChar is within range (1,6), means prev char can have choose to become either 1 or 2, hence got 2 times more ways from dp[i - 2]
                if('0' <= currChar && currChar <= '6'){
                    withPrev = dp[i - 2] * 2;
                }
                //if prevChar == * and currChar is within range (7,9), means prev char have only 1 choice which is 1 if it chooses to become 2 the decing will not be valid as it will lead to number > 26, hence we have not got any new way to decode and value will be dp[i - 2]
                else if('7' <= currChar && currChar <= '9'){
                    withPrev = dp[i - 2];
                }
                //if prevChar == * and currChar == *, then prevChar and currChar adds 15 times more options to current decoing. ** can be transformed into 11-19(9) or 21-26(7) => 15 
                else if(currChar == '*'){
                    //1*10 + 2*7
                    withPrev = dp[i - 2] * 15;
                }
            }
            
        //3. finally add it to dp table by taking mod
			//Intermediate results can also overflow, thats why mod the results for subproblems
            dp[i] = (withoutPrev + withPrev) % 1000000007;
            
        }
        
        
        return (int)(dp[dp.length - 1]);
    }
}
