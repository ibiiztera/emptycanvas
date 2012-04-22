/*
================================================================================
  RPL/2 (R) version 4.1.0.prerelease.0
  Copyright (C) 1989-2011 Dr. BERTRAND Joël

  This file is part of RPL/2.

  RPL/2 is free software; you can redistribute it and/or modify it
  under the terms of the CeCILL V2 License as published by the french
  CEA, CNRS and INRIA.
 
  RPL/2 is distributed in the hope that it will be useful, but WITHOUT
  ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
  FITNESS FOR A PARTICULAR PURPOSE.  See the CeCILL V2 License
  for more details.
 
  You should have received a copy of the CeCILL License
  along with RPL/2. If not, write to info@cecill.info.
================================================================================
*/


#include "rpl-conv.h"


/*
================================================================================
  Analyseur syntaxique d'une expression algébrique
================================================================================
  Entrées : chaîne de caractères comprenant l'expression algébrique
--------------------------------------------------------------------------------
  Sorties : chaîne de caractères contenant l'expression convertie en
    notation polonaise inverse et liste chaînée contenant les diverses
	fonctions.
--------------------------------------------------------------------------------
  Effets de bord : néant
================================================================================
*/

unsigned char *
analyse_algebrique(struct_processus *s_etat_processus,
		unsigned char *chaine_algebrique, struct_liste_chainee **l_base_liste)
{
	struct_fonction						*s_fonction;

	struct_liste_chainee				*l_element_courant;

	logical1							chaine_invalide;
	logical1							drapeau_debut_zone_valide;
	logical1							drapeau_elimination_parentheses;
	logical1							drapeau_fin_boucle;
	logical1							drapeau_fin_zone_valide;
	logical1							drapeau_modification;
	logical1							drapeau_priorite_entierement_traitee;
	logical1							fin_boucle_extraction;
	logical1							presence_chaine;
	logical1							presence_fonction;
	logical1							presence_liste;

	long								k;

	unsigned char						*chaine_arguments;
	unsigned char						*chaine_centrale;
	unsigned char						*chaine_droite;
	unsigned char						*chaine_fonction;
	unsigned char						*chaine_gauche;
	unsigned char						*chaine_travail;
	unsigned char						*epilogue;
	unsigned char						instruction_test[6];
	unsigned char						*instruction_majuscule;
	unsigned char						*prologue;
	unsigned char						*ptr1;
	unsigned char						*ptr2;
	unsigned char						*registre_instruction_courante;
	unsigned char						registre_instruction_valide;
	unsigned char						registre_test;
	unsigned char						*sous_chaine_droite;
	unsigned char						*sous_chaine_gauche;
	unsigned char						t0;
	unsigned char						t1;
	unsigned char						t2;
	unsigned char						t3;
	unsigned char						t4;
	unsigned char						*tampon;

	unsigned long						debut_zone_algebrique;
	unsigned long						fin_zone_algebrique;
	unsigned long						i;
	unsigned long						j;
	unsigned long						longueur_chaine;
	unsigned long						longueur_tampon;
	unsigned long						niveau;
	unsigned long						niveau_liste;
	unsigned long						nombre_apostrophes;
	unsigned long						nombre_arguments;
	unsigned long						priorite;

	(*l_base_liste) = NULL;

	/*
	 * Vérification de la chaîne. Celle-ci doit comporter au moins un
	 * caractère entre les délimiteurs ''.
	 */

	presence_chaine = d_faux;
	presence_liste = d_faux;
	niveau_liste = 0;

	for(i = 1, chaine_invalide = d_vrai; i < strlen(chaine_algebrique) - 1; i++)
	{
		if (chaine_algebrique[i] != ' ')
		{
			chaine_invalide = d_faux;
		}

		if (chaine_algebrique[i] == '"')
		{
			presence_chaine = (presence_chaine == d_faux) ? d_vrai : d_faux;
		}
		else if (presence_chaine == d_faux)
		{
			if (chaine_algebrique[i] == '{')
			{
				presence_liste = d_vrai;
				niveau_liste++;
			}
			else if (chaine_algebrique[i] == '}')
			{
				presence_liste = d_vrai;
				niveau_liste--;
			}
		}
	}

	if ((chaine_invalide == d_vrai) || (presence_chaine == d_vrai) ||
			(niveau_liste != 0) || (presence_liste == d_vrai))
	{
		(*s_etat_processus).erreur_execution = d_ex_expression_invalide;
		return(NULL);
	}

	/*
	 * Transformation des "**" en "^ "
	 */

	for(i = 1; i < strlen(chaine_algebrique) - 1; i++)
	{
		if (chaine_algebrique[i] == '*')
		{
			if (chaine_algebrique[i + 1] == '*')
			{
				chaine_algebrique[i++] = '^';
				chaine_algebrique[i] = ' ';
			}
		}
	}

	if ((chaine_travail = (unsigned char *) malloc((strlen(chaine_algebrique) +
			1) * sizeof(unsigned char))) == NULL)
	{
		(*s_etat_processus).erreur_systeme = d_es_allocation_memoire;
		return(NULL);
	}

	strcpy(chaine_travail, chaine_algebrique);

	/*
	 * Retrait des espaces dans l'expression algébrique
	 */

	ptr1 = chaine_travail;
	ptr2 = chaine_travail;
	presence_chaine = d_faux;

	while((*ptr1) != d_code_fin_chaine)
	{
		if ((*ptr1) == '"')
		{
			if (presence_chaine == d_faux)
			{
				presence_chaine = d_vrai;
			}
			else
			{
				presence_chaine = d_faux;
			}
		}

		if (presence_chaine == d_faux)
		{
			if ((*ptr1) != ' ')
			{
				(*(ptr2++)) = (*ptr1);
			}
			else
			{
				instruction_test[5] = d_code_fin_chaine;

				instruction_majuscule = conversion_majuscule(strncpy(
						instruction_test, ptr1, 5));

				if (instruction_majuscule == NULL)
				{
					(*s_etat_processus).erreur_systeme =
							d_es_allocation_memoire;
					return(NULL);
				}

				/*
				 * Repérer le premier espace ou la fin de la chaîne.
				 */

				if (strcmp(instruction_majuscule, " AND ") == 0)
				{
					for(i = 0; i < 4; (*(ptr2++)) = (*(ptr1++)), i++);
					(*(ptr2++)) = (*ptr1);
				}
				else if (strcmp(instruction_majuscule, " XOR ") == 0)
				{
					for(i = 0; i < 4; (*(ptr2++)) = (*(ptr1++)), i++);
					(*(ptr2++)) = (*ptr1);
				}
				else if ((strncmp(instruction_majuscule, " OR ", 4) == 0) &&
						(strlen(instruction_majuscule) == 4))
				{
					for(i = 0; i < 3; (*(ptr2++)) = (*(ptr1++)), i++);
					(*(ptr2++)) = (*ptr1);
				}

				free(instruction_majuscule);
			}
			
			ptr1++;
		}
		else
		{
			(*(ptr2++)) = (*(ptr1++));
		}
	}

	(*ptr2) = d_code_fin_chaine;

	do
	{
		i = 0;
		drapeau_modification = d_faux;

		do
		{
			presence_chaine = d_faux;
			drapeau_debut_zone_valide = d_faux;
			debut_zone_algebrique = 0;
			drapeau_fin_boucle = d_faux;

			do
			{
				if (chaine_travail[i] == d_code_fin_chaine)
				{
					drapeau_fin_boucle = d_vrai;
				}
				else if ((chaine_travail[i] == '\'') &&
						(presence_chaine == d_faux))
				{
					drapeau_fin_boucle = d_vrai;
					debut_zone_algebrique = i;
					drapeau_debut_zone_valide = d_vrai;
				}
				else if (chaine_travail[i] == '"')
				{
					presence_chaine = (presence_chaine == d_vrai)
							? d_faux : d_vrai;
				}

				i++;
			} while(drapeau_fin_boucle == d_faux);

			presence_chaine = 0;
			drapeau_fin_zone_valide = d_faux;

			if (drapeau_debut_zone_valide == d_vrai)
			{
				j = debut_zone_algebrique + 1;
			}
			else
			{
				j = 0;
			}

			fin_zone_algebrique = 0;
			drapeau_fin_boucle = d_faux;

			do
			{
				if (chaine_travail[j] == 0)
				{
					drapeau_fin_boucle = d_vrai;
				}

				if ((chaine_travail[j] == '\'') && (presence_chaine == d_faux))
				{
					drapeau_fin_boucle = d_vrai;
					fin_zone_algebrique = j;
					drapeau_fin_zone_valide = d_vrai;
				}

				if (chaine_travail[j] == '"')
				{
					presence_chaine = (presence_chaine == d_vrai)
							? d_faux : d_vrai;
				}

				j++;
			} while(drapeau_fin_boucle == d_faux);

			if ((drapeau_debut_zone_valide == d_vrai) &&
					(drapeau_fin_zone_valide == d_vrai))
			{
				chaine_gauche = purification_chaine(
						extraction_chaine(chaine_travail,
						1, debut_zone_algebrique));
				chaine_centrale = purification_chaine(
						extraction_chaine(chaine_travail,
						debut_zone_algebrique + 1, fin_zone_algebrique + 1));
				chaine_droite = purification_chaine(
						extraction_chaine(chaine_travail,
						fin_zone_algebrique + 2, strlen(chaine_travail)));

				free(chaine_travail);

				if ((chaine_gauche == NULL) || (chaine_centrale == NULL) ||
						(chaine_droite == NULL))
				{
					(*s_etat_processus).erreur_systeme =
							d_es_allocation_memoire;
					return(NULL);
				}

				if ((strcmp(chaine_centrale, "''") == 0) ||
						(strcmp(chaine_centrale, "'()'") == 0))
				{
					free(chaine_gauche);
					free(chaine_centrale);
					free(chaine_droite);

					(*s_etat_processus).erreur_execution =
							d_ex_expression_invalide;
					return(NULL);
				}

				i = 1;
				niveau = 0;
				drapeau_elimination_parentheses = d_vrai;
				presence_chaine = d_faux;

				while(i < (strlen(chaine_centrale) - 1))
				{
					if (chaine_centrale[i] == '"')
					{
						presence_chaine = (presence_chaine == d_faux)
								? d_vrai : d_faux;

						if (i == 1)
						{
							drapeau_elimination_parentheses = d_faux;
						}
					}
					else if (presence_chaine == d_faux)
					{
						if (chaine_centrale[i] == '(')
						{
							niveau++;
						}

						if ((niveau == 0) || ((((test_cfsf(s_etat_processus,
								48) == d_vrai) && (chaine_centrale[i] ==
								'.')) || ((test_cfsf(s_etat_processus, 48)
								== d_faux) && (chaine_centrale[i] == ',')))
								&& (niveau == 1)))
						{
							drapeau_elimination_parentheses = d_faux;
						}

						if (chaine_centrale[i] == ')')
						{
							niveau--;
						}
					}

					i++;
				}

				if (drapeau_elimination_parentheses == d_vrai)
				{
					tampon = chaine_centrale;

					if ((chaine_centrale = (unsigned char *) malloc(
							(strlen(tampon) - 1) * sizeof(unsigned char)))
							== NULL)
					{
						(*s_etat_processus).erreur_systeme =
								d_es_allocation_memoire;
						return(NULL);
					}

					tampon[strlen(tampon) - 2] = d_code_fin_chaine;

					sprintf(chaine_centrale, "'%s'", &(tampon[2]));
					free(tampon);

					fin_zone_algebrique -= 2;
					drapeau_modification = d_vrai;
				}

				if ((test_expression_rpn(chaine_centrale) == d_vrai) &&
						(fin_zone_algebrique - debut_zone_algebrique > 0))
				{
					if ((tampon = purification_chaine(
							extraction_chaine(chaine_centrale, 2,
							strlen(chaine_centrale) - 1))) == NULL)
					{
						(*s_etat_processus).erreur_systeme =
								d_es_allocation_memoire;
						return(NULL);
					}

					/*
					 * Si on tombe sur une fonction intrinsèque ou
					 * extrinsèque, il doit y avoir des arguments passés
					 * entre parenthèses et on ne doit pas passer par ici !
					 */

					registre_instruction_courante = (*s_etat_processus)
							.instruction_courante;
					registre_test = (*s_etat_processus).test_instruction;
					registre_instruction_valide = (*s_etat_processus)
							.instruction_valide;

					(*s_etat_processus).test_instruction = 'Y';
					(*s_etat_processus).instruction_courante = tampon;

					analyse(s_etat_processus, NULL);

					(*s_etat_processus).test_instruction = registre_test;
					(*s_etat_processus).instruction_courante =
							registre_instruction_courante;

					if (((*s_etat_processus).instruction_valide == 'Y') &&
							((*s_etat_processus).constante_symbolique == 'N'))
					{
						free(chaine_gauche);
						free(chaine_centrale);
						free(chaine_droite);
						free(tampon);

						(*s_etat_processus).instruction_valide =
								registre_instruction_valide;

						(*s_etat_processus).erreur_execution =
								d_ex_expression_invalide;
						return(NULL);
					}

					(*s_etat_processus).instruction_valide =
							registre_instruction_valide;

					free(chaine_centrale);
					chaine_centrale = tampon;

					fin_zone_algebrique--;
					drapeau_modification = d_vrai;
				}
				else if ((test_fonction(chaine_centrale) == d_vrai) &&
						(fin_zone_algebrique - debut_zone_algebrique > 0))
				{
					i = 1;
					while((i < (strlen(chaine_centrale) - 1)) &&
							(chaine_centrale[i] != '('))
					{
						i++;
					}

					j = strlen(chaine_centrale) - 1;
					while(chaine_centrale[j] != ')')
					{
						j--;
					}

					chaine_fonction = purification_chaine(
							extraction_chaine(chaine_centrale, 2, i));
					chaine_arguments = purification_chaine(
							extraction_chaine(chaine_centrale, i + 2, j));

					i = 0;
					niveau = 0;
					nombre_arguments = 1;

					while(chaine_arguments[i] != d_code_fin_chaine)
					{
						if (chaine_arguments[i] == '(')
						{
							niveau++;
						}

						if (chaine_arguments[i] == ')')
						{
							niveau--;
						}

						if ((chaine_arguments[i] == ',') && (niveau == 0))
						{
							sous_chaine_gauche = purification_chaine(
									extraction_chaine(chaine_arguments, 1, i));
							sous_chaine_droite = purification_chaine(
									extraction_chaine(chaine_arguments, i + 2,
									strlen(chaine_arguments)));

							free(chaine_arguments);

							if ((chaine_arguments = (unsigned char *) malloc(
									(strlen(sous_chaine_gauche) + strlen(
									sous_chaine_droite) + 3 + 1) * sizeof(
									unsigned char))) == NULL)
							{
								(*s_etat_processus).erreur_systeme =
										d_es_allocation_memoire;
								return(NULL);
							}

							sprintf(chaine_arguments, "%s' '%s",
									sous_chaine_gauche, sous_chaine_droite);
							i += 2;

							free(sous_chaine_gauche);
							free(sous_chaine_droite);

							nombre_arguments++;
						}

						i++;
					}

					free(chaine_centrale);

					l_element_courant = (*l_base_liste);
					presence_fonction = d_faux;

					while((l_element_courant != NULL) &&
							(presence_fonction == d_faux))
					{
						if (strcmp((*((struct_fonction *) ((*l_element_courant)
								.donnee))).nom_fonction, chaine_fonction) == 0)
						{
							presence_fonction = d_vrai;
						}
						else
						{
							l_element_courant = (*l_element_courant).suivant;
						}
					}

					if (presence_fonction == d_vrai)
					{
						if ((*((struct_fonction *)
								((*l_element_courant).donnee)))
								.nombre_arguments != nombre_arguments)
						{
							(*s_etat_processus).erreur_execution =
									d_ex_nombre_arguments;

							free(chaine_arguments);
							free(chaine_fonction);
							free(chaine_gauche);
							free(chaine_droite);

							return(NULL);
						}
					}
					else
					{
						registre_instruction_courante = (*s_etat_processus)
								.instruction_courante;
						registre_test = (*s_etat_processus).test_instruction;
						registre_instruction_valide = (*s_etat_processus)
								.instruction_valide;

						(*s_etat_processus).test_instruction = 'Y';
						(*s_etat_processus).instruction_courante =
								chaine_fonction;

						analyse(s_etat_processus, NULL);

						(*s_etat_processus).test_instruction = registre_test;
						(*s_etat_processus).instruction_courante =
								registre_instruction_courante;
						(*s_etat_processus).instruction_valide =
								registre_instruction_valide;

						if (((unsigned long) (*s_etat_processus)
								.nombre_arguments != nombre_arguments) &&
								((*s_etat_processus).nombre_arguments != -2))
						{
							(*s_etat_processus).erreur_execution =
									d_ex_nombre_arguments_fonction;

							free(chaine_arguments);
							free(chaine_fonction);
							free(chaine_gauche);
							free(chaine_droite);

							return(NULL);
						}

						if ((l_element_courant = (struct_liste_chainee *)
								malloc(sizeof(struct_liste_chainee))) == NULL)
						{
							(*s_etat_processus).erreur_systeme =
									d_es_allocation_memoire;
							return(NULL);
						}

						(*l_element_courant).suivant = (*l_base_liste);
						(*l_base_liste) = l_element_courant;

						if ((s_fonction = malloc(sizeof(struct_fonction)))
								== NULL)
						{
							(*s_etat_processus).erreur_systeme =
									d_es_allocation_memoire;
							return(NULL);
						}

						if (((*s_fonction).nom_fonction = (unsigned char *)
								malloc((strlen(chaine_fonction) + 1) *
								sizeof(unsigned char))) == NULL)
						{
							(*s_etat_processus).erreur_systeme =
									d_es_allocation_memoire;
							return(NULL);
						}

						strcpy((*s_fonction).nom_fonction, chaine_fonction);
						(*s_fonction).nombre_arguments = nombre_arguments;

						(*(*l_base_liste)).donnee = (void *) s_fonction;
					}

					if ((chaine_centrale = (unsigned char *) malloc((strlen(
							chaine_arguments) + 1 + strlen(chaine_fonction)
							+ 1 + 2) * sizeof(unsigned char))) == NULL)
					{
						(*s_etat_processus).erreur_systeme =
								d_es_allocation_memoire;
						return(NULL);
					}

					sprintf(chaine_centrale, "'%s' %s", chaine_arguments,
							chaine_fonction);
					drapeau_modification = d_vrai;

					free(chaine_arguments);
					free(chaine_fonction);
				}
				else if ((chaine_centrale[1] == '+') ||
						(chaine_centrale[1] == '-'))
				{
					if (chaine_centrale[1] == '-')
					{
						tampon = chaine_centrale;

						if ((chaine_centrale = (unsigned char *) malloc(
								(strlen(tampon) + 5) * sizeof(unsigned char)))
								== NULL)
						{
							(*s_etat_processus).erreur_systeme =
									d_es_allocation_memoire;
							return(NULL);
						}

						tampon[strlen(tampon) - 1] = d_code_fin_chaine;
						sprintf(chaine_centrale, "'NEG(%s)'", &(tampon[2]));
						fin_zone_algebrique += 5;
						drapeau_modification = d_vrai;

						free(tampon);
					}
					else
					{
						tampon = chaine_centrale;

						if ((chaine_centrale = (unsigned char *) malloc(
								(strlen(tampon) + 7) * sizeof(unsigned char)))
								== NULL)
						{
							(*s_etat_processus).erreur_systeme =
									d_es_allocation_memoire;
							return(NULL);
						}

						tampon[strlen(tampon) - 1] = d_code_fin_chaine;
						sprintf(chaine_centrale, "'RELAX(%s)'", &(tampon[2]));
						fin_zone_algebrique += 7;
						drapeau_modification = d_vrai;

						free(tampon);
					}
				}

				if ((chaine_travail = (unsigned char *) malloc(
						(strlen(chaine_gauche) + strlen(chaine_centrale) +
						strlen(chaine_droite) + 1 + 2) * sizeof(unsigned char)))
						== NULL)
				{
					(*s_etat_processus).erreur_systeme =
							d_es_allocation_memoire;
					return(NULL);
				}

				sprintf(chaine_travail, "%s %s %s", chaine_gauche,
						chaine_centrale, chaine_droite);

				free(chaine_gauche);
				free(chaine_centrale);
				free(chaine_droite);
			}

			i = fin_zone_algebrique + 1;
		} while((drapeau_debut_zone_valide == d_vrai)
				&& (drapeau_fin_zone_valide == d_vrai));

		for(longueur_chaine = strlen(chaine_travail),
				i = nombre_apostrophes = 0; i < longueur_chaine;
				nombre_apostrophes += (chaine_travail[i++] == '\'') ? 1 : 0);

		if (nombre_apostrophes != 0)
		{
			priorite = 1;

			do
			{
				drapeau_priorite_entierement_traitee = d_vrai;

				i = 0;
				while((chaine_travail[i] != '\'') && (chaine_travail[i] != 0))
				{
					i++;
				}

				if (chaine_travail[i] == 0)
				{
					i = 0;
				}

				j = i + 1;
				while((chaine_travail[j] != '\'') && (chaine_travail[j] != 0))
				{
					j++;
				}

				if (chaine_travail[j] == 0)
				{
					j = 0;
				}

				if ((chaine_travail[i] != 0) && (j != 0))
				{
					chaine_gauche = purification_chaine(
							extraction_chaine(chaine_travail, 1, i));
					chaine_centrale = purification_chaine(
							extraction_chaine(chaine_travail,
							i + 1, j + 1));
					chaine_droite = purification_chaine(
							extraction_chaine(chaine_travail, j + 2,
							strlen(chaine_travail)));

					if ((chaine_gauche == NULL) || (chaine_centrale == NULL) ||
							(chaine_droite == NULL))
					{
						(*s_etat_processus).erreur_systeme =
								d_es_allocation_memoire;
						return(NULL);
					}

					drapeau_elimination_parentheses = d_vrai;

					if ((longueur_tampon = strlen(chaine_centrale)) != 0)
					{
						niveau = 0;

						for(i = 1; i < longueur_tampon - 1; i++)
						{
							if (chaine_centrale[i] == '(')
							{
								niveau++;
							}

							if ((niveau == 0) || ((((test_cfsf(s_etat_processus,
									48) == d_vrai) && (chaine_centrale[i] ==
									'.')) || ((test_cfsf(s_etat_processus, 48)
									== d_faux) && (chaine_centrale[i] == ',')))
									&& (niveau == 1)))
							{
								drapeau_elimination_parentheses = d_faux;
							}

							if (chaine_centrale[i] == ')')
							{
								niveau--;
							}
						}

						if (drapeau_elimination_parentheses == d_vrai)
						{
							if ((tampon = (unsigned char *) malloc(
									((longueur_tampon = strlen(
									chaine_centrale)) + 1) * sizeof(
									unsigned char))) == NULL)
							{
								(*s_etat_processus).erreur_systeme =
										d_es_allocation_memoire;
								return(NULL);
							}

							chaine_centrale[longueur_tampon - 2] =
									d_code_fin_chaine;
							sprintf(tampon, "'%s'", &(chaine_centrale[2]));
							free(chaine_centrale);
							chaine_centrale = tampon;
							drapeau_modification = d_vrai;
						}
					}

					if ((tampon = (unsigned char *) malloc(sizeof(
							unsigned char))) == NULL)
					{
						(*s_etat_processus).erreur_systeme =
								d_es_allocation_memoire;
						return(NULL);
					}

					tampon[0] = d_code_fin_chaine;
					longueur_chaine = strlen(chaine_centrale);
					niveau = 0;
					k = strlen(chaine_centrale) - 1;
					fin_boucle_extraction = d_faux;

					while((k >= 0) && (fin_boucle_extraction ==
							d_faux))
					{
						t0 = ((size_t) k < strlen(chaine_centrale))
								? chaine_centrale[k + 1] : ' ';
						t1 = chaine_centrale[k];
						t2 = (k < 1) ? ' ' : chaine_centrale[k - 1];
						t3 = (k < 2) ? ' ' : chaine_centrale[k - 2];
						t4 = (k < 3) ? ' ' : chaine_centrale[k - 3];

						if ((t0 >= 'a') && (t0 <= 'z'))
						{
							t0 = t0 + ('A' - 'a');
						}

						if ((t1 >= 'a') && (t1 <= 'z'))
						{
							t1 = t1 + ('A' - 'a');
						}

						if ((t2 >= 'a') && (t2 <= 'z'))
						{
							t2 = t2 + ('A' - 'a');
						}

						if ((t3 >= 'a') && (t3 <= 'z'))
						{
							t3 = t3 + ('A' - 'a');
						}

						if ((t4 >= 'a') && (t4 <= 'z'))
						{
							t4 = t4 + ('A' - 'a');
						}

						if (t1 == '(')
						{
							niveau++;
						}

						if (niveau == 0)
						{
							prologue = purification_chaine(
									extraction_chaine(chaine_centrale, 1, k));
							epilogue = purification_chaine(
									extraction_chaine(chaine_centrale,
									k + 2, longueur_chaine));

							if ((prologue == NULL) || (epilogue == NULL))
							{
								(*s_etat_processus).erreur_systeme =
										d_es_allocation_memoire;
								return(NULL);
							}

	/*
	 * Priorité = 1 : traitement des fonctions les plus prioritaires
	 */

							if (((priorite == 4) && (((t1 == '<') && (t0 != '=')
									&& (t2 != '=')) || ((t1 == '>') &&
									(t0 != '=') && (t2 != '=')) ||
									((t1 == '=') && (t0 != '=') && (t0 != '<')
									&& (t0 != '>') && (t2 != '<') && (t2 != '>')
									&& (t2 != '=')))) ||
									((t1 == '+') && (priorite == 5) &&
									(t2 != '\'') && (!(((t2 == '(')
									|| (t2 == '\'') || (t2 == 'e')
									|| (t2 == 'E')) && (((t3 >= '0')
									&& (t3 <= '9')) || (t3 == '.'))))) ||
									((t1 == '-') && (priorite == 6) &&
									(t2 != '\'') && (!(((t2 == '(')
									|| (t2 == '\'') || (t2 == 'e')
									|| (t2 == 'E')) && (((t3 >= '0')
									&& (t3 <= '9')) || (t3 == '.')))))
									|| ((t1 == '*') && (priorite == 7))
									|| ((t1 == '/') && (priorite == 8)) ||
									((t1 == '^') && (priorite == 9)))
							{
								drapeau_priorite_entierement_traitee = d_faux;
								fin_boucle_extraction = d_vrai;

								free(tampon);

								if ((tampon = (unsigned char *)
										malloc((strlen(prologue) +
										strlen(epilogue) + 6) *
										sizeof(unsigned char))) == NULL)
								{
									(*s_etat_processus).erreur_systeme =
											d_es_allocation_memoire;
									return(NULL);
								}

								sprintf(tampon, "%s' '%s %c",
										prologue, epilogue, t1);
								drapeau_modification = d_vrai;
							}
							else if (((priorite == 4) && (((t1 == '<') &&
									(t2 == '=')) || ((t1 == '=') &&
									(t2 == '<')) || ((t1 == '>') &&
									(t2 == '<')) || (((t1 == '>') &&
									(t2 == '=')) || ((t1 == '=') &&
									(t2 == '>')) || ((t1 == '=') &&
									(t2 == '='))))) || ((priorite == 1) &&
									(t1 == 'R') && (t2 == 'O') && (t3 == ' ')
									&& (t0 == ' ')))
							{
								drapeau_priorite_entierement_traitee = d_faux;
								fin_boucle_extraction = d_vrai;

								free(tampon);

								if ((tampon = (unsigned char *)
										malloc((strlen(prologue) +
										strlen(epilogue) + 6) *
										sizeof(unsigned char))) == NULL)
								{
									(*s_etat_processus).erreur_systeme =
											d_es_allocation_memoire;
									return(NULL);
								}

								prologue[strlen(prologue) - 1] =
										d_code_fin_chaine;

								sprintf(tampon, "%s' '%s %c%c", prologue,
										epilogue, t2, t1);
								drapeau_modification = d_vrai;
							}
							else if (((priorite == 1) && (t4 == ' ') &&
									(t3 == 'X') && (t2 == 'O') && (t1 == 'R')
									&& (t0 == ' ')) || ((priorite == 2) &&
									(t4 == ' ') && (t3 == 'A') && (t2 == 'N')
									&& (t1 == 'D') && (t0 == ' ')))
							{
								drapeau_priorite_entierement_traitee = d_faux;
								fin_boucle_extraction = d_vrai;

								free(tampon);

								if ((tampon = (unsigned char *)
										malloc((strlen(prologue) +
										strlen(epilogue) + 5) *
										sizeof(unsigned char))) == NULL)
								{
									(*s_etat_processus).erreur_systeme =
											d_es_allocation_memoire;
									return(NULL);
								}

								prologue[strlen(prologue) - 3] =
										d_code_fin_chaine;

								sprintf(tampon, "%s' '%s %c%c%c", prologue,
										epilogue, t3, t2, t1);
								drapeau_modification = d_vrai;
							}

							free(prologue);
							free(epilogue);
						}

						if (t1 == ')')
						{
							niveau--;
						}

						k--;
					}

					if (drapeau_priorite_entierement_traitee == d_vrai)
					{
						free(tampon);

						if ((tampon = (unsigned char *) malloc(
								(strlen(chaine_centrale) + 1) *
								sizeof(unsigned char))) == NULL)
						{
							(*s_etat_processus).erreur_systeme =
									d_es_allocation_memoire;
							return(NULL);
						}

						strcpy(tampon, chaine_centrale);
					}

					free(chaine_centrale);
					free(chaine_travail);

					if ((chaine_travail = (unsigned char *) malloc(
							(strlen(chaine_gauche) + strlen(tampon)
							+ strlen(chaine_droite) + 1 + 2)
							* sizeof(unsigned char))) == NULL)
					{
						(*s_etat_processus).erreur_systeme =
								d_es_allocation_memoire;
						return(NULL);
					}

					sprintf(chaine_travail, "%s %s %s", chaine_gauche,
							tampon, chaine_droite);

					free(chaine_gauche);
					free(tampon);
					free(chaine_droite);
				}

				if (drapeau_priorite_entierement_traitee == d_vrai)
				{
					priorite++;
				}
				else
				{
					priorite = 1;
				}
			} while(priorite < 10);

			/*
			 * Aucune modification n'a pu être faite sur l'expression
			 * algébrique.
			 */

			if (drapeau_modification == d_faux)
			{
				free(chaine_travail);

				(*s_etat_processus).erreur_execution = d_ex_syntaxe;
				return(NULL);
			}
		}
	} while(nombre_apostrophes != 0);

	tampon = chaine_travail;

	if ((chaine_travail = (unsigned char *) malloc((strlen(tampon) + 1 + 6) *
			sizeof(unsigned char))) == NULL)
	{
		(*s_etat_processus).erreur_systeme = d_es_allocation_memoire;
		return(NULL);
	}

	sprintf(chaine_travail, "<< %s >>", tampon);
	free(tampon);

	return(chaine_travail);
}


