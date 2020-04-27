<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<!DOCTYPE html>
<html>
<c:set var="title" value="List of patients" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<%@ include file="/WEB-INF/jspf/adminMenu.jspf"%>
<body onload="makeTableScroll();">
		<h2 style="text-align: left; margin-left: 5%;color: #C0C0C0; font-style: italic;">
			<fmt:message key="admin.jsp.text.tablePatients" />
		</h2>
		<div style="text-align: left; margin-left: 5%;">
		<form action="controller" method="post">
			<input type="hidden" class="btn btn-success" name="command"
				value="show_list_of_patiens" /><br> 
			<input type="submit" class="btn-sort" name="sorting_order" value="Sort Id" />
			<input type="submit" class="btn-sort" name="sorting_order" value="Sort Name" /> 
			<input type="submit" class="btn-sort" name="sorting_order" value="Sort Email" />
			<input type="submit" class="btn-sort" name="sorting_order" value="Sort Birth" />
		</form>
		<div style="width: 87%; text-align: left; margin-left: 0%;" class="scrollingTable">
			<table style="width: 87%; text-align: left;" id="myTable" class="table">
				<thead>
					<tr>
						<th style="background-color: #bfbfff; color: black;" scope="col"><fmt:message key="admin_jsp.label.id" /></th>
						<th style="background-color: #bfbfff; color: black;" scope="col"><fmt:message key="admin_jsp.label.FirstName" /></th>
						<th style="background-color: #bfbfff; color: black;" scope="col"><fmt:message key="admin_jsp.label.LastName" /></th>
						<th style="background-color: #bfbfff; color: black;" scope="col">Email</th>
						<th style="background-color: #bfbfff; color: black;" scope="col"><fmt:message key="admin_jsp.label.DateOfBirth" /></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${patient_list}">
						<tr class="bg-danger">
							<th scope="row" class="bg-primary" style="background-color: #6a6a6a; color: black;">${item.getId()}</th>
							<td style="background-color: #6a6a6a; color: black;">${item.getFirstName()}</td>
							<td style="background-color: #6a6a6a; color: black;" class="bg-warning">${item.getLastName()}</td>
							<td style="background-color: #6a6a6a; color: black;">${item.getEmail()}</td>
							<td style="background-color: #6a6a6a; color: black;" class="bg-success">${item.getDateOfBirth()}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
 <c:out value="${sessionScope.all_patients}"/>
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</div>
</body>
</html>