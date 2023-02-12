<%@page import="com.liferay.docs.feedback.service.service.FeedbackLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.docs.feedback.service.model.Feedback"%>
<%@ include file="/init.jsp" %>

<h1>FEEDBACK FORM</h1>

<% 
	Feedback feedback = null;
	long feedbackId = ParamUtil.getLong(renderRequest, "feedbackId");
	if(feedbackId>0){
		feedback = FeedbackLocalServiceUtil.getFeedback(feedbackId);
	}
%>

<portlet:renderURL var="viewRenderURL">
	<portlet:param name="jspPage" value="/view.jsp"></portlet:param>
</portlet:renderURL>

<portlet:actionURL name="addFeedback" var="feedbackActionURL"></portlet:actionURL>

<aui:model-context bean="<%=feedback %>" model="<%=Feedback.class %>"></aui:model-context>

<aui:form action="<%=feedbackActionURL %>">
	<aui:input name="feedbackId" type="hidden"></aui:input>
	<aui:input label="Feedback Subject:" name="feedbackSubject" type="text"></aui:input>
	<aui:input label="Feedback Message:" name="feedbackText" type="text"></aui:input>
	
	<aui:button type="submit" value="SUBMIT"></aui:button>
	
	<aui:button onClick="<%=viewRenderURL.toString() %>" type="cancel" value="CANCEL"></aui:button>
	
</aui:form>