#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <err.h>
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

typedef struct Celula
{
   Personagem elemento;
   struct Celula* prox;
   struct Celula* ant;
}Celula;


// PROTOTIPOS
Personagem LerArquivo (Personagem P, char entrada[]);
Personagem SepararPalavras (Personagem P, char leitura_arq[]);
char* TiraVirgula (char s[]);
Celula* novaCelula (Personagem pers);
void startPers ();
void InserirFim (Personagem novo);
void RemoverFim ();
void Imprimir ();
void ImprimirRemovidos (Celula* i);
void Remover ();
void SepararComando (char entrada[], Personagem novo);
void StartR();
void ImprimirRec();
void ImprimirRemovidosRec();
Celula* Pivo (Celula* esq, Celula* dir);
void swap (Celula* A, Celula* B);
void quicksortRec(Celula* esq, Celula* dir);
void quicksort();
int posicao (Celula* celula);
Celula* meio(Celula* inicio, Celula* fim);
int tamanho();
Celula* meio(Celula* inicio, Celula* fim);
void Desempatar();
void Insercao (Celula* inicio, Celula* fim);


// VARIAVEIS GLOBAIS
int n = 0; // quantidade de personagens
int qnt_removidos = 0;
Celula* NoCabeca;
Celula* Ultima;
int qnt1 = 0;
int qnt2 = 0;

int main()
{
   Personagem P;
   char entrada[1000];
   startPers();
 
   fgets (entrada, 100, stdin);
   entrada[strlen(entrada) - 1] = '\0';
   
   while (!strcmp (entrada, "FIM") == 0)
   {  
      P = LerArquivo (P, entrada);
      InserirFim(P); 
      fgets (entrada, 100, stdin);
      entrada[strlen(entrada) - 1] = '\0';
   }
   
   Celula* esq = NoCabeca->prox;
   Celula* dir = Ultima;
   quicksort ();
   Desempatar();
   Imprimir ();
   
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
   nova->prox = nova->ant = NULL;
   return nova;
}

/*
 * Essa função vai iniciar minha lista vazia
 */
void startPers()
{
   Personagem x;
   NoCabeca = novaCelula(x);
   Ultima = NoCabeca;
}

 /*
  * Essa função vai inserir um personagem no final da lista
  */
void InserirFim (Personagem novo)
{
   Celula* temp = novaCelula(novo);
   temp->ant = Ultima;
   Ultima->prox = temp;
   Ultima = Ultima->prox;
   temp = NULL;
   n++;
   qnt1++;
   qnt2++;
}

int tamanho()
{
   int tamanho = 0;
   for (Celula* i = NoCabeca->prox; i != NULL; i = i->prox);
   return tamanho;
}

/*
 * Essa função vai calcular a posição do personagem 
 */
int posicao (Celula* celula)
{
   int p = 0;
   for (Celula* i = NoCabeca; i != celula; i = i->prox, p++);
   return p;
}


/*
 * Essa função vai ordenar, usando o quicksort, os personagens de acordo com a cor do cabelo
 */
void quicksort()
{
   quicksortRec(NoCabeca->prox, Ultima);
}

Celula* meio(Celula* inicio, Celula* fim)
{
   int tamanho = 0;

   for (Celula* i = inicio; i != fim; i = i->prox, tamanho++);
   Celula* resp = inicio;
   for (; tamanho > 0; tamanho--, resp = resp->prox);
   return resp;
}

void quicksortRec(Celula* esq, Celula* dir)
{
   Celula* i = esq;
   Celula* j = dir;

   char* pivo = meio(esq, dir)->elemento.cor_do_cabelo;

   while (posicao(i) < posicao(j))
   {
      while (strcmp (i->elemento.cor_do_cabelo, pivo) < 0)
      {
         i = i-> prox;
      }
      while (strcmp(j->elemento.cor_do_cabelo, pivo) > 0)
      {
         j = j->ant;
      }
      if (posicao(i) <= posicao(j))
      {
         swap(i, j);
         i = i->prox;
         j = j->ant;
      }
   }
   if (posicao(esq) < posicao(j)) quicksortRec(esq, j);
   if (posicao(i) < posicao(dir)) quicksortRec(i, dir);
}

/*
 * Essa função vai fazer o swap
 */
void swap (Celula* A, Celula* B)
{
   Personagem aux;
   aux = A->elemento;
   A->elemento = B->elemento;
   B->elemento = aux;
}

void Desempatar()
{
   int PosI = 0;
   int PosF = 0;
   Celula* temp1;
   Celula* temp2;

   for (Celula* i = NoCabeca->prox; i != NULL; i = i->prox, PosF++)
   {
      temp1 = i;
      PosI = PosF;

      while ( i != Ultima && strcmp(i->elemento.cor_do_cabelo, i->prox->elemento.cor_do_cabelo) == 0)
      {
         i = i->prox;
         PosF++;
      }
      temp2 = i;

     if (PosF - PosI > 1) Insercao (temp1, temp2);
   }
}

void Insercao (Celula* inicio, Celula* fim)
{
   Personagem aux;
   char menor[50];

   for (Celula* i = inicio; i != fim->prox; i = i->prox)
   {
      aux = i->elemento;
      Celula* j = i->ant;
      strcpy(menor, i->elemento.nome);

      while (( j != inicio->ant) && strcmp(j->elemento.nome, menor) > 0)
      {
         j->prox->elemento = j->elemento;
         j = j->ant;
      }
      j->prox->elemento = aux;
   }
}

/*
 * Essa função vai imprimir os atributos dos personagens
 */
void Imprimir () 
{
   for(Celula* i = NoCabeca->prox; i != NULL; i = i->prox)
   {
      printf (" ## %s ## ", i->elemento.nome);
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

