package com.musicstore.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;

public class AESEncryptDecrypt {
	private static final Logger logger = Logger.getLogger(AESEncryptDecrypt.class);
	 
    private static SecretKeySpec secretKey;
    private static byte[] key;
 
    public static void setKey(String keyStr){
        MessageDigest md = null;
        try {
            key = keyStr.getBytes("UTF-8");
            md = MessageDigest.getInstance("SHA-1");
            key = md.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        }
        catch (NoSuchAlgorithmException e) {
        	logger.error("\n\nNoSuchAlgorithmException error occurred in setKey() :::: AESEncryptDecrypt ", e);
        }
        catch (UnsupportedEncodingException e) {
        	logger.error("\n\nUnsupportedEncodingException error occurred in setKey() :::: AESEncryptDecrypt ", e);
        }
    }
 
    public static String encrypt(String key, String strToBeEnc){
        try{
            setKey(key);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToBeEnc.getBytes("UTF-8")));
        }
        catch (Exception e){
        	logger.error("\n\nException error occurred in encrypt() :::: AESEncryptDecrypt ", e);
        }
        return null;
    }
 
    public static String decrypt(String key, String strToBeDec){
        try{
            setKey(key);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToBeDec)));
        }
        catch (Exception e){
        	logger.error("\n\nException error occurred in decrypt() :::: AESEncryptDecrypt ", e);
        }
        return null;
    }
}
