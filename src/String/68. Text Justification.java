/*
68. Text Justification
https://leetcode.com/problems/text-justification/description/

Given an array of strings words and a width maxWidth, 
format the text such that each line has exactly maxWidth characters and is fully (left and right) 
justified.
You should pack your words in a greedy approach; that is, 

pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that
each line has exactly maxWidth characters.

Extra spaces between words should be distributed as evenly as possible. 
If the number of spaces on a line does not divide evenly between words, 
the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left-justified, and no extra space is inserted between words.

Note:

A word is defined as a character sequence consisting of non-space characters only.
Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
The input array words contains at least one word.
 

Example 1:

Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
Output:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
Example 2:

Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
Output:
[
  "What   must   be",
  "acknowledgment  ",
  "shall be        "
]
Explanation: Note that the last line is "shall be    " instead of "shall     be", 
because the last line must be left-justified instead of fully-justified.
Note that the second line is also left-justified because it contains only one word.
Example 3:

Input: words = ["Science","is","what","we","understand","well","enough","to","explain","to","a",
"computer.","Art","is","everything","else","we","do"], maxWidth = 20
Output:
[
  "Science  is  what we",
  "understand      well",
  "enough to explain to",
  "a  computer.  Art is",
  "everything  else  we",
  "do                  "
]

*/

class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<>();
        int i = 0;

        while (i < words.length) {
            // Step 1: Get a list of words that fit within the maxWidth.
            List<String> currentLine = getWords(i, words, maxWidth);
            // for each line, there are a size of List of Strings
            // so we adding it into i.
            i += currentLine.size();

            // Step 2: Create a formatted line for the current set of words.
            ans.add(createLine(currentLine, i, words, maxWidth));
        }

        return ans;
    }

    // Step 1: Get words for the current line that fit within maxWidth.
    private List<String> getWords(int i, String[] words, int maxWidth) {
        List<String> currentLine = new ArrayList<>();
        int currLength = 0;

        while (i < words.length && currLength + words[i].length() <= maxWidth) {
            currentLine.add(words[i]);
            currLength += words[i].length() + 1; // Add 1 for spaces between words.
            i++;
        }

        return currentLine;
    }

    // Step 2: Create a formatted line for the current set of words.
    private String createLine(List<String> line, int i, String[] words, int maxWidth) {
        int baseLength = -1; // Initialize at -1 to account for spaces.

        // Calculate the length of the base line (words + spaces).
        for (String word : line) {
            baseLength += word.length() + 1; // Add 1 for spaces.
        }

        int extraSpaces = maxWidth - baseLength;

        // Check if it's the last line or there's only one word in the line.
        if (line.size() == 1 || i == words.length) {
            return String.join(" ", line) + " ".repeat(extraSpaces);
        }

        // As there is only `numsOfWords - 1` gaps in between.
        int wordCount = line.size() - 1;
        int spacesPerWord = extraSpaces / wordCount;
        int needsExtraSpace = extraSpaces % wordCount;

        // Distribute extra spaces among the leftmost words.
        for (int j = 0; j < needsExtraSpace; j++) {
            line.set(j, line.get(j) + " ");
        }

        // Add equal spaces between words.
        for (int j = 0; j < wordCount; j++) {
            line.set(j, line.get(j) + " ".repeat(spacesPerWord));
        }

        // join all the String in List<String> line into one longer String, and added it into String.
        return String.join(" ", line);
    }
}
