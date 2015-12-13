<#import "common.ftl" as html>

<@html.page>
<div class="col-xs-12 col-lg-10">
    <div class="page-header">
        <div class="row">
            <div class="col-sm-9">
                <h1>Liste de mes enregistrements</h1>
            </div>
        </div>
    </div>
    <table class="table table-hover">
        <thead>
        <tr>
            <th>Atelier</th>
            <th>Date</th>
            <th>Nombre de places inscrites</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
			<#list enregistrements as enregistrement>
            <tr>
                <td>${enregistrement.atelier.titre}</td>
                <td>${enregistrement.date}</td>
                <td>${enregistrement.nbInscrits}</td>
                <td>
                    <a class="btn btn-mini btn-default" href="/atelier/${atelier.idAtelier}" alt="Détails">
                        <i class="icon-info-sign icon-white"></i>
                        Détails
                    </a>
                </td>
            </tr>
			</#list>
        </tbody>
    </table>
</div>
</@html.page>