package com.tahauddin.syed.service.impl;

import com.tahauddin.syed.service.EncryptionService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Base64;

@Service
@RequiredArgsConstructor
@Slf4j
public class EncryptionServiceImpl implements EncryptionService {

    private Cipher encryptionCipher;
    private Cipher decryptionCipher;
    private SecretKey secretKey;


    @PostConstruct
    public void init () throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {

        LocalDateTime initializingTime = LocalDateTime.now();
        secretKey = new SecretKeySpec(Base64.getEncoder().encode("Thisisthesecretkey".getBytes()), "AES");

        encryptionCipher = Cipher.getInstance("AES");
        encryptionCipher.init(Cipher.ENCRYPT_MODE, secretKey);

        decryptionCipher = Cipher.getInstance("AES");
        decryptionCipher.init(Cipher.DECRYPT_MODE, secretKey);

        LocalDateTime completedTime = LocalDateTime.now();

        log.info("Total Time for Initializing is :: {} nano sec", Duration.between(initializingTime, completedTime).getNano());
        // time taken 0.031643759 sec
        // nano seconds :: 31643759
        //                 69712293
    }


    @Override
    public String encrypt(String plainTest) throws IllegalBlockSizeException, BadPaddingException {
        LocalDateTime startTime = LocalDateTime.now();
        log.info("Encryption Do Final Starting @{}", startTime);
        byte[] bytes = encryptionCipher.doFinal(plainTest.getBytes());
        LocalDateTime stopTime = LocalDateTime.now();
        log.info("Encryption Do Final Ended @{}", stopTime);
        log.info("Total Time taken for encryption is :: {} nano sec", Duration.between(startTime, stopTime).getNano());
        // time taken :: 0.000993159 sec
        // nano seconds :: 993159
        String encoded = Base64.getEncoder().encodeToString(bytes);
        return encoded;
    }

    @Override
    public String decrypt(String encodedText) throws IllegalBlockSizeException, BadPaddingException {
        byte[] decode = Base64.getDecoder().decode(encodedText);
        LocalDateTime startTime = LocalDateTime.now();
        log.info("Decryption Do Final Starting @{}", startTime);
        byte[] bytes = decryptionCipher.doFinal(decode);
        LocalDateTime stopTime = LocalDateTime.now();
        log.info("Decryption Do Final Ended @{}", stopTime);
        log.info("Total Time taken for decryption is :: {} nano sec", Duration.between(startTime, stopTime).getNano());
        // time taken :: 0.000388408 sec
        // nano seconds :: 388408
        String decodedString = new String(bytes);
        return decodedString;
    }


}
