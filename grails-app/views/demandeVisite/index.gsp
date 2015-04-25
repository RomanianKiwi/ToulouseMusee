
<%@ page import="toulousemusee.DemandeVisite" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'demandeVisite.label', default: 'DemandeVisite')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>

		<style type="text/css" media="screen">
			#museesFavoris {
				margin-top: 2%;
			}

			#museesFavorisBlock {
				margin-top: 1%;
				text-align: center;
			}
		</style>

	</head>
	<body>
		<a href="#list-demandeVisite" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>
		<div id="museesFavorisBlock">
			<h3 style="color:#48802C;">Mes musées préférés</h3>
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
							<td><g:hiddenField name="id" value="${museefavInstance.id}" />${fieldValue(bean: museefavInstance, field: "nom")}</td>
						</g:form>
					</tr>
				</g:each>
				</tbody>
			</table>
		</div>

		<div style="text-align: center; font-weight: bold;">
			<p>${code}</p>
		</div>
		<g:form>
			<fieldset class="form">
				<div class="fieldcontain">
					<label for="dateDebut">
						Date de debut:
					</label>
					<g:datePicker name="dateDebut" value="${new Date()}"
								  noSelection="['':'-Choose-']"
								  precision="day"/>
				</div>
				<div class="fieldcontain">
					<label for="dateFin">
						Date de fin:
					</label>
					<g:datePicker name="dateFin" value="${new Date()}"
								  noSelection="['':'-Choose-']"
								  precision="day"/>
				</div>
				<div class="fieldcontain">
					<label for="nbPersonnes">
						Nombre de personnes:
					</label>
					<g:field type="number" name="nbPersonnes" min="1" max="6" required=""></g:field>
				</div>
				<div style="margin-top: 2%; text-align: center;">
					<g:actionSubmit action="effectuerDemandeDeVisite" value="Effectuer une demande de visite"/>
				</div>
			</fieldset>
		</g:form>

	</body>
</html>
