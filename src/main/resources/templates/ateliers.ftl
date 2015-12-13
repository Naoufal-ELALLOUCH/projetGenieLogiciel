<#import "common.ftl" as html>

<@html.page>
	<div class="col-xs-12 col-lg-10">
	    <div class="page-header">
	        <div class="row">
	            <div class="col-sm-9">
	                <h1>Liste des ateliers</h1>
	            </div>
		        <#if labo?? && labo>
	                <div class="col-sm-3" style="padding: 2em 0;">
	                    <a class="btn btn-mini btn-primary" href="/laboratoire/atelier/creer" alt="Ajouter un atelier"><i class="icon-add icon-white"></i> Ajouter un atelier</a>
	                </div>
		        </#if>
	        </div>
	    </div>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Titre</th>
	                <th>Type</th>
	                <th>Thèmes disciplinaires</th>
	                <th>Public</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<#list ateliers as atelier>
				<tr>
					<td>${atelier.titre}</td>
	                <td>Atelier scientifique</td>
					<td>${atelier.themes}</td>
					<td>${atelier.cible}</td>
					<td>
                        <a class="btn btn-mini btn-default" href="/atelier/${atelier.idAtelier}" alt="Détails">
                            <i class="icon-info-sign icon-white"></i>
                            Détails
                        </a>
						<#if modificationAutorisee>
							<a class="btn btn-mini btn-primary" href="/laboratoire/atelier/${atelier.idAtelier}/modifier" alt="Modifier">
								<i class="icon-edit icon-white"></i>
								Modifier
							</a>
							<a class="btn btn-mini btn-danger" href="/laboratoire/atelier/${atelier.idAtelier}/supprimer" alt="Supprimer">
	                            <i class="icon-remove icon-white"></i>
	                            Supprimer
	                        </a>
						<#elseif enseignant>
                            <a class="btn btn-mini btn-default" href="/atelier/${atelier.idAtelier}/creneaux" alt="Inscrire">
                                <i class="icon-remove icon-white"></i>
                                Inscrire
                            </a>
						</#if>
					</td>
				</tr>
				</#list>
			</tbody>
		</table>
	</div>
</@html.page>