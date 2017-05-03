package com.example.tom.testaes2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity {
    private final static String IvAES = "Shinda99Shinda99" ;
    private final static String KeyAES = "2422567624225676";
    private final static String TextAES = "小黑人的Android教室 !";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        SetAES setAES = new SetAES();
        byte[] TextByte = new byte[0];
        byte[] TextByte2 = new byte[0];
        String Text2 = null,Text = null;
        try {
            TextByte = setAES.EncryptAES(IvAES.getBytes("UTF-8"),KeyAES.getBytes("UTF-8"),TextAES.getBytes("UTF-8"));
            Text = Base64.encodeToString(TextByte,Base64.DEFAULT);
            TextByte2 =setAES.DecryptAES(IvAES.getBytes("UTF-8"),KeyAES.getBytes("UTF-8"),Base64.decode(Text.getBytes("UTF-8"), Base64.DEFAULT));
            Text2 = new String(TextByte2,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        Log.e("加密",Text);
        Log.e("解密",Text2);

    }
    class SetAES{
        private byte[] EncryptAES(byte[] iv, byte[] key,byte[] text)
        {
            try
            {
                AlgorithmParameterSpec mAlgorithmParameterSpec = new IvParameterSpec(iv);
                SecretKeySpec mSecretKeySpec = new SecretKeySpec(key, "AES");
                Cipher mCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                mCipher.init(Cipher.ENCRYPT_MODE,
                        mSecretKeySpec,
                        mAlgorithmParameterSpec);

                return mCipher.doFinal(text);
            }
            catch(Exception ex)
            {
                return null;
            }
        }

        //AES解密，帶入byte[]型態的16位英數組合文字、32位英數組合Key、需解密文字
        private byte[] DecryptAES(byte[] iv,byte[] key,byte[] text)
        {
            try
            {
                AlgorithmParameterSpec mAlgorithmParameterSpec = new IvParameterSpec(iv);
                SecretKeySpec mSecretKeySpec = new SecretKeySpec(key, "AES");
                Cipher mCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                mCipher.init(Cipher.DECRYPT_MODE,
                        mSecretKeySpec,
                        mAlgorithmParameterSpec);

                return mCipher.doFinal(text);
            }
            catch(Exception ex)
            {
                return null;
            }
        }
    }
}
