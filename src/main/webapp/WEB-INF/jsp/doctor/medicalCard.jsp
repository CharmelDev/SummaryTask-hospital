<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<!DOCTYPE html>
<html>
<c:set var="title" value="Medical card" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<body onload="makeTableScroll();">
	<%@ include file="/WEB-INF/jspf/doctorMenu.jspf"%>
	<div class="centerCaption" style="text-align: left; margin-left: 1%;">
		<h2 style="text-align: left; font-style: italic; color: #c9957c;">
			<fmt:message key="doctor_jsp.text.MedCard" />
		</h2>
		<h3 style="text-align: left; font-style: italic;">
			<fmt:message key="doctor_jspf.text.currentAssignments" />
		</h3>
		<div class="scrollingTable">
			<table style="width: 95%;" id="myTable" class="table">
				<thead>
					<tr style="background-color: #bfbfff;">
						<th style="color: black;" scope="col"><fmt:message key="doctor_jsp.assignment.ID" /></th>
						<th style="color: black;" scope="col"><fmt:message key="doctor_jsp.assignment.Assignment" /></th>
						<th style="color: black;" scope="col"><fmt:message key="doctor_jsp.assignment.AssignmentStatus" /></th>
						<th style="color: black;" scope="col"><fmt:message key="doctor_jsp.assignment.CompleteAssignment" /></th>		
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${patientAssignment}">
						<c:if test="${item.getAssignment_status_id() == 0}">
							<tr style="background-color: #3b3b3b;" class="bg-info">
								<th scope="col">${item.getId()}</th>
								<td>${item.getAssignmentName()}</td>
								<td>${item.getAssignmentStatusName()}</td>
								<td>
									<form action="controller" method="post">
										<input type="hidden" name="command" value="complete_assignment" />
										<input type="hidden" name="medicalCard" value="medical_card" />
										<input type="hidden" name="patient_id" value="${patient.id}" />
									    <input type="hidden" name="assignment_id" value=${item.getId() } />
										<input style="background-color: #bfbfff;" type="submit" class="btn btn-secondary"
											value="<fmt:message key="doctor_jspf.button.Complete"/>" />
									</form>

								</td>
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>

	</div>
</body>
<body><h1 style="text-align: left; margin-left: 2%; color: #c9957c; font-family: Gabriola, cursive;">
<p>«У тебя есть два варианта: еще поспорить и сделать, что я сказал.</p>
<p style="text-align: left; margin-left: 15%; color: #c9957c;; font-family: Gabriola, cursive;"> Или сделать, что я сказал.»</p>
<p style="text-align: right; margin-right: 44%; color: #c9957c;; font-family: Gabriola, cursive;"> (dr. House)</p>
</h1></body>
</html>