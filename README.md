# 🔐 Jeu de devinette de code secret (Java)

Ce projet est un jeu en console développé en Java dans lequel le joueur doit deviner un code PIN à 4 chiffres généré aléatoirement. Après chaque tentative, le jeu fournit des indices pour aider le joueur à trouver le bon code.

---

## 🎮 Fonctionnement

- Un code secret de 4 chiffres (de 0 à 9) est généré aléatoirement.
- Le joueur dispose de **10 essais** pour deviner le code.
- Après chaque essai, le jeu affiche un retour :
  - `X est correct et bien placé` — le chiffre est correct et bien positionné
  - `X est correct mais mal placé` — le chiffre est correct mais mal positionné
  - `X est incorrect` — le chiffre ne fait pas partie du code
- L’essai est affiché sous forme masquée (ex : `1*3*`) pour indiquer les chiffres bien placés.

---

## 🧑‍💻 Comment jouer

1. Lancer le programme.
2. Saisir un code à 4 chiffres sans espaces (ex : `1234`).
3. Utiliser les indices pour affiner la prochaine tentative.
4. Gagner en trouvant le bon code avant la fin des 10 essais !

---

## 🛠 Fonctionnalités

- Génération aléatoire du code
- Validation des entrées (exactement 4 chiffres, pas de lettres ou symboles)
- Système d’indices :
  - Chiffres bien placés
  - Chiffres mal placés
  - Chiffres incorrects
- Affichage masqué de l’essai
- Compteur d’essais avec nombre restant

---

## 📦 Prérequis

- Java 8 ou version supérieure
- Aucun framework ou bibliothèque externe requis

---

## 🚀 Lancer le jeu

Compiler et exécuter :

```bash
javac Main.java
java Main
