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
void Imprimir (Personagem P[], bool resp);
bool PesquisaBinaria (Personagem P[], char nomeComparar[]);
void ArquivoLog (double tempo);


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
   
   char nomeComparar[100];
   bool resp = false;
   fgets (nomeComparar, 100, stdin);
   nomeComparar[strlen(nomeComparar) - 1] = '\0';
   if (strcmp (nomeComparar, "\n") == 0)
   {
      fgets (nomeComparar, 100, stdin);
   }
 
   while (strcmp (nomeComparar, "FIM") != 0)
   {
      resp = PesquisaBinaria (P, nomeComparar);
      Imprimir (P, resp);
      fgets (nomeComparar, 100, stdin);
      nomeComparar[strlen(nomeComparar) - 1] = '\0';
      //printf ("\n nome comparar (to no while): %s", nomeComparar);
   }

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
void Imprimir (Personagem P[], bool resp) 
{
   if (resp == true)
   {
      printf ("SIM\n");
   }
   else 
   {
      printf ("NAO\n");
   }
}

bool PesquisaBinaria (Personagem P[], char nomeComparar[])
{
   int esq = 0;
   int dir = n - 1;
   int meio = 0;
   int retorno = 0;
   bool resp = false;

   //printf ("\n nome que ta comparando: %s", nomeComparar);
   while (esq <= dir && resp == false)
   {
      meio = (esq+dir)/2;
      int retorno = strcmp(P[meio].nome, nomeComparar);
      //printf ("\n nome que ta comparando: %s", nomeComparar);
      //printf ("\n nome do P[%i]: %s", meio, P[meio].nome);
      qnt_comp++;
      if (retorno == 0)
      {
         resp = true;
      }
      else if (retorno < 0)
      {         
         esq = meio + 1;
         qnt_comp++;
      }
      else
      {
         dir = meio - 1;
      }
   }
   return resp;
}

void ArquivoLog (double tempo)
{
   FILE *arquivo;

   arquivo = fopen ("matricula_binaria.txt", "w");

   if (arquivo == NULL)
   {
      printf ("Erro");
      return;
   }
   
   fprintf(arquivo, "matricula: 680624 \t tempo: %lf \t comparações: %i ", tempo, qnt_comp); 
}


