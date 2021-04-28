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

typedef struct No
{
   Personagem elemento;
   struct No *esq, *dir;
   int nivel;
}No;

// PROTOTIPOS
Personagem LerArquivo (Personagem P, char entrada[]);
Personagem SepararPalavras (Personagem P, char leitura_arq[]);
char* TiraVirgula (char s[]);
void SepararComando (char entrada[], Personagem novo);
void start();
void inserir (Personagem elemento);
void inserirRec (Personagem personagem, No** i);
void mostrarPosRec (No* i);
void mostrarPos();
bool pesquisarRec (char entrada[], No* i);
bool pesquisar (char entrada[]);
int getMaior(int x, int y);
No* setNivel (No* i);
int getNivel (No* i);
No*balancear (No* i);
No* rotacionarEsq (No* no);
No* rotacionarDir(No* no);


// VARIAVEIS GLOBAIS
int n = 0; // quantidade de personagens
No* raiz;

int main()
{
   Personagem P;
   //char* entrada = (char*)malloc(sizeof(char)*100);
   char entrada[1000];
   start();

   fgets (entrada, 100, stdin);
   entrada[strlen(entrada) - 1] = '\0';
   
   while (!strcmp (entrada, "FIM") == 0)
   {  
      P = LerArquivo (P, entrada);
      inserir(P); 
      //printf ("\ntopo: %s", topoP->elemento.nome);
      fgets (entrada, 100, stdin);
      entrada[strlen(entrada) - 1] = '\0';
   }
   
   fgets (entrada, 120, stdin);
   entrada[strlen(entrada) - 1] = '\0';
   bool presente;

   while (!strcmp(entrada, "FIM") == 0)
   {
      printf ("%s ", entrada);
      presente = pesquisar(entrada);
      (presente) ? printf ("SIM") : printf("NÃO");
      printf ("\n");
      fgets (entrada, 100, stdin);
      entrada[strlen(entrada) - 1] = '\0';

   }
 
   mostrarPos();
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
}

/*
 * Criando a arvore 
 */
void start ()
{
   raiz = NULL;
}

/*
 * Criando um novo no
 */
No* novoNo (Personagem elemento)
{
   No* novo = (No*)malloc(sizeof(No));
   novo->elemento = elemento;
   novo->esq = NULL;
   novo->dir = NULL;
   novo->nivel = 1;
   return novo;
}

/*
 * Essa função vai retornar o nivel do nó
 */
int getNivel (No* i)
{
   return (i == NULL) ? 0 : i->nivel;
}

/*
 * Essa função vai definir o nivel do nó
 */
No* setNivel (No* i)
{
   int x = getNivel (i->esq);
   int y = getNivel (i->dir);
   i->nivel = 1 + getMaior(x,y);
   return i;
}

int getMaior(int x, int y)
{
   return (x>y) ? x : y;
}

/*
 * Essa função vai inserir os personagens na minha arvore
 */
void inserir (Personagem personagem)
{
   inserirRec(personagem, &raiz);
}

void inserirRec (Personagem personagem, No** i)
{
   if (*i == NULL)
   {
      *i = novoNo(personagem);
   }
   else if (strcmp (personagem.nome, (*i)->elemento.nome) < 0)
   {
      inserirRec(personagem, &((*i)->esq));
   }
   else if (strcmp (personagem.nome, (*i)->elemento.nome) > 0)
   {
      inserirRec(personagem, &((*i)->dir));
   }
   else
   {
      errx(1, "Erro ao inserir");
   }
   *i = balancear((*i));

}


/*
 * Essa função vai balancear a arvore
 */
No* balancear (No* i)
{
   if (i != NULL)
   {
      int fator = getNivel(i->dir) - getNivel(i->esq);

      if (abs(fator) <= 1)
      {
         i = setNivel(i);
      }
      else if (fator == -2)
      {
         int fatorFilhoE = getNivel(i->esq->dir) - getNivel(i->esq->esq);
         if (fatorFilhoE == 1)
         {
            i->esq = rotacionarEsq(i->esq);
         }
         i = rotacionarEsq(i);
      }
      
      else if (fator == 2)
      {
         int fatorFilhoD = getNivel (i->dir->dir) - getNivel (i->dir->esq);

         if (fatorFilhoD == - 1)
         {
            i->dir = rotacionarDir(i->dir);
         }
         i = rotacionarEsq(i);
      }

   }
   return i;
}

/*
 * Essa função vai pesquisar na minha árvore
 */
bool pesquisar (char entrada[])
{
   printf ("raiz ");
   pesquisarRec (entrada, raiz);
}
bool pesquisarRec (char entrada[], No* i)
{
   bool resp;
   if (i == NULL)
   {
      resp = false;
   }
   else if (strcmp (entrada, i->elemento.nome) == 0)
   {
      resp = true;
   }
   else if (strcmp (entrada, i->elemento.nome) < 0)
   {
      printf ("esq ");
      resp = pesquisarRec (entrada, i->esq);
   }
   else if (strcmp (entrada, i->elemento.nome) > 0)
   {
      printf ("dir ");
      resp = pesquisarRec (entrada, i->dir);
   }
   return resp; 
}

/*
 * Essa função vai rotacionar  nó para a esquerda
 */
No* rotacionarEsq (No* no)
{
   No* noDir = no->dir;
   No* noDirEsq = noDir->esq;

   noDir->esq = no;
   no->dir = noDirEsq;

   setNivel(no);
   setNivel(noDir);
   return noDir;
}

/*
 * Essa funçã vai rotacionar o nó para a direita
 */
No* rotacionarDir(No* no)
{
   No* noEsq = no->esq;
   No* noEsqDir = noEsq->dir;

   noEsq->dir = no;
   no->esq = noEsqDir;

   setNivel(no);
   setNivel(noEsq);

   return noEsq;
}

void mostrarPos()
{
   printf ("[ ");
   mostrarPosRec(raiz);
   printf ("]");
}

void mostrarPosRec(No* i) 
{
   if (i != NULL) 
   {
      mostrarPosRec(i->esq);
      mostrarPosRec(i->dir);
      printf("%s ", i->elemento.nome);
   }
}
