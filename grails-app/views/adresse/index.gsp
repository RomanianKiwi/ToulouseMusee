
<%@ page import="toulousemusee.Adresse" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'adresse.label', default: 'Adresse')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-adresse" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-adresse" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="numero" title="${message(code: 'adresse.numero.label', default: 'Numero')}" />
					
						<g:sortableColumn property="rue" title="${message(code: 'adresse.rue.label', default: 'Rue')}" />
					
						<g:sortableColumn property="codePostal" title="${message(code: 'adresse.codePostal.label', default: 'Code Postal')}" />
					
						<g:sortableColumn property="ville" title="${message(code: 'adresse.ville.label', default: 'Ville')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${adresseInstanceList}" status="i" var="adresseInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${adresseInstance.id}">${fieldValue(bean: adresseInstance, field: "numero")}</g:link></td>
					
						<td>${fieldValue(bean: adresseInstance, field: "rue")}</td>
					
						<td>${fieldValue(bean: adresseInstance, field: "codePostal")}</td>
					
						<td>${fieldValue(bean: adresseInstance, field: "ville")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${adresseInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
