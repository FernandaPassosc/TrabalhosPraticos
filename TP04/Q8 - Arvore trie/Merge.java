import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.InputMismatchException;

class Merge
{
   public static int comparacoes = 0;
   public static ArvoreTrie arvore;
   public static void main (String[] args) throws Exception
   {
      final long inicio = System.nanoTime();
      MyIO.setCharset("UTF-8");
      //MyIO.setCharset("iso-8859-1");
      arvore = new ArvoreTrie();
      String entrada = "";
      entrada = MyIO.readLine();
      
      while(!entrada.contains("FIM"))
      {  
         //MyIO.print ("entrada " + entrada);
         Personagem personagem = new Personagem();
         personagem.LeArquivo(entrada);
         arvore.inserir(personagem.getnome());
         entrada = MyIO.readLine();
      }

      entrada = MyIO.readLine();
      ArvoreTrie arvore2 = new ArvoreTrie();

      while (!entrada.contains("FIM"))
      {
         Personagem personagem = new Personagem();
         personagem.LeArquivo(entrada);
         arvore2.inserir(personagem.getnome());
         entrada = MyIO.readLine();
      }
      arvore2.Merge();
      boolean presente;
      //boolean presente2;
      entrada = MyIO.readLine();

      while(!entrada.contains("FIM"))
      {
         MyIO.print (entrada + " ");
         presente = arvore.pesquisar(entrada);
         //presente2 = arvore2.pesquisar(entrada);
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

      //escrever.println ("matricula: 680624\t" + "tempo: " + tempo + "\t" + "quantidade de comparações: " + Arvore.comparacoes); 
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

   public static Personagem CloneNulo ()
   {
      Personagem p = new Personagem();
      p.setnome("nulo");
      p.setaltura(0);
      p.setpeso(0.0);
      p.setcorDoCabelo("nao");
      p.setcorDaPele("nao");
      p.setcorDosOlhos("nao");
      p.setanoNascimento("nao");
      p.setgenero("nao");
      p.sethomeWorld("nao");

      return p;
   }
 

} // FIM DA CLASSE PERSONAGENS

class No
{
   public char elemento;
   public int tamanho = 255;
   public No[] prox;
   public boolean folha;

   public No()
   {
      this(' ');
   }

   public No (char elemento)
   {
      this.elemento = elemento;
      prox = new No[tamanho];
      for (int i = 0; i < tamanho; i++) prox[i] = null;
      folha = false;
   }

   public static int hash (char x)
   {
      return (int)x;
   }
}
class Hash
{
   int tabela[];
   int tamanho;
   //Personagem NULO = Personagem.CloneNulo();

   public Hash()
   {
      this(255);
   }

   public Hash (int tamanho)
   {
      this.tamanho = tamanho;

      this.tabela = new int [this.tamanho];

      for (int i = 0; i < tamanho; i++)
      {
         tabela[i] = -1;
      }
   } 
}

class ArvoreTrie
{
   private No raiz;

   public ArvoreTrie()
   {
      raiz = new No();
   }

   public void inserir (String s) throws Exception
   {
      inserir(s, raiz, 0);
   }
   
   private void inserir(String s, No no, int i) throws Exception 
   {
        //MyIO.println ("vou inserir o: " + s);
        if(no.prox[s.charAt(i)] == null)
        {
           no.prox[s.charAt(i)] = new No(s.charAt(i));

            if(i == s.length() - 1)
            {
               no.prox[s.charAt(i)].folha = true;
            }else
            {
                inserir(s, no.prox[s.charAt(i)], i + 1);
            }

        } else if (no.prox[s.charAt(i)].folha == false && i < s.length() - 1)
        {
            inserir(s, no.prox[s.charAt(i)], i + 1);

        } else 
        {
            throw new Exception("Erro ao inserir!");
        } 
   }

   public boolean pesquisar(String s) throws Exception 
   {
        return pesquisar(s, raiz, 0);
   }

   public boolean pesquisar(String s, No no, int i) throws Exception 
   {
      boolean resp;
      if(no.prox[s.charAt(i)] == null)
      {
         resp = false;
      
      }else if(i == s.length() - 1)
      {
         resp = (no.prox[s.charAt(i)].folha == true);
      
      }else if(i < s.length() - 1 )
      {
         resp = pesquisar(s, no.prox[s.charAt(i)], i + 1);
      
      }else 
      {
         throw new Exception("Erro ao pesquisar!");
      }
        return resp;
   }

   public void mostrar()
   {
      mostrar("", raiz);
   }

   public void mostrar(String s, No no) 
   {
      if(no.folha == true)
      {
            //System.out.println("Palavra: " + (s + no.elemento));
      }else 
      {
         for(int i = 0; i < no.prox.length; i++)
         {
            if(no.prox[i] != null)
            {
               // System.out.println("ESTOU EM (" + no.elemento + ") E VOU PARA (" + no.prox[i].elemento + ")");
               mostrar(s + no.elemento, no.prox[i]);
            }
         }
       }
    }

   public void Merge() throws Exception 
   {
      Merge ("", raiz);
   }

   private void Merge(String s, No no) throws Exception 
   {
      if (no.folha == true)
      {
         s += no.elemento;
         String resp = "";
         for (int i = 1; i < s.length(); i++)
         {
            resp += s.charAt(i);
         }
         //MyIO.println ("vou inserir o: '" + resp +"'");
         Merge.arvore.inserir(resp);
      }
      else
      {
         for (int i = 0; i < no.prox.length; i++)
         {
            if (no.prox[i] != null) Merge(s + no.elemento, no.prox[i]);
         }
      }
   
   }

      



}
