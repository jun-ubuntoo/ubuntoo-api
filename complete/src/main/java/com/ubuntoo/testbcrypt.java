package com.ubuntoo;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class testbcrypt {
	public static void main(String[] args) {
		String password = "LaxLion1";
		String bcryptHashString = BCrypt.withDefaults().hashToString(11, password.toCharArray());
		// $2a$12$US00g/uMhoSBm.HiuieBjeMtoN69SN.GE25fCpldebzkryUyopws6
		
		System.out.println(bcryptHashString);
		BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), bcryptHashString);
		System.out.println(result);
		
		password = "B68!pVUJbAW79h5";
		bcryptHashString = "$2a$11$e4HXnDDPbGpVeph2LTAMneZMQn2EyK7yN0WfiTwZAer0mtYcE7E1K";
		

		result = BCrypt.verifyer().verify(password.toCharArray(), bcryptHashString);
		System.out.println(result);
	}
}
