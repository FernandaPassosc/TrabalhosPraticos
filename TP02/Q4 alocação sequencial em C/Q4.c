#include <string.h>
#include <stdio.h>
#include <stdlib.h>
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
void Imprimir (Personagem P[]);
bool VerificaEspaco(char entrada[]);
void SepararComando (Personagem P[], Personagem R[], char entrada[]);
void ChamarMetodo (Personagem P[], Personagem R[], char entrada[]);
void IdentificarComando (Personagem P[], Personagem R[], char entrada[]);
void InserirInicio (Personagem P[], char arquivo[]);
void InserirFim (Personagem P[], char arquivo[]);
void InserirPosicao (Personagem P[], int posicao, char arquivo[]);
void RemoverInicio (Personagem P[], Personagem R[]);
void RemoverFim (Personagem P[], Personagem R[]);
void RemoverPosicao (Personagem P[], Personagem R[], int posicao);
void ImprimirRemovidos (Personagem R[]);


// VARIAVEIS GLOBAIS
int n = 0; // quantidade de personagens
int qnt_removidos = 0;


int main()
{
   Personagem P[500];
   Personagem R[50]; // personagem removido
   char entrada[1000];
   
   fgets (entrada, 100, stdin);
   entrada[strlen(entrada) - 1] = '\0';
   
   while (strcmp (entrada, "FIM") != 0)
   {
      LerArquivo (P, entrada);
      n++;
      fgets (entrada, 100, stdin);
      entrada[strlen(entrada) - 1] = '\0';
   }

   char novaEntrada[1000];
   int qnt = 0;
   int cont = 0;
   scanf ("%i\n", &qnt);

   fgets (novaEntrada, 100, stdin);
   novaEntrada[strlen(novaEntrada) - 1] = '\0';

   while (cont < qnt)
   {
      if (strstr(novaEntrada, " ") != NULL)
      {
         SepararComando (P, R, novaEntrada);     
      }
      else if (strstr(novaEntrada, " ") == NULL)
      {
         ChamarMetodo (P, R, novaEntrada);
      }
   
      fgets (novaEntrada, 100, stdin);
      novaEntrada[strlen(novaEntrada) - 1] = '\0';
      cont++;
   }

   //ImprimirRemovidos (R);
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
 * Essa função vai separar a entrada que contem um comando e um arquivo
 */
void SepararComando (Personagem P[], Personagem R[], char entrada[])
{
   char *ptr_entrada = entrada;
   char *ptr_separado;
   const char separar[2] = " ";
   char string[10][20];
   int k = 0;
   
   ptr_separado = strtok(ptr_entrada, separar);  
   
   while (ptr_separado != NULL)
   {
      strcpy(string[k], ptr_separado);
      ptr_separado = strtok(NULL, separar); 
      k++;
   }
   printf ("\nstring[0]: %s", string[0]);
   printf ("\nstring[1]: %s", string[1]);
   if (strcmp (string[0], "II") == 0)
   {
      InserirInicio (P, string[1]);
   }
   else if (strcmp (string[0], "IF") == 0)
   {
      InserirFim (P, string[1]);
   }
   else if (strcmp (string[0], "I*") == 0)
   {
      InserirPosicao(P, atoi(string[1]), string[2]);
   }
   else if (strcmp (string[0], "R*") == 0)
   {
      RemoverPosicao (P, R, atoi(string[1]));
   }
}


/*
 * Essa função vai chamar o metodo do comando
 */
void ChamarMetodo (Personagem P[], Personagem R[], char entrada[])
{
   if (strcmp (entrada, "RI") == 0)
   {
      RemoverInicio (P, R);
   }
   else
   {
      RemoverFim (P, R);
   }
}


/*
 * Essa função vai inserir um personagem no inicio da lista
 */
void InserirInicio (Personagem P[], char arquivo[])
{
   for (int i = n; i > 0; i--)
   {
      P[i] = P[i-1];
   }
   int aux = n;
   n = 0;
   LerArquivo (P, arquivo);
   n = aux + 1;
}


/*
 * Essa função vai inserir um personagem no final da lista
 */
void InserirFim (Personagem P[], char arquivo[])
{
   LerArquivo (P, arquivo);
   n++;
}


/*
 * Essa função vai inserir um personagem em determinada posição
 */
void InserirPosicao (Personagem P[], int posicao, char arquivo[])
{
   for (int i = n; i > posicao; i--)
   {
      P[i] = P[i-1];
   }
   int aux = n;
   n = posicao;
   LerArquivo (P, arquivo);
   n = aux + 1;
}


/*
 * Essa função vai remover o personagem do inicio da lista
 */
void RemoverInicio (Personagem P[], Personagem R[])
{
   R[qnt_removidos] = P[0];

   for (int i = 0; i < n; i++)
   {
      P[i] = P[i+1];
   }
   n--;
   qnt_removidos++;
}


/*
 * Essa função vai remover o personagem do final da lista
 */
void RemoverFim (Personagem P[], Personagem R[])
{
   R[qnt_removidos] = P[--n];
   qnt_removidos++;
}



/*
 * Essa função vai remover o personagem de determinada posição
 */
void RemoverPosicao (Personagem P[], Personagem R[], int posicao)
{
   R[qnt_removidos] = P[posicao];
   
   for (int i = posicao; i < n; i++) 
   {
      P[i] = P[i+1];
   } 
   qnt_removidos++;
   n--;
}


/*
 * Essa função vai imprimir os atributos dos personagens
 */
void Imprimir (Personagem P[]) 
{
   for (int i = 0; i < n; i++)
   {
      printf ("[%i]  ## %s ## ", i, P[i].nome);
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


/*
 * Essa função vai imprimir o nome dos personagens removidos 
 */
void ImprimirRemovidos (Personagem R[])
{
   for (int i = 0; i < qnt_removidos; i++)
   {
      printf ("(R) %s\n", R[i].nome);
   }
}


