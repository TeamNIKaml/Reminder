package com.teamNIKaml.reminder.property;

public class XOREncryption {
	
	
	 public static String encryptKey(String key){
		    int ch = 0;

		    StringBuilder encryptedKey = new StringBuilder();

		    for(int i = 0; i < key.length(); i++ ){

		        ch = key.charAt(i);

		        ch = ~ch;

		        encryptedKey.append(ch);
		    }

		    return encryptedKey.toString();
		    
		    }
	 
	 public static  String encrypt(String message, String key){

		    StringBuilder encryptedMessage = new StringBuilder();
		    char ch;
		    int j = 0;

		    for(int i = 0; i < message.length(); i++, j++ ){

		        if(j >= key.length()){
		            j = 0;
		        }

		        ch = message.charAt(i);

		        ch = (char) (key.charAt(j) ^ ch);

		        encryptedMessage.append(ch);

		    }

		    return encryptedMessage.toString();
		    }

}
