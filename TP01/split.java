class split
{
   public static boolean isFim (String s)
   {
      return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
   }
   
   public static String[] split(String palavra, char letraDivisora) {

        int numeroDePalavrasSeparadas = 1;

        // Primeiro checa quantas letras divisoras existem na palavra para criar o vetor
        // estático.
        for (int i = 0; i < palavra.length(); i++) {
            if (palavra.charAt(i) == letraDivisora) {
                numeroDePalavrasSeparadas++;
            }
        }

        // Caso de não ser encontrada nenhuma letra igual à procurada.
        if (numeroDePalavrasSeparadas == 1) {
            String[] palavrasSeparadas = new String[1];
            palavrasSeparadas[0] = palavra;
            return palavrasSeparadas;
        }

        // Vetor retornado.
        String[] palavrasSeparadas = new String[numeroDePalavrasSeparadas];

        // Inteiros auxiliares.
        int numeroDePalavrasEncontradas = 0;
        int indexDaUltimaLetraEncontrada = -1;
        int tamanhoDaProximaPalavra = 0;

        // Agora vai pegando palavra por palavra dentro da string.
        for (int i = 0; i <= palavra.length(); i++) {
            if (i == palavra.length() || palavra.charAt(i) == letraDivisora) {
                tamanhoDaProximaPalavra = i - (indexDaUltimaLetraEncontrada + 1);
                char[] proximaPalavra = new char[tamanhoDaProximaPalavra];
                int indexDaLetraAdicionadaNaNovaPalavra = 0;

                for (int j = indexDaUltimaLetraEncontrada + 1; j < i; j++) {
                    proximaPalavra[indexDaLetraAdicionadaNaNovaPalavra] = palavra.charAt(j);
                    indexDaLetraAdicionadaNaNovaPalavra++;
                }

                palavrasSeparadas[numeroDePalavrasEncontradas] = new String(proximaPalavra);
                numeroDePalavrasEncontradas++;
                indexDaUltimaLetraEncontrada = i;
            }
        }

        return palavrasSeparadas;
    }


   public static void main (String[] args)
   {
      String[] entrada = new String[2000];
      int num = 0;
      String letraDivisoria = "";
      char[] LetraDivisoria = new char[1];
      
      do{
            entrada[num] = MyIO.readLine();
            letraDivisoria = MyIO.readLine();
            LetraDivisoria[num] = letraDivisoria.charAt(0);

      }while (!(isFim(entrada[num])));

      for (int i = 0; i < num; i++)
      {
         split(entrada[i], LetraDivisoria[i]);
      
      }
   
   }

}
