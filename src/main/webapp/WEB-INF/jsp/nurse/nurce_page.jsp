<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<html>
<c:set var="title" value="Nurce page" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<body onload="makeTableScroll();">
	<%@ include file="/WEB-INF/jspf/nurceMenu.jspf"%>
	<h2 style="text-align: right; margin-right: 1%; color: #db9877; font-style: italic">
		<fmt:message key="nurce_jsp.text.NurcePage" />
	</h2>
	<div style="text-align: right;
	  margin-right: 1%; width: 50%;" class="scrollingTable">

		<table id="myTable" class="table table-bordered">
			<thead>
				<tr style="background-color: #bfbfff;" class="bg-primary">
					<th style="color: black;" scope="col"><fmt:message key="doctor_jsp.assignment.ID" /></th>
					<th style="color: black;" scope="col"><fmt:message
							key="doctor_jsp.assignment.Assignment" /></th>
					<th style="color: black;" scope="col"><fmt:message
							key="doctor_jsp.assignment.AssignmentStatus" /></th>
					<th style="color: black; " scope="col"><fmt:message
							key="doctor_jsp.assignment.CompleteAssignment" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${patientAssignment}">
					<c:if test="${item.getAssignment_status_id() == 0}">
						<tr style="background-color: #3b3b3b;">
							<th scope="col">${item.getId()}</th>
							<td>${item.getAssignmentName()}</td>
							<td>${item.getAssignmentStatusName()}</td>
							<td>
								<form action="controller" method="post">
									<input type="hidden" name="command"
										value="complete_nurce_assignment" /> <input type="hidden"
										name="medicalCard" value="medical_card" /> <input
										type="hidden" name="patient_id"
										value="${item.getPatient_id()}" /> <input type="hidden"
										name="assignment_id" value="${item.getId()}" /> <input style="background-color: #bfbfff;"
										type="submit" class="btn btn-success"
										value="<fmt:message key="doctor_jspf.button.Complete"/>" />
								</form>

							</td>
						</tr>
					</c:if>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body> <br></br>
<body> <h1 style="text-align: right; margin-right: 27%; color: #ed1222; font-family: Gabriola, cursive;">« Пациент умер? </h1> <br></br>
 <h1 style="text-align: right; margin-right: 2%; color: #ed1222; font-family: Gabriola, cursive;"> Или мне надо работать? »</h1></body>
</html>