class PalindromoIterativoJava
{
	public static boolean isFim (String s)
	{
		return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
	}

	public static boolean ehPalindromo (String s)
	{
		boolean eh = true;
		int j = s.length()-1;

		for (int i = 0; i < j; i++)
		{
			if (s.charAt(i) != s.charAt(j))
			{
				eh = false;
				i = j;
			}else {
				j = j-1;
			}
		
		}
	
		return eh;
	}
	
	
	public static void main (String[] args)
	{
	
		int qntPalavras = 0;
		String[] entrada = new String[1000];

		do
		{
			entrada[qntPalavras] = MyIO.readLine();

		}while (isFim(entrada[qntPalavras++]) == false); // com esse ++ analisa a palavra 0?
		qntPalavras--; // desconsiderar a linha "FIM"

		for (int i = 0; i < qntPalavras; i++)
		{
			if(ehPalindromo(entrada[i]) == false)
			{
				MyIO.println ("NAO");
			}else{
				MyIO.println ("SIM");
			}
	 	}
	}













}
