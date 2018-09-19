package pl.karolcyprowski.grafika;

import pl.karolcyprowski.grafika.graphics.Point3D;

public class AxisRotator {
	
	private static final double ROTATION_STEP = 0.01;
	private static final double ROTATION_STEP_Z = 0.01;
	
	private static int OPERATION_TYPE = 0;
	
	private static final int ROTATE_X_POSITIVE = 1;
	private static final int ROTATE_X_NEGATIVE = 2;
	private static final int ROTATE_Y_POSITIVE = 3;
	private static final int ROTATE_Y_NEGATIVE = 4;
	private static final int ROTATE_Z_POSITIVE = 5;
	private static final int ROTATE_Z_NEGATIVE = 6;
	
	public void setRotateX(boolean plus) {
		if (plus) {
			OPERATION_TYPE = ROTATE_X_POSITIVE;
		} else {
			OPERATION_TYPE = ROTATE_X_NEGATIVE;
		}
		
	}
	
	public void setRotateY(boolean plus) {
		if (plus) {
			OPERATION_TYPE = ROTATE_Y_POSITIVE;
		} else {
			OPERATION_TYPE = ROTATE_Y_NEGATIVE;
		}
	}
	
	public void setRotateZ(boolean plus) {
		if (plus) {
			OPERATION_TYPE = ROTATE_Z_POSITIVE;
		} else {
			OPERATION_TYPE = ROTATE_Z_NEGATIVE;
		} 
	}
	
	public void rotate(ProjectorMatrix matrix) {
		switch(OPERATION_TYPE) {
			case ROTATE_X_POSITIVE:
				rotateX(matrix, true);
				break;
			case ROTATE_X_NEGATIVE:
				rotateX(matrix, false);
				break;
			case ROTATE_Y_POSITIVE:
				rotateY(matrix, true);
				break;
			case ROTATE_Y_NEGATIVE:
				rotateY(matrix, false);
				break;
			case ROTATE_Z_POSITIVE:
				rotateZ(matrix, true);
				break;
			case ROTATE_Z_NEGATIVE:
				rotateZ(matrix, false);
				break;
			default:
				break;
		}
		OPERATION_TYPE = 0;
	}
	
	private void rotateX(ProjectorMatrix matrix, boolean plus) {
		double rad = ROTATION_STEP;
		if (!plus) {
			rad = rad * (-1);
		}
		double[][] rotation = {{1, 0, 0 ,0}, {0, Math.cos(rad), -1 * Math.sin(rad), 0}, {0, Math.sin(rad), Math.cos(rad), 0}, {0, 0, 0, 1}};
		matrix.changeMatrix(rotation);
//		System.out.println(matrix.toString());
	}
	
	private void rotateY(ProjectorMatrix matrix, boolean plus) {
		double rad = ROTATION_STEP;
		if (!plus) {
			rad = rad * (-1);
		}
		double[][] rotation = {{Math.cos(rad), 0, Math.sin(rad) ,0}, {0, 1, 0, 0}, {-1 * Math.sin(rad), 0, Math.cos(rad), 0}, {0, 0, 0, 1}};
		matrix.changeMatrix(rotation);
	}
	
	private void rotateZ(ProjectorMatrix matrix, boolean plus) {
		double rad = ROTATION_STEP_Z;
		if (!plus) {
			rad = rad * (-1);
		}
		double[][] rotation = {{Math.cos(rad),-1 * Math.sin(rad), 0 ,0}, {Math.sin(rad), Math.cos(rad), 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};
		matrix.changeMatrix(rotation);
	}
	
	public void reset() {
		OPERATION_TYPE = 0;
	}

}
