package aesTest;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;



/**
 *
 * @author Administrator
 *
 */
public class AesTest {
    private static final byte[] IV_AES128_CTR = {
            0x25 , 0x20 , 0x78 , 0x69 , 0x67 , 0x75 , 0x26 , 0x69 ,
            0x6b , 0x65 , 0x20 , 0x49 , 0x56 , 0x20 , 0x20 , 0x25
    };

    private static final byte[] AES_EN_KEY_FIXED =
    {
        0x25 , 0x20 , 0x78 , 0x69 , 0x67 , 0x75 , 0x26 , 0x69 , 0x6b , 0x65 , 0x20 , 0x4b , 0x45 , 0x59 , 0x20 , 0x25
    };


    // 加密
    public static byte[] Encrypt( byte[] content, byte[] key ) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(IV_AES128_CTR);

        Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        return cipher.doFinal(content);


    }

    public static byte[] DecryptAES( byte[] content, byte[] key )
            throws InvalidKeyException, NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidAlgorithmParameterException,
            IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(IV_AES128_CTR);
        Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
        return cipher.doFinal(content);
    }

    public static void main(String[] args) throws Exception {

        byte[] key = {};
        String PID = "IKE 00010001";
        byte[] userID = {0x00, 0x00, 0x00, (byte) 0xc4};
        byte[] ciphertext = {
                0x67,0x3c,0x0c,0x1b,(byte)0xb4,(byte)0xcf,(byte)0xa8,(byte)0xdf,(byte)0xe3,(byte)0xe6,(byte)0xaa,(byte)0x81,0x31,(byte)0xc5,0x54,0x55,(byte)0xe1
        };
        byte[] content = {
            (byte) 0x88, (byte) 0x88, (byte) 0x88, (byte) 0x88, 0x00, 0x00, 0x00, 0x77, 0x04
        };
        byte[] packHead = {0x00,0x00,0x00,0x19,0x00,0x00,0x00,0x10,0x00,0x00,0x00,0x66,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x75 };

        byte[] packFrame = new byte[256];//
        System.arraycopy(packHead, 0, packFrame, 0, packHead.length);

        key = BinaryConvert.toBytes(userID, PID.getBytes());
        key = BinaryConvert.getMD5(key);

        System.out.printf("加密过程\r\n明文：");
        for (byte x : content) {
            System.out.printf("%02x ", x);
        }
        System.out.printf("\r\n");
//        byte[] ciphertext1 = Encrypt(content , key);//AES_EN_KEY_FIXED
        byte[] ciphertext1 = Encrypt(content , AES_EN_KEY_FIXED);//AES_EN_KEY_FIXED

        System.out.printf("密文：");
        for (byte x : ciphertext1) {
            System.out.printf("%02x ", x);
        }
        System.out.printf("\r\n");

        System.arraycopy(ciphertext1, 0, packFrame, packHead.length, ciphertext1.length);
        int c = MyCRC32.getCRC32(packFrame, ciphertext1.length + packHead.length);
        System.out.printf("CRC32: 0x%08x\r\n", c);





        System.out.printf("解密过程\r\n密文：");
        for (byte x : ciphertext) {
            System.out.printf("%02x ", x);
        }
        System.out.printf("\r\n");

        byte[] content1 = DecryptAES(ciphertext, AES_EN_KEY_FIXED);
        System.out.printf("明文：");
        for (byte x : content1) {
            System.out.printf("%02x ", x);
        }
        System.out.printf("\r\n");


    }
}