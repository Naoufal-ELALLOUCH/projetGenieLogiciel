# Projet IT340 : Génie Logiciel [![Build Status](https://travis-ci.org/Naoufal-ELALLOUCH/projetGenieLogiciel.svg?branch=master)](https://travis-ci.org/Naoufal-ELALLOUCH/projetGenieLogiciel) [![Coverage Status](https://coveralls.io/repos/Naoufal-ELALLOUCH/projetGenieLogiciel/badge.svg?branch=master&service=github)](https://coveralls.io/github/Naoufal-ELALLOUCH/projetGenieLogiciel?branch=master)

Ce répertoire GitHub s'inscrit dans le cadre du cours [IT340](http://www.labri.fr/perso/falleri/perso/ens/it340/) de [Jean-Rémy Fallery](http://www.labri.fr/perso/falleri/perso/) et a pour but de voir toutes les techniques usuelles qui permettent de mener à bien un projet de réalisation logicielle. Dans la pratique, nous nous sommes basés sur le [cahier des charges](http://www.labri.fr/perso/falleri/dist/ens/it340/cdc.pdf) du projet [Fête de la science](http://circuit-scientifique-bordelais.cnrs.fr).

## Phase de conception
#### Architecture
* [Architecture 3-tiers](doc/conception/architecture/archi-3-tiers.png)
* [Diagramme de base de données](doc/conception/architecture/database.pdf)
* [Diagramme d'activités](doc/conception/architecture/diagramme-activites.jpg)
* [Diagramme de classes](doc/conception/architecture/uml.png)

#### Exigences
* [User stories](doc/exigences/exigences.pdf)

## Phase de codage
#### Bibliothèques
* [Java Spark](http://sparkjava.com)
* [H2](http://www.h2database.com)
* [Freemarker](http://freemarker.incubator.apache.org)

## Phase de tests
#### Bibliothèques
* [JUnit](http://junit.org)
* [Mockito](http://mockito.org)
* [Cobertura](http://cobertura.github.io/cobertura/)

#### Outils
* [Travis](https://travis-ci.org/Naoufal-ELALLOUCH/projetGenieLogiciel)
* [Coveralls](https://travis-ci.org/Naoufal-ELALLOUCH/projetGenieLogiciel)

## Gestion de projet
* [Trello](https://trello.com/b/Moydo9fP/it340)
* [Github](https://github.com/Naoufal-ELALLOUCH/projetGenieLogiciel)
* Google Drive

## Manipulation du logiciel
#### Instructions
Pour lancer le logiciel, vous pouvez exécuter les commandes suivantes dans un terminal :
```sh
$ mvn clean package
$ mvn exec:java
```

#### Configuration
Plusieurs fichiers de configuration sont mis à disposition :

##### application.properties
```properties
# Port sur lequel le serveur sera lancé
port=4567
```

##### bdd.properties
```properties
# URL de la base de données de production
url=~/production
# Identifiants de la base de données
login=s
password=2
```
