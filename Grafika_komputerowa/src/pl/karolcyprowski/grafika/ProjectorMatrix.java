package pl.karolcyprowski.grafika;

import pl.karolcyprowski.grafika.graphics.Rotable;

public class ProjectorMatrix {
	
	private static final double ZOOM_STEP = 0.1;
	private double d;
	
	private double[][] matrix = {{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};
	
	public ProjectorMatrix() {
		this.d = 1;
	}
	
	public double[][] getProjectionMatrix() {
		double[][] m = {{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 1/d, 0}};
		return m;
	}

	public double getD() {
		return d;
	}

	public void setD(double d) {
		this.d = d;
	}
	
	public void reset() {
		this.matrix[0][0] = 1;
		this.matrix[0][1] = 0;
		this.matrix[0][2] = 0;
		this.matrix[0][3] = 0;
		this.matrix[1][0] = 0;
		this.matrix[1][1] = 1;
		this.matrix[1][2] = 0;
		this.matrix[1][3] = 0;
		this.matrix[2][0] = 0;
		this.matrix[2][1] = 0;
		this.matrix[2][2] = 1;
		this.matrix[2][3] = 0;
		this.matrix[3][0] = 0;
		this.matrix[3][1] = 0;
		this.matrix[3][2] = 0;
		this.matrix[3][3] = 1;
	}
	
	public double[][] getMatrix() {
		return matrix;
	}
	
	public void changeMatrix(double[][] rotationMatrix) {
		matrix = Matrix.multiply(rotationMatrix, matrix);
	}
	
	public void increaseD() {
		d += ZOOM_STEP;
//		matrix[3][3] = d;
		System.out.println("Increased value d: " + d);
	}
	
	public void decreaseD() {
		d -= ZOOM_STEP;
//		matrix[3][3] = d;
		System.out.println("Decreased value d: " + d);
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Projector Matrix: [");
//		builder.append(x);
//		builder.append("; y=");
//		builder.append(y);
//		builder.append("; z=");
//		builder.append(z);
//		builder.append(")");
		return builder.toString();
	}
	
}