unsigned char *
purification_chaine(unsigned char *chaine)
{
	long						i;
	long						j;

	unsigned char				*chaine_purifiee;

	i = 0;
	j = strlen(chaine) - 1;

	while(chaine[i] == ' ')
	{
		if ((i++) > j)
		{
			i = j;
			break;
		}
	}
	
	if (j >= 0)
	{
		while(chaine[j] == ' ')
		{
			if ((--j) < 0)
			{
				j = 0;
				break;
			}
		}
	}

	chaine_purifiee = extraction_chaine(chaine, i + 1, j + 1);
	free(chaine);

	return(chaine_purifiee);
}


logical1
test_expression_rpn(unsigned char *chaine)
{
	long					j;

	unsigned char			t;
	unsigned char			t0;
	unsigned char			t1;
	unsigned char			t2;
	unsigned char			t3;
	unsigned char			t4;

	unsigned long			compteur;
	unsigned long			longueur_chaine;
	unsigned long			i;
	unsigned long			niveau;

	/*
	 * On teste d'abord la chaîne pour vérifier qu'il n'y a pas de fonction
	 * utilisant la notation infixe.
	 */

	compteur = 0;

	for(longueur_chaine = strlen(chaine), i = 1; i < longueur_chaine; i++)
	{
		/*
		 * On saute les chaînes de caractères
		 */

		if (chaine[i - 1] == '"')
		{
			i++;
			while(chaine[i - 1] != '"')
			{
				i++;
			}
		}

		j = ((long) i) - 2;
		t0 = (i >= 2) ? chaine[i - 2] : '?';
		t1 = chaine[i - 1];
		t2 = chaine[i];
		t3 = ((i + 1) < strlen(chaine)) ? chaine[i + 1] : '?';
		t4 = ((i + 2) < strlen(chaine)) ? chaine[i + 2] : '?';

		/*
		 * Ouverture d'une parenthèse signalant une fonction
		 */

		if ((t1 != '+') && (t1 != '-') && (t1 != '*') && (t1 != '/')
				&& (t1 != '\'') && (t2 == '('))
		{
			niveau = 0;

			do
			{
				if ((t = chaine[i++]) == '(')
				{
					niveau++;
				}
				else if (t == ')')
				{
					niveau--;
				}
			} while(((niveau != 0) || (t != ')')) && (i < longueur_chaine));

			if (i < longueur_chaine)
			{
				t2 = chaine[i];
			}
			else
			{
				t2 = ' ';
			}
		}

		/*
		 * Signalement de l'une des quatre opérations et des fonctions
		 * infixes traditionnelles
		 */

		if ((t2 == '+') || (t2 == '-') || (t2 == '*') || (t2 == '/')
				|| (t2 == '^') || (t2 == '<') || (t2 == '>') || (t2 == '=')
				|| ((t0 == ' ') && ((t1 == 'A') || (t1 == 'a')) &&
				((t2 == 'N') || (t2 == 'n')) && ((t3 == 'D') || (t3 == 'd'))
				&& (t4 == ' ')) ||
				((t0 == ' ') && ((t1 == 'O') || (t1 == 'o')) &&
				((t2 == 'R') || (t2 == 'r')) && (t3 == ' ')) ||
				((t0 == ' ') && ((t1 == 'X') || (t1 == 'x')) &&
				((t2 == 'O') || (t2 == 'o')) && ((t3 == 'R') || (t3 == 'r'))
				&& (t4 == ' ')))
		{
			compteur++;
		}

		/*
		 * Signalement d'un nombre
		 */

		if (((t2 == '+') || (t2 == '-')) && ((t1 == '(')
				|| ((t1 == 'e') || (t1 == 'E') || (t1 == '\'')))
				&& (((t0 >= '0') && (t0 <= '9')) || (t0 == '.')))
		{
			compteur--;
		}
		else if (((t2 == '+') || (t2 == '-')) && (t1 == '\'') && (j < 0)
				&& (((t3 >= '0') && (t3 <= '9')) || (t3 == '.')))
		{
			compteur--;
		}
	}

	return(((compteur == 0) && (test_fonction(chaine) == d_faux))
			? d_vrai : d_faux);
}


