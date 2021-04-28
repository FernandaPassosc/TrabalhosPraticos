class CiframentoRec
{
   
	public static boolean isFim (String s)
	{
		return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
	}
	

	public static String FraseCip (String s, int i, String Cip)
	{
		if (i < s.length())
		{
			Cip += (char)(s.charAt(i)+3);
         Cip = FraseCip (s, ++i, Cip);
		
		}
		
		return Cip;
	}	

 

	public static void main (String[] args)
	{
		String[] entrada = new String[1000];
		int qntPalavras = 0;

		do
		{
			entrada[qntPalavras] = MyIO.readLine();
		}while (isFim(entrada[qntPalavras++]) == false);
		qntPalavras--;
		
      String Cip = "";
		for (int i = 0; i < qntPalavras; i++)
		{
			MyIO.println (FraseCip(entrada[i], 0, Cip));
		}	
	
	}
}
