<%@ page import="com.worthsoln.utils.LegacySpringUtils" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<ul class="nav nav-pills">
    <li <%=("index".equals(request.getAttribute("currentNav"))) ? "class\"active\"" : "" %>><html:link action="/index">Home</html:link></li>
    <%
        if (LegacySpringUtils.getSecurityUserManager().isLoggedInToTenancy()) {
    %>

    <li <%= ("patient_details".equals(request.getAttribute("currentNav"))) ? "class=\"active\"" : ""%>><html:link action="/patient/patient_details">My Details</html:link></li>

    <logic:present tenancy="rpv">
        <li <%= ("patient_view".equals(request.getAttribute("currentNav"))) ? "class=\"active\"" : "" %>><html:link action="/patient/patient_view">Patient Info</html:link></li>
    </logic:present>

    <logic:present tenancy="ibd">
        <li <%=("ibd_myibd".equals(request.getAttribute("currentNav"))) ? "class=\"active\"" : "" %>><html:link action="/myibd">My IBD</html:link></li>
        <li <%=("ibd_careplan".equals(request.getAttribute("currentNav"))) ? "class=\"active\"" : "" %>><html:link action="/careplan">Care Plan</html:link></li>
        <li <%=("patient_entry".equals(request.getAttribute("currentNav"))) ? "class=\"active\"" : "" %>><html:link action="/patient/patient_entry">Enter Symptoms</html:link></li>
        <li <%=("ibd_medications".equals(request.getAttribute("currentNav"))) ? "class=\"active\"" : "" %>><html:link action="/medications">Medicines</html:link></li>
        <li <%=("ibd_nutrition".equals(request.getAttribute("currentNav"))) ? "class=\"active\"" : "" %>><html:link action="/nutrition">Nutrition</html:link></li>
    </logic:present>
    <logic:present tenancy="rpv">
        <li <%= ("aboutme".equals(request.getAttribute("currentNav"))) ? "class=\"active\"" : "" %>><html:link action="/patient/aboutme">About Me</html:link></li>
        <li <%=("patient_entry".equals(request.getAttribute("currentNav"))) ? "class=\"active\"" : "" %>><html:link action="/patient/patient_entry">Enter My Own Results</html:link></li>
        <li <%=("medicines".equals(request.getAttribute("currentNav"))) ? "class=\"active\"" : "" %>><html:link action="/patient/medicines">Medicines</html:link></li>
    </logic:present>

    <li <%=("results".equals(request.getAttribute("currentNav"))) ? "class=\"active\"" : "" %>><html:link action="/patient/results">Results</html:link></li>

    <li <%=("letters".equals(request.getAttribute("currentNav"))) ? "class=\"active\"" : "" %>><html:link action="/patient/letters" >Letters</html:link></li>

    <li <%=("contact".equals(request.getAttribute("currentNav"))) ? "class=\"active\"" : "" %>><html:link action="/patient/contact">Contact</html:link></li>

    <logic:present tenancy="ibd">
        <li <%= ("ibd_myibd".equals(request.getAttribute("currentNav"))) ? "class=\"active\"" : "" %>><html:link action="#">Education</html:link></li>
    </logic:present>
    <logic:present tenancy="rpv">
        <li <%= ("xxxxxxx".equals(request.getAttribute("currentNav"))) ? "class=\"active\"" : "" %>><html:link href="/forums/list.page" styleClass="">Forum</html:link></li>
    </logic:present>
    <%
        }
    %>
    <li <%= ("help".equals(request.getAttribute("currentNav"))) ? "class=\"active\"" : "" %>><a href="/help.do">Help</a></li>
</ul>
