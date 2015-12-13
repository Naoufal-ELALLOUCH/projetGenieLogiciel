<#import "common.ftl" as html>

<@html.page>
<div class="container" >
    <div classe="page-header">
        <div class="row">
            <div class="col-sm-9">
                <h1>Atelier</h1>
            </div>
	        <div class="col-sm-3" style="padding: 2em 0;">
		        <#if modificationAutorisee?? && modificationAutorisee>
                    <a class="btn btn-mini btn-primary" href="/laboratoire/atelier/${atelier.idAtelier}/modifier" alt="Modifier"><i class="icon-add icon-white"></i> Modifier l'atelier</a>
	            </#if>
	        </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-6">

            <h3>${atelier.titre}</h3>

            <div>
                <strong>Thèmes disciplinaires : </strong>
                <p style="padding-left: 1em; ">${atelier.themes}</p>
            </div>

            <legend>Zone</legend>
            <address>
                <strong>Adresse :</strong>
                <p style="padding-left: 1em; ">${atelier.adresse}</p>

            </address>

            <legend>Animateurs / Conférenciers</legend>
            <p style="padding-left: 1em;">${atelier.orateurs}</p>

            <legend>Partenaires</legend>
            <p style="padding-left: 1em;">${atelier.partenaires}</p>

            <legend>Public visé</legend>
            <p style="padding-left: 1em;">${atelier.cible}</p>

            <legend>Remarques</legend>
            <p style="padding-left: 1em;">${atelier.remarques}</p>

            <legend>Liste des créneaux</legend>
			<#if modificationAutorisee?? && modificationAutorisee>
                <a class="btn btn-mini btn-info" href="/atelier/${atelier.idAtelier}/creneaux" alt="Ajouter">Ajouter un créneau</a>
			</#if>
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Jour</th>
                    <th>Heure</th>
                    <th>Capacité</th>
			        <#if modificationAutorisee?? && modificationAutorisee>
                        <th>Action</th>
			        </#if>
                </tr>
                </thead>
                <tbody>
			        <#list creneaux as c>
                    <tr>
                        <td>${c.date?date}</td>
                        <td>${c.date?string["HH:mm"]}</td>
                        <td>${c.capacite}</td>
				        <#if modificationAutorisee?? && modificationAutorisee>
                            <td>
                                <a class="btn btn-mini btn-primary" href="/atelier/${idAtelier}/creneaux/${c.idCreneau}" alt="Modifier">
                                    <i class="icon-edit icon-white"></i>
                                    Modifier
                                </a>
                                <a class="btn btn-mini btn-danger" href="/atelier/${idAtelier}/creneaux/${c.idCreneau}/supprimer" alt="Supprimer">
                                    <i class="icon-remove icon-white"></i>
                                    Supprimer
                                </a>
                            </td>
				        </#if>
                    </tr>
			        </#list>
                </tbody>
            </table>

        </div>

    </div>
</div>
</@html.page>