<%@page import="com.liferay.docs.feedback.service.model.Feedback"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.portal.kernel.dao.search.ResultRow"%>
<%@ include file="/init.jsp" %>
    
   <% ResultRow row = (ResultRow) request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW); 
    Feedback feedback = (Feedback) row.getObject();
   %>

<liferay-ui:icon-menu>
	<liferay-portlet:actionURL name="deleteFeedback" var="deleteActionURL">
		<portlet:param name="feedbackId" value="<%=String.valueOf(feedback.getFeedbackId()) %>"></portlet:param>
	</liferay-portlet:actionURL>
<liferay-ui:icon image="delete" label="DELETE" url="<%=deleteActionURL.toString() %>">
</liferay-ui:icon>

<portlet:renderURL var="updateRenderURL">
		<portlet:param name="feedbackId" value="<%=String.valueOf(feedback.getFeedbackId()) %>"></portlet:param>
		<portlet:param name="jspPage" value="/feedback.jsp"></portlet:param>
	</portlet:renderURL>
	
	<liferay-ui:icon image="edit" label="EDIT" url="<%=updateRenderURL.toString() %>">
	</liferay-ui:icon>


</liferay-ui:icon-menu>