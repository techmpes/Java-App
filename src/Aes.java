import java.io.*;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public final class Aes{
    private final String aeskey;
    SecretKeySpec key;
    public Aes(String akey) throws Exception{
        aeskey=akey;
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] encodedKey = decoder.decodeBuffer(aeskey);
      
        key = new SecretKeySpec(encodedKey,0,encodedKey.length, "AES");   
    }
    public String encrypt(String plainText) throws Exception 
    {
        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(plainText.getBytes());
        String encryptedValue = new BASE64Encoder().encode(encVal);
        return encryptedValue;
    }
    public String decrypt(String encrypetdText) throws Exception 
    {
        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encrypetdText);
        byte[] decValue = c.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
           
    }
    
}
