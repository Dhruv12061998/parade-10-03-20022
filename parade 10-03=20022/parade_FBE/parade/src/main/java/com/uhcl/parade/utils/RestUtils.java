package com.uhcl.parade.utils;

import java.awt.image.BufferedImage;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;



import java.io.ByteArrayInputStream;
import java.io.File;


public class RestUtils {

	public static final int CLIENT_UPDATE_APPROVE = -1;
	public static final int CLIENT_UPDATE_REJECT = -2;
	public static final int CLIENT_UPDATE_REJECTED_UNDO = -3;

	public static final DecimalFormat df2 = new DecimalFormat(".##");

	public static Map map(Object... args) {
		Map map = new HashMap();
		for (int i = 0; i < args.length; i += 2) {
			map.put(args[i], args[i + 1]);
		}
		return map;
	}

	public static boolean isAuthorized(String username, String pswd) {
		// TODO Auto-generated method stub
		if ((username.equals("abhi") || username.equals("sukesh") || username.equals("tanuja")
				|| username.equals("ranveer")) && pswd.equals("$neem!!t")) {
			return true;
		} else {
			return false;
		}
	}

	public static String getActivationKey() {
		// TODO Auto-generated method stub
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public static String md5(String pass) {
		String generatedPassword = null;

		// Create MessageDigest instance for MD5
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(pass.getBytes());
			// Get the hash's bytes
			byte[] bytes = md.digest();
			// This bytes[] has bytes in decimal format;
			// Convert it to hexadecimal format
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			// Get complete hashed password in hex format
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Add password bytes to digest

		return generatedPassword;
	}

	public static String Base64ToImage(String data, String filename, String path) {

		try {
			String base64Image = data.split(",")[1];
			byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);
			BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageBytes));

			// write the image to a file

			File directory = new File(path);
			if (!directory.exists()) {
				directory.mkdirs();
			}

			File outputfile = new File(path + filename);
			if (!outputfile.exists()) {
				outputfile.getParentFile().mkdirs();
				outputfile.createNewFile();
			}

			ImageIO.write(img, "png", outputfile);
			return outputfile.getAbsolutePath();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static Date getFirstDateOfMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}

}
