package com.example.BloggerApp.common.utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Converter {

    public static byte[] decodeBase64(String base64String) throws UnsupportedEncodingException {
        return Base64.getMimeDecoder().decode(base64String.getBytes(StandardCharsets.UTF_8));
    }

    public static String encodeBase64(byte[] image){
        return Base64.getEncoder().encodeToString(image);
    }
}