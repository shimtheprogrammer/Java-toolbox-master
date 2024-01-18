
public class mean {

	public static int k=2;
	public static int[][] dataset=new int[][]{{1,1},{2,1},{4,3},{5,4}};
	public static int[][] centroid=new int[k][2];
	public static double distance[][]=new double[4][2];
	
	public static void main(String []args){
			for(int i=0; i<k;i++){
				centroid[i][0]=dataset[i][0];
				centroid[i][1]=dataset[i][1];
			
				for (int j=0;j<4;j++){
				int x=Integer.valueOf(dataset[j][0]);
				int y=Integer.valueOf(dataset[j][1]);
				
				int d=(x-Integer.valueOf(centroid[i][0]))*(x-Integer.valueOf(centroid[i][0]));//euclidean distance for first element
				int f=(y-Integer.valueOf(centroid[i][1]))*(y-Integer.valueOf(centroid[i][1]));//euclidean for second element;
				
				int sum=d+f;
				distance[j][i]= Math.sqrt(sum);
				

				} 
				
				}
			for(int i=0; i<4;i++){
				//System.out.println(distance[i][0]);
				//System.out.println(distance[i][1]);
				//System.out.print("\t");
				if(distance[i][0]<distance[i][1]){
					//dataset[][]
				}
			}
			
			
			
			
}
}

