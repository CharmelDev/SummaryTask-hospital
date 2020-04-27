<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<!DOCTYPE html>
<html>
<c:set var="title" value="List of patients" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<%@ include file="/WEB-INF/jspf/doctorMenu.jspf"%>
<body onload="makeTableScroll();">
	<h2 class="centerCaption" style="text-align: left; margin-left: 1%; color: #c9957c; font-style: italic;">
		<fmt:message key="doctor_jsp.text.ListOfPatients" />
	</h2>

	<div style="text-align: left; margin-left: 1%;" class="scrollingTable">
		<table id="myTable" class="table">
			<thead>
				<tr style="background-color: #bfbfff;">
					<th style="color: black;" scope="col"><fmt:message key="admin_jsp.label.id" /></th>
					<th style="color: black;" scope="col"><fmt:message key="admin_jsp.label.FirstName" /></th>
					<th style="color: black;" scope="col"><fmt:message key="admin_jsp.label.LastName" /></th>
					<th style="color: black;" scope="col"><fmt:message key="admin_jsp.label.DateOfBirth" /></th>
					<th style="color: black;" scope="col">Email</th>
					<th style="color: black;" scope="col"><fmt:message key="doctor_jsp.text.patientCard" /></th>
				</tr>
			</thead>
			<tbody>

				<c:forEach var="item" items="${patient_list}">
					<c:if test="${!item.isDischarged()}">
						<tr style="background-color: #3b3b3b;" class="bg-danger">
							<td>${item.getId()}</td>
							<td>${item.getFirstName()}</td>
							<td>${item.getLastName()}</td>
							<td>${item.getDateOfBirth()}</td>
							<td>${item.getEmail()}</td>
							<td>

								<form action="controller" method="post">
									<input type="hidden" name="command" value="patient_card" /> <input
										type="hidden" name="patient_id" value="${item.getId()}" /> 
										<input style="background-color: #bfbfff;" type="submit" class="btn btn-success"
										value=<fmt:message key="doctor_jsp.button.OpenCard"/> />
								</form>
							</td>
						</tr>
					</c:if>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body><br></br>
<body><h1 style="text-align: left; margin-left: 0%; color: #c9957c; font-family: Gabriola, cursive;">
<p>«Вот так и развивалась медицина. Пациентам иногда становится лучше.</p>
<p style="text-align: left; margin-left: 7%; color: #c9957c; font-family: Gabriola, cursive;"> Ты не знаешь почему, но пока не дашь обоснование,</p>
<p style="text-align: left; margin-left: 17%; color: #c9957c; font-family: Gabriola, cursive;"> они тебе не заплатят.»</p>
<p style="text-align: right; margin-right: 43%; color: #c9957c; font-family: Gabriola, cursive;"> (dr. House)</p>
</h1></body>
</html>