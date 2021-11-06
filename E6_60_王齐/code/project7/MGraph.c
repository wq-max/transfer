#include<stdio.h>
#include<stdlib.h>
#include"MGraph.h"

void CreateMGraph(MGraph* G)
{
	FILE* f = fopen("P6-input.txt", "r");
	if (f == NULL)
	{
		printf("文件打开出错！");
		return;
	}
	int n, e=0;
	int i, j, k;
	int u,v,w,fs;
	for(i = 0; !feof(f); i++)
    {
        if(i == 0)
        {
            fscanf(f, "%d %d", &n, &e);
            G->n = n;
	        G->e = e;
	        for(j = 0;j < n; j++)
            {
                G->Vex[j] = j;
                for(k = 0; k < n; k++)
                {
                    if(j == k)
                        G->Edge[j][k] = 0;
                    else
                        G->Edge[j][k] = MaxDistance;
                }
            }
        }
        if(i >0 && i <= e)
        {
            fscanf(f, "%d%d%d", &u,&v,&w);
            G->Edge[u][v] = w;
        }
        if(i > e)
        {
            fscanf(f, "%d", &fs);
            FS[i-e-1] = fs;
        }
    }
	fclose(f);

}

void ShowMGraph(MGraph G)
{
	int n = G.n;
	int i,j;
	for (i = 0; i < n; i++)
    {
        for (j = 0; j < n; j++)
			printf("%6d ", G.Edge[i][j]);
        printf("\n");
    }

}

void DFSM(MGraph G,int u)
{
   printf("DFS From V%d:",u);
   int visited[MaxVNum];
   int i;
   for(i = 0; i < G.n; i++)
        visited[i] = 0;
   DFSMGraph(G,u,visited);
   for(i = 0; i < G.n; i++)
    if(visited[i] == 0)
      DFSMGraph(G,i,visited);
   printf("\n");
}

void DFSMGraph(MGraph G,int u,int *visited)
{
    printf("V%d ",u);
    fprintf(fout,"V%d   ",u);
    visited[u] = 1;
    int i;
    for(i = 0; i < G.n; i++)
    {
        if(visited[i] == 0 && G.Edge[u][i]!=0 && G.Edge[u][i]!=MaxDistance)
            DFSMGraph(G,i,visited);
    }
}

void BFSM(MGraph G, int u)
{
   printf("BFS From V%d:",u);
   int visited[MaxVNum];
   int i;
   for(i = 0; i < G.n; i++)
        visited[i] = 0;
   BFSMGraph(G,u,visited);
   for(i = 0; i < G.n; i++)
    if(visited[i] == 0)
      BFSMGraph(G,i,visited);
   printf("\n");
}

void BFSMGraph(MGraph G, int u, int *visited)
{
    int queue[MaxVNum];
    int front =0, rear = 1;
    queue[front] = u;
    while(front < rear)
    {
        int k = queue[front];
        front = (front + 1) % MaxVNum;
        if(visited[k] == 0)
        {
            printf("V%d ",k);
            fprintf(fout,"V%d   ",k);
            visited[k] = 1;
        }
        int i;
        for(i = 0; i < G.n; i++)
        {
            if(visited[i] == 0 && G.Edge[k][i]!=0 && G.Edge[k][i]!=MaxDistance)
            {
                queue[rear] = i;
                rear = (rear + 1) % MaxVNum;
            }
        }
    }
}

void Dijkstra(MGraph G, int u)
{
    int dist[MaxVNum];
    int flag[MaxVNum];
    int p[MaxVNum];
    int i;
    for(i = 0; i < G.n; i++)
    {
        dist[i] = G.Edge[u][i];
        flag[i] = 0;
        if(dist[i] == MaxDistance)
            p[i] = -1;
        else
            p[i] = u;
    }
    p[u] = -1;
    flag[u] = 1;
    int j;
    for(i = 0; i < G.n; i++)
    {
        int temp = MaxDistance, t = u;
        for(j = 0; j < G.n; j++)
        {
            if(flag[j] == 0 && dist[j] < temp)
            {
                temp = dist[j];
                t = j;
            }
        }
        if(t == u)
            break;
        flag[t] = 1;
        for(j = 0; j < G.n; j++)
        {
            if(flag[j] == 0 && G.Edge[t][j] < MaxDistance && G.Edge[t][j] != 0)
            {
                if(dist[j] > (dist[t] + G.Edge[t][j]))
                {
                    dist[j] = dist[t] + G.Edge[t][j];
                    p[j] = t;
                }
            }
        }
    }
    int x;
    int S[MaxVNum];
    int index = 0;
    for(i = 0; i < G.n; i++)
    {
        x = p[i];
        if(x == -1 && u != i)
        {
            //printf("没有v%d到v%d的路径\n",u,i);
            continue;
        }
        while(x != -1)
        {
            S[index] = x;
            index = (index + 1) % MaxVNum;
            x = p[x];
        }
        while(index > 0)
        {
            index = (index - 1) % MaxVNum;
            printf("v%d,",S[index]);
            fprintf(fout,"v%d,",S[index]);
        }
        if(i != u)
        {
             printf("v%d:  %d\n",i,dist[i]);
             fprintf(fout,"v%d:  %d\n",i,dist[i]);
        }

    }
}
