changement : 

principe ocp : 

creation de classe : AppleFactory et PoisonousApple
afin d'adhérer au principe OCP

principe lsp :  il n'y a pas de violation du principe lsp dans ce code car il n'y a pas de hiérarchie de classe ou d'implémentation qui fasse en sorte que ce ne sois pas conforme au principe lsp

principe DIP : la classe main est responsable de l'instanciation des dépendances et de leur passage au contrôleur. Cela permet de déplacer la responsabilité de la création d'instances en dehors du contrôleur, ce qui est conforme au principe d'inversion de dépendance.

principe de l'isp ( pas reussi ) : ce que j'aurai pu mettre en place afin que mon code respecte le principe de l'isp au été tout d'abord de séparer Imodel en ISnakeModel et IAppleModel 

ISnakeModel aurait contenue les méthodes spécifiques a la gestions des serpents comme (changePlayerDirection, getAiSnake, getPlayerSnake)

IAppleModel aurait contenue les méthodes spécifiques à la gestion des pommes (getApples, positionApples)

j'aurai du implémenter dans model toutes les méthodes mise dans IsnakeModel et IAppleModel cela aurait permis a model de se conformer a l'ISP

pour ce qui est du controller j'aurai du placer dans son constructeur ISnakeModel et IAppleModel afin de pouvoir interagir avec le serpent et les pommes dans le jeu

dans main j'aurai crée une instance de model afin de passer ISnakeModel et IAppleModel au controller



