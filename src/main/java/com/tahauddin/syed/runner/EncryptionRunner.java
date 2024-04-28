package com.tahauddin.syed.runner;

import com.tahauddin.syed.service.EncryptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class EncryptionRunner implements CommandLineRunner {

    private final EncryptionService encryptionService;

    @Override
    public void run(String... args) throws Exception {
        String encryptedText = encryptionService.encrypt ("EncryptMeText" );
        log.info("Encrypted Text is :: {}", encryptedText);

        String decrypt = encryptionService.decrypt ( encryptedText ) ;
        log.info("Decryption Text is :: {}", decrypt);
    }
}
