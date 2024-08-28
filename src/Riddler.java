import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static java.lang.System.exit;

/**
 * The Riddler:
 * A puzzle by Zach Blick
 * for Adventures in Algorithms
 * At Menlo School in Atherton, CA
 *
 * Completed by: Alexandre Haddad-Delaveau
 */
public class Riddler {

    public String decryptOne(String encrypted) {
        // Decode string
        byte[] bytes = encrypted.getBytes(StandardCharsets.UTF_8);

        // Loop for each character
        for (int i = 0; i < bytes.length; i++) {
            // Uppercase letters
            if (bytes[i] >= 65 && bytes[i] <= 90) {
                // Subtract 26 to prevent number from going negative
                bytes[i] -= 26;

                // Add offset
                bytes[i] += 9;

                // Add 26 back if number is too low
                if (bytes[i] < 65) {
                    bytes[i] += 26;
                }
            }

            // Lowercase letters
            else if (bytes[i] >= 97 && bytes[i] <= 122) {
                // Subtract 26 to prevent number from going negative
                bytes[i] -= 26;

                // Add offset
                bytes[i] += 9;

                // Add 26 back if number is too low
                if (bytes[i] < 97) {
                    bytes[i] += 26;
                }
            }
        }

        return new String(bytes, StandardCharsets.UTF_8);
    }

    public String decryptTwo(String encrypted) {
        // Split string into numbers
        String[] segments = encrypted.split(" ");
        byte[] bytes = new byte[segments.length];
        for (int i = 0; i < segments.length; i++) {
            bytes[i] = (byte) Integer.parseInt(segments[i], 10);
        }

        // Convert bytes to ascii
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public String decryptThree(String encrypted) {
        // Split string into segments of 8 (1 byte)
        String[] segments = new String[encrypted.length() / 8];
        for (int i = 0; i < segments.length; i++) {
            segments[i] = encrypted.substring(i * 8, (i + 1) * 8);
        }

        // Parse split strings into bytes
        byte[] bytes = new byte[segments.length];
        for (int i = 0; i < segments.length; i++) {
            bytes[i] = (byte) Integer.parseInt(segments[i], 2);
        }

        // Convert bytes to ascii
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public String decryptFour(String encrypted) {
        byte[] decryptedBytes = new byte[encrypted.length()];

        // Loop over each character
        for (int i = 0; i < encrypted.length(); i++) {
            // Get substring for specific emoji
            String emoji = encrypted.substring(i, i + 1);

            // Get the last byte of the emoji
            byte lastByte = emoji.getBytes(StandardCharsets.UTF_8)[2];
            byte secondToLastByte = emoji.getBytes(StandardCharsets.UTF_8)[1];
            System.out.println(Arrays.toString(emoji.getBytes(StandardCharsets.UTF_8)));

            // Check for special character (last byte is -101)
            if (secondToLastByte == -101) {
                decryptedBytes[i] = (byte) (129 + lastByte);
            } else {
                decryptedBytes[i] = (byte) (193 + lastByte);
            }
        }

        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }
}
