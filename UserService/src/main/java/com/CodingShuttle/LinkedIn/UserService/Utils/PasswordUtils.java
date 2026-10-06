package com.CodingShuttle.LinkedIn.UserService.Utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {

    public static String hashPassword(String plainTextPassword){
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }
    public static boolean checkPassword(String plainTextPassword, String hashedPassword){
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }

    public static boolean verifyPassword(String password, String hashedPassword) {
        return checkPassword(password, hashedPassword);
    }
}
