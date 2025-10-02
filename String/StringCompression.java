package String;
/**
 * A Java program to compress a string by counting consecutive characters.
 * For example, "aaabbc" becomes "a3b2c".
 */
public class StringCompression {

    /**
     * Compresses a string using the specified format.
     * @param str The string to compress.
     * @return The compressed string.
     */
    public static String compress(String str) {
        // Using StringBuilder for better performance than String concatenation
        StringBuilder newStr = new StringBuilder("");

        for (int i = 0; i < str.length(); i++) {
            Integer count = 1;
            // Count consecutive occurrences of the same character
            while (i < str.length() - 1 && str.charAt(i) == str.charAt(i + 1)) {
                count++;
                i++;
            }
            // Append the character
            newStr.append(str.charAt(i));
            
            // Append the count if it's greater than 1
            if (count > 1) {
                newStr.append(count.toString());
            }
        }
        return newStr.toString();
    }

    public static void main(String[] args) {
        System.out.println("--- String Compression ---");
        String toCompress = "aaabbcccdd";
        System.out.println("Original string: " + toCompress);
        System.out.println("Compressed form: " + compress(toCompress));
    }
}
