package jdoPrac;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Employee implements Serializable {
	public static final long serialversionUID =1;
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String dept;
	private String designation;
	private double salary;
	
	public Employee(String name, String dept, String des, double salary) {
		this.name = name;
		this.dept = dept;
		this.designation = dept;
		this.salary = salary;
	}
	
	


	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getDept() {
		return dept;
	}




	public void setDept(String dept) {
		this.dept = dept;
	}




	public String getDesignation() {
		return designation;
	}




	public void setDesignation(String designation) {
		this.designation = designation;
	}




	public double getSalary() {
		return salary;
	}




	public void setSalary(double salary) {
		this.salary = salary;
	}




	@Override
	public String toString() {
		return String.format("(Name : %s, Dept :%s, Designation :%s, Salary :%f)", this.name,this.dept,this.designation,this.salary);
	}
}
