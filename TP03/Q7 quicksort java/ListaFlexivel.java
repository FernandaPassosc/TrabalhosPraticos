import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class ListaFlexivel
{
   public static Lista lista = new Lista();
   
   public static void main (String[] args) throws Exception
   {
      MyIO.setCharset("UTF-8"); 
      String entrada = "";
      entrada = MyIO.readLine();

      while(!entrada.contains("FIM"))
      {  
         Personagem personagem = new Personagem();
         personagem.LeArquivo(entrada);
         lista.InserirFim(personagem);
         entrada = MyIO.readLine();
      }

      lista.quicksort();
      lista.Desempatar();
      lista.mostrar();
   }
}
    
class Personagem
{
    // ATRIBUTOS 
    private String frase_completa = "";
    private String nome = "";
    private int altura = 0;
    private double peso = 0;
    private String corDoCabelo = "";
    private String corDaPele = "";
    private String corDosOlhos = "";
    private String anoNascimento = "";
    private String genero = "";
    private String homeWorld = "";
    public Personagem prox;

 
    // CONSTRUTOR
    Personagem(String nome, int altura, double peso, String corDoCabelo, String corDaPele, String corDosOlhos, String anoNascimento, String genero, String homeWorld){  
       this.frase_completa = frase_completa;
       this.nome = nome;
       this.altura = altura;
       this.peso = peso;
       this.corDoCabelo = corDoCabelo;
       this.corDaPele = corDaPele;
       this.corDosOlhos = corDosOlhos;
       this.anoNascimento = anoNascimento;
       this.genero = genero;
       this.homeWorld = homeWorld;
       this.prox = null;
    } 
     
    // CONSTRUTOR VAZIO
    Personagem ()
    {
    }
       
    // METODOS

// -------------------------------------------SET'S AND GET'S----------------------------------------------------------------------------- 
    public void setfrase_completa (String frase_completa)
    {
       this.frase_completa = frase_completa;
    }
    public void setnome (String nome)
    {
       this.nome = nome;
    }
    public void setaltura (int altura)
    {
       this.altura = altura;
    }
    public void setpeso (double peso)
    {
       this.peso = peso;
    }
    public void setcorDoCabelo (String corDoCabelo)
    {
       this.corDoCabelo = corDoCabelo;
    }
    public void setcorDaPele (String corDaPele)
    {
       this.corDaPele = corDaPele;
    }
    public void setcorDosOlhos (String corDosOlhos)
    {
       this.corDosOlhos = corDosOlhos;
    }
    public void setanoNascimento (String anoNascimento)
    {
       this.anoNascimento = anoNascimento;
    }
    public void setgenero (String genero)
    {
       this.genero = genero;
    }
    public void sethomeWorld (String homeWorld)
    {
       this.homeWorld = homeWorld;
    }
    public String getfrase_completa()
    {
       return this.frase_completa;
    }
    public String getnome ()
    {
       return this.nome;
    }
    public int getaltura()
    {
       return this.altura;
    }
    public double getpeso()
    {
       return this.peso;
    }
    public String getcorDoCabelo()
    {
       return this.corDoCabelo;
    }
    public String getcorDaPele()
    {
       return this.corDaPele;
    }
    public String getcorDosOlhos()
    {
       return this.corDosOlhos;
    }
    public String getanoNascimeto()
    {
       return this.anoNascimento;
    }
    public String getgenero()
    {
       return this.genero;
    }
    public String gethomeWorld()
    {
       return this.homeWorld;
    }
//-----------------------------------i-------------END OF SET'S E GET'S-------------------------------------------------------------------
    
    // METODO PARA LEITUA DO ARQUIVO TXT
    
    public void LeArquivo (String patch)
    {
       String dados_do_personagem = "";

       try
       {
          FileReader arq = new FileReader(patch); // abrindo o arquivo 
          BufferedReader ler_arq = new BufferedReader(arq); // lendo meu arquivo

          String o_que_ta_dentro_do_arquivo = ler_arq.readLine();
          
          while (ler_arq.ready())
          {
             o_que_ta_dentro_do_arquivo = ler_arq.readLine(); // é uma string só, nao precisa concatenar
             
          }

          this.frase_completa = o_que_ta_dentro_do_arquivo;
          arq.close();
          ler_arq.close();
          
       }catch (IOException ex)
       {
          System.err.printf("Erro %s.\n", ex.getMessage());
       }

       SepararDados();

    } // FIM DA LEITURA DO ARQUIVO

