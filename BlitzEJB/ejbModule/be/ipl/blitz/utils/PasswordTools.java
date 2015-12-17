package be.ipl.blitz.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public final class PasswordTools {

  public static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";
  public static final int DERIVED_KEY_LENGHT = 160;
  public static final int ITERATIONS = 10000;

  /**
   * Takes the password and a salt to create an encrypted password.
   *
   * @param password
   *          The password to hash
   * @param salt
   *          A salt represented by an array of bytes
   * @return The encrypted password
 * @throws Exception 
   */
  public static byte[] hash(String password, byte[] salt) throws Exception {
    return pbkdf2(password.toCharArray(), salt, ITERATIONS, DERIVED_KEY_LENGHT);
  }

  /**
   * Generates a random salt.
   *
   * @return A random salt
 * @throws Exception 
   */
  public static byte[] generateSalt() throws Exception {
    try {
      SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
      byte[] salt = new byte[8];
      random.nextBytes(salt);
      return salt;
    } catch (NoSuchAlgorithmException e) {
    	throw new Exception();
    }
  }

  /**
   * Method to hash a password.
   *
   * @param password
   *          The password to hash
   * @param salt
   *          A salt represented by an array of bytes
   * @param iterations
   *          Number of iterations needed by the hash algorithm
   * @param keyLength
   *          The key length
   * @return The hashed password
 * @throws Exception 
   */
  public static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int keyLength) throws Exception {
    try {
      PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, keyLength);
      SecretKeyFactory skf = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
      return skf.generateSecret(spec).getEncoded();
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new Exception("Error in password hashing.");
    }
  }
}
