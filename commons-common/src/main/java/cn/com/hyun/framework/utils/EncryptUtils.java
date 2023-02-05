package cn.com.hyun.framework.utils;

import cn.com.hyun.framework.exception.FrameworkUtilException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by hyunwoo
 */
public final class EncryptUtils {
    private static final int RANDOM_LENGTH = 48;
    private static final int SALT_LENGTH = 32;
    private static final int ITERATIONS = 1000;
    private static final int DESIRED_KEY_LENGTH = 256;
    private static final String SALT_TOKEN_ALGORITHMS = "SHA1PRNG";
    private static final String PASSWORD_ALGORITHMS = "PBKDF2WithHmacSHA1";

    private EncryptUtils() {
    }

    public static String generateRandom() {
        return generateSecureRandom(RANDOM_LENGTH);
    }

    public static String generateSalt() {
        return generateSecureRandom(SALT_LENGTH);
    }

    private static String generateSecureRandom(int length) {
        try {
            SecureRandom random = SecureRandom.getInstance(SALT_TOKEN_ALGORITHMS);
            byte[] bytes = random.generateSeed(length);

            return Base64.encodeBase64String(bytes).replaceAll("\\+", "A").replaceAll("/", "b").replaceAll("=", "C");
        } catch (NoSuchAlgorithmException e) {
            throw new FrameworkUtilException(e);
        }
    }

    public static String encryptPassword(String password, String salt) {
        try {
            if (StringUtils.isBlank(password)) {
                throw new IllegalArgumentException("密码不能为空");
            }
            if (StringUtils.isBlank(salt)) {
                throw new IllegalArgumentException("盐值不能为空");
            }

            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(PASSWORD_ALGORITHMS);
            SecretKey key = secretKeyFactory.generateSecret(new PBEKeySpec(
                    password.toCharArray(), Base64.decodeBase64(salt), ITERATIONS, DESIRED_KEY_LENGTH)
            );

            return Base64.encodeBase64String(key.getEncoded());
        } catch (Exception e) {
            throw new FrameworkUtilException(e);
        }
    }

    public static boolean validatePassword(String password, String token, String encryptPassword) {
        try {
            if (StringUtils.isBlank(password)) {
                throw new IllegalArgumentException("密码不能为空");
            }
            if (StringUtils.isBlank(token)) {
                throw new IllegalArgumentException("盐值不能为空");
            }
            if (StringUtils.isBlank(encryptPassword)) {
                throw new IllegalArgumentException("加密密码不能为空");
            }

            String verifyPassword = encryptPassword(password, token);
            return StringUtils.equals(verifyPassword, encryptPassword);
        } catch (IllegalArgumentException e) {
            throw new FrameworkUtilException(e);
        }
    }
}
