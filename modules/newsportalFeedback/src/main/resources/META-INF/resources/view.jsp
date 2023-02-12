<%@page import="com.liferay.portal.kernel.dao.search.SearchContainer"%>
<%@page import="com.liferay.docs.feedback.service.service.FeedbackLocalServiceUtil"%>
<%@ include file="/init.jsp" %>

<h1>Feedback Portlet</h1>
<portlet:renderURL var="feedbackRenderURL">
   <portlet:param name="jspPage" value="/feedback.jsp"></portlet:param>
</portlet:renderURL>
<a href="${feedbackRenderURL}">Add New Feedback</a>

<liferay-ui:tabs names="Feedback">

<liferay-ui:search-container>
   <liferay-ui:search-container-results results="<%=FeedbackLocalServiceUtil.getFeedbacks(searchContainer.getStart(), searchContainer.getEnd()) %>">
</liferay-ui:search-container-results>

   <liferay-ui:search-container-row className="com.liferay.docs.feedback.service.model.Feedback" modelVar="feedback">
   
      <liferay-ui:search-container-column-text name="Feedback Subject" property="feedbackSubject"></liferay-ui:search-container-column-text>
      <liferay-ui:search-container-column-text name="Message" property="feedbackText"></liferay-ui:search-container-column-text>
      <liferay-ui:search-container-column-jsp name= "BUTTONS" align="right" path="/buttons.jsp"></liferay-ui:search-container-column-jsp>
  
         </liferay-ui:search-container-row>
<liferay-ui:search-iterator></liferay-ui:search-iterator>

</liferay-ui:search-container>
</liferay-ui:tabs>