import java.util.Arrays;

public class MatrixRowsColumnsSum {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
                // Додайте вашу матрицю тут
        };

        findRowsAndColumnsWithEqualSums(matrix);
    }

    public static void findRowsAndColumnsWithEqualSums(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[] rowsSum = new int[rows];
        int[] colsSum = new int[cols];

        // Обчислення сум елементів рядків та стовпців
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                rowsSum[i] += matrix[i][j];
                colsSum[j] += matrix[i][j];
            }
        }

        // Пошук рядків та стовпців з однаковими сумами
        boolean found = false;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (rowsSum[i] == colsSum[j]) {
                    System.out.println("Рядок " + i + " і стовпець " + j + " мають однакову суму: " + rowsSum[i]);
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("Рядки та стовпці з однаковими сумами відсутні.");
        }
    }
}
