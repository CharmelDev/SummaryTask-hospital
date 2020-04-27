<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>


<!DOCTYPE html>

<html>
<c:set var="title" value="Add patient" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>
	<%@ include file="/WEB-INF/jspf/adminMenu.jspf"%>
<body>

	<h2 style="text-align: left; margin-left: 5%; color: #C0C0C0; font-style: italic;">
		<fmt:message key="admin.jsp.text.addPatient" />
	</h2>
	<c:if test="${form_error != null }">
		<b class="bg-danger"><fmt:message key="${form_error}" /></b>
	</c:if>
	<div style="text-align: left; margin-left: 5%;">
		<form action="controller" method="post" accept-charset="UTF-8">
			<input type="hidden" name="command" value="add_patient" /><br>
			<br>
			<table style="width: 79%; text-align: left;" class="input-data">
				<tr>
					<td style="background-color: #939393; color: black;"><fmt:message key="admin_jsp.label.FirstName" />:</td>
					<td style="background-color: #939393;"><input type="text" placeholder="first name"
						name="first_name" required="required" /></td>
				</tr>
				<tr>
					<td style="background-color: #6a6a6a; color: black;"><fmt:message key="admin_jsp.label.LastName" />:</td>
					<td style="background-color: #6a6a6a;"><input type="text" name="last_name"
						placeholder="last name" required="required" /></td>
				</tr>
				<tr>
					<td style="background-color: #939393; color: black;"><fmt:message key="admin_jsp.label.Doctor" />:</td>
					<td style="background-color: #939393;"><select name="doctor">
							<c:forEach var="item" items="${medStaffList}">
								<c:if test="${item.getRoleId() == 1}">
									<option value="${item.getId()}">${item.getFirstName()} ${item.getLastName()}</option>
								</c:if>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td style="background-color: #6a6a6a; color: black;"><fmt:message key="admin_jsp.label.DateOfBirth" />:</td>
					<td style="background-color: #6a6a6a;"><input type="date" name="date_of_birth"
						required="required" max="2020-01-01" min="1900-01-01" /></td>
				</tr>
				<tr>
					<td style="background-color: #939393; color: black;"><fmt:message key="admin_jsp.label.Telephone" />:</td>
					<td style="background-color: #939393;"><input type="text" placeholder="0123456789" name="telephon_number" /></td>
				</tr>
				<tr>
					<td style="background-color: #6a6a6a; color: black;"><fmt:message key="admin_jsp.label.Email" />:</td>
					<td style="background-color: #6a6a6a;"><input type="email" placeholder="your@addres.mail" name="email" /></td>
				</tr>
			</table>
			<input style="background-color: #939393;" class="btn btn-success" type="submit"
				value="<fmt:message key="admin.jsp.button.submit"/>" />
		</form>
	</div>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
<body><h1 style="text-align: left; margin-left: 19%; color: #C0C0C0; font-family: Gabriola, cursive;">
<p> «Сразу он не умрёт, но будет об этом мечтать.» 
<p style="text-align: left; margin-left: 65%; color: #C0C0C0; font-family: Gabriola, cursive;"> (dr. House)</p>
</h1></body>
</html>