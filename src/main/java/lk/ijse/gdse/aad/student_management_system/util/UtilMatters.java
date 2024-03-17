package lk.ijse.gdse.aad.student_management_system.util;

import java.util.Base64;
import java.util.UUID;

public class UtilMatters {
    public static String generateID() {
        return UUID.randomUUID().toString();
    }
    public static String convertBase64(String data){
        return Base64.getEncoder().encodeToString(data.getBytes());
    }
}
