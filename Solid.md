changement : 

creation de classe : AppleFactory et PoisonousApple
afin d'adhérer au principe OCP

principe lsp :  il n'y a pas de violation du principe lsp dans ce code car il n'y a pas de hiérarchie de classe ou d'implémentation qui fasse en sorte que ce ne sois pas conforme au principe lsp

principe DIP : la classe main est responsable de l'instanciation des dépendances et de leur passage au contrôleur. Cela permet de déplacer la responsabilité de la création d'instances en dehors du contrôleur, ce qui est conforme au principe d'inversion de dépendance.



