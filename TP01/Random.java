import java.util.Random; 

class Random 
{
   public static void main(String[] args)
   {
         Random gerador = new Random();
         gerador.setSeed(4);
         MyIO.println((char)('a' + (Math.abs(gerador.nextInt()) % 26)));
        
   
   
   }





}
