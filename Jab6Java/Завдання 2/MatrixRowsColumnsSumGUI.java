import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MatrixRowsColumnsSumGUI extends JFrame {
    private JPanel panel;
    private JButton loadButton;
    private JTable table;
    private DefaultTableModel tableModel;

    public MatrixRowsColumnsSumGUI() {
        setTitle("Пошук рядків та стовпців з однаковими сумами");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        panel = new JPanel(new BorderLayout());

        loadButton = new JButton("Завантажити матрицю з файлу");
        panel.add(loadButton, BorderLayout.NORTH);

        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    try {
                        String filePath = fileChooser.getSelectedFile().getPath();
                        BufferedReader reader = new BufferedReader(new FileReader(filePath));

                        String line;
                        while ((line = reader.readLine()) != null) {
                            String[] rowData = line.split("\\s+");
                            tableModel.addRow(rowData);
                        }

                        reader.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Помилка читання файлу!", "Помилка", JOptionPane.ERROR_MESSAGE);
                    } catch (NumberFormatException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Невірний формат вхідних даних у файлі!", "Помилка", JOptionPane.ERROR_MESSAGE);
                    } catch (CustomArithmeticException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Сталася власна виняткова ситуація: " + ex.getMessage(), "Помилка", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MatrixRowsColumnsSumGUI());
    }

    static class CustomArithmeticException extends ArithmeticException {
        public CustomArithmeticException(String message) {
            super(message);
        }
    }
}
