#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <time.h>

typedef struct 
{
   char nome[20];
   int altura;
   double peso;
   char cor_do_cabelo[50];
   char cor_da_pele[50];
   char cor_dos_olhos[50];
   char ano_aniversario[50];
   char genero[10];
   char home_world[50];
}Personagem;

// PROTOTIPOS
void LerArquivo (Personagem P[], char entrada[]);
void SepararPalavras (Personagem P[], char leitura_arq[]);
char* TiraVirgula (char s[]);
void Imprimir (Personagem P[]);
bool PesquisaBinaria (Personagem P[], char nomeComparar[]);
void ArquivoLog (double tempo);
void OrdenacaoInsercao (Personagem P[]);


// VARIAVEIS GLOBAIS
int n = 0;
int qnt_comp = 0;

int main()
{
   clock_t tempo;
   Personagem P[500];
   char entrada[1000];
   
   fgets (entrada, 100, stdin);
   entrada[strlen(entrada) - 1] = '\0';
   
   tempo = clock();
   while (strcmp (entrada, "FIM") != 0)
   {
      LerArquivo (P, entrada);
      n++;
      fgets (entrada, 100, stdin);
      entrada[strlen(entrada) - 1] = '\0';
   }
   OrdenacaoInsercao(P);
   Imprimir(P);

   tempo = clock() - tempo;
   double aux = (((double)tempo)/((double)(CLOCKS_PER_SEC/1000)));

   ArquivoLog (aux);

   return 0;
}


/*
 * Essa função vai realizar a leitura do arquivo enviado
 */
void LerArquivo (Personagem P[], char entrada[])
{
   char leitura_arq[1000];
   FILE *ptr_arq;
   ptr_arq = fopen(entrada, "r");

   while (!feof(ptr_arq))
   {
      fscanf (ptr_arq, "%[^\n]\n", leitura_arq);
   }
   fclose (ptr_arq);

   SepararPalavras(P, leitura_arq);
}


/*
 * Essa função vai separar os Atributos do personagem
 */
void SepararPalavras (Personagem P[], char leitura_arq[])
{  
   char *pers = leitura_arq;
   int k = 0;
   char string[50][20];
   char *ptr_separado;
   const char separar[2] = "'";
   
   ptr_separado = strtok (pers, separar);
   
   //while (ptr_separado != NULL)
   for (int i = 0; i < 36; i++)
   {
      strcpy(string[k], ptr_separado);
      ptr_separado = strtok(NULL, separar);
      k++;
   }
   
   for (int i = 0; i < 36; i++)
   {
      if (strcmp (string[i], "name") == 0)
      {
         strcpy (P[n].nome, string[i+2]);
      }
      else if (strcmp (string[i], "height") == 0)
      {
         P[n].altura = atoi(string[i+2]);
      }
      else if (strcmp (string[i], "mass") == 0)
      {
         P[n].peso = atof(TiraVirgula(string[i+2]));
      }
      else if (strcmp (string[i], "hair_color") == 0)
      {
         strcpy (P[n].cor_do_cabelo, string[i+2]);
      }
      else if (strcmp(string[i], "skin_color") == 0)
		{
			strcpy (P[n].cor_da_pele, string[i+2]);
		}
		else if (strcmp(string[i], "eye_color") == 0)
		{
			strcpy (P[n].cor_dos_olhos, string[i+2]);
		}
		else if (strcmp(string[i], "birth_year") == 0)
		{
			strcpy (P[n].ano_aniversario, string[i+2]);
		}
		else if (strcmp(string[i], "gender") == 0)
		{
			strcpy (P[n].genero, string[i+2]);
		}
		else if (strcmp(string[i], "homeworld") == 0)
		{
			strcpy (P[n].home_world, string[i+2]);
		}

   }
}

/*
 * Essa função recebe uma string e remove dela as virgulas
 */
char* TiraVirgula (char s[])
{
   char *aux = (char*)malloc(10*sizeof(char));
   int cont = 0;
   for (int i = 0; i < strlen(s); i++)
   {
      if (!(s[i] == ','))
      {
         aux[cont] = s[i];
         cont++;
      }
   }
   return aux;
}

/*
 * Essa função vai imprimir os atributos dos personagens
 */
void Imprimir (Personagem P[]) 
{
   for (int i = 0; i < 10; i++)
   {
      printf (" ## %s ## ", P[i].nome);
		printf ("%i ## ", P[i].altura);
		printf ("%g ## ", P[i].peso);
		printf ("%s ## ", P[i].cor_do_cabelo);
		printf ("%s ## ", P[i].cor_da_pele);
		printf ("%s ## ", P[i].cor_dos_olhos);
		printf ("%s ## ", P[i].ano_aniversario);
		printf ("%s ## ", P[i].genero);
		printf ("%s ## ", P[i].home_world);
		//printf ("nome: %s  ano: '%s'", P[i].nome, P[i].ano_aniversario);
      printf ("\n");	
      
   }
}

/*
 * Essa função vai ordenar sequencialmente minha lista
 */
void OrdenacaoInsercao (Personagem P[])
{
   int j = 0;
   Personagem aux;
   int resp = 1;
   for (int i = 1; i < n; i++)
   {
      aux = P[i];
      j = i - 1;

      resp = strcmp(P[i].ano_aniversario, "unknown");

      while ((j >= 0) && strcmp(P[j].ano_aniversario, aux.ano_aniversario) > 0 && resp != 0)
      {  
            P[j+1] = P[j];
            j--; 
      }
      
      P[j+1] = aux;
   }
}

void ArquivoLog (double tempo)
{
   FILE *arquivo;

   arquivo = fopen ("matricula_insercaoParcial.txt", "w");

   if (arquivo == NULL)
   {
      printf ("Erro");
      return;
   }
   
   fprintf(arquivo, "matricula: 680624 \t tempo: %lf \t comparações: %i ", tempo, qnt_comp); 
}


