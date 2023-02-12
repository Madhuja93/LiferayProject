package com.liferay.docs.feedback.portlet.portlet;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.docs.feedback.portlet.constants.NewsPortalFeedbackPortletKeys;
import com.liferay.docs.feedback.service.model.Feedback;
import com.liferay.docs.feedback.service.service.FeedbackLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author Madhuja
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=NewsPortalFeedback",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + NewsPortalFeedbackPortletKeys.NEWSPORTALFEEDBACK,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class NewsPortalFeedbackPortlet extends MVCPortlet {
public void addFeedback(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException, PortalException {

	String sub = ParamUtil.getString(actionRequest, "feedbackSubject");
	String message = ParamUtil.getString(actionRequest, "feedbackText");
	long feedbackId = ParamUtil.getLong(actionRequest, "feedbackId");
	
	System.out.println("Feedback Subject is :" + sub);
	System.out.println("Feedback Message is :" + message);
	
	Feedback feedback;
	
	if(feedbackId>0) {
		//Update existing feedback
		feedback = FeedbackLocalServiceUtil.getFeedback(feedbackId);
		
		feedback.setFeedbackSubject(sub);
		feedback.setFeedbackText(message);
		
		FeedbackLocalServiceUtil.updateFeedback(feedback);
		
		System.out.println("Feedback has been updated succedssfully" + feedbackId);
	}
	
	else {
		//Add New Feedback
		//auto increment for feedbackId 
		feedbackId = CounterLocalServiceUtil.increment();
		
		//Created feedback object 
		feedback = FeedbackLocalServiceUtil.createFeedback(feedbackId);
		
		//Initialize fd sub and message into databse
		feedback.setFeedbackSubject(sub);
		feedback.setFeedbackText(message);
		
		FeedbackLocalServiceUtil.addFeedback(feedback);
		
		System.out.println("Feedback has been saved successfully" + feedbackId);
	}
	
	sendRedirect(actionRequest, actionResponse);
	
}



public void deleteFeedback(ActionRequest actionRequest, ActionResponse actionResponse) throws PortalException, IOException  {
	long feedbackId =ParamUtil.getLong(actionRequest, "feedbackId");
	
	FeedbackLocalServiceUtil.deleteFeedback(feedbackId);
	
	sendRedirect(actionRequest, actionResponse);
}
}
