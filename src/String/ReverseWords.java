package String;

import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;

import static String.ReverseString.reverseString;
import static Utils.Swap.swapChar;

public class ReverseWords {
    // in this question,
    // we would like to reverse a whole sentence
    // solution is:
    // we can treat this sentence as an ultra-long string and reverse it.
    // then for each word in the sentence, we could reverse it.
    public static String reverseWord(String input) {
        // we would need to assume
        // there is no trailing or leading space
        // for each word in the input string, there is only one space in between.
        String reversedString = reverseString(input);
        int start = 0;
        char[] array = reversedString.toCharArray();
        // find startIndex and endIndex for each word in the reversedString
        for (int i = 0; i < reversedString.length(); i++) {
            // check the startIndex
            if (array[i] != ' ' && (i == 0 || array[i - 1] == ' ')) {
                start = i;
            }
            // check the endIndex
            if (array[i] != ' ' && (i == array.length - 1 || array[i + 1] == ' ')) {
                swapChar(array, start, i);
            }
        }
        return new String(array);
    }

    public static void main(String[] args) {
        String input = "aozamn lvoe I";
        System.out.println("What is the current result looks like: " + reverseWord(input) + "\n");
        String str = "Love";
        System.out.printf("Print current string=" + str.substring(0, 1) + "\n" + "Current string2=" + str.substring(0, 0) );
    }
}
