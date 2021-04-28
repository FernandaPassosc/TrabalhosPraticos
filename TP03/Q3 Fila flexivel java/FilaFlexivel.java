import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class FilaFlexivel
{
   public static Fila fila = new Fila();
   
   public static void main (String[] args) throws Exception
   {
      MyIO.setCharset("UTF-8"); 
      String entrada = "";
      entrada = MyIO.readLine();

      while(!entrada.contains("FIM"))
      {  
         Personagem personagem = new Personagem();
         personagem.LeArquivo(entrada);
         fila.Enfileirar(personagem);
         entrada = MyIO.readLine();
      }
        
      int modificacoes = MyIO.readInt();
      Processos(modificacoes);     
      fila.mostrarElementos();
   }  
    
   /*
    * Esse metodo vai analisar qual processo da lista vai ser executado
    */
   public static void Processos (int modificacoes) throws Exception 
   {
      String in = "";
      String[] aux = new String[500];
       
      for (int i = 0; i < modificacoes; i++)
      {
         in = MyIO.readLine();
               
         if (in.contains(" "))
         {                    
            aux = in.split(" ");
            Personagem novo = new Personagem();
            novo.LeArquivo(aux[1]);
            fila.Enfileirar(novo);
         }else
         {
            aux[0] = in;
            fila.mostrarRemovido();
            fila.Remover();
         }
      }
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

    public Celula()
    {
        this (null);
    }

    public Celula (Personagem elemento)
    {
        this.elemento = elemento;
        this.prox = null;
    }
}
class Fila
{
   public double soma = 0;
   public int qntPers = 0;
   public static int indiceP = 0;
   public static int indiceU = 0;
   private Celula primeiro;
   private Celula ultimo;
   private Celula primeiroRe; // celula que guarda o primeiro personagem removido
   private Celula ultimoRe;

    
   public Fila() 
   {
      primeiro = new Celula();
      ultimo = primeiro;
   }

   /*
    * Esse metodo vai inserir um personagem no inicio da minha fila
    */
   public void Enfileirar (Personagem novo)
   {
      // primeir inserção na fila
      if (indiceP == indiceU)
      {
         primeiro.elemento = novo;
         qntPers++;
         soma();
         ultimo.prox = new Celula();
         ultimo = ultimo.prox;
         primeiro.prox = ultimo;
         indiceU = (indiceU + 1) % 6;
      }else
      {
         if (((indiceU + 1) % 6) == indiceP)
         {
            Remover();
         }
         ultimo.elemento = novo;
         qntPers++;
         soma();
         ultimo.prox = new Celula();
         ultimo = ultimo.prox;
         indiceU = (indiceU + 1) % 6;
      }
   }
    


   /*
    * Esse metodo vai remover um personagem do inicio da fila
    */
   public void Remover() 
   {
      /*if (primeiro == ultimo) 
      {
		   throw new Exception("Erro ao remover (vazia)!");
      }*/
         qntPers--;
         removerSoma();
         primeiro = primeiro.prox;
         indiceP = (indiceP + 1) % 6;
   }
   
   /*
    * Esse metodo vai calcular a soma das alturas dos personagens na fila 
    */
   public void soma()
   {
      soma += ultimo.elemento.getaltura();
      media();
   }


   /*
    * Esse metodo vai remover a altua de um personagem removido
    */
   public void removerSoma()
   {
      soma = soma - primeiro.elemento.getaltura();
   }

   /*
    * Esse metodo vai mostrar a media da altura dos personagens da minha fila
    */
   public void media ()
   {
      if (qntPers != 0)
      {
         double media = soma/qntPers;
         MyIO.println(ArredondaMedia(media));
      }
   }

   /*
    * Essa função vai arredondar a minha media de alturas 
    */
   int ArredondaMedia(double number)
   {
      return (number >= 0) ? (int)(number + 0.5) : (int)(number - 0.5);
   }

   /*
    * Esse metodo vai mostrar os elementos removidos da lista
    */
   public void mostrarRemovido()
   {
      MyIO.println ("(R) " + primeiro.elemento.getnome());
   }

   /*
    * Essa função vai mostrar os elementos da fila
    */
   public void mostrarElementos()
   {
      int j = 0;
      for (Celula i = primeiro; i.prox != null; i = i.prox, j++)
      {
         MyIO.print ("[" + i + "] ");
         i.elemento.imprimir();
      }
   }
}
