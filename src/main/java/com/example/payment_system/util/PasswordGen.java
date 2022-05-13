package com.example.payment_system.util;

import org.springframework.stereotype.Component;

@Component
public class PasswordGen {

    public String getPassword(int size){
        int x = (int) Math.round((Math.random() + 0.1) * 1000000);
        String str = Integer.toString(x);

        return str.substring(0, size);
    }
}
