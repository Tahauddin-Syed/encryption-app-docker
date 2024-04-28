package com.tahauddin.syed.controller;

import com.tahauddin.syed.service.EncryptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class EncryptionController {

    private final EncryptionService encryptionService;

    @GetMapping("/encrypt")
    public ResponseEntity encrypt(@RequestHeader("plainText") String plainText) throws IllegalBlockSizeException, BadPaddingException {
        String encryptedText = encryptionService.encrypt(plainText);
        Map<String, String> responseMap = Map.of("plainText", plainText,
                "encryptedText", encryptedText);
        return ResponseEntity.ok(responseMap);
    }

    @GetMapping("/decrypt")
    public ResponseEntity decrypt(@RequestHeader("encryptedText") String encryptedText) throws IllegalBlockSizeException, BadPaddingException {
        String decryptedText = encryptionService.decrypt(encryptedText);
        Map<String, String> responseMap = Map.of("encryptedText", encryptedText,
                "plainText", decryptedText);
        return ResponseEntity.ok(responseMap);
    }


}
