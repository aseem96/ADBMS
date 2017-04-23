import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Student implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int roll;
	private String name;
	private String subject;
	private int marks;
	private int age;
	
	public Student(int r,String n,String sub,int m,int a) {
		this.roll = r;
		this.name = n;
		this.subject = sub;
		this.marks = m;
		this.age = a;
	}
	
	public int getRoll() {
		return this.roll;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getSubject() {
		return this.subject;
	}
	
	public int getMarks() {
		return this.marks;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public String toString() {
		return String.format("(Roll: %d,  Name: %s,  Subject: %s,  Marks: %d, Age: %d)",this.roll,this.name,this.subject,this.marks,this.age);
	}
	
}
