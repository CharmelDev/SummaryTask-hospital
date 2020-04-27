<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<!DOCTYPE html>
<html>
<c:set var="title" value="List of medical staff" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<%@ include file="/WEB-INF/jspf/adminMenu.jspf"%>
<body onload="makeTableScroll();">
		<h2 style="text-align: left; margin-left: 5%; color: #C0C0C0; font-style: italic;">
			<fmt:message key="admin.jsp.text.tableNameMedStaff" />
		</h2>
		<div style="text-align: left; margin-left: 5%;">
		<form action="controller" method="post">
		    <input type="hidden" name="command" value="show_list_of_medical_staff" /><br> 
		    <input type="submit" class="btn-sort" name="sorting_order" value="Sort Id" />
			<input type="submit" class="btn-sort" name="sorting_order" value="Sort Name" /> 
			<input type="submit" class="btn-sort" name="sorting_order" value="Sort Specialization" /> 
			<input type="submit" class="btn-sort" name="sorting_order" value="Sort Number of patients" />
		</form>
		<div style="width: 87%; text-align: left; margin-left: 0%;" class="scrollingTable">
			<table style="width: 87%; text-align: left; margin-left: 0%;" id="myTable" class="table">
				<thead>
					<tr>
						<th style="background-color: #bfbfff; color: black;" scope="col"><fmt:message key="admin_jsp.label.id" /></th>
						<th style="background-color: #bfbfff; color: black;" scope="col"><fmt:message key="login_jsp.label.login" /></th>
						<th style="background-color: #bfbfff; color: black;" scope="col"><fmt:message key="admin_jsp.label.FirstName" />
						</th>
						<th style="background-color: #bfbfff; color: black;" scope="col"><fmt:message key="admin_jsp.label.LastName" /></th>
						<th style="background-color: #bfbfff; color: black;" scope="col"><fmt:message key="admin_jsp.label.Category" />
							<fmt:message key="admin_jsp.label.id" /></th>
						<th style="background-color: #bfbfff; color: black;" scope="col"><fmt:message key="admin_jsp.label.categoryName" /></th>
						<th style="background-color: #bfbfff; color: black;" scope="col"><fmt:message key="admin_jsp.label.numberOfPatients" /></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${med_staff_list}">
						<tr class="bg-primary">
							<th scope="row" style="background-color: #6a6a6a; color: black;">${item.getId()}</th>
							<td style="background-color: #6a6a6a; color: black;">${item.getLogin()}</td>
							<td style="background-color: #6a6a6a; color: black;">${item.getFirstName()}</td>
							<td style="background-color: #6a6a6a; color: black;">${item.getLastName()}</td>
							<td style="background-color: #6a6a6a; color: black;">${item.getCategoryId()}</td>
							<td style="background-color: #6a6a6a; color: black;">${item.getCategoryName() }</td>
							<td style="background-color: #6a6a6a; color: black;" class="bg-success">${item.getNumberOfPatients()}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</div>
</body>
</html>