package com.tahauddin.syed.service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public interface EncryptionService {

    String encrypt(String plainTest) throws IllegalBlockSizeException, BadPaddingException;

    String decrypt(String encodedText) throws IllegalBlockSizeException, BadPaddingException;

}
