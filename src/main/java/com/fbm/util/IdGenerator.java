package com.fbm.util;

import java.util.UUID;

/**
 * Created by Mocart on 20-Aug-17.
 */
public class IdGenerator {
    public static Long generateRandomId(){
        Long l = UUID.randomUUID().getLeastSignificantBits();
        if(l < 0){
            l *= (-1);
        }
        return l;
    }
}
