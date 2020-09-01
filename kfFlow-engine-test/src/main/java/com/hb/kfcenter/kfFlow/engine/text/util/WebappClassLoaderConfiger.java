package com.hb.kfcenter.kfFlow.engine.text.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

public class WebappClassLoaderConfiger {

	private static final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCF0WvTvCJW0InEdlgFiIWKoU/XMbc2dzb0mONNBRcxvl5lifqi0fFm26RyiOPii52uPQrL9ukGJSFQfhndAeMuQ5/cvNkL+YBQXhQCvyhVa44A0cE30h3ofYmHkcii35X+L6gZJUmtZSiEZ/6j3cvDuL0nmp2Y4vRxdLI5XAPVkwIDAQAB";
	private static final String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIXRa9O8IlbQicR2WAWIhYqhT9cxtzZ3NvSY400FFzG+XmWJ+qLR8WbbpHKI4+KLna49Csv26QYlIVB+Gd0B4y5Dn9y82Qv5gFBeFAK/KFVrjgDRwTfSHeh9iYeRyKLflf4vqBklSa1lKIRn/qPdy8O4vSeanZji9HF0sjlcA9WTAgMBAAECgYAnUPNEP1c7eIOxpC+SWJ0bsO9bQ3EX4IjV7oGB5If/BnaHkLWB1vr9BHVZbVy+99Q5GBgu+vlV49zY4GPK6j4LsfAcFBxizjbSXUYpwEs0x+XrXo4A9RrGNSS8o7XpGZiwFe3uocTH9Ixl6iGVogM+lxk5EMVRlS3bcDlr6nun2QJBALtBdu26C/Q4c+qgFvsQT6r0AgWbGxNSqngXb95lfTjlXcNMzgSwvMBQS5OqR9W4Fyzmfz+kwF75//pdsKi8I80CQQC28dCvxyw7jo69LmZXlB6L7gSI4h0Tg8oZ4Zbj8pDAO6H4eZljG6mdx7doC+B/WaON2TwXnPv0PM4Qm7cCST7fAkBej42GOShmOMfYmOVh/XetxbDrscr3dAxfvr4gWbHltHYn9LYoyjNXDnVL6XaDJ4aUhwWFVyZKEAdDIGLgormVAkAu5vwbtLKQalIKCnVOirjyIwmilmXh9O3BiOaIdk3KGv/BlltO326y9tr8n97kh3S5DzKq0yJ9UYF7HJDk0yZ9AkAevhenEZ/KAeP+6kK2FzXF65S5zor5fBtPEyV8neRKo1vJukPQIT6HP/CdKjNB3iJXWeUjKWPyTC0pymlKVHlu";
	
	public static String encrypt( String str ) throws Exception{
		byte[] decoded = Base64.decodeBase64(publicKey);
		RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, pubKey);
		String outStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes("UTF-8")));
		return outStr;
	}
	
	public static byte[] decrypt(String str) throws Exception{
		//64位解码加密后的字符串
		byte[] inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));
		//base64编码的私钥
		byte[] decoded = Base64.decodeBase64(privateKey);  
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));  
		//RSA解密
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, priKey);
		String outStr = new String(cipher.doFinal(inputByte));
		return StringUtils.getBytesUtf8(outStr);
	}
	
	public static void toEncryptFile(File yFile,String path) {
		if (yFile.exists() && yFile.isFile()) {
			FileInputStream fileInputStream = null;
			BufferedInputStream bis = null;
			InputStreamReader reader = null;
			BufferedReader bReader = null;
			FileOutputStream fileOutputStream = null;
			BufferedOutputStream bos = null;
			try {
				fileInputStream = new FileInputStream(yFile);
				bis = new BufferedInputStream(fileInputStream);
				fileOutputStream = new FileOutputStream(path+File.separator+yFile.getName());
				bos = new BufferedOutputStream(fileOutputStream);
				StringBuilder sb = new StringBuilder("");
//				int data;
//				while ((data = bis.read()) != -1) {
//					bos.write(data);
//				}
//				bos.write(sb.toString().getBytes());
				int len = 0;
				while ((len=bis.read())!=-1)
		        {
					char c = (char)len;
					String _s = new String(String.valueOf(c).getBytes("UTF-8"),"GBK");
					sb.append(_s);
//					String s = new String(bytes,"GB2312");
//					bytes=new byte[1024];
//		            bos.write(s.getBytes("GB2312"));
		        }
				bos.write(sb.toString().getBytes("GBK"));
				bos.flush();
//		        reader = new InputStreamReader(new FileInputStream(yFile),"GBK");//定义一个fileReader对象，用来初始化BufferedReader
//		        bReader = new BufferedReader(reader);//new一个BufferedReader对象，将文件内容读取到缓存
//		        StringBuilder sb = new StringBuilder();//定义一个字符串缓存，将字符串存放缓存中
//		        String s = null;
//		        while ((s =bReader.readLine()) != null) {//逐行读取文件内容，不读取换行符和末尾的空格
//		            sb.append(s + "\n");//将读取的字符串添加换行符后累加存放在缓存中
//		        }
//		        bReader.close();
//		        String str = sb.toString();
//		        fileOutputStream = new FileOutputStream(path+File.separator+yFile.getName());
//				bos = new BufferedOutputStream(fileOutputStream);
//				bos.write(str.getBytes("GBK"));
//				bos.flush();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (bReader != null)
					try {
						bReader.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				if (reader != null)
					try {
						reader.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				if (fileOutputStream != null)
					try {
						fileOutputStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				if (bos != null)
					try {
						bos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}
	}
	
	public static void main(String[] args) {
		String path = "D:\\Download\\kfFlow-engine-0.0.1-SNAPSHOT\\com\\hb\\kfcenter\\kfFlow\\engine";
		path += File.separator + "util";
		String _path = path +"z";
		File classFile = new File(path);
		if (classFile.exists() && classFile.listFiles() != null ) {
			File _wFiles = new File(_path);
			if (!_wFiles.exists())
				_wFiles.mkdirs();
			Arrays.asList(classFile.listFiles()).forEach(f -> toEncryptFile(f,_path));;
		}
	}
}
