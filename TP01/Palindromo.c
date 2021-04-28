#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>


bool isFim (char s[]);
bool EhPalind (char s[]);
int main ()
{
	char entrada[2000];
   bool eh;
   
   do{
      
      if (!(isFim(entrada)))
      {
         fgets(entrada, 200, stdin);
         eh = EhPalind (entrada);
         if (eh == true)
         {
             printf ("SIM\n");
         }else{
            printf ("NAO\n");
         }
	   }

	}while (!(isFim(entrada)));
}
bool isFim (char s[])
{
	return (strlen(s) >= 3 && s[0] == 'F' && s[1] == 'I' && s[2] == 'M');

}
bool EhPalind (char s[])
{
   bool eh = false;
   char inversa[2000];

   strcpy (inversa, s); // copia
   strrev (inversa); // inverte
   printf ("%s", inversa); 
   if (inversa == s)
   {
      eh = true;
   }
   return eh; 
}





