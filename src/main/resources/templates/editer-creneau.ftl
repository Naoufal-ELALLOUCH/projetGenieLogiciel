<#import "common.ftl" as html>

<@html.page>
<div class="container">
    <div class="row">
        <h1>${title}</h1>

	    <#if creneaux??>
		    <h3>Créneaux</h3>
	        <table class="table table-hover">
	            <thead>
	            <tr>
	                <th>Jour</th>
	                <th>Heure</th>
	                <th>Capacité</th>
	            </tr>
	            </thead>
	            <tbody>
				    <#list creneaux as c>
	                <tr>
	                    <td>${c.date?date}</td>
	                    <td>${c.date?string["HH:mm"]}</td>
	                    <td>${c.capacite}</td>
	                </tr>
				    </#list>
	            </tbody>
	        </table>
	    </#if>

        <form role="form" method="post">
            <div class="col-lg-6">
                <div class="form-group">
                    <label for="titre">Jour</label>
                    <div class="input-group">
                        <input type="date" name="jour" class="form-control" value="<#if creneau??>${creneau.date?string["yyyy-MM-dd"]}</#if>" placeholder="'YYYY-MM-DD'">
                        <span class="input-group-addon"></span>
                    </div>
                </div>
                <div class="form-group">
                    <label for="titre">Heure</label>
                    <div class="input-group">
                        <input type="time" name="heure" class="form-control" value="<#if creneau??>${creneau.date?string["HH:mm"]}</#if>">
                        <span class="input-group-addon"></span>
                    </div>
                </div>
                <div class="form-group">
                    <label for="titre">Capacité</label>
                    <div class="input-group">
                        <input type="text" name="capacite" class="form-control" value="<#if creneau??>${creneau.capacite}</#if>">
                        <span class="input-group-addon"></span>
                    </div>
                </div>
                <input type="submit" name="submit" id="submit" value="Valider" class="btn btn-info pull-left">
            </div>
        </form>
    </div>
</div>
</@html.page>