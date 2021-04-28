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
        
      int modificacoes = MyIO.readInt();
      Processos(modificacoes);
      lista.mostrar();      
      
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
         }else
         {
            aux[0] = in; 
         }
         if (aux[0].contains("II")) // OPCÇAR DE INSERIR NO INICIO
         { 
            Personagem novo = new Personagem();
            novo.LeArquivo(aux[1]);
            lista.InserirInicio (novo);
                  
         }else if (aux[0].contains("IF")) // OPÇÃO DE INSERIR NO FIM
         {
            Personagem novo = new Personagem();
            novo.LeArquivo(aux[1]);
            lista.InserirFim (novo);
 
         }else if (aux[0].contains("I*")) // OPÇÃO DE INSERIR EM DETERMINADA POSIÇÃO
         {
            Personagem novo = new Personagem();
            int pos = Integer.parseInt(aux[1]);
            novo.LeArquivo(aux[2]);
            lista.IP(novo, pos);
               
         }else if (aux[0].contains ("RI")) // OPÇÃO DE REMOVER DO INICIO
         {
            lista.RemoverInicio();
               
         }else if (aux[0].contains ("RF")) // OPÇÃO DE REMOVER DO FINAL
         {
            lista.RemoverFim();
                    
         }else if (aux[0].contains ("R*")) // OPÇÃO DE REMOVER DE DETERMINADA POSIÇÃO
         {         
            int posi = Integer.parseInt(aux[1]);
            lista.RemoverP(posi);
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
class Lista
{
   private Celula primeiro;
   private Celula ultimo;
   private Celula primeiroRe; // celula que guarda o primeiro personagem removido
   private Celula ultimoRe;
    
   public Lista() 
   {
      primeiro = new Celula();
      ultimo = primeiro;
      primeiroRe = new Celula();
      ultimoRe = primeiroRe;
   }

   /*
    * Esse metodo vai inserir um personagem no inicio da minha lista
    */
   public void InserirInicio (Personagem novo)
   {
      Celula temp = new Celula(novo);
      temp.prox = primeiro.prox;
      primeiro.prox = temp;
      if (primeiro == ultimo)
      {
         ultimo = temp;
      }
      temp = null;
   }
        
   /*
    * Esse metodo vai inserir um personagem no final da minha lista
    */
   public void InserirFim (Personagem novo) 
   {
      ultimo.prox = new Celula(novo);
      ultimo = ultimo.prox;
   }

   /*
    * Esse metodo vai inserir um personagem em determinada posição 
    */
   public void IP (Personagem novo, int pos) throws Exception
   {
      int tamanho = tamanho();
      if(pos < 0 || pos > tamanho)
      {
         throw new Exception("Erro ao inserir posicao (" + pos + " / tamanho = " + tamanho + ") invalida!");
      }else if (pos == 0)
      {
         InserirInicio(novo);
      }else if (pos == tamanho)
      {
         InserirFim(novo);
      }else
      {
         Celula i = primeiro;
         for (int j = 0; j < pos; j++, i = i.prox);
         Celula temp = new Celula(novo);
         temp.prox = i.prox;
         i.prox = temp;
         temp = i = null;
      }
           
   }

   /*
    * Esse metodo vai remover um personagem do inicio da lista
    */
   public void RemoverInicio() throws Exception
   {
      if (primeiro == ultimo) 
      {
		   throw new Exception("Erro ao remover (vazia)!");
      }
         Celula temp = primeiro;
         primeiro = primeiro.prox;
         ultimoRe.prox = new Celula(primeiro.elemento);
         ultimoRe = ultimoRe.prox;
         temp.prox = null;
         temp = null;
   }
   
   /*
    * Esse metodo vai remover um personagem do final da lista
    */
   public void RemoverFim()
   { 
      ultimoRe.prox = new Celula(ultimo.elemento);
      ultimoRe = ultimoRe.prox;
      Celula i = primeiro;
      for ( ; i.prox != ultimo; i = i.prox);
      ultimo = i;
      ultimo.prox = i = null;
   }

   /*
    * Esse metodo vai remover um personagem de determinada posição da lista
    */
   public void RemoverP(int pos) throws Exception

   { 
      int tamanho = tamanho();
      if (primeiro == ultimo)
      {
			throw new Exception("Erro ao remover (vazia)!");

      } else if(pos < 0 || pos >= tamanho)
      {
			throw new Exception("Erro ao remover (posicao " + pos + " / " + tamanho + " invalida!");
      } else if (pos == 0)
      {
         RemoverInicio();
      } else if (pos == tamanho - 1)
      {
         RemoverFim();
      } else 
      {
         Celula i = primeiro;
         for (int j = 0; j < pos; j++, i = i.prox);
         Celula temp = i.prox;

         ultimoRe.prox = new Celula(temp.elemento);
         ultimoRe = ultimoRe.prox;
         i.prox = temp.prox;
         temp.prox = null;
         i = temp = null;
      }
   } 

   /*
    * Esse metodo vai mostrar os elementos da lista
    */
   public void mostrar()
   {
      Celula i = primeiro;
      Celula r = primeiroRe;
      
      for ( ; r.prox != null; r = r.prox)
      {
         MyIO.println ("(R) " + r.prox.elemento.getnome());
      }
      
      for (int cont = 0; i.prox != null; i = i.prox, cont++)
      {
         MyIO.print ("[" + cont + "]");
         i.prox.elemento.imprimir();
      }
   }

   /*
    * Esse metodo vai verificar o tamanho da lista
    */
   public int tamanho()
   {
      int tamanho = 0;

      for (Celula i = primeiro; i.prox != ultimo; i = i.prox, tamanho++);
      return tamanho;
   }
}
