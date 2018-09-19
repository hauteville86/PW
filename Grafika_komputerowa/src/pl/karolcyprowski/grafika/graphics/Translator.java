package pl.karolcyprowski.grafika.graphics;

import pl.karolcyprowski.grafika.ProjectorMatrix;

public class Translator {
	
	public static final double TRANSLATION_STEP = 2; 
	
	private static final double MAX_Y = 500;
	
	private static int OPERATION_TYPE = 0;
	
	private static final int TRANSLATE_X_POSITIVE = 1;
	private static final int TRANSLATE_X_NEGATIVE = 2;
	private static final int TRANSLATE_Y_POSITIVE = 3;
	private static final int TRANSLATE_Y_NEGATIVE = 4;
	private static final int TRANSLATE_Z_POSITIVE = 5;
	private static final int TRANSLATE_Z_NEGATIVE = 6;
	
	private double[][] matrix = {{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};

	public Translator() {
		
	}
	
	public void changeX(boolean increase) {
		if (increase) {
			OPERATION_TYPE = TRANSLATE_X_POSITIVE;
		} else {
			OPERATION_TYPE = TRANSLATE_X_NEGATIVE;
		}
//		Wall3D.changeReferenceX(increase);
	}
	
	public void changeY(boolean increase) {
		if (increase) {
			OPERATION_TYPE = TRANSLATE_Y_POSITIVE;
		} else {
			OPERATION_TYPE = TRANSLATE_Y_NEGATIVE;
		}
//		Wall3D.changeReferenceY(increase);
	}
	
	public void changeZ(boolean increase) {
		if (increase) {
			OPERATION_TYPE = TRANSLATE_Z_POSITIVE;
		} else {
			OPERATION_TYPE = TRANSLATE_Z_NEGATIVE;
		}
//		Wall3D.changeReferenceZ(increase);
	}
	
	public double[][] getMatrix() {
		return matrix;
	}
	
	public void translate(ProjectorMatrix matrix) {
		reset();
		switch(OPERATION_TYPE) {
			case TRANSLATE_X_POSITIVE:
				translateX(matrix, true);
				break;
			case TRANSLATE_X_NEGATIVE:
				translateX(matrix, false);
				break;
			case TRANSLATE_Y_POSITIVE:
				translateY(matrix, true);
				break;
			case TRANSLATE_Y_NEGATIVE:
				translateY(matrix, false);
				break;
			case TRANSLATE_Z_POSITIVE:
				translateZ(matrix, true);
				break;
			case TRANSLATE_Z_NEGATIVE:
				translateZ(matrix, false);
				break;
			default:
				break;
		}
		OPERATION_TYPE = 0;
	}
	
	private void translateX(ProjectorMatrix matrix, boolean plus) {
		double translationStep = TRANSLATION_STEP;
		if (!plus) {
			translationStep = translationStep * (-1); 
		}
		this.matrix[0][3] = translationStep;
		matrix.changeMatrix(this.matrix);
//		Wall3D.translateReferencePoint(translationStep, 0, 0);
	}
	
	private void translateY(ProjectorMatrix matrix, boolean plus) {
		double translationStep = TRANSLATION_STEP;
		if (!plus) {
			translationStep = translationStep * (-1); 
		}
		this.matrix[1][3] = translationStep;
		matrix.changeMatrix(this.matrix);
//		Wall3D.translateReferencePoint(0, translationStep, 0);
	}
	
	private void translateZ(ProjectorMatrix matrix, boolean plus) {
		double translationStep = TRANSLATION_STEP;
		if (!plus) {
			translationStep = translationStep * (-1); 
		}
		this.matrix[2][3] = translationStep;
		matrix.changeMatrix(this.matrix);
//		Wall3D.translateReferencePoint(0, 0, translationStep);
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
}
