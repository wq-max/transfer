#ifndef MGRAPH_H_INCLUDED
#define MGRAPH_H_INCLUDED

#define MaxVNum 100 //���������ֵ
#define MaxDistance 65535  //��������֮��������
int FS[MaxVNum];   //�����洢��������ʼ����
FILE* fout; //��������������output.txt
typedef struct {
	int Vex[MaxVNum];
	int Edge[MaxVNum][MaxVNum];
	int n, e;       //������������
} MGraph;

void CreateMGraph(MGraph* G);
void ShowMGraph(MGraph G);
void DFSM(MGraph G,int u);
void DFSMGraph(MGraph G,int u,int *visited);
void BFSM(MGraph G, int u);
void BFSMGraph(MGraph G, int u, int *visited);
void Dijkstra(MGraph G, int u);

#endif // MGRAPH_H_INCLUDED
