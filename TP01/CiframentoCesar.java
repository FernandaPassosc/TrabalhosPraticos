class CiframentoCesar
{
	public static boolean isFim (String s)
	{
		return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
	}
	
	public static String FraseCip (String s)
	{
		
		//String LetraCip = "";
		String LetraCip = ""; // declaração de string é diferente da de arranjos?? como retonar string? 


		for (int i = 0; i < s.length(); i++)
		{
			LetraCip += (char)(s.charAt(i)+3);
		
		}
		
		return LetraCip;


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
			
		for (int i = 0; i < qntPalavras; i++)
		{
			MyIO.println (FraseCip(entrada[i]));
		}	
	
	}






}
