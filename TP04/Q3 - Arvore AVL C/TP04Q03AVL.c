#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <time.h>
#include <locale.h>

int comp = 0;
int mov = 0;

/**
 * Tipo personagem
*/
typedef struct 
{
    char   nome[100];
    int    altura;
    float  peso;
    char   corDoCabelo[100];
    char   corDaPele[100];
    char   corDosOlhos[100];
    char   anoNascimento[100];
    char   genero[100];
    char   homeworld[100];

}Personagem;

/**
* Captura informacoes uteis da string para setar    
* @param dados string com conteudo do arquivo.
*/
void escreveAtributos(Personagem *p){
    
    printf(" ## %s" ,p -> nome);
    printf(" ## %i" ,p -> altura);
    printf(" ## %g" ,p -> peso);
    printf(" ## %s" ,p -> corDoCabelo);
    printf(" ## %s" ,p -> corDaPele);
    printf(" ## %s" ,p -> corDosOlhos);
    printf(" ## %s" ,p -> anoNascimento);
    printf(" ## %s" ,p -> genero);
    printf(" ## %s" ,p -> homeworld);
    printf(" ## ");
    printf("\n"); // Quebra de linha
}

// ------- FIM CLASSE PERSONAGEM -------- //
            
// ------------------ NO --------------- //

typedef struct No{

    Personagem* personagem;
    struct No* dir;
    struct No* esq;
    int nivel;

}No;

/**
 * Criacao do novo no
 * @param nome Conteudo do no.
 */
No* novoNo(Personagem* p) {
   No* novo = (No*)malloc(sizeof(No));
   novo->personagem = p;
   novo->esq = NULL;
   novo->dir = NULL;
   novo->nivel = 1;
   
   return novo;
}

int getNivel(No* no){
    if (no == NULL) return 0;
    else return no->nivel;
}

No* setNivel(No* no){
    int a = getNivel(no->dir);
    int b = getNivel(no->esq);
    
    if (a>b)
        no->nivel = a+1;
    else no->nivel = b+1;

    return no;
}

// --------------- FIM NO ------------ //

// ------------ ARVORE AVL ----------- //

No* raiz;

void start(){    
    raiz = NULL;
}

bool pesquisarREC(char* x, No* i) {
    
    bool resp;
	
    if (i == NULL) { 
        
        resp = false;

    } else if (strcmp(x,i->personagem->nome) == 0) { 
        
        resp = true; 

    } else if (strcmp(x,i->personagem->nome) < 0) { 
        printf("esq ");
        resp = pesquisarREC(x, i->esq); 

    } else { 
        printf("dir ");
        resp = pesquisarREC(x, i->dir); 
    }
      
    return resp;
}

bool pesquisar(char* nome){
	return pesquisarREC(nome, raiz);
}

No* rotacionarDir(No* no) {

      No* noEsq = no->esq;
      No* noEsqDir = noEsq->dir;

      noEsq->dir = no;
      no->esq = noEsqDir;

      setNivel(no);
      setNivel(noEsq);

      return noEsq;
}

No* rotacionarEsq(No* no) {
      
    No* noDir = no->dir;
    No* noDirEsq = noDir->esq;

    noDir->esq = no;
    no->dir = noDirEsq;

    setNivel(no);
    setNivel(noDir);
    
    return noDir;
}

No* balancear(No* no) {
        
        if(no != NULL){
            int fator = getNivel(no->dir) - getNivel(no->esq);

            //Se balanceada
            if (abs(fator) <= 1){
                no = setNivel(no);

            //Se desbalanceada para a direita
            }else if (fator == 2){

                int fatorFilhoDir = getNivel(no->dir->dir) - getNivel(no->dir->esq);

                //Se o filho a direita tambem estiver desbalanceado
                if (fatorFilhoDir == -1) {
                    no->dir = rotacionarDir(no->dir);
                }
                no = rotacionarEsq(no);

            //Se desbalanceada para a esquerda
            }else if (fator == -2){

            int fatorFilhoEsq = getNivel(no->esq->dir) - getNivel(no->esq->esq);

            //Se o filho a esquerda tambem estiver desbalanceado
            if (fatorFilhoEsq == 1) {
               no->esq = rotacionarEsq(no->esq);
            }
            no = rotacionarDir(no);

            }
        }

        return no;
}


