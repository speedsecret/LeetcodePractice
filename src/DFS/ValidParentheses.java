package DFS;

/*
* Using a stack to store the visited elements when we are looping the String
* if the current one can be matched with existing one in the previous stack,
* we could pop it out and keep the looping process and kept the check until the loop is done
* if not, we just return false
*
* Algo:
* If it is the left Bracket, we just need to push it to the stack.
* if it is right bracket, we may need to check if there is a match with previous one, if so, we just pop it up and discard the current one
* and continue loop
 */

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ValidParentheses {
    // Solution1:
    // Use 3 hashSet to store possible results
    public static boolean isValidParentheses_I(String s) {
        // base case
        if (s == null || s.length() == 0) {
            return true;
        }
        char cur = s.charAt(0);
        Deque<Character> stack = new ArrayDeque<>();
        stack.push(cur);
        Set<Character> leftBracketSet = new HashSet<>(3);
        Set<Character> rightBracketSet = new HashSet<>(3);
        Set<String> bracketSet = new HashSet<>(3);
        leftBracketSet.add('{');
        leftBracketSet.add('(');
        leftBracketSet.add('[');
        rightBracketSet.add('}');
        rightBracketSet.add(')');
        rightBracketSet.add(']');
        bracketSet.add("[]");
        bracketSet.add("{}");
        bracketSet.add("()");
        for (int i = 1; i < s.length(); i++) {
            cur = s.charAt(i);
            if (leftBracketSet.contains(cur)) {
                stack.push(cur);
            } else {
                //peek and check
                if (stack.isEmpty()) {
                    return false;
                }
                char preChar = stack.peek();
                String curString = Character.toString(preChar) + Character.toString(cur);
                if (bracketSet.contains(curString)) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.size() == 0;
    }

    /*
    * Solution 2:
    * Instead of using multiple sets we can probably use one hashMap
     */
    public static boolean isValidParentheses_II(String s) {
        Map<Character, Character> bracketMapping = new HashMap<>();
        bracketMapping.put(')', '(');
        bracketMapping.put(']', '[');
        bracketMapping.put('}', '{');
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (bracketMapping.containsKey(c)) {
                char topElement = stack.isEmpty() ? '#' : stack.pop();
                if (topElement != bracketMapping.get(c)) {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String s = "(){}}{";
        System.out.printf("If this is a validParentheses?" + isValidParentheses_I(s));
    }
}
