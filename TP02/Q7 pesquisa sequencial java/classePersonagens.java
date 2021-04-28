import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.InputMismatchException;
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
      
} // FIM DA CLASSE PERSONAGENS


public class classePersonagens
{
   public static int qnt_comp;
   public static void main (String[] args)
   {
         
         final long inicio = System.nanoTime();
         MyIO.setCharset("UTF-8");
         Personagens[] P = new Personagens[2000];
         int qnt_pers = 0;
         String entrada = "";
         entrada = MyIO.readLine();
       

         while(!entrada.contains("FIM"))
         {  
            P[qnt_pers] = new Personagens();
            P[qnt_pers].LeArquivo(entrada);
            entrada = MyIO.readLine();   
            qnt_pers++;
         }

         String nomeRecebido = MyIO.readLine();
         
         Contem(P, nomeRecebido, qnt_pers);

         final long tempoFinal = System.nanoTime() - inicio;
         ArquivoLog (tempoFinal);
   }      
   
   public static void Contem (Personagens[] P, String nomeRecebido, int n)
   {
         boolean resp = false;
         Personagens aux = new Personagens();

         for (int i = 0; i < n; i++)
         {
            while (!nomeRecebido.contains("FIM"))
            {
               for (int j = 0; j < n; j++)
               {
                  qnt_comp++;
                  if (nomeRecebido.contains("FIM")) 
                  {
                     i = n;
                  }
                  else
                  {
                     if (nomeRecebido.equals(P[j].getnome()))
                     {  
                        resp = true;
                     }
                     qnt_comp++;
                  }     
               }
               nomeRecebido = MyIO.readLine();
               Imprimir(P, resp);
               resp = false;
            }
         }
   }


   public static void Imprimir (Personagens[] P, boolean resp)
   {
      if (resp)
      {
         MyIO.println ("SIM");
      }
      else
      {
         MyIO.println ("NAO");
      }
   }

   public static void ArquivoLog (final long tempo) throws Exception
   {
      FileWriter arq = new FileWriter ("matricula_sequencial.txt"); 
      PrintWriter escrever = new PrintWriter(arq);

      escrever.println ("matricula: 680624\t" + " tempo: " + tempo + "\t" + "quantidade de comparações: " + qnt_comp);
         
   }

} // FIM DA CLASSE QUE TEM A MAIN

