<#macro page>
    <html>
        <head>
	        <title>${title}</title>
	        <meta charset="utf-8">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
            <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	        <style>
		        .navbar {
			        margin-bottom: 0;
			        border-radius: 0;
		        }

		        .row-content {height: 450px;}
	        </style>
        </head>
        <body>
			<nav class="navbar navbar-inverse">
				<div class="container-fluid">
					<div class="navbar-header">
						<a class="navbar-brand" href="#" alt="Logo">Fête de la science</a>
					</div>
					<div class="collapse navbar-collapse" id="myNavBar">
						<ul class="nav navbar-nav">
							<li>
								<a href="/" alt="Accueil">Accueil</a>
							</li>
                            <li>
                                <a href="/ateliers" alt="Ateliers">Ateliers</a>
                            </li>
							<#if labo?? && labo>
	                            <li>
	                                <a href="/laboratoire/ateliers" alt="Mes ateliers">Mes ateliers</a>
	                            </li>
							<#elseif enseignant?? && enseignant>
                                <li>
                                    <a href="/enseignant" alt="Mes enregistrements">Mes enregistrements</a>
                                </li>
							</#if>
						</ul>
						<ul class="nav navbar-nav navbar-right">
							<li>
								<#if connected?? && connected>
                                    <a href="/deconnexion" alt="Déconnexion">
                                        <span class="glyphicon glyphicon-log-out"></span>
                                        Déconnexion
                                    </a>
								<#else>
                                    <a href="/authentification" alt="Connexion">
                                        <span class="glyphicon glyphicon-log-in"></span>
                                        Connexion
                                    </a>
								</#if>
							</li>
						</ul>
					</div>
				</div>
			</nav>

            <#nested>
        </body>
    </html>
</#macro>