    // METODO PARA SEPARAR OS DADOS DO PERSONAGEM

    public void SepararDados()
    {
       String[] frase_separada = this.frase_completa.split("',"); // A frase separada pega por exemplo "}'name': 'Ackabar'
       String[] caracteristicas = new String[2000]; // a caracteristica vai pegar apenas o nome ou altura...
       
       for (int i = 0; i <= 8; i++) // usei menor ou igual a oito porque cada entrada tem, que me interessa,
       {                            //9 strings separadas por 8 virgulas
                    
          caracteristicas = frase_separada[i].split ("'");

          //OBS: switch (caracteristicas[i]) SWITCH NAO TRABALHA COM BOOLEAN
          
          if (caracteristicas[1].contains("name"))
          {
                setnome(caracteristicas[3]);
          }
          else if (caracteristicas[1].contains("height"))
          {
                if (caracteristicas[3].contains("unknow"))
                {
                   setaltura(0);
                }
                else setaltura(Integer.parseInt(caracteristicas[3]));
                
          }
          else if (caracteristicas[1].contains("mass"))
          {
                if (caracteristicas[3].contains("unknow"))
                {
                   setpeso(0);
                }
                else setpeso(Double.parseDouble(TiraVirgula(caracteristicas[3])));
          }
          else if (caracteristicas[1].contains("hair_color"))
          {
                setcorDoCabelo(caracteristicas[3]);
          }
          else if (caracteristicas[1].contains("skin_color"))
          {
                setcorDaPele(caracteristicas[3]);
          }
          else if (caracteristicas[1].contains("eye_color"))
          {
                setcorDosOlhos(caracteristicas[3]);
          }
          else if (caracteristicas[1].contains("birth_year"))
          {
                setanoNascimento(caracteristicas[3]);
          }
          else if (caracteristicas[1].contains("gender"))
          {
                setgenero(caracteristicas[3]);
          }
          else if (caracteristicas[1].contains("homeworld"))
          {
                sethomeWorld (caracteristicas[3]);
          }


       }// FIM DO FOR
    }// FIM DO METODO SEPARAR DADOS

    public static String TiraVirgula(String s)
    {
        String aux = "";
        for (int i = 0; i < s.length(); i++)
        {
            if (s.charAt(i) != ',')
            {
                 aux += s.charAt(i);
            }
        }
        return aux;
    }

    public Personagem Clone()
    {  
        Personagem p = new Personagem();
    
        p.setnome(this.nome);
        p.setaltura(this.altura);
        p.setpeso(this.peso);
        p.setcorDoCabelo(this.corDoCabelo);
        p.setcorDaPele(this.corDaPele);
        p.setcorDosOlhos(this.corDosOlhos);
        p.setanoNascimento(this.anoNascimento);
        p.setgenero(this.genero);
        p.sethomeWorld(this.homeWorld);

        return p;
    }

    public void imprimir() 
    {
       MyIO.println (" ## " + this.nome + " ## " + this.altura + " ## "  + String.valueOf(this.peso).replace(".0","") + " ## " + this.corDoCabelo + " ## " + this.corDaPele         + " ## " + this.corDosOlhos + " ## " + this.anoNascimento + " ## " + this.genero + " ## " + this.homeWorld + " ## ");             
    }
 

} // FIM DA CLASSE PERSONAGENS

class Celula
{
    public Personagem elemento;
    public Celula prox;
    public Celula ant;

    public Celula()
    {
        this (null);
    }

    public Celula (Personagem elemento)
    {
        this.elemento = elemento;
        this.prox = null;
        this.ant = null;
    }
}

class Lista
{
   private Celula NoCabeca;
   private Celula Ultima;
    
