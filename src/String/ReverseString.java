package String;

public class ReverseString {
    public static String reverseString(String input) {
        return new StringBuilder(input).reverse().toString();
    }

    public static void main(String[] args) {
        String str = "Love";
        System.out.println("The reversed string is: " + reverseString(str));
    }
}
