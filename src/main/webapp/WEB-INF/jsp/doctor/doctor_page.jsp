<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<!DOCTYPE html>
<html>
<c:set var="title" value="Doctor page" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
	<%@ include file="/WEB-INF/jspf/doctorMenu.jspf"%>
	<h1 style="text-align: left; margin-left: 25%; color: #c9957c; font-style: italic;">
		<fmt:message key="doctor_jsp.text.doctorPage" />
	</h1>

	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body> <br></br>
<body><h1 style="text-align: left; margin-left: 3%; color: #bfbfff; font-family: Gabriola, cursive;">
<p>« Нужно проверить на паразитов, вирусы, бактерии, грибки, прионы,</p>
<p>  радиацию, токсины, химикаты, вирусы с порносайтов.</p> Я проверю интернет, вы займетесь всем остальным. »
<p style="text-align: right; margin-right: 43%; color: #bfbfff; font-family: Gabriola, cursive;"> (dr. House)</p>
</h1></body>
</html>