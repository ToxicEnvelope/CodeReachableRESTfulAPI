package com.codereachable.webservices.restfulwebservices.v2.controllers.user.crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class PasswordEncryptor {
   private static MessageDigest md;
   private static BCryptPasswordEncoder encoder;
	   
   public static String cryptWithMD5AndEncode(String pass){
    try {
        md = MessageDigest.getInstance("MD5");
        byte[] passBytes = pass.getBytes();
        md.reset();
        byte[] digested = md.digest(passBytes);
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<digested.length;i++){
            sb.append(Integer.toHexString(0xff & digested[i]));
        }
        return EncodeWithBCrypt(sb.toString());
    } 
    catch (NoSuchAlgorithmException ex) {
        Logger.getLogger(PasswordEncryptor.class.getName()).log(Level.SEVERE, null, ex);
    }
        return null;
   }

   private static String EncodeWithBCrypt(String pass) {
	   encoder = new BCryptPasswordEncoder();
	   return encoder.encode(pass);
   }
}