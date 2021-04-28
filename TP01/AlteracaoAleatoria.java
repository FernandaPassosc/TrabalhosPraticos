import java.util.Random;

class AlteracaoAleatoria
{
   public static boolean isFim (String s)
   {
         return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
   
   }

   public static String Troca(String s, char letra_sorteada, char letra_troca)
   {
         String nova = "";
         for (int i = 0; i < s.length(); i++)
         {
            if (s.charAt(i) == letra_sorteada){  
               nova += letra_troca;
            }else{
               nova += s.charAt(i);
            }

         }
      return nova;
   
   }

   public static char Alteracao ( Random gerador)
   {
      char letra = (char)('a' + (Math.abs(gerador.nextInt()) % 26));
      return letra;
   }

   public static void main (String[] args)
   { 
        String[] entrada = new String[2000];
         
        Random gerador = new Random();
        gerador.setSeed(4);
         
               
         
         int i = 0;

         do
         {
              entrada[i] = MyIO.readLine();
         
         }while (!(isFim(entrada[i++])));
         i--;
      
         char letra_sorteada;
         char letra_troca;

         for (int cont = 0; cont < i; cont++)
         {
         
           //char letra_sorteada = (char)('a' + (Math.abs(gerador.nextInt()) % 26));
           //char letra_troca = (char)('a' + (Math.abs(gerador.nextInt()) % 26));
           letra_sorteada = Alteracao(gerador);
           letra_troca = Alteracao(gerador);

           MyIO.println (Troca(entrada[cont], letra_sorteada, letra_troca));
         
         }
    
   }










} // fim da classe AlteracaoAleatoria
