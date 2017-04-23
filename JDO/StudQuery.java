import java.io.IOException;
import java.io.Serializable;
import javax.persistence.*;
import java.util.*;

public class StudQuery {
	
	public void insertStudent(int num,EntityManager em) {
		Scanner s = new Scanner(System.in);
		int roll,age,marks;
		String name,sub;
		em.getTransaction().begin();
		for(int i = 0;i < num;i++) {
			System.out.println("STUDENT NO. "+i+"\n");
			System.out.print("ENTER ROLL NO: ");
			roll = s.nextInt();
			System.out.print("ENTER NAME: ");
			name = s.next();
			System.out.print("ENTER SUBJECT: ");
			sub = s.next();
			System.out.print("ENTER MARKS: ");
			marks = s.nextInt();
			System.out.print("ENTER AGE: ");
			age = s.nextInt();
			Student st = new Student(roll,name,sub,marks,age);
			em.persist(st);
		}
		em.getTransaction().commit();
	}
	
	public static void main(String args[]) throws IOException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/student.odb");
		EntityManager em = emf.createEntityManager();
		StudQuery sq = new StudQuery();
		Query q;
		Student st;
		Scanner s = new Scanner(System.in);
		int ch,num;
		List <String>list;
		List <Student> stud;
		List <Integer>mark;
		
		while(true) {
			System.out.print("\nENTER CHOICE: ");
			ch = s.nextInt();
			
			switch(ch) {
				case 1:
					System.out.println("\nENTER NUMBER OF STUDENTS: ");
					num = s.nextInt();
					sq.insertStudent(num,em);
					break;
				case 2:
					q = em.createQuery("SELECT st from Student st");
					System.out.println("RESULT : ");
					stud = q.getResultList();
					for(Student stu : stud)
						System.out.println(stu.toString());
					break;
				case 3:
					//Display the name of all students who score the marks more than 60% in  subject Adv. Database	
					q = em.createQuery("SELECT st.name from Student st where st.marks > 60 and st.subject='AD'");
					System.out.println("RESULT : ");
					list = q.getResultList();
					for(String str : list)
						System.out.println(str.toString());
					break;
				case 4:
					//Display the number of students score more than average marks.
					int avg = 0,count = 0;;
					q = em.createQuery("SELECT st.marks from Student st");
					System.out.println("RESULT : ");
					mark = q.getResultList();
					for(int i : mark) {
						count++;
						avg += i;
					}
					avg /= count;
					//System.out.println("Average: "+avg);
					q = em.createQuery("SELECT st from Student st where st.marks > "+String.valueOf(avg));
					stud = q.getResultList();
					for(Student stu : stud)
						System.out.println(stu.toString());
					break;
					
				case 5:
					//List the name of the student who score the second highest marks.
					q = em.createQuery("SELECT st from Student st order by st.marks desc");
					System.out.println("RESULT : ");
					stud = q.getResultList();
					System.out.println(stud.get(1).toString());
					break;
				
				case 6:
					//Display the Name of student who is tops in subject Adv. Databases.
					q = em.createQuery("SELECT st from Student st order by st.marks desc");
					System.out.println("RESULT : ");
					q.setMaxResults(1);
					stud = q.getResultList();
					System.out.println(stud.get(0).toString());
					break;
			}
		}
	}
}
