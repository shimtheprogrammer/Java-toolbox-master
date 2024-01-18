import java.util.Scanner;

public class BackPropagation 
{
	public static void main(String args[])
	{
		int target;
		double output = 0, error, nerror1, nerror2, input1, input2;
		double[][] weights = new double[][]{{0.1,0.3},{0.5,0.2},{0.2,0.1}};
		boolean stop = false;
		
		Scanner input = new Scanner(System.in);
		
		System.out.printf("Enter the first input\t");
		input1 = input.nextDouble();
		System.out.printf("Enter the second input\t");
		input2 = input.nextDouble();
		System.out.printf("Enter the target\t");
		target = input.nextInt();
		
		while(stop == false)
		{
			output = sigmoid((sigmoid(input1*weights[0][0]+input2*weights[1][0]))*weights[2][0]+(sigmoid(input1*weights[0][1]+input2*weights[1][1]))*weights[2][1]);
		
			if(output < (target + 0.01) && output > (target - 0.01))
			{
				stop = true;
				
				System.out.println("Final weights are;");
				
				for(int i = 0; i < 3; i++)
				{
					for(int j = 0; j <2; j++)
					{
						System.out.printf("\n%f",weights[i][j]);
					}
				}
			}
		    error = (output * (1 - output)) * (target - output);
			
			weights[2][0] = weights[2][0] + (error * sigmoid(input1*weights[0][0]+input2*weights[1][0]));
			weights[2][1] = weights[2][1] + (error * sigmoid(input1*weights[0][1]+input2*weights[1][1]));
			
			nerror1 = (output * (1 - output)) * (error * weights[2][0]);
			nerror2 = (output * (1 - output)) * (error * weights[2][1]);
			
			weights[0][0] = weights[0][0] + (nerror1 * input1);
			weights[1][0] = weights[1][0] + (nerror1 * input2);
			weights[0][1] = weights[0][1] + (nerror2 * input1);
			weights[1][1] = weights[1][1] + (nerror2 * input2);
		}
	}

	static double sigmoid(double out) 
	{
		return 1/(1+Math.pow(2.718281828,-out));
	}
}
