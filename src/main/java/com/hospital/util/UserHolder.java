package com.hospital.util;

import com.hospital.entity.Users;
import org.apache.catalina.User;

public class UserHolder {
    private static final ThreadLocal<String> tl = new ThreadLocal<>();

    public static void saveUser(String userId){
        tl.set(userId);
    }

    public static String getUserId(){
        return tl.get();
    }

    public static void removeUser(){
        tl.remove();
    }
}
