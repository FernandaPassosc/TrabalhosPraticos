class AlgebraBooleana
{ 
   public static boolean isFim (String s)
   {
      return (s.length() >= 3 && s.charAti(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
   }
   
   






   public static void main (String[] args)
   {
      String entrada = new String[2000];
      int qnt_strings = 0;
   
      do{
            entrada[qnt_strings] = MyIO.readLine();
      }while (!(isFim(entrada[qnt_strings++])));
      qnt_strings--;
      
      for (int i = 0; i < qnt_strigns; i++)
      {
         MyIO.println(
      
      }
   }

}
