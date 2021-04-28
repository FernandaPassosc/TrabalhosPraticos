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

/*typedef struct Hash
{
   
}
*/

// PROTOTIPOS
Personagem LerArquivo (Personagem P, char entrada[]);
Personagem SepararPalavras (Personagem P, char leitura_arq[]);
char* TiraVirgula (char s[]);
void SepararComando (char entrada[], Personagem novo);
void start();
Celula* novaCelula (Personagem elemento);
void startHash();
void inserirLista(Personagem p, int i);
int posicao (int altura);
void inserirHash(Personagem p);
bool pesquisar (char nome[]);
void mostrar (Personagem p);

// VARIAVEIS GLOBAIS
int n = 0; // quantidade de personagens


int main()
{
   Personagem P;
   //char* entrada = (char*)malloc(sizeof(char)*100);
   char entrada[1000];
   start();
   startHash();

   fgets (entrada, 100, stdin);
   entrada[strlen(entrada) - 1] = '\0';
   
   while (!strcmp (entrada, "FIM") == 0)
   {  
      P = LerArquivo (P, entrada);
      inserirHash(P); 
      //printf ("\ntopo: %s", topoP->elemento.nome);
      fgets (entrada, 100, stdin);
      entrada[strlen(entrada) - 1] = '\0';
   }
   
   fgets (entrada, 120, stdin);
   entrada[strlen(entrada) - 1] = '\0';
   bool presente=false;

   while (!strcmp(entrada, "FIM") == 0)
   {
      printf ("%s ", entrada);
      presente = pesquisar(entrada);
      (presente) ? printf ("SIM") : printf("NÃO");
      printf ("\n");
      fgets (entrada, 100, stdin);
      entrada[strlen(entrada) - 1] = '\0';

   }
 
  // mostrar(P);
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

Personagem PersonagemNulo()
{
   Personagem nulo;
   strcpy (nulo.nome, "nulo");
   nulo.altura = 0;
   nulo.peso = 0.0;
   strcpy (nulo.cor_do_cabelo, "nao");
   strcpy (nulo.cor_da_pele, "nao");
   strcpy (nulo.cor_dos_olhos, "nao");
   strcpy (nulo.ano_aniversario, "nao");
   strcpy (nulo.genero, "nao");
   strcpy (nulo.home_world, "nao");
   return nulo;
}


//----------------------Lista------------------------------

Celula* novaCelula (Personagem elemento)
{
   Celula* nova = (Celula*)malloc(sizeof(Celula));
   nova->elemento = elemento;
   nova->prox = NULL;
   return nova;
}

Celula *primeiro[25];
Celula *ultimo[25];

/*
 * Criar o inicio da lista
 */
void start()
{
   Personagem nulo = PersonagemNulo();
   //primeiro = novaCelula(nulo);
   //ultimo = primeiro;
}

/*
 * Inserir na lista
 */

Celula* tabela[25];

void inserirLista(Personagem p, int i)
{
   if (ultimo[i] == NULL) ultimo[i] = tabela[i];
   
   ultimo[i]->prox = novaCelula(p); 
   ultimo[i] = ultimo[i]->prox;  
}

//------------------------------hash------------------------------

void startHash()
{
   Personagem NULO = PersonagemNulo();
   
   //construindo a tabela
   for (int i = 0; i < 25; i++)
   {
      primeiro[i] = tabela[i];
      tabela[i] = novaCelula(NULO);
 
      //start();
      // tabela[i]->prox = start()
   }

}
int posicao (int altura)
{
   return altura % 25;
}

void inserirHash(Personagem p)
{
  int pos = posicao(p.altura);

  if (strcmp(tabela[pos]->elemento.nome, "nulo") == 0)
  {
      primeiro[pos] = tabela[pos]; 
      tabela[pos]->elemento = p;
  }
  else
  {
      inserirLista(p, pos);
  }
}

bool pesquisar (char nome[])
{
   bool resp = false;
   for (int i = 0; i < 25; i++)
   {
      if (strcmp(tabela[i]->elemento.nome, nome)==0)
      {
         resp = true;
         i = 25;
      }
      else if (tabela[i]->prox != NULL)
      {
         for (Celula* j = tabela[i]; j != NULL; j = j->prox)
         {
            if(strcmp(j->elemento.nome, nome) == 0)
            {
               resp = true;
            }
         }
      }
   }
   return resp;
}

void mostrar (Personagem p)
{
   printf ("saco: %s", ultimo[16]->elemento.nome);
   for (int i = 0; i < 25; i++)
   {
      if (tabela[i]->prox !=NULL)
      {
      for (Celula* j = primeiro[i]->prox; j != ultimo[i]; j = j->prox)
      {
         if (strcmp(p.nome, "Barriss Offee") == 0) printf ("ultimo: aaa%s", ultimo[i]->prox->elemento.nome); 

         printf("Persoanem: %s na lista de posição: %i\n", j->elemento.nome, i);
      }
      }
   }

}
