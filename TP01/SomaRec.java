class SomaRec 
{
   public static void main (String[] args)
   {
      int soma = Soma(0);
      MyIO.println (soma);
   }

   public static int Soma(int i)
   {
      int soma = 0;
      if( i < 6)
      soma += i + Soma(++i);
      MyIO.println (soma);
      return soma;
   }
}
