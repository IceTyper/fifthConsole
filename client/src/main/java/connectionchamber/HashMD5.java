package connectionchamber;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//статический класс, хэширующий пароли
public class HashMD5 {
    public static String hash(String input) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] hashedBytes = messageDigest.digest(input.getBytes());
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : hashedBytes) stringBuilder.append(String.format("%02x", b));
        return stringBuilder.toString();
    }

    /*public static void main(String[] args) throws NoSuchAlgorithmException {
        String input = "";
        String hashed = hash(input);
        System.out.println(input);
        System.out.println(hashed);
    }*/
}
