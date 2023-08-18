/*
Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
Return the answer in any order. A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

Example 1:
Input: digits = "23"
Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]

Example 2:
Input: digits = ""
Output: []

Example 3:
Input: digits = "2"
Output: ["a","b","c"]
*/

class Solution {
    Map<Character, String> telMap = new HashMap<>();

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<String>();
        }
        // Methodology
        // Use a hashMap<Character, String>
        // as the length is only 4, which determined the length of output string is 4 too
        // however as character 7 and character 9 have a longer String, it is not convenint to 
        // use a rigid number of for loop, so to use a charArray length based for loop
        // Use StringBuilder to add character flexibilly
        telMap.put('2', "abc");
        telMap.put('3', "def");
        telMap.put('4', "ghi");
        telMap.put('5', "jkl");
        telMap.put('6', "mno");
        telMap.put('7', "pqrs");
        telMap.put('8', "tuv");
        telMap.put('9', "wxyz");
        
        List<String> res = new ArrayList<>();
        DepthFirstSearch(res, new StringBuilder(), digits, 0);
        return res;
    }

    private void DepthFirstSearch(List<String> res, StringBuilder sb, String digits, int index) {
        // base case
        if (sb.length() == digits.length()) {
            res.add(sb.toString());
            return;
        }
        // recursive rule
        // find your string
        String curStr = telMap.get(digits.charAt(index));
        for (char curChar: curStr.toCharArray()) {
            sb.append(curChar);
            DepthFirstSearch(res, sb, digits, index + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
