/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloud.brochureforce.dataaccess.security.decrypter;

import java.security.Key;
import java.security.spec.KeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import javax.crypto.Cipher;

/**
 * This class provides functionality to decrypt text previously encrypted using
 * the bundled keys.
 *
 * @author LIA Solutions SAS.
 */
public class Decrypter {

  /*
 * It reads the bundled keys that are going to be used to decrypt
 * the database passwords.
 *
 * @author LIA Solutions SAS.
   */
  private static class KeysReader {

    /*
	 * Not for instantiation.
     */
    private KeysReader() {
    }

    /**
     * Reads the bundled private key.
     *
     * @return Private key loaded in memory.
     * @throws IOException If the private key can't be loaded.
     */
    static PrivateKey readBundledPrivateKey() throws IOException {
      ObjectInputStream oin = null;
      try {
        oin = new ObjectInputStream(new BufferedInputStream(KeysReader.class
          .getResourceAsStream("/com/cloud/brochureforce/dataaccess/security/decrypter/keys/private.key")));
        BigInteger m = (BigInteger) oin.readObject();
        BigInteger e = (BigInteger) oin.readObject();
        RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(m, e);
        KeyFactory fact = KeyFactory.getInstance("RSA");
        PrivateKey pubKey = fact.generatePrivate(keySpec);
        return pubKey;
      } catch (IOException | ClassNotFoundException | NoSuchAlgorithmException | InvalidKeySpecException e) {
        throw new RuntimeException("Spurious serialisation error", e);
      } finally {
        try {
          if (oin != null) {
            oin.close();
          }
        } catch (Exception e) {
        }
      }
    }

    /**
     * Reads the bundled symmetric key.
     *
     * @return Symmetric key loaded in memory.
     * @throws IOException If the symmetric key can't be loaded.
     */
    static String readSymmetricKey() throws IOException {
      int ch;
      StringBuilder encryptedSymmetricKey = new StringBuilder();
      BufferedInputStream bis = null;
      try {
        bis = new BufferedInputStream(KeysReader.class
          .getResourceAsStream("/com/cloud/brochureforce/dataaccess/security/decrypter/keys/symmetric.key"));
        while ((ch = bis.read()) != -1) {
          encryptedSymmetricKey.append((char) ch);
        }
        if (encryptedSymmetricKey.length() <= 0) {
          throw new IOException("The file \"symmetric.key\" doesn't contain a valid key.");
        }
      } finally {
        try {
          if (bis != null) {
            bis.close();
          }
        } catch (IOException e) {
        }
      }
      return encryptedSymmetricKey.toString();
    }

    /**
     * Decrypts the symmetric key.
     *
     * @return Decrypted symmetric key loaded in memory.
     * @throws IOException If the symmetric key can't be descrypted.
     */
    static String decryptSymmetricKey() throws Exception {
      Cipher cipher = Cipher.getInstance("RSA");
      cipher.init(Cipher.DECRYPT_MODE, readBundledPrivateKey());
      byte[] encryptedSymmetricKeyBytes = Base64.decode(readSymmetricKey());
      byte[] decryptedSymmetricKeyBytes = cipher.doFinal(encryptedSymmetricKeyBytes);
      String decryptedSymmetricKey = new String(decryptedSymmetricKeyBytes);
      return decryptedSymmetricKey;
    }
  }

  /*
	 * Not for instantiation.
   */
  private Decrypter() {
  }

  /**
   * Decrypts a previously encrypted text.
   *
   * @param encryptedText Encrypted string data to decrypt.
   * @return Decrypted text.
   * @throws Exception If an error occurs when decrypting.
   */
  public static synchronized String decrypt(String encryptedText) throws Exception {
    String symmetricKey = KeysReader.decryptSymmetricKey();
    symmetricKey = symmetricKey.substring(0, 24);
    KeySpec desedeKeySpec = new DESedeKeySpec(symmetricKey.getBytes());
    Key key = SecretKeyFactory.getInstance("DESede").generateSecret(desedeKeySpec);
    Cipher cipher = Cipher.getInstance("DESede");
    cipher.init(Cipher.DECRYPT_MODE, key);
    byte[] decryptedBytes = cipher.doFinal(Base64.decode(encryptedText));
    String decryptedText = new String(decryptedBytes);
    return decryptedText;
  }
}
