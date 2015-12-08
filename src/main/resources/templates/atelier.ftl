<#import "common.ftl" as html>

<@html.page>
<div class="container" >
    <font size="8"><b>Atelier</b></font>
    <div class="row">
        <div class="col-lg-6">

            <h2>${atelier.titre}</h2>

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

        </div>

    </div>
</div>
</@html.page>