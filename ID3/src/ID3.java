import java.util.Scanner;


public class ID3 
{	
	public static String[][] training_data = new String[][]{{"red","square","big","like"},
															{"blue","square","big","like"},
															{"red","round","small","dislike"},
															{"green","square","small","dislike"},
															{"red","round","big","like"},
															{"green","round","big","dislike"}};
	public static int[][] training_set = new int[][]{{0,0,0,0},{1,0,0,0},{0,1,1,1},{2,0,1,1},{0,1,0,0},{2,1,0,1}};
	public static int[][] finale;
	
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		
		String[] info_gain = new String[]{"InfoGain Like","InfoGain Color","InfoGain Shape","InfoGain Size"}; 
		double[][] infogain = new double[][]{{0,0},{0,0},{0,0},{0,0}};
		int x = 0, nx = 0, y = 0, ny = 0, z = 0, nz = 0, total = 0, ntotal = 0, k = 1, l = 3;
		double big = -1;
		
		System.out.printf("Please enter the entries from the table in small letters, with each element having up to 3 varietions\n"); 
		String one = input.next();
		String one1 = input.next();
		for(int n = 0; n < 2; n++)
		{
			for(int j = 0; j < 6 ; j++)
			{
				if(training_set[j][l] == 0 )
				{
					x++;
				}
				if(training_set[j][l] == 0 )
				{
					nx++;
				}
			}
			
			infogain[0][0] = entropy(x,nx);
			infogain[0][1] = l;
			x=y=z=nx=ny=nz=0;
			
			System.out.printf("%s\t",info_gain[0]);
			System.out.printf("%f\n\n",infogain[0][0]);
		
			for(int i = 0; i < 3 ; i++)
			{
				for(int j = 0; j < 6 ; j++)
				{
					if(training_set[j][i] == 0 && training_set[j][l] == 0)
					{
						x++;
					}
					if(training_set[j][i] == 1 && training_set[j][l] == 0)
					{
						y++;
					}
					if(training_set[j][i] == 2 && training_set[j][l] == 0)
					{
						z++;
					}
					if(training_set[j][i] == 0 && training_set[j][l] == 1)
					{
						nx++;
					}
					if(training_set[j][i] == 1 && training_set[j][l] == 1)
					{
						ny++;
					}
					if(training_set[j][i] == 2 && training_set[j][l] == 1)
					{
						nz++;
					}
				}
				
				total = x + y +z;
				ntotal = nx + ny + nz;
				
				//System.out.printf("\n\n x = %d nx = %d y = %d  ny = %d z = %d nz = %d\n\n",x,nx,y,ny,z,nz);
				
				infogain[k][0] = infogain[0][0] - ((x + nx) * entropy(x,nx)) / (total + ntotal) - ((y + ny) * entropy(y,ny)) / (total + ntotal) - ((z + nz) * entropy(z,nz)) / (total + ntotal);
				infogain[k][1] = i;
				
				x=y=z=nx=ny=nz=0;
				
				System.out.printf("%s\t",info_gain[k]);
				System.out.printf("%f\n",infogain[k][0]);
				k++;
			}
		
			//double show = entropy(3,3)- ((3) * entropy(2,1)) / 6 - ((1) * entropy(1,0)) / 6 - ((2) * entropy(0,2)) / 6;
			for( int m = 1; m < 4; m ++)
			{
				if(infogain[m - 1][0] > infogain[m][0])
				{
					big = infogain[m - 1][1];
				}
			}
			
			l = (int) big;
			
			System.out.printf("\nThe highest information gain is  %f\n",infogain[1][0]);
			
			double d = 14, c = 0;
			double show = entropy(9,5);
			
			//System.out.printf("\n\n final entropy  %f\t\n\n",show);
			
			k = 1;
		}
	}
	
	static double entropy(double p, double n)
	{
		double pn = p + n;
		double show = ( ( -( p/( pn ) )* logbase2( p/(pn) ) ) + ( -( n/( pn )) * logbase2( n/(pn) ) ) ) ;
		//System.out.printf("\n\n fraction  %f\t\n\n",show);
		if(show > 0 || show < 0)
		{
			return show;
		}
		else
		{
			return 0;
		}
	}
	
	static double logbase2( double x)
	{
		return ((Math.log(x)/(Math.log(2))));
	}
	
}
