package jdoPrac;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Point implements Serializable {
	public static final long serialversionUID =1;
	
	@Id
	@GeneratedValue
	private int id;
	private double x;
	private double y;
	
	public Point(double x,double y) {
		this.x = x;
		this.y = y;
	}
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public double getX() {
		return x;
	}


	public void setX(double x) {
		this.x = x;
	}


	public double getY() {
		return y;
	}


	public void setY(double y) {
		this.y = y;
	}


	@Override
	public String toString() {
		return String.format("(%f,%f)", this.x,this.y);
	}
}
