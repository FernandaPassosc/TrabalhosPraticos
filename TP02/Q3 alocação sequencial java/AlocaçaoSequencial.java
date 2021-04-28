import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

      public void imprimir() 
      {
         MyIO.println (" ## " + this.nome + " ## " + this.altura + " ## "  + String.valueOf(this.peso).replace(".0","") + " ## " + this.corDoCabelo + " ## " + this.corDaPele         + " ## " + this.corDosOlhos + " ## " + this.anoNascimento + " ## " + this.genero + " ## " + this.homeWorld + " ## ");             
      }
   
     /* Public void imprimirNome()
      {
     
      }*/

} // FIM DA CLASSE PERSONAGENS




public class classePersonagens
{
   public static void main (String[] args)
   {
         MyIO.setCharset("UTF-8"); 
         Personagens[] P = new Personagens[2000];
         int qnt_pers = 0;
         String entrada = "";
         entrada = MyIO.readLine();
         Lista lista = new Lista();     
         Lista removidos = new Lista();

         while(!entrada.contains("FIM"))
         {  
            P[qnt_pers] = new Personagens();
            P[qnt_pers].LeArquivo(entrada);
            entrada = MyIO.readLine();   
            qnt_pers++;
         }
         int j = 0; //numero de mudanças
         String in = "";// nova entrada
         String[] aux = new String[2000];
         
         Personagens removido;
         int qnt_removidos = 0;
         
         if(entrada.contains("FIM"))
         {
            j = MyIO.readInt();
            for (int cont = 0; cont < j; cont++)
            {
               in = MyIO.readLine();
               
                 if (in.contains(" "))
                 {                    
                    aux = in.split(" ");
                 }else
                 {
                    aux[0] = in; 
                 }
               
                
               // AGORA VOU COLOCAR AS OPÇOES DE MOVIMENTAÇÕES
               
               if (aux[0].contains("II")) // OPCÇAR DE INSERIR NO INICIO
               { 
                  Personagens novo = new Personagens();
                  novo.LeArquivo(aux[1]);
                  lista.InserirInicio (novo, P);
                  

               }else if (aux[0].contains("IF")) // OPÇÃO DE INSERIR NO FIM
               {
                  Personagens novo = new Personagens();
                  novo.LeArquivo(aux[1]);
                  lista.InserirFim (novo, P);
               
               }else if (aux[0].contains("I*")) // OPÇÃO DE INSERIR EM DETERMINADA POSIÇÃO
               {
                  Personagens novo = new Personagens();
                  int pos = Integer.parseInt(aux[1]);
                  novo.LeArquivo(aux[2]);
                  lista.IP(novo, P, pos);
               
               }else if (aux[0].contains ("RI")) // OPÇÃO DE REMOVER DO INICIO
               {
                  removido = lista.RemoverInicio(P);
                  removidos.IF(removido);
                  removidos.n++;
                  qnt_removidos++;
               
               }else if (aux[0].contains ("RF")) // OPÇÃO DE REMOVER DO FINAL
               {
                  removido[qnt_removidos] = lista.RemoverFim(P);
                  n--;
                  qnt_removidos++;
               
               }else if (aux[0].contains ("R*")) // OPÇÃO DE REMOVER DE DETERMINADA POSIÇÃO
               {
                  
                  int posi = Integer.parseInt(aux[1]);
                  removido[qnt_removidos] = RemoverP(P, posi);
                  n--;
                  qnt_removidos++;
               }

            }
         }
         for (int i = 0; i < qnt_removidos; i++)
         {
            MyIO.println ("(R) " + removido[i].getnome());
         }

         for (int i = 0; i < n; i++)
         {
            MyIO.print ("[" + i + "] ");
            P[i].imprimir();
         }
   }


} // FIM DA CLASSE QUE TEM A MAIN

class Lista 
{
   private Personagens[] P;
   public int n = 0;
   public int qnt_removidos;
   
   /*
    * Construtor
    */
   public Lista ()
   {
      this(2000);
   }

   public Lista (int tamanho)
   {
      P = new Personagens[tamanho];
      n = 0;
   }
   
   /*
    * Esse método vai inserir um personagem no inicio da lista
    */
   public void InserirInicio(Personagens novo, Personagens[] P)
   {
      if (n >= 2000) // tamanho do array, .length() deu erro
      {
         MyIO.println ("ERRO");
      }
      
      for (int i = n; i > 0; i--)
      {
         P[i] = P[i-1].Clone();
      }

      P[0] = novo.Clone();
      n++;
   }

   public void InserirFim (Personagens novo, Personagens[] P)
   {
      if (n >= 2000)
      {
         MyIO.println ("ERRO");
      }
      
      P[n] = novo.Clone();
      n++;
   }
   
   public void IP (Personagens novo, Personagens[] P, int pos)
   {
      if (n >= 2000)
      {
         MyIO.println ("ERRO");
      }

      for (int i = n; i > pos; i--)
      {
         P[i] = P[i-1];
      }

      P[pos] = novo.Clone();
      n++;
   
   }
   public static Personagens RemoverInicio(Personagens[] P, int n)
   {
      if (n==0)
      {
         MyIO.println ("ERRO");
      }
      
      Personagens removido = P[0].Clone();
      
      for (int i = 0; i < n; i++)
      {
         P[i] = P[i+1];   
      }
      n--;
      P[qnt_removidos] = renovido.Clone();
      return removido;
   }
   public Personagens RemoverFim (Personagens[] P, int n)
   {
      if (n==0)
      {
         MyIO.println ("ERRO");
      }

      return P[--n].Clone();
   }

   public static Personagens RemoverP (Personagens[] P, int n, int pos)
   {
      if (n==0)
      {
         MyIO.println("ERRO");
      }
   
      Personagens removido = P[pos].Clone();

      for (int i = pos; i < n; i++)
      {
         P[i] = P[i+1];
      }
      
      n--;
      return removido;
   }
}
