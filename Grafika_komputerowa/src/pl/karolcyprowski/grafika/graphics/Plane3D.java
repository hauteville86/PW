package pl.karolcyprowski.grafika.graphics;

public class Plane3D {

	private double x;
	private double y;
	private double z;
	private double d;
	
	public Plane3D(double x, double y, double z, double d) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
		this.d = d;
	}
	
	public Plane3D(Point3D point, Vector3D vector) {
		this.x = vector.getX();
		this.y = vector.getY();
		this.z = vector.getZ();
		this.d = -(vector.getX() * point.getX() + vector.getY() * point.getY() + vector.getZ() * point.getZ());
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

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public double getD() {
		return d;
	}

	public void setD(double d) {
		this.d = d;
	}
	
	public Vector3D getVector() {
		return new Vector3D(x, y, z, 1);
	}
	
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append("Plane3D: (x=");
		b.append(x);
		b.append("; y=");
		b.append(y);
		b.append("; z=");
		b.append(z);
		b.append("; d=");
		b.append(d);
		b.append(")");
		return b.toString();
	}
	
	
	
	
}
