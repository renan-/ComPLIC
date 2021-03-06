##ComPLIC
```
    Copyright 2015 Renan Strauss

    ComPLIC is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    ComPLIC is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with ComPLIC.  If not, see <http://www.gnu.org/licenses/>

```

Le langage PLIC est un langage qui se veut très simple (cf fonctionnalités actuelles).
Ce compilateur transforme le code PLIC en code assembleur MIPS.
La version de base de ComPLIC a été réalisée à l'IUT Nancy-Charlemagne, en 2ème année de DUT Informatique, dans le module Compilation.

##Description du langage

* Commentaires Ruby/Python style : \#
* Grammaire
	- ######Bloc
		```
			{ Declaration* Instruction+ } | => Instruction
		```

	- ######Types
		```
			| entier | booleen |
		```

	- ######Declaration
		```
			Type idf+ ;
		```

	- ######Instruction
		```
			| Affichage | Affectation | Condition | Iteration |
		```

	- ######Affichage
		```
			ecrire idf ;
		```

	- ######Affectation
		```
			idf := Expression ;
		```

	- ######Condition
		```
			| si Expression alors Bloc sinon Bloc | si Expression alors Bloc |
		```

	- ######Iteration
		```
			| tantque Expression repeter Bloc | pour idf dans Expression .. Expression repeter |
		```

	- ######Expression
		```
			| Terme | Terme { +|- Terme }+ |
		```

	- ######Terme
		```
			| Facteur | Facteur { *|/ FACTEUR }+ |
		```

	- ######Facteur
		```
			| idf | n e Z | ( Expression ) |
		```

	- ######Opérateurs logiques
		```
			| et | ou |
		```

	- ######Opérateurs de comparaison
		```
			| = | < | > | <= | >= | != |
		```

	- ######Opérateurs arithmétiques
		```
			| + | - | * | / |
		```

	- ###### Opérandes
		```
			| vrai | faux | n e Z | idf | ( Expression ) |
		```

Le fichier Test0.plic reprend toutes ces fonctionnalités.
Les autres fichiers testent differentes fonctionnalites : tantque, pour, boucles imbriquees, etc.

Pour lancer le code MIPS généré, téléchargez [le simulateur MARS](http://courses.missouristate.edu/KenVollmar/MARS/).
Avec mars.jar dans le repertoire, on peut dire au compilateur de lancer directement le programme MIPS avec MARS:

```
java plic.Main -exec FichierSource.plic
```

Sinon, pour generer out.mips:

```
java plic.Main FichierSource.plic
```

##Exemple de code PLIC

```
# # # # # # # # # # # # # # # # # # # #
#                                     #
# Exemple de programme ecrit en PLIC  #
#                                     #
# # # # # # # # # # # # # # # # # # # #

programme Exemple {

	#
	# Declarations
	#	

	entier a b c ;
	booleen d ;

	#
	# Instructions
	#

	a := 4 ;
	b := 5 ;

	# N'affiche rien

	si a > b
		alors {
			ecrire a ;
		}

	# Affiche les 10 premiers entiers positifs

	pour c dans 1 .. 10
		repeter => ecrire c ;

	# En affiche 10 de plus

	d := ( a = b ) ou vrai ;
	si d alors {
		tantque c <= 20
			repeter {
				ecrire c ;
				c := c + 1 ;
			}
	}
}
```