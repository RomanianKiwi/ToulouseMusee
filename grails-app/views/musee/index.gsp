
<%@ page import="toulousemusee.Adresse; toulousemusee.Musee" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'musee.label', default: 'Musee')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>

        <style type="text/css" media="screen">
            body {
                max-width: 1400px;
            }

			#page-body {
				margin: 2em 1em 1.25em 18em;
			}

			#museesTable > tbody > tr > td {
				text-align: center;
				vertical-align: middle;
			}
        </style>

	</head>
	<body>
		<a href="#list-musee" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="page-body" role="main">
			<h1>Welcome to ToulouseMusee</h1>
		</div>
		<div id="list-musee" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>

            <g:form>
                <fieldset class="form">
                    <div class="fieldcontain">
                        <label for="nom">
                            Nom du musée contient :
                        </label>
                        <g:textField name="nom"/>
                        <label for="codePostal">
                            Le code postal contient :
                        </label>
                        <g:select id="codePostal" name='codePostal' value="${adresse?.codePostal}"
								  noSelection="${['':'Choisir un code postal']}"
								  from='${toulousemusee.Adresse.listUnique()}'></g:select>
                    </div>
                    <div class="fieldcontain">
                        <label for="rue">
                            Le nom de la rue contient :
                        </label>
                        <g:textField name="rue"/>
                    </div>
                    <div style="float: right">
                        <g:actionSubmit action="doSearchMusees" value="Rechercher"/>
                    </div>
                </fieldset>
            </g:form>

			<table id="museesTable">
			<thead>
					<tr>
						<g:sortableColumn property="nom" title="${message(code: 'musee.nom.label', default: 'Nom')}" />
                        <g:sortableColumn property="telephone" title="${message(code: 'musee.telephone.label', default: 'Telephone')}" />
                        <g:sortableColumn property="adresse" title="${message(code: 'musee.adresse.label', default: 'Adresse')}" />
						<g:sortableColumn property="accesMetro" title="${message(code: 'musee.accesMetro.label', default: 'Acces Metro')}" />
						<g:sortableColumn property="accesBus" title="${message(code: 'musee.accesBus.label', default: 'Acces Bus')}" />
                        <g:sortableColumn property="horairesOuverture" title="${message(code: 'musee.horairesOuverture.label', default: 'Horaires Ouverture')}" />
						<th><g:message code="musee.gestionnaire.label" default="Gestionnaire" /></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
				<g:each in="${museeInstanceList}" status="i" var="museeInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<g:form>
							<td><g:hiddenField name="nom" value="${fieldValue(bean: museeInstance, field: "nom")}" /><g:link action="addMuseeToFav" id="${museeInstance.id}">${fieldValue(bean: museeInstance, field: "nom")}</g:link></td>
							<td><g:hiddenField name="telephone" value="${fieldValue(bean: museeInstance, field: "telephone")}" />${fieldValue(bean: museeInstance, field: "telephone")}</td>
							<td><g:hiddenField name="adresse" value="${fieldValue(bean: museeInstance, field: "adresse")}" />${fieldValue(bean: museeInstance, field: "adresse")}</td>
							<td><g:hiddenField name="accesMetro" value="${fieldValue(bean: museeInstance, field: "accesMetro")}" />${fieldValue(bean: museeInstance, field: "accesMetro")}</td>
							<td><g:hiddenField name="accesBus" value="${fieldValue(bean: museeInstance, field: "accesBus")}" />${fieldValue(bean: museeInstance, field: "accesBus")}</td>
							<td><g:hiddenField name="horairesOuverture" value="${fieldValue(bean: museeInstance, field: "horairesOuverture")}" />${fieldValue(bean: museeInstance, field: "horairesOuverture")}</td>
							<td><g:hiddenField name="gestionnaire" value="${fieldValue(bean: museeInstance, field: "gestionnaire")}" />${fieldValue(bean: museeInstance, field: "gestionnaire")}</td>
							<td><g:actionSubmit action="addMuseeToFav" value="Ajouter à ma liste de musées"/></td>
						</g:form>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate max="5" total="${museeInstanceCount ?: 5}" />
			</div>

            <table id="museesFavoris">
                <thead>
                <tr>
                    <g:sortableColumn property="nom" title="${message(code: 'musee.nom.label', default: 'Nom')}" />
                </tr>
                </thead>
                <tbody>
                <g:each in="${museeFavorisList}" status="i" var="museefavInstance">
                    <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                        <g:form>
                            <td><g:hiddenField name="nom" value="${fieldValue(bean: museefavInstance, field: "nom")}" />${fieldValue(bean: museefavInstance, field: "nom")}</td>
                        </g:form>
                    </tr>
                </g:each>
                </tbody>
            </table>
		</div>
	</body>
</html>
