#include"sort.h"

void Selectsort(int A[], int N)
{
	for (int i = 0; i < N; i++)
	{
		int min = A[i];
		int k = i;
		for (int j = i+1; j < N; j++)
		{
			if (min > A[j])
			{
				min = A[j];
				k = j;
			}
		}
		int temp = A[i];
		A[i] = min;
		A[k] = temp;
	}
}

void Shellsort(int A[], int N)
{
	int i, j, Increment;
	int Tmp;
	for (Increment = N/2; Increment > 0; Increment/=2)
	{
		for (i = Increment; i < N; i++)
		{
			Tmp = A[i];
			for (j = i; j >= Increment; j -= Increment)
			{
				if (Tmp < A[j - Increment])
				{
					A[j] = A[j - Increment];
				}
				else
				{
					break;
				}
			}
			A[j] = Tmp;
		}
	}
}