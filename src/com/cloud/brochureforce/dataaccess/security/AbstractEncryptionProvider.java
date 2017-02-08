/**
 * 
 */
package com.cloud.brochureforce.dataaccess.security;

import org.datanucleus.store.encryption.ConnectionEncryptionProvider;

import com.cloud.brochureforce.dataaccess.security.decrypter.Decrypter;

public abstract class AbstractEncryptionProvider implements ConnectionEncryptionProvider {
	@Override
	public String decrypt(String encryptedPassword) {
		String decryptedPassword;
		try {
			decryptedPassword = Decrypter.decrypt(encryptedPassword);
		} catch (Exception e) {
			throw new IllegalStateException(e.getMessage());
		}
		return decryptedPassword;
	}
}