void inserirRec(Personagem* p, No** i) {

    if (*i == NULL) {
        *i = novoNo(p);

    } else if (strcmp(p->nome, (*i)->personagem->nome) < 0) {
        inserirRec(p, &((*i)->esq));

    } else if (strcmp(p->nome, (*i)->personagem->nome) > 0) {
        inserirRec(p, &((*i)->dir));

    } else {
        printf("Erro ao inserir!");
    }

    *i = balancear((*i));
}

void inserir(Personagem* p) {
    inserirRec(p, &raiz);
}

            
// ----------- PRINCIPAL --------------- // 

/**
* Aloca as informacoes para cada atributo   
* @param dados string com informacao.
* @param mod numero de controle para alocar.
*/
void setAtributos(Personagem *p,int mod, char dados[]){
    
    char unknown[] = "unknown";

    if (mod == 1){ // nome
        strcpy(p -> nome,dados);
    }

    if(mod == 2){ // altura
        int i;
        if (strcmp(unknown, dados) == 0){
            i = 0;
        }
        else i = atoi(dados);
        
        p -> altura = i;
    }

    if(mod == 3){ // peso
        float f;
        
        char aux[100];
        for (int j = 0, l = 0; j < strlen(dados); j++){
            if (dados[j] != ','){
                aux[l] = dados[j];
                l++;
            }
        }
        
        f = atof(aux);

        if (strcmp(unknown, aux) == 0){
            f = 0;
        }

        p -> peso = f;
    }
            
    if(mod == 4){ // Cor do cabelo
        strcpy(p -> corDoCabelo,dados);
    }

    if(mod == 5){ // Cor da pele
        strcpy(p -> corDaPele, dados);
    }

    if(mod == 6){ // Cor dos olhos
        strcpy(p -> corDosOlhos,dados);
    }

    if(mod == 7){ // Ano da Nascimento
        strcpy(p -> anoNascimento, dados);
    }
    
    if(mod == 8){ // Genero
        strcpy(p -> genero, dados);
    }
    
    if(mod == 9){ // Homeworld
        strcpy(p -> homeworld, dados);
    }

}

/**
* Captura informacoes uteis da string para setar    
* @param dados string com conteudo do arquivo.
*/
void varreString(Personagem *p, char dados[]){

    int in = 0; int i = 0;

    for (int pos = 0, cont = 1; cont <= 9; pos++){
        
        if (dados[pos] == ':'){
            
            in = pos+3; // começo da informação
            static char aux[100];
            
            while (dados[in] != '\''){
                aux[i] = dados[in];
                in++; i++;
            }

            setAtributos(p,cont,aux);

            memset(aux, 0, 100);
            cont++;
            i = 0;
        }
    }
}


/**
* Le conteudo de um arquivo e aloca em uma string.    
* @param caminho path do arquivo a ser lido.
*/
void leArquivo(Personagem *p, char caminho[]){
    
    char aux[1000];
    FILE *arq = fopen(caminho, "r");
    
    fscanf(arq, "%[^\n]s", aux);
    varreString(p, aux);

    fclose(arq);
}

/**
 * Metodo privado recursivo para exibir elementos.
 * @param i No em analise.
 */
void mostrarPosRec(No* i) {
   if (i != NULL) {
      mostrarPosRec(i->esq);
      mostrarPosRec(i->dir);
      printf("%s ", i->personagem->nome);
   }
}

void mostrarPos() {
   printf("[ ");
   mostrarPosRec(raiz);
   printf("]\n");
}


int main(){
     
    clock_t begin = clock(); 
    char caminho[100]; int i=0;

    start();
    
    scanf("%[^\n]s", caminho);
    
    while(strcmp(caminho,"FIM") != 0){ // Enquanto não for fim
        
        Personagem *p = (Personagem*)malloc(sizeof(Personagem));
        leArquivo(p,caminho);
        inserir(p);
        i++;

        scanf(" %[^\n]s", caminho); // Repete leitura do path
    }
    
    char nome[100]; i = 0;

    while(strcmp(nome,"FIM") != 0){ // Enquanto não for fim
        
        fflush(stdin);
        scanf("%[^\n]s", nome);
        
        printf("%s raiz ", nome);
        bool resp = pesquisar(nome);
        i++;
        
        if (resp == true) printf("SIM\n");
        else printf("N%cO\n",199);

    }

    clock_t end = clock();
    double Tempo = (end - begin) / (double)CLOCKS_PER_SEC / 1000.0;
    
    FILE *arq;
    arq = fopen("matricula_avl.txt","w");

    fprintf(arq,"matricula: 655310\tTempo de Execução: %fms\tComparacoes: %i\t Movimentacoes: %i",Tempo,comp,mov);
    
    return 0;
}
