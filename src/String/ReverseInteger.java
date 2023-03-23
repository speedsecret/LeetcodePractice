package String;

/*
Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.

Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
 */

public class ReverseInteger {
    public static int reverseInteger(int x) {
        // use pop and push strategy
        // while checking all potential overflow cases
        int reverseNumber = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (reverseNumber > Integer.MAX_VALUE / 10 || (reverseNumber == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            if (reverseNumber < Integer.MIN_VALUE / 10 || (reverseNumber == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }
            reverseNumber = reverseNumber * 10 + pop;
        }
        return reverseNumber;
    }
}
