import java.util.Random;
import java.util.Scanner;


public class Genetics 
{
	static Random generator = new Random();
	
	public static int geneLength = 5, size = 30, chromosomNumber = 10;
	public static int[][][] population = new int[size][chromosomNumber][geneLength];
	public static int[][][] population1 = new int[size][chromosomNumber][geneLength];;;
	public static int[] genes;
	public static int[][] fit;					
	public static int fitness = 0, fixedfit = 0;
	
	public static void main(String[] args)
	{
		
		int found = 0, gen = 0, trial = 0, temp;
		boolean stop = false;
		
		Scanner input = new Scanner(System.in);
		
		System.out.printf("Please enter number of genes per chromosome \t");
		geneLength = input.nextInt();
		System.out.printf("\nPlease enter number of chromosomes per individual \t");
		chromosomNumber = input.nextInt();
		System.out.printf("\nPlease enter number of individuals in the population \t");
		size = input.nextInt();
		
		System.out.printf("\n\nPlease enter a 1 or 0 to represent the gene in the positions stated\n");
		for(int a = 0; a < chromosomNumber; a++)
		{
			for(int s = 0; s < geneLength ; s++)
			{
				System.out.printf("Chromosome %d Gene %d\t",a ,s );
				temp = input.nextInt();
			}
		}
		System.out.printf("\n");
		
		fixedfit = calcfitness(fit);
		
		population = populate(size);
		
		for(int i = 0; i < size; i++)
		{
			for(int g = 0; g < chromosomNumber; g++)
			{
				System.out.printf(" {");
				for(int j = 0; j < geneLength ; j++)
				{
					System.out.printf("%d ",population[i][g][j]);
				}
				System.out.printf("} ");
			}
			System.out.printf("\n");
		}
		System.out.printf("%d\n",fixedfit);
		
		found = checkpop(population);
		
		while(stop == false)
		{
			if(found == size && gen == size)
			{
				population = populate(size);
				population1 = populate(size);
				trial++;
				System.out.printf("\n try ++\n");
				found = size;
				gen = 0;
			}
			else if(found == size && gen < size)
			{
				System.out.printf("\nno solution in generation %d trial %d\n",gen ,trial);
				fitness = 0;
				nextgen();
				
				found = checkpop(population1);
				gen++;
				fitness = 0;
				
				for(int m = 0; m < size; m++)
				{
					for(int o = 0; o < chromosomNumber; o++)
					{
						System.out.printf(" {");
						for(int n = 0; n < geneLength ; n++)
						{
							population[m][o][n] = population1[m][o][n];
							System.out.printf("%d ",population[m][o][n]);
						}
						System.out.printf("} ");
					}
					System.out.printf("%d\n",found);
				}
			}
			else
			{
				stop = true;
				System.out.printf("\nsolution found at %d and in generation %d and try %d\n",found ,gen ,trial);
				for(int h = 0; h < chromosomNumber; h++)
				{
					System.out.printf(" {");
					for(int j = 0; j < geneLength ; j++)
					{
						System.out.printf("%d ",population[found][h][j]);
					}
					System.out.printf("} ");
				}
			}
		}
	}

	private static void nextgen() 
	{
		int[][] parent1 = new int[chromosomNumber][geneLength];
		int[][] parent2 = new int[chromosomNumber][geneLength];
		
		for(int i = 0; i < size; i++)
		{
			parent1 = getparent();
			parent2 = getparent();
			
			for(int h = 0; h < chromosomNumber; h++)
			{
				for(int j = 0; j < geneLength; j++)
				{
					if(j < (geneLength / 2))
					{
						population1[i][h][j] = parent1[h][j];
					}
					else
					{
						population1[i][h][j] = parent2[h][j];
					}
				}
			}
		}
		
		for(int l = 0; l < (size * 0.1); l++)
		{
			int mutant = generator.nextInt(size);
			int temp = 0;
			
			for(int m = 0; m < chromosomNumber; m ++)
			{
				temp = population1[mutant][m][0];
				population1[mutant][m][0] = population1[mutant][m][geneLength - 1];
				population1[mutant][m][geneLength - 1] = temp;
			}
		}
	}

	private static int[][] getparent()// uses tournament selection with k as 5
	{
		int[][] fitnesschart = new int[5][2];
		int[][] indi = new int[chromosomNumber][geneLength];
		int ran = 0;
		int win;
		
		for(int i = 0; i < 5; i++)
		{
			ran = generator.nextInt(size);
			System.out.printf("random no %d \n ",ran);
			
			for(int k = 0; k < chromosomNumber; k ++)
			{
				for(int j = 0; j < geneLength; j ++)
				{
					indi[k][j] = population[ran][k][j];
				}
				fitnesschart[i][0] = calcfitness(indi);
				fitnesschart[i][1] = ran;
				fitness = 0;
			}
		}
		
		win = fitnesschart[0][1];
		
		for(int m = 1; m < 5; m++)
		{
			ran = generator.nextInt(size);
			if((fitnesschart[m][0] - fixedfit) <= (fitnesschart[m - 1][0] - fixedfit))
			{
				win = fitnesschart[m][1];
			}
		}
		
		for(int n = 0; n < chromosomNumber; n++)
		{
			for(int l = 0; l < geneLength; l ++)
			{
				indi[n][l] = population[win][n][l];
			}
		}
			
		return indi;
	}
	
	private static int checkpop(int[][][] population2) 
	{
		int[][] indi = new int[chromosomNumber][geneLength];
		
		for(int k = 0; k < size; k++)
		{
			for(int m = 0; m < chromosomNumber; m ++)
			{
				for(int l = 0; l < geneLength; l++)
				{
					indi[m][l] = population2[k][m][l];
				}
			}
			
			if(calcfitness(indi) == fixedfit)
			{
				return k;
			}
		}
		return size;
	}

	private static int calcfitness(int[][] indi) //based on NK model where n = 10 and k = 1
	{
		int fit = 0;
		for(int j = 0; j < chromosomNumber; j++)
		{
			for(int i = 1; i < geneLength ; i++)
			{
				
					if(indi[j][i - 1] == 0 && indi[j][i] == 0)
					{
						fitness = fitness + 0;
					}
					
					if(indi[j][i - 1] == 0 && indi[j][i] == 1)
					{
						fitness = fitness + 1;
					}
					
					if(indi[j][i - 1] == 1 && indi[j][i] == 0)
					{
						fitness = fitness + 2;
					}
					
					if(indi[j][i - 1] == 1 && indi[j][i] == 1)
					{
						fitness = fitness - 1;
					}	
			}
		}
		fit = fitness;
		fitness = 0;
		return fit;
	}

	private static int[][][] populate(int size1) 
	{
		int[][][] popu = new int[size1][chromosomNumber][geneLength];
		genes = new int[geneLength];
		
		for(int i = 0; i < size1; i++)
		{
			for(int k = 0; k < chromosomNumber; k++)
			{
				for(int j = 0; j < geneLength; j++)
				{
					byte gene = (byte) Math.round(Math.random());
					genes[j] = gene;
					popu[i][k][j] = genes[j];
				}
			}
		}
		return popu;
	}

}
