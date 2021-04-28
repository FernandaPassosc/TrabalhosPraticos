import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.*;

class Personagens
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
      private boolean resp = false;

   
      // CONSTRUTOR
      Personagens(String nome, int altura, double peso, String corDoCabelo, String corDaPele, String corDosOlhos, String anoNascimento, String genero, String homeWorld){  
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
      } 
       
      // CONSTRUTOR VAZIO
      Personagens ()
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
      public void setresp (boolean resp)
      {
         this.resp = resp;
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
      public boolean getresp()
      {
         return this.resp;
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


   public Personagens Clone()
   {  
      Personagens p = new Personagens();
      
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
      
      public void Imprimir() 
      {
         MyIO.println (" ## " + this.nome + " ## " + this.altura + " ## "  + String.valueOf(this.peso).replace(".0","") + " ## " + this.corDoCabelo + " ## " + this.corDaPele         + " ## " + this.corDosOlhos + " ## " + this.anoNascimento + " ## " + this.genero + " ## " + this.homeWorld + " ## ");             
      }
   
} // FIM DA CLASSE PERSONAGENS


public class QuicksortParcial
{  
   public static int qnt_comp = 0;
   public static int qnt_pers;

   public static void main (String[] args) throws Exception
   {
         final long inicio = System.nanoTime();
         MyIO.setCharset("UTF-8");
         //MyIO.setCharset("iso-8859-1");
         Personagens[] P = new Personagens[2000];
         String entrada = "";
         entrada = MyIO.readLine();

         while(!entrada.contains("FIM"))
         {  
            P[qnt_pers] = new Personagens();
            P[qnt_pers].LeArquivo(entrada);
            entrada = MyIO.readLine();   
            //MyIO.println ("cor do cabelo: " + P[qnt_pers].getcorDoCabelo() + "posição: " + qnt_pers);
            qnt_pers++;
            
         }
         
         Quicksort quick = new Quicksort();
         //quicksort.aleatorio();
         quick.quicksort(P);
         
         for (int i = 0; i < 10; i++)
         {
            P[i].Imprimir();
         }
         final long tempoFinal = System.nanoTime() - inicio;
         ArquivoLog(tempoFinal);
   }      
   

   public static void ArquivoLog (final long tempo) throws Exception
   {
      FileWriter arq = new FileWriter ("matricula_insercao.txt");
      PrintWriter escrever = new PrintWriter(arq);

      escrever.println ("matricula: 680624\t" + " tempo: " + tempo + "\t" + "quantidade de comparações: " + qnt_comp);

   }

} // FIM DA CLASSE QUE TEM A MAIN

class Quicksort
{
   public static int n = QuicksortParcial.qnt_pers;
   public Quicksort()
   {
      super();
   }

   /*public Quicksort()
   {
      super(tamanho);
   }*/


   public static void quicksort(Personagens[] P)
   {
      quicksort(P, 0, QuicksortParcial.qnt_pers-1);
   }

   private static void quicksort(Personagens[] P, int esq, int dir)
   {
      int i = esq;
      int j = dir;
      //MyIO.println ("i: " + i + " j: " + j);
      Personagens pivo = P[(dir+esq)/2].Clone();

      while (i <= j)
      {
         while (P[i].getcorDoCabelo().compareTo(pivo.getcorDoCabelo()) < 0)i++;
         while (P[j].getcorDoCabelo().compareTo(pivo.getcorDoCabelo()) > 0)j--;

         if (i <= j)
         {
            swap (P, i, j);
            i++;
            j--;
         }
      }

      if (esq < j) quicksort (P, esq, j);
      if (i < dir) quicksort (P, i, dir);
      Desempatar(P); 
   }

   public static void swap(Personagens[] P, int i, int j) 
   {
      Personagens aux = P[i].Clone();
      P[i] = P[j].Clone();
      P[j] = aux.Clone();
   }
   
   public static void Desempatar (Personagens[] P)
   {
      int PosI = 0; 
     
      for (int i = 0; i < n; i++)
      {
         if ( (i < n- 1) && (P[i].getcorDoCabelo().compareTo(P[i+1].getcorDoCabelo()) == 0))
         {
            PosI = i;
     
            while((i < n - 1) && P[i].getcorDoCabelo().compareTo(P[i+1].getcorDoCabelo()) == 0) i++;
         
            if (i - PosI > 1) Insercao(P, PosI, i);
         }
        
      }
   }

   public static void Insercao(Personagens[] P, int inicio, int fim)
   {
      Personagens aux;
      String menor;

      for (int i = inicio; i <= fim; i++)
      {
         aux = P[i];
         int j = i - 1;

         menor = P[i].getnome();
  
         while ((j >= inicio) && (P[j].getnome().compareTo(menor)) > 0)
         {
            P[j+1]= P[j].Clone();
            j--;
         }

         P[j+1] = aux.Clone();
      }
   }
}





