
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class jdoquery{

	public static void main(String[] args)  throws Exception{
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("$objectdb/points.odb");
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		for(int i=0;i<10;i++)
		{
			//point p=new point(new Random().nextInt(100),new Random().nextInt(100));
			//em.persist(p);
		//	point p=new point(83,43);
			//em.persist(p);
		}
		
		
		em.getTransaction().commit();
		
		System.out.println("Select p from point p");
		Query q7=em.createQuery("select p from point p");
		List<point> results = q7.getResultList();
        for (point p : results) {
            System.out.println(p);
        }
		
        System.out.println("\n");
	//	Query q15=em.createQuery("select p, p1 from plane p1,point p");
	//	System.out.println(q15.getResultList());
      
       
		System.out.println("select p.x from point p where p.y>=60");
		Query q2=em.createQuery("select p.x from point p where p.y>=60");
		System.out.println("--->"+q2.getResultList());
		System.out.println(); 
		
		
		System.out.println("select avg(p.y) from point p");
		Query q3=em.createQuery("select avg(p.y) from point p");
		System.out.println("--->"+q3.getResultList());
		System.out.println();
		
		
		System.out.println("select max(p.x) from point p");
		Query q4=em.createQuery("select max(p.x) from point p");
		System.out.println("--->"+q4.getResultList());
		System.out.println();
		
		
		System.out.println("select p from point p order by p.x");
		Query q5=em.createQuery("select p from point p order by p.x");
		System.out.println("--->"+q5.getResultList());
		System.out.println();
		
		
		System.out.println("select p.x from point p where p.y>60 and p.x>30");
		Query q6=em.createQuery("select p.x from point p where p.y>60 and p.x>30");
		System.out.println("--->"+q6.getResultList());
		System.out.println();
		
		
		System.out.println("select count(p) from point p");
		Query q8=em.createQuery("select count(p) from point p");
		System.out.println("--->"+q8.getResultList());
		System.out.println();
		
		System.out.println("select p from point p where (p.x%10)==5");
		Query q9=em.createQuery("select p from point p where (p.x%10)==5");
		System.out.println("--->"+q9.getResultList());
		System.out.println();
		
		System.out.println("select p from point p order by p.y");
		Query q10=em.createQuery("select p from point p order by p.y");
		q10.setMaxResults(5);
		System.out.println("--->"+q10.getResultList());
		System.out.println();
		
		
		System.out.println("select p from point p where p.x in(53,25,2)");
		Query q11=em.createQuery("select p from point p where p.x in(53,25,2)");
		System.out.println("--->"+q11.getResultList());
		System.out.println();
		
		System.out.println("select p from point p where p.x > p.y");
		Query q12=em.createQuery("select p from point p where p.x > p.y");
		System.out.println("--->"+q12.getResultList());
		System.out.println();
		
		
		System.out.println("select p from point p where p.x between 10 and 20");
		Query q14=em.createQuery("select p from point p where p.x between 10 and 20");
		System.out.println("--->"+q14.getResultList());
		System.out.println();
		
		
		
	}

}
