<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<html>

<c:set var="title" value="Settings" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
	<div align="center">
		<table id="main-container" class="input-data">

			<tr>
				<td class="content">
					<%-- CONTENT --%>

					<form id="settings_form" action="controller" method="get">
						<input type="hidden" name="command" value="updateSettings" />

						<div>
							<h3>
								<fmt:message key="settings_jsp.label.localization" />
							</h3>
							<select name="localeToSet">
								<c:choose>
									<c:when test="${not empty defaultLocale}">
										<option value="">${defaultLocale}[Default]</option>
									</c:when>
									<c:otherwise>
										<option value="" />
									</c:otherwise>
								</c:choose>

								<c:forEach var="localeName" items="${locales}">
									<option value="${localeName}">${localeName}</option>
								</c:forEach>
							</select>
						</div>
						<input type="submit" class="btn btn-primary"
							value='<fmt:message key="settings_jsp.button.update"/>'><br />
					</form> <%-- CONTENT --%>
				</td>
			</tr>

		</table>
	</div>
</body>
</html>