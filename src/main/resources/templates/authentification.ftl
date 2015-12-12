<#import "common.ftl" as html>

<@html.page>
<div class="container">
    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <h1 class="text-center login-title">Authenfication</h1>
            <div class="account-wall">

				<#if erreur??>
		            <!-- pas le temps pour une classe désolé c'est moche -->
		            <p class="bg-danger" style="padding: 1em; margin: 2em 0">${erreur}</p>
				</#if>

                <form class="form-signin" method="post">
                    <input type="text" class="form-control" placeholder="Email" name="email" required autofocus>
	                <br>
                    <input type="password" class="form-control" placeholder="Mot de passe" name="motDePasse" required>
	                <br>
                    <button class="btn btn-lg btn-primary btn-block" type="submit">Connexion</button>
                </form>
            </div>
            <a href="/inscription" class="text-center new-account">Créer un compte</a>
        </div>
    </div>
</div>
</@html.page>