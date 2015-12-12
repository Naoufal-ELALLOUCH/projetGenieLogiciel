<#import "common.ftl" as html>

<@html.page>
<div class="container">
    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <h1 class="text-center login-title">Inscription</h1>
            <div class="account-wall">

                <form class="form-signin" method="post">
                    <input type="text" class="form-control" placeholder="Nom" name="nom" autofocus>
                    <br>
                    <input type="text" class="form-control" placeholder="Prénom" name="prenom" autofocus>
                    <br>
                    <input type="text" class="form-control" placeholder="Email" name="email" required autofocus>
	                <br>
                    <input type="password" class="form-control" placeholder="Mot de passe" name="motDePasse" required>
	                <br>
	                <span>Compte :</span>
	                <select class="form-control" name="type">
		                <option value="enseignant">Enseignant</option>
		                <option value="labo">Laboratoire</option>
	                </select>
                    <button class="btn btn-lg btn-primary btn-block" type="submit">Inscription</button>
                </form>
            </div>
        </div>
    </div>
</div>
</@html.page>