#include <stdio.h>
#include <stdlib.h>
#include"MGraph.h"

int main()
{
    fout = fopen("output.txt", "w");
    MGraph G;
    CreateMGraph(&G);
    //ShowMGraph(G);
    int visited[MaxVNum];
    int i,j;
    for(i = 0; i < G.n; i++)
        visited[i] = 0;
    /*for(i = 0; FS[i]!=-1;i++)
    {
        printf("DFS From V%d: ",FS[i]);
        fprintf(fout,"DFS From V%d: ",FS[i]);
        DFSMGraph(G,FS[i],visited);
        printf("\n");
        fprintf(fout,"\n");
        for(j = 0; j < G.n; j++)
           visited[j] = 0;
    }
    for(i = 0; FS[i]!=-1;i++)
    {
        printf("BFS From V%d: ",FS[i]);
        fprintf(fout,"BFS From V%d: ",FS[i]);
        BFSMGraph(G,FS[i],visited);
        printf("\n");
        fprintf(fout,"\n");
        for(j = 0; j < G.n; j++)
           visited[j] = 0;
    }
    for(i = 0; FS[i]!=-1;i++)
       Dijkstra(G,FS[i]);*/
    Dijkstra(G,0);
    return 0;
}
