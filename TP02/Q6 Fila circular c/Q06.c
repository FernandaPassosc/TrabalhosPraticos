#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <stdbool.h>

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
void SepararComando (Personagem P[], Personagem R[], char entrada[]);
void Inserir (Personagem P[], Personagem R[], char arquivo[]);
void Remover (Personagem P[], Personagem R[]);
void CalcularMedia (Personagem P[]);
int ArredondaMedia(double number);
void ImprimirMedia (Personagem P[], double media);
void ImprimirRemovidos (Personagem P[]);
void Imprimir (Personagem P[]);


// VARIAVEIS GLOBAIS
int n = 0;
int qnt_removidos = 0;
int primeiro = 0; // posição do primeiro elemento da fila
int ultimo = 0; // posição do ultimo elemento da fila
int qnt_fila = 0;
double soma = 0;

int main()
{
   Personagem P[500];
   Personagem R[50];
   char entrada[1000];
   
   fgets (entrada, 100, stdin);
   entrada[strlen(entrada) - 1] = '\0';
   
   while (strcmp (entrada, "FIM") != 0)
   {
      Inserir (P, R, entrada);
      //LerArquivo(P, entrada);
      
      n++;
      fgets (entrada, 100, stdin);
      entrada[strlen(entrada) - 1] = '\0';
      
   }

   char novaEntrada[200];
   char arquivo[20];
   int qnt = 0;
   scanf ("%i\n", &qnt);
   


   fgets (novaEntrada, 100, stdin);
   novaEntrada[strlen(novaEntrada) - 1] = '\0';

   for (int i = 0; i < qnt; i++)
   {
      if (strstr(novaEntrada, " ") != NULL)
      {
         SepararComando(P, R, novaEntrada);
      }
      else
      {
         ImprimirRemovidos(P);
         Remover(P, R);
      }
      
      fgets (novaEntrada, 100, stdin);
      novaEntrada[strlen(novaEntrada) - 1] = '\0';
   }

   //Imprimir (P);
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
 * Esse metodo vai separar o comando e o arquivo
 */
void SepararComando (Personagem P[], Personagem R[], char entrada[])
{
   char *ptr_entrada = entrada;
   char *separado;
   char string[5][10];
   int k = 0;

   separado = strtok (ptr_entrada, " ");

   while (separado != NULL)
   {
      strcpy (string[k], separado);
      separado = strtok (NULL, " ");
      k++;
   }
   
   Inserir(P, R, string[1]);
}


/*
 * Essa função vai inserir um personagem na fila
 */
void Inserir (Personagem P[], Personagem R[], char arquivo[])
{
   if ((ultimo + 1) % 6 == primeiro)
   {
      Remover(P, R);
   }
   
   int aux = n;
   n = ultimo;
   LerArquivo (P, arquivo);
   soma += P[n].altura;
   n = aux;
   qnt_fila++;
   n++;
   ultimo = (ultimo + 1) % 6;
   CalcularMedia(P);
}


/*
 * Essa função vai calcular a media das alturas dos personagens
 */
void CalcularMedia (Personagem P[])
{
   double media = 0;
   media = soma/qnt_fila;
   ImprimirMedia (P, media);
}

/*
 * Essa função vai arredondar a minha media de alturas 
 */
int ArredondaMedia(double number)
{
    return (number >= 0) ? (int)(number + 0.5) : (int)(number - 0.5);
}



/*
 * Essa função vai remover um personagem da fila
 */
void Remover (Personagem P[], Personagem R[])
{
   double media = 0;
   R[qnt_removidos] = P[primeiro];
   qnt_removidos++;
   soma = soma - P[primeiro].altura;
   primeiro = (primeiro + 1) % 6;
   n--;
   qnt_fila--;

}


/*
 * Essa função vai imprimir os atributos dos personagens
 */
void ImprimirMedia (Personagem P[], double media) 
{
      printf ("%d\n", ArredondaMedia(media));
}



/*
 * Essa função vai imprimir o nome dos personagens removidos
 */
void ImprimirRemovidos (Personagem P[])
{
   printf ("(R) %s\n", P[primeiro].nome);
}


