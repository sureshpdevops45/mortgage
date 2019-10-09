package com.hcl.mortgage.utils;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.messages.TextMessage;

public class Sms {
		public void sendSms(Long mobileNumber, long accountNumber, String randomPassword) {
			String mobile=Long.toString(mobileNumber);
			mobile="+91"+mobile;
			NexmoClient client = NexmoClient.builder()
			        .apiKey("02205d9e")
			        .apiSecret("d4O6AxdZJmlL9zs4")
			        .build();

			SmsSubmissionResponse responses = client.getSmsClient().submitMessage(new TextMessage(
			        "NEXMO",
			        mobile,
			        "Your Account Number is "+accountNumber +" and the password is "+randomPassword));
			}
}
