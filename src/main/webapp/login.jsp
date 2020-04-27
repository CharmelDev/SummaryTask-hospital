<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
	<h1 style="text-align: right; color: #C0C0C0; margin-right: 2%;">
		HELL>o
	</h1>

	<form id="login_form" action="controller" method="post" style="text-align: right; color: #C0C0C0; margin-right: 2%;">
		<input type="hidden" name="command" value="login" />
		<div class="form-group">
			<span><fmt:message key="login_jsp.label.login" /></span>
			 <input type="text" name="login" placeholder="Enter login" required="required" /> <br>
		</div>
		<div class="form-group">
			<span><fmt:message key="login_jsp.label.password" /></span>
			<input type="password" name="password" placeholder="Enter password" required="required" /> <br> <br>
			<input style="background-color: #C0C0C0;" type="submit" value="<fmt:message key="login_jsp.buttonn.login"/>" />
		</div>
	</form>
</body>
</html>