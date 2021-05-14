package com.savenotes.controller;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.savenotes.mail.FeedbackSender;
import com.savenotes.viewmodel.FeedbackViewModel;

@RestController
@RequestMapping("/api/feedback")
@CrossOrigin
public class FeedbackController 
{
	@Autowired
	private FeedbackSender feedbackSender;
	
	private FeedbackController(FeedbackSender feedbackSender)
	{
		this.feedbackSender = feedbackSender;
	}
	
	@PostMapping
	public void sendFeedback(@Valid @RequestBody FeedbackViewModel feedbackViewModel,
			BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
		{
			throw new ValidationException("Feedback has error; cant not send feedback;");
		}
		
		this.feedbackSender.sendFeedback(
				feedbackViewModel.getEmail(),
				feedbackViewModel.getName(),
				feedbackViewModel.getFeedback());
	}

}
