package pl.karolcyprowski.grafika.graphics;

public class Vector3D {
	
	private double x;
	private double y;
	private double z;
	private double value;
	
	public Vector3D(double x, double y, double z, double value) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.value = value;
	}
	
	public Vector3D(Point3D a, Point3D b) {
		double ax, ay, az, bx, by, bz;		
		if (a.getProjection() != null) {
			double[][] aProjection = a.getProjection();
			ax = aProjection[0][0];
			ay = aProjection[1][0];
			az = aProjection[2][0];
		} else {
			ax = a.getX();
			ay = a.getY();
			az = a.getZ();
		}
		
		
		if (b.getProjection() != null) {
			double[][] bProjection = b.getProjection();
			bx = bProjection[0][0];
			by = bProjection[1][0];
			bz = bProjection[2][0];
		} else {
			bx = b.getX();
			by = b.getY();
			bz = b.getZ();
		}
		
		this.x = ax - bx;
		this.y = ay - by;
		this.z = az - bz;
//		if (this.x < 0) {
//			this.x *= -1;
//		}
//		if (this.z < 0) {
//			this.z *= -1;
//		}
//		if (this.y < 0) {
//			this.y *= -1;
//		}
		this.value = 1;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getZ() {
		return z;	
	}
	
	public double getValue() {
		return value;
	}
	
	public static Vector3D getCrossProduct(Vector3D a, Vector3D b) {
		double x = a.getY() * b.getZ() - a.getZ() * b.getY();
		double y = a.getZ() * b.getX() - a.getX() * b.getZ();
		double z = a.getX() * b.getY() - a.getY() * b.getX();
		return new Vector3D(x, y, z, 1);		
	}
	
	public static double getScalarProduct(Vector3D a, Vector3D b) {
		double x = a.getX() * b.getX();
		double y = a.getY() * b.getY();
		double z = a.getZ() * b.getZ();
		return x + y + z;
	}
	
	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append("Vector3D: (x=");
		b.append(this.getX());
		b.append("; y=");
		b.append(this.getY());
		b.append("; z=");
		b.append(this.getZ());
		b.append(")");
		return b.toString();
	}

}
