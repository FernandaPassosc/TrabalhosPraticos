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
      //MyIO.setCharset("UTF-8"); 
      MyIO.setCharset("iso-8859-1");
      ArvoreAlvinegra arvore = new ArvoreAlvinegra();
      String entrada = "";
      entrada = MyIO.readLine();
      
      while(!entrada.contains("FIM"))
      {  
         Personagem personagem = new Personagem();
         personagem.LeArquivo(entrada);
         arvore.inserir(personagem);
         entrada = MyIO.readLine();
      }

      entrada = MyIO.readLine();
      boolean presente;

      while(!entrada.contains("FIM"))
      {
         MyIO.print (entrada + " " + "raiz ");
         presente = arvore.pesquisar(entrada);
         if(presente) MyIO.print ("SIM");
         else{MyIO.print ( "NÃO");}
         //else{MyIO.print ( "N"+(char)195+"O" );}
         MyIO.println ("");
         entrada = MyIO.readLine();
      }
   
      final long tempoFinal = System.nanoTime() - inicio;
      ArquivoLog(tempoFinal);
   }
   public static void ArquivoLog (final long tempo) throws Exception 
   {
      FileWriter arq = new FileWriter ("680624_arvoreBinaria.txt");
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

class No
{
    public boolean cor;
    public Personagem elemento;
    public No dir;
    public No esq;


    public No(Personagem elemento)
    {
        this (elemento, false, null, null);
    }

    public No (Personagem elemento, boolean cor)
    {
         this(elemento, cor, null, null);
    }


    public No (Personagem elemento, boolean cor, No dir, No esq)
    {
        this.elemento = elemento;
        this.cor = cor;
        this.dir = dir;
        this.esq = esq;
    }
}
class ArvoreAlvinegra
{
   private No raiz;
   
   /*
    * Construtor
    */
   public ArvoreAlvinegra()
   {
      raiz = null;
   }

   /*
    * Esse dois métodos servem para inserir o elemento na minha árvore
    */
   public void inserir (Personagem elemento) throws Exception
   {
      if (raiz == null)
      {
         raiz = new No(elemento, false);
      
      }else if (raiz.esq == null && raiz.dir == null)
      {
         if (raiz.elemento.getnome().compareTo(elemento.getnome()) > 0)
         {
            raiz.esq = new No(elemento, true);
         
         }else
         {
            raiz.dir = new No(elemento, true);
         }
      
      }else if (raiz.esq == null)
      {
         if (raiz.elemento.getnome().compareTo(elemento.getnome()) > 0)
         {
            raiz.esq = new No(elemento);
         }
         else if (raiz.dir.elemento.getnome().compareTo(elemento.getnome()) > 0)
         {
            raiz.esq = new No(raiz.elemento);
            raiz.elemento = elemento;
         }
         else
         {
            raiz.esq = new No(raiz.elemento);
            raiz.elemento = raiz.dir.elemento;
            raiz.dir.elemento = elemento;
         }

         raiz.esq.cor = raiz.dir.cor = false;
      
      }else if (raiz.dir == null)
      {
         if (raiz.elemento.getnome().compareTo(elemento.getnome()) < 0)
         {
            raiz.dir = new No(elemento);
         }
         else if (raiz.esq.elemento.getnome().compareTo(elemento.getnome()) > 0)
         {
            raiz.dir = new No(raiz.elemento);
            raiz.elemento = raiz.elemento;
         }
         else 
         {
            raiz.dir = new No(raiz.elemento);
            raiz.elemento = raiz.esq.elemento;
            raiz.esq.elemento = elemento;
         }

         raiz.esq.cor = raiz.dir.cor = false;

      }else
      {
         inserir(elemento, null, null, null, raiz);
      }
   }

   private void inserir(Personagem elemento, No bisavo, No avo, No pai, No i) throws Exception
   {
      if (i == null)
      {
         if(elemento.getnome().compareTo(pai.elemento.getnome()) < 0)
         {
            i = pai.esq = new No (elemento, true);
         }
         else
         {
            i = pai.dir = new No(elemento, true);
         }
         if (pai.cor == true)
         {
            balancear(bisavo, avo, pai, i);
         }
      }
      else
      {
         if (i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true)
         {
            i.cor = true;
            i.esq.cor = i.dir.cor = false;

            if (i == raiz)
            {
               i.cor = false;
            }
            else if (pai.cor == true)
            {
               balancear(bisavo, avo, pai, i);
            }
         
         }
         
         if (elemento.getnome().compareTo(i.elemento.getnome()) < 0)
         {
            inserir(elemento, avo, pai, i, i.esq);
         
         }else if(elemento.getnome().compareTo(i.elemento.getnome()) > 0)
         {
            inserir(elemento, avo, pai, i, i.dir);
         }
         else
         {
            throw new Exception ("Erro ao inserir, elemento repetio!");
         }

      }
   }

   /*
    * Esse metodo vai balancear a árvore
    */
   private void balancear(No bisavo, No avo, No pai, No i)
   {
      if (pai.cor == true)
      {
         if (pai.elemento.getnome().compareTo(avo.elemento.getnome()) > 0)
         {
            if (i.elemento.getnome().compareTo(pai.elemento.getnome()) > 0)
            {
               avo = rotacaoEsq(avo);
            }
            else
            {
               avo = rotacaoDirEsq(avo);
            }
         
         }else 
         {
            if (i.elemento.getnome().compareTo(pai.elemento.getnome()) < 0)
            {
               if (i.elemento.getnome().compareTo(pai.elemento.getnome()) < 0)
               {
                  avo = rotacaoDir(avo);
               }
               else
               {
                  avo = rotacaoEsqDir(avo);
               }
            }
         }

         if (bisavo == null)
         {
            raiz = avo;
         }
         else
         {
            if (avo.elemento.getnome().compareTo(bisavo.elemento.getnome()) < 0)
            {
               bisavo.esq = avo;
            }
            else
            {
               bisavo.dir = avo;
            }
         }

         avo.cor = false;
         avo.esq.cor = avo.dir.cor = true;
      }
   }

   private No rotacaoDir(No no) 
   {
      No noEsq = no.esq;
      No noEsqDir = noEsq.dir;

      noEsq.dir = no;
      no.esq = noEsqDir;

      return noEsq;
   }

   private No rotacaoEsq(No no) 
   {
      No noDir = no.dir;
      No noDirEsq = noDir.esq;

      noDir.esq = no;
      no.dir = noDirEsq;
      return noDir;
   }

   private No rotacaoDirEsq(No no) 
   {
      no.dir = rotacaoDir(no.dir);
      return rotacaoEsq(no);
   }

   private No rotacaoEsqDir(No no) 
   {
      no.esq = rotacaoEsq(no.esq);
      return rotacaoDir(no);
   }
   
   /*
    * Esse método vai persquisar se um personagem existe na minha lista
    */
   public boolean pesquisar (String nomeProcurado)
   {
      return pesquisar(nomeProcurado, raiz);
   }
   
   private boolean pesquisar (String nomeProcurado, No i)
   {
      boolean resp;
   
      Arvore.comparacoes++;
      if (i == null)
      {
         resp = false;
      }
      else if (nomeProcurado.compareTo(i.elemento.getnome()) == 0)
      {
         Arvore.comparacoes++;
         resp = true;
      }
      else if (nomeProcurado.compareTo(i.elemento.getnome()) < 0)
      {
         Arvore.comparacoes++;
         MyIO.print ("esq ");
         resp = pesquisar (nomeProcurado, i.esq);
      }
      else
      {
         MyIO.print ("dir ");
         resp = pesquisar (nomeProcurado, i.dir);
      }
      
      return resp;
   }

}
