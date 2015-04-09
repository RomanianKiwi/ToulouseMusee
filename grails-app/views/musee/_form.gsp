<%@ page import="toulousemusee.Musee" %>



<div class="fieldcontain ${hasErrors(bean: museeInstance, field: 'nom', 'error')} required">
	<label for="nom">
		<g:message code="musee.nom.label" default="Nom" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nom" required="" value="${museeInstance?.nom}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: museeInstance, field: 'horairesOuverture', 'error')} required">
	<label for="horairesOuverture">
		<g:message code="musee.horairesOuverture.label" default="Horaires Ouverture" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="horairesOuverture" required="" value="${museeInstance?.horairesOuverture}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: museeInstance, field: 'telephone', 'error')} required">
	<label for="telephone">
		<g:message code="musee.telephone.label" default="Telephone" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="telephone" required="" value="${museeInstance?.telephone}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: museeInstance, field: 'accesMetro', 'error')} ">
	<label for="accesMetro">
		<g:message code="musee.accesMetro.label" default="Acces Metro" />
		
	</label>
	<g:textField name="accesMetro" value="${museeInstance?.accesMetro}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: museeInstance, field: 'accesBus', 'error')} ">
	<label for="accesBus">
		<g:message code="musee.accesBus.label" default="Acces Bus" />
		
	</label>
	<g:textField name="accesBus" value="${museeInstance?.accesBus}"/>

</div>

