
#include<stdio.h>
#include<time.h>
#include<stdlib.h>
#include"sort.h"

#define NAX_SIZE 64000
#define K 1
clock_t start, stop;
double total_time;
double duration;

int main()
{
    int i;
    int data[NAX_SIZE];
    srand(time(NULL));
    for (i = 0; i < NAX_SIZE; i++)
        data[i] = rand();
    /*for (i = 0; i < NAX_SIZE; i++)
        printf("%10d", data[i]);
    printf("\n");*/
    start = clock();
    int *A = data;
    for (i = 0; i < K; i++)
    {
        Selectsort(A, NAX_SIZE);
        //Shellsort(A, NAX_SIZE);
        A = data;

    }
    
    stop = clock();
    total_time = ((double)(stop - start)) / CLK_TCK;
    duration = total_time / K;
    /*for (i = 0; i < NAX_SIZE; i++)
        printf("%10d", data[i]);
    printf("\n");*/
    printf("运行%d次的时间为：%f\n", K, total_time);
    printf("运行1次的时间为：%f\n", duration);
    return 0;
}
