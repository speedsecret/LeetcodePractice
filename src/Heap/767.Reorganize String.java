/*
Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.
Return any possible rearrangement of s or return "" if not possible.

Example 1:

Input: s = "aab"
Output: "aba"
Example 2:

Input: s = "aaab"
Output: ""
 

Constraints:

1 <= s.length <= 500
s consists of lowercase English letters.
*/

class Solution {
    public String reorganizeString(String s) {
        // use priorityQueue --> maxHeap
        // step1: construct an int arr
        // step2: set up maxHeap and iterative the arr, build up the maxHeap
        // step3: construct an new StringBuilder, poll the maxHeap
        // step3.1 if the current one is useful, appened it into the StringBuilder, then decrement 1 
        // then to see if its value still large than 0, if so, then we should add it back to the maxHeap.
        // step3.2 if the current one is not applicable, check the second one, then append the seoncd one
        // into the StringBuilder, do the validation check for the second element too.
        int[] charCounts = new int[26];

        // count each character's occurence
        for (char c : s.toCharArray()) {
            charCounts[c - 'a']++;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(b[1], a[1])
        );

        // populate the int[] arr with characters and their counts
        for (int i = 0; i < 26; i++) {
            if (charCounts[i] > 0) {
                pq.offer(new int[]{i + 'a', charCounts[i]});
            }
        }

        // Reconstruct the string
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            int[] first = pq.poll();
            // if the string is empty or the current character is different from the last character
            if (sb.length() == 0 || first[0] != sb.charAt(sb.length() - 1)) {
                sb.append((char) first[0]);
                if (--first[1] > 0) {
                    pq.offer(first);
                }
            } else {
                // if the pq is empty, then we were not able 
                // to construct a valid string

                // this check is after the last pq poll()
                // we are here to check if having subsequent char
                if (pq.isEmpty()) {
                    return "";
                }

                int[] second = pq.poll();
                sb.append((char) second[0]);
                if (--second[1] > 0) {
                    pq.offer(second);
                }
                
                pq.offer(first);
            }
        }

        return sb.toString();
    }
}
