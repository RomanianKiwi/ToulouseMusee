<%@ page import="toulousemusee.DemandeVisite" %>



<div class="fieldcontain ${hasErrors(bean: demandeVisiteInstance, field: 'nbPersonnes', 'error')} required">
	<label for="nbPersonnes">
		<g:message code="demandeVisite.nbPersonnes.label" default="Nb Personnes" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="nbPersonnes" type="number" min="1" max="6" value="${demandeVisiteInstance.nbPersonnes}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: demandeVisiteInstance, field: 'statut', 'error')} required">
	<label for="statut">
		<g:message code="demandeVisite.statut.label" default="Statut" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="statut" required="" value="${demandeVisiteInstance?.statut}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: demandeVisiteInstance, field: 'code', 'error')} required">
	<label for="code">
		<g:message code="demandeVisite.code.label" default="Code" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="code" type="number" value="${demandeVisiteInstance.code}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: demandeVisiteInstance, field: 'dateDebutPeriode', 'error')} required">
	<label for="dateDebutPeriode">
		<g:message code="demandeVisite.dateDebutPeriode.label" default="Date Debut Periode" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="dateDebutPeriode" precision="day"  value="${demandeVisiteInstance?.dateDebutPeriode}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: demandeVisiteInstance, field: 'dateFinPeriode', 'error')} required">
	<label for="dateFinPeriode">
		<g:message code="demandeVisite.dateFinPeriode.label" default="Date Fin Periode" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="dateFinPeriode" precision="day"  value="${demandeVisiteInstance?.dateFinPeriode}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: demandeVisiteInstance, field: 'museeDemande', 'error')} ">
	<label for="museeDemande">
		<g:message code="demandeVisite.museeDemande.label" default="Musee Demande" />
		
	</label>
	

</div>

