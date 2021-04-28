#include <stdio.h>
#include <stdlib.h>

typedef struct Celula
{
   int elemento;
   struct Celula* prox;
   struct Celula* ant;
}Celula;




/*
 * Essa função vai criar minha celula
 */
Celula* novaCelula (int elemento)
{
   Celula* nova = (Celula*)malloc(sizeof(Celula));
   nova->elemento = elemento;
   nova->prox = nova->ant = NULL;
   return nova;
}

int main()
{
   Celula* noCabeca(0);
   Celula* Ultima = noCabeca
   for (Celula* i = NoCabeca, int cont = 0; cont<7; Ultima = Ultima->prox)
   {
      i->elemento = MyIO.readInt();
   }
   



}
