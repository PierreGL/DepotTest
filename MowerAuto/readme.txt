Description :
Ce programme permet de prendre en entrée un ou plusieurs fichiers définissant le parcours d'une tondeuse automatique sur un gazon.
Chaque fichier représente une opération, c'est à dire un gazon avec un ensemble de tondeuses ayant une position initiale et suivant chacune une série d'instructions.

Le programme va exécuter les instructions et afficher le résultat en français ou en anglais dans une console en respectant les règles de gestion suivantes :
-Une tondeuse ne peut pas être placée initialement en dehors du gazon.
-Une tondeuse ne peut pas sortir du gazon suite à une instruction.
-Une tondeuse ne peut pas être initiallement placée là où une autre tondeuse est déjà placée.
-Une tondeuse ne pas aller sur la position d'une tondeuse suite à une instruction.
-Excepter dans les cas pré-cités une tondeuse suit les instructions : A=avancer, G=Tourner à gauche, D=Tourner à droite.

Structure :
Ce projet est écrit en java 8 avec une structure Maven. 
Il intègre des tests unitaire avec la librairie JUnit 4, la journalisation avec log4j 2 
et l'internationalisation avec les librairies JRE.
Le projet est constitué des couches suivantes : "DAO" pour gérer l'accès aux fichier, "BUSINESS" pour la logique métier, "UI" pour l'interface homme machine..

Build :
Pour builder le projet installer maven 3 sur son poste. Se positionner à la racine du projet (dans le répertoire où se situe le pom.xml) 
et taper la commande "mvn install". Le projet est buildé à la condition que tout les tests unitaires s'exécutent.
Pour builder plus rapidement il y a la possibilité de taper "mvn install -P quick", les tests sont alors ignorés.
Un artefact MowerAutoLauncher.jar est créé dans le dossier target/
Cet artefact est l'exécutable du projet.

Executer :
Pour executer le projet il est nécessaire d'avoir une jre 1.8 sur son poste. Taper en ligne de commande "java -jar MowerAutoLauncher.jar".
La console s'ouvre et il est demandé de saisir le chemin du fichier de données.
Il est également possible de préciser directement le chemin du fichier en argument : 
"java -jar MowerAutoLauncher.jar <.../MonFichier.txt>".
Pour quitter saisir q dans la console.