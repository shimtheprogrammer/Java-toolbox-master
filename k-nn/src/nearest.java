import java.util.Scanner;


public class nearest {

	public static int k=8;//choose K
	public static int trainingSet[][]=new int[][]{{4,3},{1,3},{3,3},{3,7},{7,4},{4,1},{6,5},{5,6},{3,7},{6,2},
		{4,6},{4,4},{5,8},{7,8},{5,6},{10,5},{7,6},{4,10},{9,7},{5,4},{8,5},{6,6},{7,4},{8,8}};
	public static int querySet[][]=new int[][]{{6,5}};
	public static int distance[]=new int[24];
	public static int testSet[][][]=new int[24][2][2];

	public static void main(String []args){
		int i, d1, d2, d;
		Scanner input = new Scanner(System.in); 
		
		System.out.printf("In cluster a the data sets are\t 1,1 2,1\nIn cluster b the data sets are\t 4,3 5,4" );
		
		int g8 = input.nextInt();
		for(i=0; i<24; i++){//calculate distance from the queryset to the training set
			d1=((trainingSet[i][0])-(querySet[0][0]))*((trainingSet[i][0])-(querySet[0][0]));
			d2=((trainingSet[i][1])-(querySet[0][1]))*((trainingSet[i][1])-(querySet[0][1]));
			
			d=d1+d2;
			int x1=Integer.valueOf(trainingSet[i][0]);
			int x2=Integer.valueOf(trainingSet[i][1]);
			testSet[i][0][0]=x1;
			testSet[i][1][0]=x2;
			testSet[i][0][1]=d;
			//System.out.print(testSet[i][0][1]);
			//System.out.printf("\n");
			
			testSet[i][0][1]=distance[i];
		
		}
		
		
		/****************SORT***************/
		//sort the distance
		 int count=0;
		 int swap;
		 		 
		  do{
		  for(int j=1; j<23; j++){
			 if(testSet[j][0][1]<=testSet[j-1][0][1]){

					swap=testSet[j][0][0];
					testSet[j][0][0]=testSet[j-1][0][0];
					testSet[j-1][0][0]=swap;
					
					swap=testSet[j][1][0];
					testSet[j][1][0]=testSet[j-1][1][0];
					testSet[j-1][1][0]=swap;
					
					swap=testSet[j][0][1];
					testSet[j][0][1]=testSet[j-1][0][1];
					testSet[j-1][0][1]=swap;
					count++;//to indicate that swap has happened
				}
			
		  }
		 
		  }while(count>0);
		  //System.out.print(k);
		  System.out.printf("%d nearest neighbours are:\n",k);
		  for(i=0; i<k; i++){
			  
			  System.out.print(testSet[i][0][0]);
			  System.out.printf(",");
			  System.out.print(testSet[i][1][0]);
			  System.out.printf("\n");
			  
		  } 
		  System.out.printf("the query set is classified in the cluster\t");
		  System.out.print(testSet[2][0][0]);
		  System.out.printf(",");
		  System.out.print(testSet[2][1][0]);
		  System.out.printf("\n");
		  
	}
	}
	