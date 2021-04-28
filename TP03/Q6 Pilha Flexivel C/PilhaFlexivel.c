#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <err.h>

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

typedef struct Celula
{
   Personagem elemento;
   struct Celula* prox;
}Celula;


// PROTOTIPOS
Personagem LerArquivo (Personagem P, char entrada[]);
Personagem SepararPalavras (Personagem P, char leitura_arq[]);
char* TiraVirgula (char s[]);
Celula* novaCelula (Personagem pers);
void startPers ();
void InserirFim (Personagem novo);
void RemoverFim ();
void Imprimir (Celula* i, int j);
void ImprimirRemovidos (Celula* i);
void Remover ();
void SepararComando (char entrada[], Personagem novo);
void StartR();
void ImprimirRec();
void ImprimirRemovidosRec();


// VARIAVEIS GLOBAIS
int n = 0; // quantidade de personagens
int qnt_removidos = 0;
Celula* topoP;
Celula* topoR;

int main()
{
   Personagem P;
   //char* entrada = (char*)malloc(sizeof(char)*100);
   char entrada[1000];
   startPers();
   StartR();
 
   fgets (entrada, 100, stdin);
   entrada[strlen(entrada) - 1] = '\0';
   
   while (!strcmp (entrada, "FIM") == 0)
   {  
      P = LerArquivo (P, entrada);
      InserirFim(P); 
      //printf ("\ntopo: %s", topoP->elemento.nome);
      fgets (entrada, 100, stdin);
      entrada[strlen(entrada) - 1] = '\0';
   }

   int qnt = 0;
   int cont = 0;
   scanf ("%d\n", &qnt);
   fgets (entrada, 100, stdin);
   entrada[strlen(entrada) - 1] = '\0';
   char aux[200];
   
   //printf ("\ntopo: %s", topoP->elemento.nome);

   while (cont < qnt)
   {
      if (strstr(entrada, " ") != NULL)
      {
         SepararComando(entrada, P);
      }
      else
      {
         RemoverFim();
      }

      fgets (entrada, 100, stdin);
      entrada[strlen(entrada) - 1] = '\0';
      cont++;
   }

   ImprimirRemovidosRec ();
   ImprimirRec ();
   return 0;
}


/*
 * Essa função vai realizar a leitura do arquivo enviado
 */
Personagem LerArquivo (Personagem P, char entrada[])
{
   char leitura_arq[1000];
   FILE *ptr_arq;
   ptr_arq = fopen(entrada, "r");

   while (!feof(ptr_arq))
   {
      fscanf (ptr_arq, "%[^\n]\n", leitura_arq);
   }
   fclose (ptr_arq);

   return SepararPalavras(P, leitura_arq);
}


/*
 * Essa função vai separar os Atributos do personagem
 */
Personagem SepararPalavras (Personagem P, char leitura_arq[])
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
         strcpy (P.nome, string[i+2]);
      }
      else if (strcmp (string[i], "height") == 0)
      {
         P.altura = atoi(string[i+2]);
      }
      else if (strcmp (string[i], "mass") == 0)
      {
         P.peso = atof(TiraVirgula(string[i+2]));
      }
      else if (strcmp (string[i], "hair_color") == 0)
      {
         strcpy (P.cor_do_cabelo, string[i+2]);
      }
      else if (strcmp(string[i], "skin_color") == 0)
		{
			strcpy (P.cor_da_pele, string[i+2]);
		}
		else if (strcmp(string[i], "eye_color") == 0)
		{
			strcpy (P.cor_dos_olhos, string[i+2]);
		}
		else if (strcmp(string[i], "birth_year") == 0)
		{
			strcpy (P.ano_aniversario, string[i+2]);
		}
		else if (strcmp(string[i], "gender") == 0)
		{
			strcpy (P.genero, string[i+2]);
		}
		else if (strcmp(string[i], "homeworld") == 0)
		{
			strcpy (P.home_world, string[i+2]);
		}
   }
   return P;
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
 * Essa função vai separar o comando de inserir e o arquivo
 */
void SepararComando (char entrada[], Personagem novo)
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
   novo = LerArquivo(novo, string[1]);
   InserirFim(novo);

   
}

/*
 * Essa função vai criar minha celula
 */
Celula* novaCelula (Personagem elemento)
{
   Celula* nova = (Celula*)malloc(sizeof(Celula));
   nova->elemento = elemento;
   nova->prox = NULL;
   return nova;
}

/*
 * Essa função vai iniciar minha lista vazia
 */
void startPers()
{
   topoP = NULL;
}
void StartR()
{
   topoR = NULL;
}

 /*
  * Essa função vai inserir um personagem no final da lista
  */
void InserirFim (Personagem novo)
{
   Celula* temp = novaCelula(novo);
   temp->prox = topoP;
   topoP = temp;
   temp = NULL;
   n++;
   //printf ("\ntopo: %s", topoP->elemento.nome);
}


/*
 * Essa função vai remover o personagem do fim da lista
 */
void RemoverFim ()
{
    //printf ("\ntopo antes da remoção: %s", topoP->elemento.nome);
   
    if (topoP == NULL)
   {
      printf ("erro");
   }
   else
   {
      // inserir na minha pilha de removidos
      Celula* temp = topoP;
      topoP = topoP->prox;
      temp->prox = topoR;
      topoR = temp;
      temp = NULL;
      n--;
      //printf ("\ntopo depois da remoção: %s", topoP->elemento.nome);
      //printf ("\ntopoRemovidos depois da remoção: %s\n", topoR->elemento.nome);

   }
}

void Remover()
{
  printf ("\ntopo: %s", topoP->elemento.nome);
 

}
/*
 * Essa função vai imprimir os atributos dos personagens
 */
void ImprimirRec()
{
   Celula* i = topoP;
   Imprimir(i, n);
}

void Imprimir (Celula* i, int j) 
{
  
   if(i != NULL)
   {
      Imprimir(i->prox, --j);
      printf ("[%i]  ## %s ## ", j, i->elemento.nome);
		printf ("%i ## ", i->elemento.altura);
		printf ("%g ## ", i->elemento.peso);
		printf ("%s ## ", i->elemento.cor_do_cabelo);
		printf ("%s ## ", i->elemento.cor_da_pele);
		printf ("%s ## ", i->elemento.cor_dos_olhos);
		printf ("%s ## ", i->elemento.ano_aniversario);
		printf ("%s ## ", i->elemento.genero);
		printf ("%s ## ", i->elemento.home_world);
      printf ("\n");	
   }
}


/*
 * Essa função vai imprimir o nome dos personagens removidos 
 */
void ImprimirRemovidosRec()
{
   Celula* i = topoR;
   ImprimirRemovidos(i);
}

void ImprimirRemovidos (Celula* i)
{
   if (i != NULL)
   {
      ImprimirRemovidos(i->prox);
      printf ("(R) %s\n", i->elemento.nome);
   }
   
}


