import java.util.Scanner;

public class PageRank {
	
	int path[][] = new int[10][10];
	double page_rank[] = new double[10];
	double nodes;
	
	public void CalcPageRank() {
		double DampingFactor = 0.85;
		double temp[] = new double[10];
		double InitialPageRank = 1/nodes;
		double OutLinks = 0;
		int step = 1;
		int k = 1;
		
		//Assign initial Page Rank
		for(int i = 1;i <= nodes;i++)
			page_rank[i] = InitialPageRank;
		
		//Display initial Page Rank
		System.out.println("\nINTITAL PAGE RANKS:");
		for(int i = 1;i <= nodes;i++)
			System.out.println(i+": "+page_rank[i]);
		
		//calculate new Page Rank in iterations
		while(step <= 2) {
			
			//Copy Page Rank to temp array
			for(int i = 1;i <= nodes;i++) {
				temp[i] = page_rank[i];
				page_rank[i] = 0;
			}
			
			//Iterate through nodes
			for(int i = 1;i <= nodes;i++) {
				for(int j = 1;j <= nodes;j++) {
					if(path[j][i] == 1) {
						k = 1;
						OutLinks = 0;
						while(k <= nodes) {
							if(path[j][k] == 1) {
								OutLinks++;
							}
							k++;
						}
						//Calculate PageRank 
						page_rank[i] += temp[j] * (1/OutLinks);
					}
				}
			}
			System.out.println("\nAFTER ITERATION "+step+", PAGE RANK:");
			for(int i = 1;i <= nodes;i++)
				System.out.println(i+": "+page_rank[i]);
			step++;
		}
		
		//Add Damping Factor
		for(int i = 1;i <= nodes;i++)
			page_rank[i] = (1-DampingFactor) + (DampingFactor * page_rank[i]);
		
		//Final PageRank Values
		System.out.println("\nFINAL PAGE RANK VALUES:\n");
		System.out.println("NODE\tPAGE_RANK");
		for(int i = 1;i <= nodes;i++)
			System.out.println(i+"\t"+page_rank[i]);
	}
	
	public static void main(String args[]) {
		Scanner s =  new Scanner(System.in);
		PageRank pr = new PageRank();
		System.out.println("PAGE RANKING ALGORITHM\n");
		System.out.println("ENTER THE NUMBER OF NODES/WEBPAGES: ");
		pr.nodes = s.nextDouble();
		System.out.println("ENTER THE PATH/ADJACENCY MATRIX (0/1):");
		for(int i = 1;i <= pr.nodes;i++) {
			for(int j = 1;j <= pr.nodes;j++) {
				pr.path[i][j] = s.nextInt();
				if(i == j)
					pr.path[i][j] = 0;
			}
		}
		System.out.println("\nMATRIX:");
		for(int i = 1;i <= pr.nodes;i++) {
			for(int j = 1;j <= pr.nodes;j++) {
				System.out.print(pr.path[i][j]+ " ");
			}
			System.out.println();
		}
		pr.CalcPageRank();
	}	
}
