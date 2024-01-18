import java.util.Scanner;


public class perceptron 
{
	public static double learning_rate = 0.1;
	public static double threshold = 0.5;
	public static int output[];
	public static int actual[];
	public static int[][] training_set = new int[][]{{1,0,0},{1,0,1},{1,1,0},{1,1,1}};
	
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		
		output = new int[4];
		actual = new int[]{0,0,0,0};
		double[] weights = new double[]{0,0,0};
		int n, i = 0, j = 0;
		double s = 0, d;
		boolean stop = false;
		
		System.out.printf("Please enter 4 binary digits for the target\n");
		
		for(int k = 0; k < 4; k++)
		{
			System.out.printf("\t");
			output[k] = input.nextInt();
		}
		
		while(stop == false)
		{
			for(j = 0; j < 3; j++)
			{
				s = s + training_set[i][j] * weights[j];
			}
			
			if(s > threshold)
			{
				n = 1;
			}
			
			else
			{
				n = 0;
			}
			
			actual[i] = n;
			
			d = (output[i] - n) * learning_rate;
			
			for(j = 0; j < 3; j++)
			{
				weights[j] = weights[j] + (training_set[i][j] * d);
			}
			
			i++;
			
			if( i == 4)
			{
				i = 0;
			}
			
			s = 0;
			
			if(actual[0] == output[0] && actual[1] == output[1] && actual[2] == output[2] && actual[3] == output[3])
			{
				stop = true;		
			}	
		}
		
		System.out.printf("The final weights will be %.2f %.2f %.2f",weights[0],weights[1],weights[2]);
	}
}
