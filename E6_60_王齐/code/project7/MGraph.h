#ifndef MGRAPH_H_INCLUDED
#define MGRAPH_H_INCLUDED

#define MaxVNum 100 //顶点数最大值
#define MaxDistance 65535  //两个顶点之间最大距离
int FS[MaxVNum];   //用来存储遍历的起始顶点
FILE* fout; //用来保存输出结果output.txt
typedef struct {
	int Vex[MaxVNum];
	int Edge[MaxVNum][MaxVNum];
	int n, e;       //顶点数，边数
} MGraph;

void CreateMGraph(MGraph* G);
void ShowMGraph(MGraph G);
void DFSM(MGraph G,int u);
void DFSMGraph(MGraph G,int u,int *visited);
void BFSM(MGraph G, int u);
void BFSMGraph(MGraph G, int u, int *visited);
void Dijkstra(MGraph G, int u);

#endif // MGRAPH_H_INCLUDED
