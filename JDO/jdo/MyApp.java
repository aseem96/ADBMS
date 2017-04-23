package jdoPrac;


import java.io.IOException;
import java.io.Serializable;

import javax.naming.spi.DirStateFactory.Result;
import javax.persistence.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MyApp {
	
	public int showMenu(BufferedReader br) throws IOException {
		int ch;
		System.out.println("\n\n*** JDO exec ***");
		System.out.println("1. Input Point objects");
		System.out.println("2. Count number of points");
		System.out.println("3. MAX of X co-ordinate");
		System.out.println("4. MIN of Y co-ordinate");
		System.out.println("5. SELECT all Point objects");
		System.out.println("6. Find and object by id");
		System.out.println("7. Update an Object");
		System.out.println("8. WHERE clause");
		System.out.println("9. DELETE object");
		System.out.println("10. Exit");
		System.out.println("Your choice : ");
		ch = Integer.parseInt(br.readLine());
		
		return ch;
	}
	
	public void insertPoints(int pnum,EntityManager em) {
		Point p;
		Random r = new Random();
		double x,y;
		// begin transaction
		em.getTransaction().begin();
		for(int i=0;i<pnum;i++) {
			x = r.nextInt(1000);	
			y = r.nextInt(1000);
			System.out.println("x : "+x+" & y: "+y);
			p = new Point(x,y);
			// make Persistent objects
			em.persist(p);
		}
		// commit transaction
		em.getTransaction().commit();
	}
	
	public static void main(String args[]) throws IOException {
		
		// creating JDO Entity Connection
	
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/p1.odb");
		EntityManager em = emf.createEntityManager();
		
		
		// Input buffer object
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		boolean go = true;
		MyApp appObj = new MyApp();
		int pnum, count,objid;
		Point p;
		Query q;
		List<Point> plist;
		double newx, newy;
		
		while(go) {
			
			int ch = appObj.showMenu(br);
			switch(ch) {
			
			case 1: System.out.println("Enter number of points to be entered :");
					pnum = Integer.parseInt(br.readLine());
					appObj.insertPoints(pnum,em);
					break;
			case 2: System.out.println("Getting count of point objects");
					q = em.createQuery("SELECT COUNT(p) from Point p");
					System.out.println("Total points : "+q.getSingleResult());
					break;
			
			case 3: System.out.println("Getting MAX of X co-ordinates");
					q = em.createQuery("SELECT MAX(p.x) from Point p");
					System.out.println("Max Y co-ordinate : "+q.getSingleResult());
					break;
					
			case 4: System.out.println("Getting MIN of X co-ordinates");
					q = em.createQuery("SELECT MIN(p.y) from Point p");
					System.out.println("MIN Y co-ordinate : "+q.getSingleResult());
					break;		
			
			case 5: System.out.println("SELECT all Point objects");
					q = em.createQuery("SELECT p from Point p",Point.class);
					plist = q.getResultList();
					// display all objects
					for(Point pobj : plist) {
						System.out.println(pobj.toString());
					}
					break;
			
			case 6: System.out.println("Finding an object by id");
					System.out.println("Enter the object id :");;
					objid = Integer.parseInt(br.readLine());
					p = em.find(Point.class, objid);
					System.out.println("Fetched co-ordinates : "+p.toString());
					break;
			
			case 7: System.out.println("Updating object");
					System.out.println("Enter the object id :");;
					objid = Integer.parseInt(br.readLine());
					System.out.println("Enter new x cordinate");
					newx = Double.parseDouble(br.readLine());
					System.out.println("Enter new y cordinate");
					newy = Double.parseDouble(br.readLine());
					p = em.find(Point.class, objid);
					System.out.println("Object "+objid+" before updating :"+p.toString());
					// start transaction
					em.getTransaction().begin();
					p.setX(newx);
					p.setY(newy);
					em.persist(p);
					em.getTransaction().commit();
					// commit transaction
					p = em.find(Point.class, objid);
					System.out.println("Object "+objid+" after updating :"+p.toString());
					break;
			
			case 8: System.out.println("Finding an object WHERE X co-ordinator is greater than a limit");
					System.out.println("Enter the x lower limit :");
					newx = Double.parseDouble(br.readLine());
					q = em.createQuery("SELECT p from Point p where p.x > "+String.valueOf(newx),Point.class);
					plist = q.getResultList();
					// display all objects
					for(Point pobj : plist) {
						System.out.println(pobj.toString());
					}
					break;
			
			case 9: System.out.println("Deleting object");
					System.out.println("Enter the object id :");;
					objid = Integer.parseInt(br.readLine());
					p = em.find(Point.class, objid);
					System.out.println("Object "+objid+" before updating :"+p.toString());
					
					// start transaction
					System.out.println("Deleteing object "+objid);
					em.getTransaction().begin();
					em.remove(p);
					em.getTransaction().commit();
					System.out.println("Object "+objid+" deleted succesfully");
					// 	commit transaction
					try{
						p = em.find(Point.class, objid);
					}
					catch(Exception e){
						System.out.println("Object "+objid+" Not Found.");
					}
					break;
					
			case 10: go = false;
					 break;
			default: System.out.println("Wrong choice entered");
					 
			}
		}
		em.close();
		emf.close();
	}
}
