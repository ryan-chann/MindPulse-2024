package edu.mingjun.mindpulse.utils;

import java.util.UUID;

public class UuIdGenerator {
    public static String generateUuId() {
        return UUID.randomUUID().toString();
    }
}
