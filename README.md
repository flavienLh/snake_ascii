# ASCII SNAKE
Création d'un Snake en ASCII.

## Règle du jeu :
- deux serpents se déplacent sur une grille.
- leur déplacement est régulier et automatique.
- un serpent peut se déplacer dans les 4 directions, cependant il ne peut que changer de direction à 90°. Il ne peut donc aller qu'à gauche ou à droite. Tout dépend de sa direction.
- un serpent peut se déplacer partout sur la grille tant qu'il ne se mord pas la queue ou celle de l'autre serpent.
- un serpent a une taille initiale de 3 cases.
- des pommes apparaissent à intervalles réguliers sur la grille.
- les pommes ont une valeur de 1 à 9.
- un serpent peut manger les pommes et grandit du nombre de cases correspondant à la valeur de la pomme.
- certaines pommes sont empoisonnées et font rétrécir le serpent.
- les pommes empoisonnées ont une valeur de -1 à -9.
- un serpent peut manger les pommes empoisonnées et rétrécit du nombre de cases correspondant à la valeur de la pomme.
- l'un des serpents est contrôlé par le joueur.
- l'autre serpent est contrôlé par l'ordinateur.
- la grille est une sphère. Cela veut dire que sortir à droite de l'écran fait réapparaître le serpent à gauche de l'écran et que sortir en haut de l'écran fait réapparaître le serpent en bas de l'écran.
- le jeu s'arrête :
  - si le serpent joueur se mord la queue ou celle de l'ordinateur.
  - si un serpent a une taille de 0.
  - si un serpent atteint une taille de 100.
  
## Caractères ASCII à utiliser :
- `█` : serpent joueur
- `░` : serpent ordinateur
- `<`, `>`, `^`, `v` : tête de serpent (en fonction de sa direction)
- `1`, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9` : pommes en fonction de leur valeur
- `A`, `B`, `C`, `D`, `E`, `F`, `G`, `H`, `I` : pommes empoisonnées en fonction de leur valeur
- ` ` : case vide

## Touche de contrôle du serpent joueur :
- `q` : gauche
- `d` : droite

## Projet de départ :
- un projet maven vous est fourni comme base de travail.
- il est architecturé en MVC.
- il contient une classe GameWindow qui permet d'afficher une grille de jeu ainsi que la capture des touches du clavier.
- vous pouvez totalement le modifier si vous le souhaitez.
- voici le diagramme de classes de ce projet :
- ![Diagramme de classes du projet de départ](Class%20Diagram%20-%20AsciiSnake.png)

## Travail à rendre :
- un programme fonctionnel avec la dernière version dans la branche `master` du dépôt.
- un fichier .VPP incluant le diagramme de classes de la solution proposée. Si vous trouvez utile de proposer d'autres diagrammes, vous pouvez les inclure dans le même fichier.

## Evaluation :
- vendredi 09/06/2023 après-midi, chaque groupe présentera son projet.
- je vous poserai des questions sur votre code et votre architecture.
- si vous ne parvenez pas à implémenter toutes les fonctionnalités, ce sera malgré tout très bien, j'en suis certain. L'important est de montrer que vous avez compris les concepts et que vous avez essayé de les mettre en œuvre.

## Constitution des équipes :
- 3 personnes maximum par équipe.
- les équipes sont libres.

## Conseils :
- commencez par faire simple.
- commencez par penser votre architecture avant de coder.
- commencez avec un ordinateur crétin qui se déplace aléatoirement.
- mettez les pommes de côté au début.
- faîtes du pair programming.
- Pensez objet !
- Pensez Java !
- Pensez MVC !
- Pensez SOLID !
- Pensez DRY !
- Pensez KISS !
- Pensez GoF !
- Pensez TDD !
