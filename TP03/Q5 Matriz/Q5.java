class CelulaQuadrupla 
{
   public int elemento;
   public CelulaQuadrupla prox, ant, sup, inf;
   
   public CelulaQuadrupla()
   {
      this(0, null, null, null, null);
   }
   public CelulaQuadrupla (int x)
   {
      this (x, null, null, null, null);
   }
   public CelulaQuadrupla(int x, CelulaQuadrupla prox, CelulaQuadrupla ant, CelulaQuadrupla sup, CelulaQuadrupla inf)
   {
      this.elemento = x;
      this.prox = prox;
      this.ant = ant;
      this.sup = sup;
      this.inf = inf;
   }
   public void setEelemento(int e)
   {
      this.elemento = e;
   }
   public int getElemento()
   {
      return this.elemento;
   }
}

class Matriz
{
   private CelulaQuadrupla primeira = new CelulaQuadrupla();
   private CelulaQuadrupla ultima;
   private CelulaQuadrupla auxiliar;
   private CelulaQuadrupla embaixo;
   public int linhas, colunas;

  /*
   * Construtor vazio 
   */
   public Matriz()
   {
      this(2, 2);
   }

   /*
    * Construtor linhas e coluna
    */
   public Matriz (int linhas, int colunas)
   { 
      this.linhas = linhas;
      this.colunas = colunas;
      
      int contC = 1, contL= 1;

      // Construindo a primeira linha
      
     
      for (CelulaQuadrupla j = primeira; contC < colunas; j = j.prox, contC++)
      {
         j.prox = new CelulaQuadrupla();
         j.prox.ant = j; // PRECISA MODICAR: recebe "j", a celula que está sendo trabalhada no momento
      }
      
      contC = 0;
      CelulaQuadrupla aux = new CelulaQuadrupla(); // essa auxiliar vai ficar em cima das celulas que estamos criando, pra concatenar "celula.sup"

      for (CelulaQuadrupla i = primeira; contL < linhas; contL++)
      {
         aux = i; // sempre fica na linha de cima da que vamos trabalhar
         i.inf = new CelulaQuadrupla();
         i = i.inf; // essa parte ja concatena as celulas da coluna 0

         for (CelulaQuadrupla j = i; contC < colunas; j = j.prox, contC++)
         {
            
            if ( contC != 0) // ja estao concatenadas
            {
               j.sup = aux;
               aux.inf = j;
               j.ant = aux.ant.inf; 
               aux.ant.inf.prox = j;
            }
            
            if (aux.prox != null) // se não for a ultima coluna
            {
               aux = aux.prox;
               j.prox = new CelulaQuadrupla();
            }

         }
         contC = 0; // precisa zerar as colunas sempre que começar uma nova linha
      
      }
      /*
      for (int i = 0; i < linhas; i++)
      {

         for (int j = 0; j < colunas; j++)
         {
            // preenchendo a primeira linha:
            if (i == 0)
            {
               if (j == 0)
               {
                  this.primeira = new CelulaQuadrupla();
                  embaixo = primeira;
                  auxiliar = primeira;
               }else
               {
                  //CelulaQuadrupla celula = new CelulaQuadrupla(); 
                  auxiliar.prox = new CelulaQuadrupla(); 
                  auxiliar.prox.ant = auxiliar; 
                  auxiliar = auxiliar.prox; 
               }
            
            // criando o restante das linhas
            }
            else
            { 
               CelulaQuadrupla celula = new CelulaQuadrupla(); 
               auxiliar.inf = new CelulaQuadrupla(); 
               celula.sup = auxiliar; 
 
               if (j != 0)
               {
                  auxiliar.ant.inf.prox = celula; 
                  celula.ant = auxiliar.ant.inf; 
               }
               if (auxiliar.prox != null)
                  auxiliar = auxiliar.prox;
            }

      
         } 

         if (i != 0)
         {
            embaixo = embaixo.inf;
         }

         auxiliar = embaixo;
      } */  
   }

   public void Imprimir()
   {
      CelulaQuadrupla i;
      CelulaQuadrupla k = primeira;
      for (int l = 0; l < linhas; k = k.inf, l++)
      {
         i = k;
         for (int j = 0; j < colunas; i = i.prox, j++)
         { 
            MyIO.print(i.getElemento() + " ");
         }

         MyIO.println("");
      }
      //MyIO.println("");
   }
   
   public void Setar()
   {
      int elemento = 0;
      CelulaQuadrupla PercorrerColuna = new CelulaQuadrupla();
      CelulaQuadrupla PercorrerLinha = primeira;
      
      for (int i = 0; i < this.linhas; i++, PercorrerLinha = PercorrerLinha.inf)
      {
         PercorrerColuna = PercorrerLinha;
         for (int j = 0; j < this.colunas; j++, PercorrerColuna = PercorrerColuna.prox)
         {
            elemento = MyIO.readInt();
            PercorrerColuna.setEelemento(elemento);
         }
      } 
   }

