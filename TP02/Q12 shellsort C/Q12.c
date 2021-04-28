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
void ArquivoLog (double tempo);
void OrdenacaoSequencialRec (Personagem P[], int i, int j);
void insercaoPorCor(Personagem P[], int h, int cor);
void shellsort (Personagem P[]);
void Desempate(Personagem P[]);
void Insercao(Personagem P[], int i, int fim);
void swap(Personagem P[], int i, int j);
void decrescente(Personagem P[]); 

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
   decrescente(P);
   shellsort(P);
   Desempate(P);
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
   for (int i = 0; i < n; i++)
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
		printf ("\n");	
   }
}


void decrescente(Personagem P[]) 
{
  /* Personagem maior = P[0];
   Personagem aux;

   for (int i = 0; i < n; i++) 
   {
      if (maior.peso <= P[i].peso)
      {
         aux = P[i];
         P[i] = maior;
         maior = P[i];
      }
   }*/
}
/*
 * Essa função recursiva, vai ordenar sequencialmente minha lista
 */
void shellsort (Personagem P[])
{
   int h = 1;
   do
   {
      h = (h*3) + 1;
      qnt_comp++;
   }while (h <n);

   do
   {
      h /= 3;
      for (int cor = 0; cor < h; cor++)
      {
         insercaoPorCor(P, h, cor);
      }
   qnt_comp++;   
   }while (h != 1);
   
}

void insercaoPorCor(Personagem P[], int h, int cor)
{
   for (int i = (h + cor); i < n; i += h)
   {
      Personagem tmp = P[i];
      int j = i - h;

      while ((j >= 0) && (P[j].peso > tmp.peso))
      {
         printf ("eii");
         P[j+h] = P[j];
         j -= h;
      }
      P[j+h] = tmp;
   }
  //Desempate(P);

}
void swap(Personagem P[], int i, int j) 
{
   Personagem temp = P[i];
   P[i] = P[j];
   P[j] = temp;
}


void Desempate(Personagem P[])
{
   int PosI = 0; // posição inicial
   int PosF = 0;
   
   for (int i = 0; i < n; i++)
   {
      if ((P[i].peso == P[i+1].peso) &&  (i < n-1))
      {
         //PosI = PosF = i;
         PosI = i;

         while (P[i].peso == P[i+1].peso) i++; 
      }
      
      if (i - PosI > 1) Insercao(P, PosI, i);
   }
}

void Insercao(Personagem P[], int inicio, int fim)
{
   Personagem aux;
   char menor[100];
   
   for (int i = inicio; i <= fim; i++)
   {
      aux = P[i];
      int j = i -1;
      strcpy(menor, P[i].nome);

      while (j >= inicio && (strcmp(P[j].nome, menor) > 0))
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

   arquivo = fopen ("matricula_seleçãoRecursiva.txt", "w");

   if (arquivo == NULL)
   {
      printf ("Erro");
      return;
   }
   
   fprintf(arquivo, "matricula: 680624 \t tempo: %lf \t comparações: %i ", tempo, qnt_comp); 
}


