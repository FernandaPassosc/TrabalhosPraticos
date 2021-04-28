class BoolVogais
{

   public static boolean isFim (String s)
   {
      return (s.length() >= 3 && s.charAt(0) == 'f' && s.charAt(1) == 'i' && s.charAt(2) == 'm');
   }
  
   public static boolean SoVogais(String s, int i)
   {
      
      boolean X1 = true;

      if (i < s.length())
      {        
         if(s.charAt(i) != 'a' && s.charAt(i) != 'e' && s.charAt(i) != 'i' && s.charAt(i) != 'o' && s.charAt(i) != 'u' && s.charAt(i) != '.' && s.charAt(i) != ',' && s.charAt(i) != ' '){
         
               X1 = false;
         }else{
                    
               X1 = SoVogais(s, ++i);
         }

         
       }
      return X1;
      
    }

   public static boolean SoConsoantes(String s, int i)
   {
      boolean X2 = true;
      
      if (i < s.length())
      { 
         if (s.charAt(i) >= '0' && s.charAt(i) <= '9')
         {
            X2 = false;
         }else if (s.charAt(i) == 'a' || s.charAt(i) == 'e' || s.charAt(i) == 'i' || s.charAt(i) == 'o' || s.charAt(i) == 'u') 
            {
               X2 = false;
            }else{
               X2 = SoConsoantes(s, ++i);
            }
        
      }

      return X2;
   }

   public static boolean NumeroInt(String s, int i)
   {
      boolean X3 = true;

      if (i < s.length()){
          if (!(s.charAt(i) >= '0' && s.charAt(i) <= '9'))
               X3 = false;
          }else{
               X3 = NumeroInt (s, ++i);
          }
      return X3;
   }
   
   public static boolean NumeroReal(String s, int i, int cont)
   {
      boolean X4 = true;
     
     
      if (i < s.length())
      {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9' || s.charAt(i) == '.' || s.charAt(i) == ','){
               if (s.charAt(i) == '.' || s.charAt(i) == ',')
               {
                  cont++;
               }
              X4 = NumeroReal (s, ++i, cont);
            }else
            {
               X4 = false;
            }
            if (cont > 1)
            {
               X4 = false;
            }
        }
      
   
      return X4;
   
   }

   
   public static void main (String[] args)
   {
         String[] palavra = new String[3000];

         int qnt_palavras = 0;

         

         do
         {
            palavra[qnt_palavras] = MyIO.readLine();
            palavra[qnt_palavras] = palavra[qnt_palavras].toLowerCase();
               
         }while (!(isFim(palavra[qnt_palavras++])));
         qnt_palavras--;


         for (int cont = 0; cont < qnt_palavras; cont++)
         {
            
            if (SoVogais(palavra[cont], 0) == true)
            {
               MyIO.println ("SIM NAO NAO NAO");
            }else if (SoConsoantes(palavra[cont], 0) == true){ 
               MyIO.println ("NAO SIM NAO NAO");
           }else if (NumeroInt(palavra[cont], 0) == true){ 
               MyIO.println ("NAO NAO SIM NAO"); 
            }else if (NumeroReal(palavra[cont], 0, 0) == true){
               MyIO.println ("NAO NAO NAO SIM");
            }else{
               MyIO.println("NAO NAO NAO NAO");
            }
         }
   }











}
