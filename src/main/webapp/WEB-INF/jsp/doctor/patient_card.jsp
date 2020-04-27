<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<!DOCTYPE html>
<html>
<c:set var="title" value="Patient card" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<%@ include file="/WEB-INF/jspf/doctorMenu.jspf"%>
<body onload="makeTableScroll();">
		<fmt:message var="patientCardLabel" key="doctor_jsp.patient.text.PatientCard" />
		<c:set var="patientCardLabel" value="${patientCardLabel} ${patient.firstName}" />
	
		<fmt:message var="patientDiagnosLabel" key="doctor_jsp.patient.text.CurrentDiagnose" />
		<c:set var="patientDiagnosLabel" value="${patientDiagnosLabel} ${patient.diagnoseName}" />
		
		
		<table style="text-align: left; margin-left: 1%; font-style: italic;" class="input-data centeredTable">
			<thead >
				<tr>
					<th style="background-color: #3b3b3b; color: #bfbfff;">${patientCardLabel}</th>
				</tr>
				<tr>
					<th style="background-color: #3b3b3b; color: #bfbfff;">${patientDiagnosLabel}</th>
				</tr>
			</thead>
		</table><br/>
	
	<form action="controller" method="post">
		<input type="hidden" name="command" value="set_diagnose" /> 
		<input type="hidden" name="patient_id" value="${patient.id}" /> 
		<table style="text-align: left; margin-left: 1%; background-color: #3b3b3b; color: #bfbfff;" class="centeredTable">
			<tbody>
				<tr>
					<td>
						<fmt:message key="doctor_jsp.patient.text.PatientCard.setDiagnose" />
					</td>
					<td>
						<select name="diagnose_name">
							<c:forEach var="diagnose" items="${diagnosList}">
								<option value="${diagnose.getId()}">${diagnose.getDiagnosName()}</option>
							</c:forEach>
						</select>
					</td>
					<td>
						<input type="submit" style="float: center; clear: left; color: #bfbfff;" class="btn-sort" />
					</td>
				</tr>
			</tbody>
		</table>
	</form>

	<form action="controller" method="post">
		<input type="hidden" name="command" value="add_assignment" /> 
		<input type="hidden" name="patient_id" value="${patient.id}" />
		<table style="text-align: left; margin-left: 1%; background-color: #3b3b3b; color: #bfbfff;" class="centeredTable">
			<tbody>
				<tr>
					<td>
						<fmt:message key="doctor_jsp.patient.text.PatientCard.addAssignment" />
					</td>
					<td>
						<select name="assignment_name">
								<c:forEach var="assignment" items="${assignmentList}">
									<option value="${assignment}">${assignment}</option>
								</c:forEach>
						</select> 
					</td>
					<td>
						<input type="submit" style="float: center; clear: left; color: #bfbfff;" class="btn-sort" />
					</td>
				</tr>
			</tbody>
		</table>
	</form>

	<h3 style="text-align: left; margin-left: 1%;" class="centerCaption">
		<fmt:message key="doctor_jsp.patient.text.PatientCard.CurrentAssignment" />:
	</h3>
	<div style="text-align: left; margin-left: 1%;" class="scrollingTable">
		<table id="myTable" class="table ">
			<thead>
				<tr style="background-color: #3b3b3b;" class="bg-primary">
					<th style="color: #bfbfff;" scope="col"><fmt:message key="doctor_jsp.assignment.ID" /></th>
					<th style="color: #bfbfff;" scope="col"><fmt:message
							key="doctor_jsp.assignment.Assignment" /></th>
					<th style="color: #bfbfff;" scope="col"><fmt:message
							key="doctor_jsp.assignment.AssignmentStatus" /></th>
					<th style="color: #bfbfff;" scope="col"><fmt:message
							key="doctor_jsp.assignment.CompleteAssignment" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${patientAssignmentList}">
					<c:if test="${item.getAssignment_status_id() == 0}">
						<tr style="background-color: #3b3b3b;" class="bg-info">
							<th scope="row">${item.getId()}</th>
							<td>${item.getAssignmentName()}</td>
							<td>${item.getAssignmentStatusName()}</td>
							<td>
								<form action="controller" method="post">
									<input type="hidden" name="command" value="complete_assignment" />
									<input type="hidden" name="patient_id" value="${patient.id}" />
									<input type="hidden" name="assignment_id" value="${item.getId()}" />
									<input type="submit" class="btn btn-success" value="Complete" />
								</form>

							</td>
						</tr>
					</c:if>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<br>
	<h3 style="text-align: left; margin-left: 1%;" class="centerCaption">
		<fmt:message key="doctor_jsp.patient.text.PatientCard.CompleteAssignment" />:
	</h3>
	<div style="text-align: left; margin-left: 1%;" class="scrollingTable">
		<table id="myTable" class="table ">
			<thead>
				<tr style="background-color: #3b3b3b;" class="bg-primary">
					<th style="color: #bfbfff;" scope="col"><fmt:message key="doctor_jsp.assignment.ID" /></th>
					<th style="color: #bfbfff;" scope="col"><fmt:message
							key="doctor_jsp.assignment.Assignment" /></th>
					<th style="color: #bfbfff;" scope="col"><fmt:message
							key="doctor_jsp.assignment.AssignmentStatus" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${patientAssignmentList}">
					<c:if test="${item.getAssignment_status_id() == 1}">
						<tr style="background-color: #3b3b3b;" class="bg-success">
							<th scope="row">${item.getId()}</th>
							<td>${item.getAssignmentName()}</td>
							<td>${item.getAssignmentStatusName()}</td>
						</tr>
					</c:if>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<br>

	<fmt:message var="patientDischargeLabel" key="doctor_jsp.patient.text.PatientCard.DiaschargePatient" />
	<c:set var="patientDischargeLabel" value="${patientDischargeLabel} ${patient.firstName}" />
	
	<form action="controller" method="post" style="text-align: left; margin-left: 1%;">
		<input type="hidden" name="command" value="discharged_patient" /> 
		<input type="hidden" name="patient_id" value="${patient.id}" /> 
		<input style="color: #bfbfff;" type="submit" class="btn-sort" value="${patientDischargeLabel}">
	</form>

	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>