
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class queryplane{

	public static void main(String[] args)  throws Exception{
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("$objectdb/db/world.odb");
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		for(int i=0;i<10;i++)
		{
			//point p=new point(new Random().nextInt(100),new Random().nextInt(100));
			//em.persist(p);
		}
		em.getTransaction().commit();
		//Query q9 =mg.createQuery("select count(emp.Name) from Employee emp,Market mk  where  emp.Name=mk.Name ");
		//System.out.println(q9.getResultList());
		

		Query q7=em.createQuery("select p from point p");
		List<point> results = q7.getResultList();
        for (point p : results) {
            System.out.println(p);
        }
		
        Query q15=em.createQuery("select  p1 from plane p1");
    		System.out.println(q15.getResultList());
        
       /* Query q1=em.createQuery("select p from point p");
        q1.setFirstResult(5);
		System.out.println("set first :: "+q1.getResultList());
		System.out.println();
		//TypedQuery<point> q2=em.createQuery("select p from point p",point.class);
		
		Query q2=em.createQuery("select p.x from point p where p.y>=60");
		System.out.println("p.y > 60  :: "+q2.getResultList());
		System.out.println(); 
		
		
		Query q3=em.createQuery("select avg(p.y) from point p");
		System.out.println("avg(p.y) :: "+q3.getResultList());
		System.out.println();
		
		Query q4=em.createQuery("select max(p.x) from point p");
		System.out.println("max(p.x) :: "+q4.getResultList());
		System.out.println();
		
		Query q5=em.createQuery("select p from point p order by p.x");
		System.out.println("order by p.x :: "+q5.getResultList());
		System.out.println();
		
		Query q6=em.createQuery("select p.x from point p where p.y>60 and p.x>30");
		System.out.println("p.y > 60 and p.x>30 :: "+q6.getResultList());
		System.out.println();
		
		Query q8=em.createQuery("select count(p) from point p");
		System.out.println("count :: "+q8.getResultList());
		System.out.println();
		
		
		Query q9=em.createQuery("select p from point p where (p.x%10)==5");
		System.out.println("modulo :: "+q9.getResultList());
		System.out.println();
		
		
		Query q10=em.createQuery("select p from point p order by p.y");
		q10.setMaxResults(5);
		System.out.println("set max :: "+q10.getResultList());
		System.out.println();
		
		Query q11=em.createQuery("select p from point p where p.x in(53,25,2)");
		System.out.println("In  :: "+q11.getResultList());
		System.out.println();
		
		Query q12=em.createQuery("select p from point p where p.x > p.y");
		System.out.println("p.x >p.y  :: "+q12.getResultList());
		System.out.println();
		
		
		Query q13=em.createQuery("select p from point p where p.x in(53,25,2)");
		System.out.println("In  :: "+q11.getResultList());
		System.out.println();
		
		*/
		
		
	}

}
