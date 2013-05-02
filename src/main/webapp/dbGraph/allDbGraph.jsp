<!--
"mongobird" is released under a dual license model designed to developers 
and commercial deployment.

For using OEMs(Original Equipment Manufacturers), ISVs(Independent Software
Vendor), ISPs(Internet Service Provider), VARs(Value Added Resellers) 
and another distributors, or for using include changed issue
(modify / application), it must have to follow the Commercial License policy.
To check the Commercial License Policy, you need to contact Cardinal Info.Tech.Co., Ltd.
(http://www.citsoft.net)
 *
If not using Commercial License (Academic research or personal research),
it might to be under AGPL policy. To check the contents of the AGPL terms,
please see "http://www.gnu.org/licenses/"
-->
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.Calendar"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>  

<%
	String path = request.getContextPath();
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	if (request.getProtocol().equals("HTTP/1.1"))
		response.setHeader("Cache-Control", "no-cache");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="main.title"/></title>
<%@ include file="./dbGraphCommon.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dbGraph_js/allDbGraph.js"></script>
</head>
<body onresize="touchSlider()">
<!-- header start -->
<%@ include file="/top_menu.jsp"%>
<!-- // header end -->
<!-- container start-->
<div id="container">
	<!-- content start -->
	<div id="content_graph">
	<h1>
		<img src="./img/Subtit_totalDB<spring:message code="common.img"/>.png">
	</h1>
		<form method="post" name="frm_demon" id="frm_demon">
		<input type="hidden" id="sdate" name="sdate" value="${comm.sdate}" />
		<input type="hidden" id="edate" name="edate" value="${comm.edate}" />
		<input type="hidden" id="type_gubun" name="type_gubun" value="${comm.type_gubun}"/>
		<input type="hidden" id="sortItem" name="sortItem" value="big"/>
		<input type="hidden" id="count" name="count" value="${comm.count}"/>
		<input type="hidden" id="graph_period" name="graph_period" value="${comm.graph_period}"/>
		<input type="hidden" id="sliderMin" name="sliderMin" value="${comm.sliderMin}"/>
		<input type="hidden" id="sliderMax" name="sliderMax" value="${comm.sliderMax}"/>
		<div id="menu_graph">
			<ul>
				<li class="end"><b><img src="./img/graph_subtit01_slt01<spring:message code="common.img"/>.png"></b></li>
				<li>
					<input type="radio" id="search_gubun" name="search_gubun" value="totalDbDataSize" <c:if test="${'totalDbDataSize'==comm.search_gubun}" >checked</c:if> />  Total DB Data Size
				</li>
				<li>
					<input type="radio" id="search_gubun" name="search_gubun" value="totalDbIndexSize" <c:if test="${'totalDbIndexSize'==comm.search_gubun}" >checked</c:if> />  Total DB Index Size
				</li>
				<li class="noline"></li>
				<li class="end"><b><img src="./img/DBgraph_subtit02<spring:message code="common.img"/>.png"></b></li>
				<li>
					<input type="radio" id="dstype" name="dstype" value="cur" <c:if test="${'cur'==comm.dstype}" >checked</c:if> />  difference 
				</li>
				<li>
					<input type="radio" id="dstype" name="dstype" value="acc" <c:if test="${'acc'==comm.dstype}" >checked</c:if> />  accumulation 
				</li>
			</ul>
		</div>
				<%@ include file="./dbGraphCommonImage.jsp" %>
				
		</form>
	</div>
</div>
</body>
</html>
