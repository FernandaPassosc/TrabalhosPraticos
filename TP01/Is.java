class Is

{
	static boolean isFim(String fim)
	{
		return (fim.length() >= 3 && fim.charAt(0) == 'F' && fim.charAt(1) == 'I' && fim.charAt(2) == 'M');
	}

	static boolean isVogal(String frase)
	{
		frase = frase.toUpperCase();
		boolean vogais = true;

		for(int i = 0; i < frase.length() && vogais; i++)
		{
         //MyIO.println ("Estou analisando: " + frase.charAt(i));
			if(frase.charAt(i) == 'A')
         {
				//MyIO.println ("entrei no A");
            vogais = true;
         }else if(frase.charAt(i) == 'E')
         {
				//MyIO.println ("entrei no E");
            vogais = true;
         }
			else if(frase.charAt(i) == 'I')
         {
         	//MyIO.println ("entrei no I");
				vogais = true;
         }
			else if(frase.charAt(i) == 'O')
         {
            //MyIO.println ("entrei no O");
				vogais = true;
         }
			else if(frase.charAt(i) == 'U')
         {
            //MyIO.println ("entrei no U");
            vogais = true;
         }
			else
         {
				vogais = false;
         }
		}
		return vogais;
	}
	
	static boolean isConsoante(String frase)
	{
		frase = frase.toUpperCase();
		boolean consoantes = true;
		
		for(int i = 0; i < frase.length(); i++)
		{
			if(frase.charAt(i) >= '0' && frase.charAt(i) <= '9')
				consoantes = false;
			else if(frase.charAt(i) == 'A' || frase.charAt(i) == 'E' || frase.charAt(i) == 'I' || frase.charAt(i) == 'O' || frase.charAt(i) == 'U')
				consoantes = false;
		}
		return consoantes;
	}
	
	static boolean isInteiro(String frase)
	{
		frase = frase.toUpperCase();
		boolean inteiros = true;

		for(int i = 0; i < frase.length(); i++)
		{
			if(frase.charAt(i) >= '0' && frase.charAt(i) <= '9')
				inteiros = true;
			else if(frase.charAt(i) == '.' || frase.charAt(i) == ',')
				inteiros = false;
			else
				inteiros = false;
		}
		return inteiros;
	}
	
	static boolean isReal(String frase)
	{
		frase = frase.toUpperCase();
		boolean reais = true;

		if(isVogal(frase) || isConsoante(frase))
			reais = false;

		for(int i = 0; i < frase.length(); i++)
		{
			if((frase.charAt(i) >= '0' && frase.charAt(i) <= '9') || (frase.charAt(i) == '.' || frase.charAt(i) == ','))
				reais = true;
			else
				reais = false;
		}
		return reais;
	}

	public static void main(String[] args)
	{
		MyIO.setCharset("UTF-8");
      String[] frase = new String[1000];
		int entradas = 0;

		do
		{
			frase[entradas] = MyIO.readLine();
		}while(!isFim(frase[entradas++]));
		entradas--;

		for(int i = 0; i < entradas; i++)
		{
			if(isVogal(frase[i]))
				MyIO.print("SIM ");
			else
				MyIO.print("NAO ");
			if(isConsoante(frase[i]))
				MyIO.print("SIM ");
			else
				MyIO.print("NAO ");
			if(isInteiro(frase[i]))
				MyIO.print("SIM ");
			else
				MyIO.print("NAO ");
			if(isReal(frase[i]))
				MyIO.println("SIM");
			else
				MyIO.println("NAO");
		}
	}
}

