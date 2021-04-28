import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.InputMismatchException;

class Arvore
{
   public static int comparacoes = 0;

   public static void main (String[] args) throws Exception
   {
      final long inicio = System.nanoTime();
      MyIO.setCharset("iso-8859-1");
      ArvoreBinaria arvore = new ArvoreBinaria();
      arvore.inserir();
      String entrada = "";
      entrada = MyIO.readLine();
      
      while(!entrada.contains("FIM"))
      {  
         Personagem personagem = new Personagem();
         personagem.LeArquivo(entrada);
         arvore.inserir1(personagem);
         entrada = MyIO.readLine();
      }

      entrada = MyIO.readLine();
      boolean presente;

      while(!entrada.contains("FIM"))
      {
         MyIO.print (entrada + " " + "raiz ");
         presente = arvore.pesquisar(entrada);
         if(presente) MyIO.print ("SIM");
         else{MyIO.print ( "N"+(char)195+"O" );}
         MyIO.println ("");
         entrada = MyIO.readLine();
      }
   
      //arvore.mostrarPre();
      final long tempoFinal = System.nanoTime() - inicio;
      ArquivoLog(tempoFinal);
   }
   public static void ArquivoLog (final long tempo) throws Exception 
   {
      FileWriter arq = new FileWriter ("680624_arvoreArvore.txt");
      PrintWriter escrever = new PrintWriter(arq);

      escrever.println ("matricula: 680624\t" + "tempo: " + tempo + "\t" + "quantidade de comparações: " + Arvore.comparacoes); 
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

class NoString
{
    public Personagem elemento;
    public NoString dir;
    public NoString esq;


    public NoString(Personagem elemento)
    {
        this (elemento, null, null);
    }

    public NoString (Personagem elemento, NoString dir, NoString esq)
    {
        this.elemento = elemento;
        this.dir = null;
        this.esq = null;
    }
}
   
class NoInteiros
{
   public int elemento;
   public NoInteiros esq;
   public NoInteiros dir;
   public NoString comeco;

   public NoInteiros(int x)
   {
      this(x, null, null);
   }

   public NoInteiros(int x, NoInteiros esq, NoInteiros dir)
   {
      this.elemento = x;
      this.esq = esq;
      this.dir = dir;
      this.comeco = comeco;
   }
}

class ArvoreBinaria
{
   private NoInteiros raiz;
  
   /*
    * Construtor
    */
   public ArvoreBinaria()
   {
      raiz = null;
   }

   /*
    * Esse método vai chamar o metodo de inserir mandando os elementos inteiros
    */
   public void inserir() throws Exception
   {
      raiz = inserir(7, raiz);
      raiz = inserir(3, raiz);
      raiz = inserir(11, raiz);
      raiz = inserir(1, raiz);
      raiz = inserir(5, raiz);
      raiz = inserir(9, raiz);
      raiz = inserir(12, raiz);
      raiz = inserir(0, raiz);
      raiz = inserir(2, raiz);
      raiz = inserir(4, raiz);
      raiz = inserir(6, raiz);
      raiz = inserir(8, raiz);
      raiz = inserir(10, raiz);
      raiz = inserir(13, raiz);
      raiz = inserir(14, raiz);
   }

   private NoInteiros inserir(int elemento, NoInteiros i) throws Exception
   {
      Arvore.comparacoes++;
      if (i == null)
      {
         i = new NoInteiros(elemento);
      }
      else if (elemento < i.elemento)
      {
         Arvore.comparacoes++;
         i.esq = inserir(elemento, i.esq);
      }
      else if (elemento > i.elemento)
      {  
         Arvore.comparacoes++;
         i.dir = inserir(elemento, i.dir);
      }
      else
      {
         new Exception ("Elemento inteiro ja existente");
      }
   
      return i; 
   }

   /*
    * Esse método vai mostrar começando pela raiz
    */
   public void mostrarPre() throws Exception
   {
      mostrarPre(raiz);
   }

   private void mostrarPre(NoInteiros i)
   {
      Arvore.comparacoes++;
      if (i != null)
      {
         MyIO.print (i.elemento + " ");
         mostrarPre(i.esq);
         mostrarPre(i.dir);
      }
   }


   /*
    * Esse método vai inserir meu personagem na árvore de acordo com o mode 15 da altura
    */
   
   public void inserir1(Personagem personagem) throws Exception
   {
      //MyIO.println ("pers nome: " + personagem.getnome());
      inserir1(personagem, raiz);
      //MyIO.println ("nome do no: " + raiz.comeco.elemento.getnome());

   }

   private void inserir1(Personagem personagem, NoInteiros i) throws Exception
   {
      Arvore.comparacoes++;
      if (personagem.getaltura() % 15 == i.elemento)
      {
         i.comeco = inserir2(personagem, i.comeco);
        // MyIO.println ("nome do no: " + i.comeco.elemento.getnome());
      }
      else if(personagem.getaltura() % 15 < i.elemento)
      {
         Arvore.comparacoes++;
         inserir1(personagem, i.esq);
      }
      else if (personagem.getaltura() % 15 > i.elemento)
      {
         Arvore.comparacoes++;
         inserir1(personagem, i.dir);
      }
      else
      {
         new Exception ("Erro");
      }

     
   //   MyIO.println ("mod altura: " + personagem.getaltura() % 15);
      //MyIO.println ("nome do no: " + i.comeco.elemento.getnome()); 
   }

   private NoString inserir2 (Personagem personagem, NoString i) throws Exception
   {
      if (i == null)
      {  
         //MyIO.println ("vou colocar um elemento");
         i = new NoString (personagem);
         //MyIO.println ("coloquei: " + i.elemento.getnome());
      }
      else if (personagem.getnome().compareTo(i.elemento.getnome()) < 0)
      {
         i.esq = inserir2 (personagem, i.esq);
      }
      else if (personagem.getnome().compareTo(i.elemento.getnome()) > 0)
      {
        i.dir =  inserir2(personagem, i.dir);
      }
      else
      {
         new Exception ("Erro");
      
      }
      return i;
   }

   public boolean pesquisar(String nomeProcurado) 
   {
      return pesquisar1(nomeProcurado, raiz);
   }
   
   private boolean pesquisar1 (String nomeProcurado, NoInteiros i) 
   {
      boolean resp;
   
      if (i == null)
      {
         resp = false;
      }
      else
      {
         //MyIO.println ("nome do perrengue: " + i.comeco.elemento.getnome());
         resp = pesquisar2(nomeProcurado, i.comeco);

         if (!resp)
         {
            MyIO.print ("esq ");
            resp = pesquisar1(nomeProcurado, i.esq);

            if (!resp)
            {
               MyIO.print ("dir ");
               resp = pesquisar1(nomeProcurado, i.dir);
            }
         }
      }
      
      return resp;
   }

   private boolean pesquisar2(String nomeProcurado, NoString i) 
   {
     boolean resp;
   
      if (i == null)
      {
         resp = false;
      }
      else if (nomeProcurado.compareTo(i.elemento.getnome()) == 0)
      {
         resp = true;
      }
      else 
      {
         MyIO.print ("ESQ ");
         resp = pesquisar2(nomeProcurado, i.esq);

         if (!resp)
         {
            MyIO.print ("DIR ");
            resp = pesquisar2(nomeProcurado, i.dir);
         }
      }
      
      return resp;
      
      /*boolean resp;
      
      if (i == null)
      {    
         resp = false;
      }
      else if (nomeProcurado.compareTo(i.elemento.getnome()) == 0)
      {
         resp = true;
      
      }else if (nomeProcurado.compareTo(i.elemento.getnome()) < 0)
      {
         MyIO.print ("ESQ ");
         resp = pesquisar2(nomeProcurado, i.esq);
      
      }else
      {
         MyIO.print ("DIR ");
         resp = pesquisar2(nomeProcurado, i.dir);
      }*/
      
      //return resp;   
   }

}
