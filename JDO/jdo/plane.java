
import java.io.Serializable;
import javax.persistence.Entity;
@Entity
public class plane implements Serializable {
	
		private static final long serialVersionUID = 1L;

		 Long id;
		 int x;
		 int y;
		 int z;
		public plane(int x, int y,int z) {
			super();
			this.x = x;
			this.y = y;
			this.z = z;
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
		
		
		public int getZ() {
			return z;
		}
		
		
		@Override
		public String toString() {
			return String.format("(%d, %d, %d)", this.x, this.y,this.z);
		}
		
		

	}

