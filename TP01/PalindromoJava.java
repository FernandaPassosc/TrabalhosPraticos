class PalindromoJava{
	public static boolean isFim (String s)
	{
		return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
	
	}
	
	public static boolean EhPalindromo (String s)
	{
		boolean eh = true;
		int j = s.length();
//		MyIO.println(s.length());
		

		for (int i=0; i < j; i++)
		{
		 	if (s.charAt(i) != s.charAt(j))
			{
			 	eh = false;
			}		
		
			j--;	
			
		}	
		
		return eh;
	
	}

	/*public static boolean Saida (String s)
	{
		String[] resp = new String[10];
		
		if (EhPalindromo(s) == false)
		{
			resp = "NAO";
		}else {
			resp = "SIM";
		}
	
		return resp;
	}*/

	public static void main (String[] args){
		String[] entrada = new String[1000];
		int qntpalavras = 0;
		
		do
		{
			entrada[qntpalavras] = MyIO.readLine();
		}while (isFim(entrada[qntpalavras++]) == false);
		qntpalavras--; // Desconsiderar a linha "FIM"
	
	

		// Para cada linha de entrada, gerar uma resposta
		for (int i = 0; i < qntpalavras; i++) // é apenas < e nao <= pq a linha "FIM" foi desconsiderada
		{
			if (EhPalindromo(entrada[i]) == false)
			{
				MyIO.println ("NAO");
			}else {
				MyIO.println ("SIM");
			}
		}
	} // end of main

}
