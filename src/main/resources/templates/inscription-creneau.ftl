<#import "common.ftl" as html>

<@html.page>
<div class="container">
    <div class="row">
        <h1>${title}</h1>

        <form role="form" method="post">
            <div class="col-lg-6">
                <div class="form-group">
                    <label for="titre">Nombre de places à réserver</label>
                    <div class="input-group">
                        <input type="text" name="nbInscrits" class="form-control">
                        <span class="input-group-addon"></span>
                    </div>
                </div>
                <input type="submit" name="submit" id="submit" value="Valider" class="btn btn-info pull-left">
            </div>
        </form>
    </div>
</div>
</@html.page>