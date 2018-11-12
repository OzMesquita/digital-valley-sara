package br.com.n2s.sara.model;

import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.Cipher;

public class Encrypt {
	private static String IV = "SARA";
	private static String chave = "123456";
	
	public static String encrypt(String texto) throws Exception{
		byte[] resultado;
		Cipher encripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(chave.getBytes("ISO-8859-1"), "AES");
        encripta.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(IV.getBytes("ISO-8859-1")));
        resultado = encripta.doFinal(texto.getBytes("ISO-8859-1"));
		return new String (resultado, "ISO-8859-1");
	}
	
	public static String decrypt (String textoEnc) throws Exception{
		Cipher decripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(chave.getBytes("ISO-8859-1"), "AES");
        decripta.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec(IV.getBytes("ISO-8859-1")));
        return new String(decripta.doFinal(textoEnc.getBytes()),"ISO-8859-1");
	}
}
