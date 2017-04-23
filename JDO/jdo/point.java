import java.io.Serializable;

import javax.persistence.Entity;
@Entity
public class point implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 Long id;
	 int x;
	 int y;
	public point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	public Long getId() {
		return id;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	
	
	@Override
	public String toString() {
		return String.format("(%d, %d)", this.x, this.y);
	}
	
	

}
