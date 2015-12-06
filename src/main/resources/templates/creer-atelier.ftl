<#import "common.ftl" as html>

<@html.page>
	<div class="container">
		<div class="row">
			<h1>Créer un atelier</h1>

			<form role="form" method="post">
				<div class="col-lg-6">
					<div class="form-group">
						<label for="titre">Titre</label>
						<div class="input-group">
							<input type="text" class="form-control" name="titre" id="titre">
							<span class="input-group-addon"></span>
						</div>
					</div>
	                <div class="form-group">
	                    <label for="theme">Thème disciplinaire</label>
	                    <div class="input-group">
	                        <input type="text" class="form-control" id="theme">
	                        <span class="input-group-addon"></span>
	                    </div>
	                </div>
	                <div class="form-group">
	                    <label for="zone">Zone</label>
	                    <div class="input-group">
	                        <input type="text" class="form-control" id="zone">
	                        <span class="input-group-addon"></span>
	                    </div>
	                </div>
	                <div class="form-group">
	                    <label for="adresse">Adresse</label>
	                    <div class="input-group">
	                        <textarea  id="adresse" class="form-control" rows="2"></textarea>
	                        <span class="input-group-addon"></span>
	                    </div>
	                </div>
	                <div class="form-group">
	                    <label for="animateurs">Animateurs/Conférenciers</label>
	                    <div class="input-group">
	                        <input type="text" class="form-control" id="animateurs">
	                        <span class="input-group-addon"></span>
	                    </div>
	                </div>
	                <div class="form-group">
	                    <label for="partenaires">Partenaires</label>
	                    <div class="input-group">
	                        <input type="text" class="form-control" id="partenaires">
	                        <span class="input-group-addon"></span>
	                    </div>
	                </div>
	                <div class="form-group">
	                    <label for="cibles">Public visé</label>
	                    <div class="input-group">
	                        <input type="text" class="form-control" id="cibles">
	                        <span class="input-group-addon"></span>
	                    </div>
	                </div>
	                <div class="form-group">
	                    <label for="remarques">Remarques</label>
	                    <div class="input-group">
	                        <input type="text" class="form-control" id="remarques">
	                        <span class="input-group-addon"></span>
	                    </div>
	                </div>
	                <input type="submit" name="submit" id="submit" value="Valider" class="btn btn-info pull-left">
				</div>
			</form>
		</div>
	</div>
</@html.page>