package pe.edu.vallegrande.cipher_feedback.service;

import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.util.Base64;

@Service
public class CFBService {

    private final String ALGORITHM = "AES";
    private final String TRANSFORMATION = "AES/CFB/NoPadding";
    private SecretKey secretKey;
    private IvParameterSpec iv;

    public CFBService() throws Exception {
        // Generar clave secreta
        KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
        keyGen.init(128);
        this.secretKey = keyGen.generateKey();

        // Generar vector de inicialización (IV)
        byte[] ivBytes = new byte[16];
        this.iv = new IvParameterSpec(ivBytes);
    }

    public String encrypt(String plainText) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public String decrypt(String encryptedText) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
    }

}
