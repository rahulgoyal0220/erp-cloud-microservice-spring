package com.spm.erp.utility;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import com.spm.erp.model.Employee;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import javax.mail.*;
import javax.mail.internet.*;

public class Util {

	public static Employee updateProperties(Employee newEmp, Employee oldEmp) {
		if (oldEmp == null || newEmp == null) {
			return null;
		}

		BeanWrapper oldObj = new BeanWrapperImpl(oldEmp);
		BeanWrapper newObj = new BeanWrapperImpl(newEmp);

		for (Field property : newEmp.getClass().getDeclaredFields()) {
			Object emp = oldObj.getPropertyValue(property.getName());
			if (emp != null) {
				newObj.setPropertyValue(property.getName(), emp);
			}
		}
		return newEmp;
	}

	public static boolean validateEmail(Employee employee) {

		String email = employee.getEmail();
		boolean valid = EmailValidator.getInstance().isValid(email);
		return valid;
	}

	public static void sendEmail(Employee employee, String subject, String body) {
		String from = "himanshumahajan38@gmail.com";
		String pass = "iWcMuPe4d!";
		String[] to = { employee.getEmail(), "himanshumahajan38@gmail.com" }; // list of recipient email addresses
	/*	String subject = "Account created";
		String body = "Hello " + employee.getFirstName()
				+ " Welcome !. Your account have been created and your default password is " + employee.getPassword();

	*/	
		sendFromGMail(from, pass, to, subject, body);
	}

	private static void sendFromGMail(String from, String pass, String[] to, String subject, String body) {
		Properties props = System.getProperties();
		String host = "smtp.gmail.com";
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", pass);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(from));
			InternetAddress[] toAddress = new InternetAddress[to.length];

			// To get the array of addresses
			for (int i = 0; i < to.length; i++) {
				toAddress[i] = new InternetAddress(to[i]);
			}

			for (int i = 0; i < toAddress.length; i++) {
				message.addRecipient(Message.RecipientType.TO, toAddress[i]);
			}

			message.setSubject(subject);
			message.setText(body);
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, pass);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (AddressException ae) {
			ae.printStackTrace();
		} catch (MessagingException me) {
			me.printStackTrace();
		}
	}

	public static String createPassword() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 18) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;
	}
	
	public static boolean checkDuplicateEmail(Employee employee, List<Employee> employees) {
		for(Employee e : employees) {
			if(e.getEmail().equals(employee.getEmail())) {
				return false;
			}
		}
		return true;
	}
}
