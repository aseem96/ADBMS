package jdoPrac;


import java.io.IOException;
import java.io.Serializable;

import javax.naming.spi.DirStateFactory.Result;
import javax.persistence.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class empApp {
	
	public int showMenu(BufferedReader br) throws IOException {
		int ch;
		System.out.println("\n\n*** JDO exec ***");
		System.out.println("1. Input Employee objects");
		System.out.println("2. Count number of Employees");
		System.out.println("3. MAX salary among employees");
		System.out.println("4. MIN salary among employees");
		System.out.println("5. SELECT all Employee objects");
		System.out.println("6. Find an object by id");
		System.out.println("7. Update an Object");
		System.out.println("8. WHERE clause");
		System.out.println("9. DELETE object");
		System.out.println("10. Exit");
		System.out.println("Your choice : ");
		ch = Integer.parseInt(br.readLine());
		
		return ch;
	}
	
	public void insertEmployees(int pnum,EntityManager em,BufferedReader br) throws IOException {
		Employee p;
		Random r = new Random();
		double sal;
		String name, dept, des;
		// begin transaction
		em.getTransaction().begin();
		for(int i=0;i<pnum;i++) {
			System.out.println("Enter name :");
			name = br.readLine();
			System.out.println("Enter dept :");
			dept = br.readLine();
			System.out.println("Enter designation :");
			des = br.readLine();
			System.out.println("Enter salary :");
			sal = Double.parseDouble(br.readLine());
			p = new Employee(name, dept, des, sal);
			// make Persistent objects
			em.persist(p);
		}
		// commit transaction
		em.getTransaction().commit();
	}
	
	public static void main(String args[]) throws IOException {
		
		// creating JDO Entity Connection
	
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/emp.odb");
		EntityManager em = emf.createEntityManager();
		
		
		// Input buffer object
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		boolean go = true;
		empApp appObj = new empApp();
		int pnum, count,objid;
		Employee p;
		Query q;
		List<Employee> plist;
		double sal;
		String name,dept,des;
		
		while(go) {
			
			int ch = appObj.showMenu(br);
			switch(ch) {
			
			case 1: System.out.println("Enter number of Employees to be entered :");
					pnum = Integer.parseInt(br.readLine());
					appObj.insertEmployees(pnum,em,br);
					break;
			case 2: System.out.println("\nGetting count of Employee objects");
					q = em.createQuery("SELECT COUNT(p) from Employee p");
					System.out.println("Total Employees : "+q.getSingleResult());
					break;
			
			case 3: System.out.println("\nGetting MAX salary from all employees");
					q = em.createQuery("SELECT MAX(p.salary) from Employee p");
					System.out.println("Max salary : "+q.getSingleResult());
					break;
					
			case 4: System.out.println("\nGetting MIN salary from all employees");
					q = em.createQuery("SELECT MIN(p.salary) from Employee p");
					System.out.println("MIN salary : "+q.getSingleResult());
					break;		
			
			case 5: System.out.println("\nSELECT all Employee objects");
					q = em.createQuery("SELECT p from Employee p",Employee.class);
					plist = q.getResultList();
					// display all objects
					for(Employee pobj : plist) {
						System.out.println(pobj.toString());
					}
					break;
			
			case 6: System.out.println("\nFinding an object by id");
					System.out.println("Enter the object id :");;
					objid = Integer.parseInt(br.readLine());
					p = em.find(Employee.class, objid);
					System.out.println("Fetched co-ordinates : "+p.toString());
					break;
			
			case 7: System.out.println("\nUpdating Salary of employee");
					System.out.println("Enter the object id :");;
					objid = Integer.parseInt(br.readLine());
					System.out.println("Enter new Salary");
					sal = Double.parseDouble(br.readLine());
					p = em.find(Employee.class, objid);
					System.out.println("Object "+objid+" before updating :"+p.toString());
					// start transaction
					em.getTransaction().begin();
					p.setSalary(sal);
					em.persist(p);
					em.getTransaction().commit();
					// commit transaction
					p = em.find(Employee.class, objid);
					System.out.println("Object "+objid+" after updating :"+p.toString());
					break;
			
			case 8: System.out.println("\nFinding an object WHERE salary is greater than a limit");
					System.out.println("Enter the salary limit:");
					sal = Double.parseDouble(br.readLine());
					q = em.createQuery("SELECT p from Employee p where p.salary > "+String.valueOf(sal),Employee.class);
					plist = q.getResultList();
					// display all objects
					for(Employee pobj : plist) {
						System.out.println(pobj.toString());
					}
					break;
			
			case 9: System.out.println("Deleting object");
					System.out.println("Enter the object id :");;
					objid = Integer.parseInt(br.readLine());
					p = em.find(Employee.class, objid);
					System.out.println("Object "+objid+" before updating :"+p.toString());
					
					// start transaction
					System.out.println("Deleteing object "+objid);
					em.getTransaction().begin();
					em.remove(p);
					em.getTransaction().commit();
					System.out.println("Object "+objid+" deleted succesfully");
					// 	commit transaction
					try{
						p = em.find(Employee.class, objid);
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
