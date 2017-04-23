import java.util.Arrays;
import java.util.Scanner;

public class kmeans {
	
	int k,n;
	int[] clusters;
	int[] points;
	double[] clusterMeans;
	
	public int indexOf(int[] a,int value) {
		for(int i = 0;i < a.length;i++){
			if(a[i] == value)
				return i;
		}
		return -1;
	}
	
	public void insert(int cluster,int point) {
		clusters[indexOf(points,point)] = cluster;
	}
	
	public double calculateMean(int cluster) {
		int sum = 0,n = 0;
		for(int i = 0;i < clusters.length;i++){
			if(clusters[i] == cluster){
				sum += points[i];
				n++;
			}
		}
		double mean = (double)sum/n;
		return mean;
	}
	
	public double calculateDiff(int point,int cluster) {
		return Math.abs((double)point - clusterMeans[cluster]); 
	}
	
	public int allocateCluster(int point){
		int cl = 0;
		double minDist = calculateDiff(point,0);
		for(int j = 1;j < k;j++) {
			double difference = calculateDiff(point,j);
			if(minDist > difference) {
				minDist = difference;
				cl = j;
			}					
		}			
		return cl;
	}
	
	public void clustering() {
		Scanner s = new Scanner(System.in);
		System.out.print("ENTER NUMBER OF NODES: ");
		n = s.nextInt();
		clusters = new int[n];
		Arrays.fill(clusters, -1);
		System.out.print("ENTER NUMBER OF CLUSTERS(k): ");
		k = s.nextInt();
		clusterMeans = new double[k];
		System.out.println("ENTER POINTS: ");
		points = new int[n];
		for(int i = 0;i < n;i++)
			points[i] = s.nextInt();
		System.out.print("POINTS = [");
		for(int i = 0;i < n;i++)
			System.out.print(points[i]+" ");
		System.out.println("]");
		for(int i = 0;i < k;i++)			
			insert(i,points[i]);
		boolean flag = true;
		
		while(flag) {
			System.out.print("\nCLUSTER MEANS: ");
			for(int i = 0;i < clusterMeans.length;i++){
				clusterMeans[i] = calculateMean(i);
				System.out.print(clusterMeans[i]+" ");
			}
			System.out.println();
			
			flag = false;
			for(int i = 0;i < points.length;i++){
				int cluster = allocateCluster(points[i]);
				if(clusters[i] != cluster){
					flag = true;
					insert(cluster,points[i]);
				}
			}
			System.out.println("\nCURRENT CLUSTERS : ");
			for(int i = 0;i < k;i++){
				System.out.print("\nCLUSTER "+i+" : ");
				for(int j = 0;j < clusters.length;j++)
					if(clusters[j] == i)
						System.out.print(points[j]+" ");
			}
			System.out.println();
		}
	}
	
	public static void main(String args[]) {
		kmeans km = new kmeans();
		km.clustering();
	}
}