   /*
    * Esse metodo vai retornar o elemento da posição desejada
    */
   public int get(int pLinha, int pColuna)
   {
      int resp = 0;
      CelulaQuadrupla i;
      CelulaQuadrupla k = primeira;
      
      for (int l = 0; l < pLinha; k = k.inf, l++)
      {
         i = k;
         for (int j = 0; j < pColuna; i = i.prox, j++)
         {
            MyIO.println (i.getElemento());
            resp = i.getElemento();
         }
      }

      return resp;
   }

   public Matriz Soma (Matriz m)
   {
      Matriz resp = new Matriz(this.linhas, this.colunas);
    
      if(this.linhas == m.linhas && this.colunas == m.colunas)
      {
         CelulaQuadrupla A1 = this.primeira; // percorre as linhas da matriz 1
         CelulaQuadrupla A2 = A1; // percorre as colunas da matriz 1
       
         CelulaQuadrupla B1 = m.primeira; // percorre as colunas da matriz 2
         CelulaQuadrupla B2 = B1; // percorre as colunas da matriz 2

         CelulaQuadrupla C1 = resp.primeira;
         CelulaQuadrupla C2 = C1;
         

         for (int i = 0; i < this.linhas; i++, A1 = A1.inf, B1 = B1.inf, C1 = C1.inf )
         {
            A2 = A1;
            B2 = B1;
            C2 = C1;
            for (int j = 0; j < this.colunas; j++, A2 = A2.prox, B2 = B2.prox, C2 = C2.prox)
            {
                C2.elemento = A2.elemento + B2.elemento;
            }
         }
      }
   
      return resp;
   }


   public Matriz Multiplicacao(Matriz m)
   { 
      Matriz resp = new Matriz(this.linhas, this.colunas);
    
      if(this.linhas == m.linhas && this.colunas == m.colunas)
      {
         CelulaQuadrupla A = this.primeira; // corresponde a matriz 1
         CelulaQuadrupla i = A; // auxiliar de A 
       
         CelulaQuadrupla B = m.primeira; // corresponde a matriz 2, precisa de duas auxiliares
         CelulaQuadrupla j = B;         // pois como a coluna precisa voltar para o inicio sempre que um novo elemento
         CelulaQuadrupla manterB = B;  // começar a ser formado, não podemos perder seu valor original

         CelulaQuadrupla C = resp.primeira; // corresponde a matriz que está sendo criada
         CelulaQuadrupla manterC = C;
         CelulaQuadrupla aux = C;  
         
         int elemento = 0;
         
         // esse for garante a formação das linhas e o retorno de B para o inicio da coluna 1
         for (int conti = 0; conti < this.linhas; A = A.inf, B = manterB, C = manterC.inf, conti++)
         {
            manterB = B;
            manterC = C;
         
            // Esses 2 fors garantem a formação dos elementos de cada linha
            for(int cont = 0; cont < this.colunas; cont++, B = B.prox, C = C.prox)
            {       
               i = A;
               j = B;
               aux = C;
               elemento = 0;
       
             
               for( ; i != null; i = i.prox, j = j.inf)
               {
                  elemento = elemento + (i.elemento * j.elemento);
               }
          
               aux.elemento = elemento;
               aux = aux.prox;
            }
         }
      }
      return resp;
   }
   
   /*
    * Esse metodo vai mostrar a diagonal principal da matriz
    */
   public void MostrarDP()
   {
      if (isQuadrada())
      {
         for (CelulaQuadrupla i = primeira; i != null; i = i.inf)
         {
            MyIO.print (i.getElemento() + " ");
            if (i.prox != null)
            {
               i = i.prox;
            }
         }
         MyIO.println ("");
      } 
   }
   
   /*
    * Esse metodo vai mostrar a diagonal secundaria da matriz
    */
   public void MostrarDS()
   {
      if (isQuadrada())
      {
         CelulaQuadrupla i = primeira;
         for ( ; i.prox != null; i = i.prox); 
         for ( ; i != null; i = i.inf)
         {
            MyIO.print (i.elemento + " ");
            if (i.ant != null)
            {
               i = i.ant;
            }
         }
         MyIO.println ("");
      }
   }

   /*
    * Esse metodo verifica se a matriz é quadrada
    */
   public boolean isQuadrada()
   {
      return (this.linhas == this.colunas);

   }
}

class Q5
{
   public static void main (String[] args)
   {
      int numeroTestes = MyIO.readInt();

      for (int i = 0; i < numeroTestes; i++)
      {
         int linhas = MyIO.readInt();
         int colunas = MyIO.readInt();
      
         Matriz matriz = new Matriz(linhas, colunas);
         matriz.Setar();
      
         linhas = MyIO.readInt();
         colunas = MyIO.readInt();
      
         Matriz matriz2 = new Matriz(linhas, colunas);
         matriz2.Setar();
         
         matriz.MostrarDP();
         matriz.MostrarDS();

         Matriz resp = matriz.Soma(matriz2);
         resp.Imprimir();
 
         resp = matriz.Multiplicacao(matriz2);
         resp.Imprimir();
      }
   }
}
