# L3: Projet GLA

# Assistant évènementiel de sorties à Paris

Le projet de génie logiciel est un module commun aux parcours informatique et MIAGE. Il a pour but et mettre en oeuvre et d'appliquer les connaissances acquises du module génie logiciel avancé sur des exemples plus conséquents.

# Projet
# Contexte

Plusieurs amis veulent sortir à Paris afin de fêter leurs retrouvailles et ont envie de passer une bonne soirée. Pour cela, ils auraient besoin d'organiser successivement trois sorties

Boire un verre dans un bar sympa
Manger au restaurant (attention: un des amis n'aime pas les kebabs!)
Sortir en boîte de nuit
# Exigences
Le projet consiste à fournir une interface avec en:

Entrée: une date, une liste de personnes ayant des contraintes d'adresses (là où ils habitent) et de préférences alimentaires
Résultat: une liste chronologique de trois adresses (bar, resto, boîte)


# Execution
Vous pouvez executer l'application soit :

1- En executant le fichier Jar ( salsalida.jar ) avec la commande suivante depuis le terminal:

java - jar salsalida.jar 

2- En executant la classe principale ( Geolocalisation.java ) depuis votre environnement de travail ( eclipse par exemple ), en clonant le projet.r
# Fonctionnement
L'utilisateur saisit les informations concernant l'évenement ( les horaires et la date , ), les informations concernant les participants ( leurs adressses )  il choisie dans une liste le type de restaurant ou il veulent diner , ces informations seront ajoutés à son profil (de par la classe profil.
Celui-ci a le choix de chercher un Trio ( bar, restaurant et boite de nuit ) ou de chercher un autre  tel que : theâtre, cinéma, musée, hôtel, spectacle, karaoke, parc ...

L'application calcule le point de rassemenblement optimal pour tous les participants et renvoie les établissements corresponds qui sont autours, suivant les differentes contraintes.
