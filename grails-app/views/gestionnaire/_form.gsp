<%@ page import="toulousemusee.Gestionnaire" %>



<div class="fieldcontain ${hasErrors(bean: gestionnaireInstance, field: 'nom', 'error')} required">
	<label for="nom">
		<g:message code="gestionnaire.nom.label" default="Nom" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nom" required="" value="${gestionnaireInstance?.nom}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: gestionnaireInstance, field: 'musees', 'error')} ">
	<label for="musees">
		<g:message code="gestionnaire.musees.label" default="Musees" />
		
	</label>
	<g:select name="musees" from="${toulousemusee.Musee.list()}" multiple="multiple" optionKey="id" size="5" value="${gestionnaireInstance?.musees*.id}" class="many-to-many"/>

</div>