logical1
test_fonction(unsigned char *chaine)
{
	logical1				drapeau_fonction;

	unsigned char			t;

	unsigned long			compteur;
	unsigned long			i;
	unsigned long			longueur_chaine;

	longueur_chaine = strlen(chaine);
	i = 1;

	while(((t = chaine[i]) != '(') && (i < (longueur_chaine - 1)))
	{
		if ((t == '+') || (t == '-') || (t == '*') ||
				(t == '/') || (t == '^') || (t == '>') || (t == '<') ||
				(t == '='))
		{
			i = longueur_chaine - 1;
		}
		else
		{
			i++;
		}
	}

	compteur = 1;
	drapeau_fonction = ((i < (longueur_chaine - 1)) && (i != 1))
			? d_vrai : d_faux;

	for(i++; i < (longueur_chaine - 2); i++)
	{
		if ((t = chaine[i]) == '(')
		{
			compteur++;
		}
		else if (t == ')')
		{
			compteur--;
		}

		if (compteur == 0)
		{
			drapeau_fonction = d_faux;
		}
	}

	return drapeau_fonction;
}


unsigned char *
extraction_chaine(unsigned char *chaine, unsigned long position_1,
		unsigned long position_2)
{
	long					i;

	unsigned char			*pointeur_ecriture;
	unsigned char			*pointeur_lecture;
	unsigned char			*sous_chaine;

	if ((position_1 < 1) || (position_2 < position_1) ||
			(position_2 > strlen(chaine)))
	{
		if ((sous_chaine = (unsigned char *) malloc(sizeof(unsigned char)))
				== NULL)
		{
			return(NULL);
		}

		(*sous_chaine) = d_code_fin_chaine;
		return(sous_chaine);
	}

	if ((sous_chaine = (unsigned char *)
			malloc((position_2 - position_1 + 2) * sizeof(unsigned char)))
			== NULL)
	{
		return(NULL);
	}

	pointeur_lecture = &(chaine[position_1 - 1]);
	pointeur_ecriture = sous_chaine;

	for(sous_chaine[i = position_2 - position_1 + 1] = 0; (--i) >= 0;
			*(pointeur_ecriture++) = *(pointeur_lecture++));

	return(sous_chaine);
}

// vim: ts=4
