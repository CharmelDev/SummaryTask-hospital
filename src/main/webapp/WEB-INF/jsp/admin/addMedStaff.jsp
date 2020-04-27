<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<html>
<c:set var="title" value="Add med staff" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
	<%@ include file="/WEB-INF/jspf/adminMenu.jspf"%>

		<h2 style="text-align: left; margin-left: 5%; color: #C0C0C0; font-style: italic;">
			<fmt:message key="admin.jsp.text.addMedStaff" />
		</h2>
		<c:if test="${form_error != null }">
			<b class="bg-danger"><fmt:message key="${form_error}" /></b>
		</c:if>
        <div style="text-align: left; margin-left: 5%;">
		<form action="controller" method="post" accept-charset="UTF-8">
			<input type="hidden" name="command" value="add_med_staff" /><br>
			<br>
			<table style="width: 79%; text-align: left;" class="input-data">
				<tr>
					<td style="background-color: #939393; color: black;"><fmt:message key="login_jsp.text.login" />:</td>
					<td style="background-color: #939393; color: black;"><input type="text" name="login" required="required" /></td>
				</tr>
				<tr>
					<td style="background-color: #6a6a6a; color: black;"><fmt:message key="login_jsp.label.password" />:</td>
					<td style="background-color: #6a6a6a; color: black;"><input type="text" name="password" required="required" /></td>
				</tr>
				<tr>
					<td style="background-color: #939393; color: black;"><fmt:message key="admin_jsp.label.FirstName" />:</td>
					<td style="background-color: #939393; color: black;"><input type="text" name="first_name" required="required" /></td>
				</tr>
				<tr>
					<td style="background-color: #6a6a6a; color: black;"><fmt:message key="admin_jsp.label.LastName" />:</td>
					<td style="background-color: #6a6a6a; color: black;"><input type="text" name="last_name" required="required" /></td>
				</tr>
				<tr>
					<td style="background-color: #939393; color: black;"><fmt:message key="admin_jsp.label.Category" />:</td>
					<td style="background-color: #939393; color: black;"><select name="category">
							<c:forEach var="item" items="${categoryList}">
								<option value="${item.getId()}">${item.categoryName}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td style="background-color: #6a6a6a; color: black;"><fmt:message key="admin_jsp.label.Role" />:</td>
					<td style="background-color: #6a6a6a; color: black;"><select name="role">
							<c:forEach var="item" items="${role}">
								<option value="${item}">${item.getName()}</option>
							</c:forEach>
					</select></td>
				</tr>
			</table>
			<input style="background-color: #939393;" class="btn btn-success" type="submit"
				value="<fmt:message key="admin.jsp.button.submit"/>" />
		</form>
	</div>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
<body><h1 style="text-align: left; margin-left: 9%; color: #C0C0C0; font-family: Gabriola, cursive;">
<p> «Последним симптомом была смерть. И, на случай, если вы пропустили этот урок </p>
<p style="text-align: left; margin-left: 16%; color: #C0C0C0; font-family: Gabriola, cursive;"> в медицинской школе, этот симптом не лечится.»</p>
<p style="text-align: left; margin-left: 69%; color: #C0C0C0; font-family: Gabriola, cursive;"> (dr. House)</p>
</h1></body>
</html>