   public Lista() 
   {
      NoCabeca = new Celula();
      Ultima = NoCabeca;
   }

        
   /*
    * Esse metodo vai inserir um personagem no final da minha lista
    */
   public void InserirFim (Personagem novo) 
   {
      Ultima.prox = new Celula(novo);
      Ultima.prox.ant = Ultima;
      Ultima = Ultima.prox;
   }

   /*
    * Esse metodo vai mostrar os elementos da lista
    */
   public void mostrar()
   {
      Celula i = NoCabeca.prox;
      
      for (int cont = 0; i != null; i = i.prox, cont++)
      {
         
         i.elemento.imprimir();
      }
   }

   /*
    * Esse metodo vai verificar o tamanho da lista
    */
   public int tamanho()
   {
      int tamanho = 0;

      for (Celula i = NoCabeca.prox; i != null; i = i.prox, tamanho++);
      return tamanho;
   }

   public void quicksort()
   {
      quicksort(NoCabeca.prox, Ultima);
   }

   private void quicksort(Celula esq, Celula dir)
   {
      Celula i = esq;
      Celula j = dir;

      String pivo = meio(esq,dir).elemento.getcorDoCabelo();

      while (posicao(i) < posicao(j))
      {
         while (i.elemento.getcorDoCabelo().compareTo(pivo) < 0)
         {
            i = i.prox;
         }
         while ((j.elemento.getcorDoCabelo().compareTo(pivo) > 0))
         {
            j = j.ant;
         }
         if(posicao(i) <= posicao(j))
         {
            swap(i, j);
            i = i.prox;
            j = j.ant;
         }
      }
      if (posicao(esq) < posicao(j)) quicksort(esq, j);
      if (posicao(i) < posicao(dir)) quicksort(i, dir);
   }

   public Celula meio(Celula inicio, Celula fim)
   {
      int tamanho = 0;
      for (Celula i = inicio; i != fim; i = i.prox, tamanho++);

      Celula resp = inicio;

      for( ; tamanho > 0; tamanho--, resp = resp.prox);
      return resp;
   }

   public int posicao (Celula celula)
   {
      int p = 0;
      for (Celula i = NoCabeca; i != celula; i = i.prox, p++);
      return p;
   
   }

   public void swap (Celula i, Celula j)
   {
      Personagem aux = j.elemento.Clone();
      j.elemento = i.elemento.Clone();
      i.elemento = aux.Clone();
   }

   /*public void Desempatar()
   {
      for (Celula i = NoCabeca.prox; i != Ultima; i = i.prox)
      {
         if (i.elemento.getcorDoCabelo().equals(i.prox.elemento.getcorDoCabelo()) && i.elemento.getnome().compareTo(i.prox.elemento.getnome()) > 0)
         {
            swap(i, i.prox);
         }
      }
   }*/
   
   public void Desempatar()
   {
      int PosI = 0;
      int PosF = 0;
      Celula temp1;
      Celula temp2;

      for (Celula i = NoCabeca.prox; i != null; i = i.prox, PosF++)
      {
         if ((i != Ultima) && (i.elemento.getcorDoCabelo().equals(i.prox.elemento.getcorDoCabelo())))
         {
            temp1 = i;
            PosI = PosF;
            while ( i != Ultima && i.elemento.getcorDoCabelo().equals(i.prox.elemento.getcorDoCabelo()))
            {
               i = i.prox;
               PosF++;
            }
            
            temp2 = i;
            if (PosF - PosI > 1) Insercao(temp1, temp2);
         }
      }
   
   }

   public void Insercao (Celula inicio, Celula fim)
   {
      Personagem aux;
      String menor;
      
      for ( Celula i = inicio; i != fim.prox; i = i.prox)
      {     
         aux = i.elemento;
         Celula j = i.ant;
         menor = i.elemento.getnome();

         while ((j != inicio.ant ) && (j.elemento.getnome().compareTo(menor)) > 0)
         {
           
            j.prox.elemento = j.elemento.Clone();
            j = j.ant;
         }
         j.prox.elemento = aux.Clone();
         
      }
   
   }
}
