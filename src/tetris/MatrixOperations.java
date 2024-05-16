package tetris;

public class MatrixOperations {

    public static int[][] rotateMatrix(int[][] matrix){
        int size = matrix.length;
        int[][] rotatedMatrix = new int[size][size];

        // Step 1: rows to columns
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                rotatedMatrix[j][i] = matrix[i][j];
            }
        }

        // step 2: inversion
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size / 2; j++) {
                int temp = rotatedMatrix[i][j];
                rotatedMatrix[i][j] = rotatedMatrix[i][size - 1 - j];
                rotatedMatrix[i][size - 1 - j] = temp;
            }
        }

        return rotatedMatrix;
    }
}