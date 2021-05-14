package com.savenotes.viewmodel;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class FeedbackViewModel 
{
	@NotNull
	private String name;
	
	@NotNull
	@Email
	private String email;
	
	@NotNull
	@Size(min = 10, max = 200, message = "Feedback should not be smaller than 10 characters")
	private String feedback;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	
	
}
