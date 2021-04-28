class PalindromoRec
{
	public static boolean isFim (String s)
	{
		return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
	}


	public static boolean Palindromo (String s, int i, int j)
	{
	
		boolean resp = true;
		if (s.charAt(i) == s.charAt(j) && i < j)
		{
			return (Palindromo(s, ++i, --j));
		}else if (s.charAt(i) != s.charAt(j))
		{
			resp = false;
		}
		
		
		return (resp);
			
	}

	public static void main (String[] args)
	{
		String[] entrada = new String[1000];
		int qnt_palavras = 0;
		
		do{
			entrada[qnt_palavras] = MyIO.readLine();
		
		}while (!(isFim(entrada[qnt_palavras++])));
		qnt_palavras--;

		for (int i = 0; i < qnt_palavras; i++)
		{
			

			if (Palindromo(entrada[i], 0, entrada[i].length() - 1))
			{
				MyIO.println("SIM");
			}else{
				MyIO.println("NAO");
			}
		}
	
	
	
	}
}














