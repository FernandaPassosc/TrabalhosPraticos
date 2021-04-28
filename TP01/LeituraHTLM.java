import java.io.*;
import java.net.*;

public class LeituraHTLM{
	public static boolean isFim(String s)
	{
		return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M'); 
	}
	
	public static String getHtml(String endereco)
	{
		URL url;
		InputStream is = null;
		BufferedReader br;
		String resp = "", line;

		try {
			url = new URL(endereco);
			is = url.openStream();  // throws an IOException
			br = new BufferedReader(new InputStreamReader(is));

			while ((line = br.readLine()) != null) {
					resp += line + "\n";
			}
} catch (MalformedURLException mue) {
	mue.printStackTrace();
	} catch (IOException ioe) {
		ioe.printStackTrace();
	}

	try {
		is.close();
		} catch (IOException ioe) {
	// nothing to see here

		}	

	return resp;
	}	

	public static void Contador (String html, String nome)
	{
		int j = html.length() - 1;
		String resp = "";
		int a = 0;
		int e = 0;
		int i = 0;
		int o = 0;
		int u = 0;
		int á = 0;
		int é = 0;
		int í = 0;
		int ó = 0;
		int ú = 0;
		int à = 0;
		int è = 0;
		int ì = 0;
		int ò = 0;
		int ù = 0;
		int ã = 0;
		int õ = 0;
		int â = 0;
		int ê = 0;
		int î = 0;
		int ô = 0;
		int û = 0;
		int consoante = 0;
		int br = 0;
		int table = 0;
		int nomedapagina = 0;

		for (int cont = 0; cont < j; cont++)
		{
			if (html.charAt(i) == 'a'){
				a++;
			}else if (html.charAt(i) == 'e'){
				e++;
			}else if (html.charAt(i) == 'i'){
				i++;
			}else if (html.charAt(i) == 'o'){
				o++;	
			}else if (html.charAt(i) == 'u'){
				u++;
			}else if (html.charAt(i) == 'á'){
				á++;
			}else if (html.charAt(i) == 'é'){
				é++;
			}else if (html.charAt(i) == 'í'){
				í++;
			}else if (html.charAt(i) == 'ó'){
				ó++;
			}else if (html.charAt(i) == 'ú'){
				ú++;
			}else if (html.charAt(i) == 'à'){
				à++;
			}else if (html.charAt(i) == 'è'){
				è++;
			}else if (html.charAt(i) == 'ì'){
				ì++;
			}else if (html.charAt(i) == 'ò'){
				ò++;
			}else if (html.charAt(i) == 'ù'){
				ù++;
			}else if (html.charAt(i) == 'ã'){
				ã++;
			}else if (html.charAt(i) == 'õ'){
				õ++;
			}else if (html.charAt(i) == 'â'){
				â++;
			}else if (html.charAt(i) == 'ê'){
				ê++;
			}else if (html.charAt(i) == 'î'){
				î++;
			}else if (html.charAt(i) == 'ô'){
				ô++;
			}else if (html.charAt(i) == 'û'){
				û++;
			}
         
         MyIO.println ("oi");
         MyIO.print (o);
		}
		
	} // fim da classe contador

	public static void main(String[] args) 
	{
		String html = new String(), nome = new String(), endereco = new String(); 
		MyIO.setCharset("UTF-8");
	   

		do 
		{
         endereco = MyIO.readLine();
     
         if (!isFim(endereco)){

			   //html = getHtml(endereco);
			
			   nome = MyIO.readLine();
			
			   //MyIO.println (htlm);
			   Contador(html, nome);
         }
      
      }while (!isFim(endereco));
	
	}	
		
} // end of class LeituraHTLM
	
	






