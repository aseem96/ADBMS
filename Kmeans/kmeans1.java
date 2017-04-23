import java.util.Arrays;
import java.util.Scanner;

public class kmeans1 {
	
	int n,k;
	double means[];
	int points[];
	int clusters[];
	
	public int indexOf(int a[],int x) {
		for(int i = 0;i < a.length;i++) {
			if(a[i] == x)
				return i;
		}
		return -1;
	}
	
	public void insert(int cluster,int point) {
		clusters[indexOf(points,point)] = cluster;
	}
	
	public double calcMean(int cluster) {
		double mean = 0;
		int count = 0,sum = 0;
		for(int i = 0;i < clusters.length;i++) {
			if(clusters[i] == cluster) {
				sum += points[i];
				count++;
			}
		}
		mean = (double)sum/count;
		return mean;
	}
	
	public double difference(int point,int cluster) {
		return(Math.abs((double)point - means[cluster]));
	}
	
	public int allocateCluster(int point) {
		int cl = 0;
		double min = difference(point,0);
		for(int i = 1;i < k;i++) {
			if(difference(point,i) < min) {
				min = difference(point,i);
				cl = i;
			}
		}
		return cl;
	}
	
	public void clustering() {
		Scanner s = new Scanner(System.in);
		System.out.print("\nEnter k: ");
		k = s.nextInt();
		means = new double[k];
		System.out.print("\nEnter n: ");
		n = s.nextInt();
		clusters = new int[n];
		Arrays.fill(clusters,-1);
		points = new int[n];
		System.out.print("\nEnter points: ");
		for(int i = 0;i < n;i++)
			points[i] = s.nextInt();
		System.out.print("\nPoints = [");
		for(int i = 0;i < n;i++)
			System.out.print(points[i]+" ");
		System.out.println("]");
		
		// First k points are defined as clusters
		for(int i = 0;i < k;i++)
			insert(i,points[i]);
		
		boolean flag = true;
		
		while(flag) {
			
			// Calculate cluster mean
			System.out.print("\nCluster Means: ");
			for(int i = 0;i < k;i++) {
				means[i] = calcMean(i);
				System.out.print(calcMean(i)+" ");
			}
			System.out.println();
			
			flag = false;
			// Allocate clusters based on difference
			for(int i = 0;i < n;i++) {
				int cluster = allocateCluster(points[i]);
				if(clusters[i]!= cluster) {
					flag = true;
					clusters[i] = cluster;
				}
			}
			
			// Show clusters for current iteration
			System.out.println("\nCurrent Clusters: \n");
			for(int i = 0;i < k;i++) {
				System.out.print("Cluster "+i+": ");
				for(int j = 0;j < n;j++) {
					if(clusters[j] == i)
						System.out.print(points[j]+" ");
				}
				System.out.println();
			}
		}
	}
	
	public static void main(String args[]) {
		kmeans1 obj = new kmeans1();
		obj.clustering();
	}
}
