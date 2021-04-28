#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>


bool isFim (char s[]);
bool EhPalind (char s[], int i, int j);
int main ()
{
	char entrada[2000];
   int eh;
   
   do{
         fgets(entrada, 200, stdin);
      
      if (!(isFim(entrada)))
      {
         eh = EhPalind (entrada, 0, strlen(entrada)-2);
         if (eh == 1)
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
bool EhPalind (char s[], int i, int j)
{
   bool eh = true;
   
  
   if (i < j)
   {
      if(s[i] != s[j])
      {
         eh = false;
         i = j;
      }else{
            eh = EhPalind(s, ++i, --j);
      }  
  }
   
   return eh; 
}


