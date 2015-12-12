<#import "common.ftl" as html>

<@html.page>
	<div class="container">
		<div class="row">
			<h1>${title}</h1>

			<form role="form" method="post">
				<div class="col-lg-6">
					<div class="form-group">
						<label for="titre">Titre</label>
						<div class="input-group">
							<input type="text" class="form-control" name="titre" id="titre" value="<#if atelier??>${atelier.titre}</#if>">
							<span class="input-group-addon"></span>
						</div>
					</div>
	                <div class="form-group">
	                    <label for="theme">Thème disciplinaire</label>
	                    <div class="input-group">
	                        <input type="text" class="form-control" name="themes" id="themes" value="<#if atelier??>${atelier.themes}</#if>">
	                        <span class="input-group-addon"></span>
	                    </div>
	                </div>
	                <div class="form-group">
	                    <label for="zone">Zone</label>
	                    <div class="input-group">
	                        <input type="text" class="form-control" name="zone" id="zone" value="<#if atelier??>${atelier.zone}</#if>">
	                        <span class="input-group-addon"></span>
	                    </div>
	                </div>
	                <div class="form-group">
	                    <label for="adresse">Adresse</label>
	                    <div class="input-group">
	                        <textarea  id="adresse" class="form-control" name="adresse" rows="2" value="<#if atelier??>${atelier.adresse}</#if>"></textarea>
	                        <span class="input-group-addon"></span>
	                    </div>
	                </div>
	                <div class="form-group">
	                    <label for="animateurs">Animateurs/Conférenciers</label>
	                    <div class="input-group">
	                        <input type="text" class="form-control" name="orateurs" id="animateurs" value="<#if atelier??>${atelier.orateurs}</#if>">
	                        <span class="input-group-addon"></span>
	                    </div>
	                </div>
	                <div class="form-group">
	                    <label for="partenaires">Partenaires</label>
	                    <div class="input-group">
	                        <input type="text" class="form-control" name="partenaires" id="partenaires" value="<#if atelier??>${atelier.partenaires}</#if>">
	                        <span class="input-group-addon"></span>
	                    </div>
	                </div>
	                <div class="form-group">
	                    <label for="cibles">Public visé</label>
	                    <div class="input-group">
	                        <input type="text" class="form-control" name="cible" id="cible" value="<#if atelier??>${atelier.cible}</#if>">
	                        <span class="input-group-addon"></span>
	                    </div>
	                </div>
	                <div class="form-group">
	                    <label for="remarques">Remarques</label>
	                    <div class="input-group">
	                        <input type="text" class="form-control" name="remarques" id="remarques" value="<#if atelier??>${atelier.remarques}</#if>">
	                        <span class="input-group-addon"></span>
	                    </div>
	                </div>
	                <input type="submit" name="submit" id="submit" value="Valider" class="btn btn-info pull-left">
				</div>
			</form>
		</div>
	</div>
</@html.page>