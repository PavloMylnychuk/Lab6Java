import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DrawingApp extends JFrame implements ActionListener {
    private JPanel drawingPanel;
    private Color currentColor = Color.BLACK;
    private int currentThickness = 3;

    public DrawingApp() {
        setTitle("Малювання з вибором кольору та товщини лінії");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        // Створення області для малювання
        drawingPanel = new JPanel() {
            Point start = null;
            Point end = null;

            {
                addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mousePressed(java.awt.event.MouseEvent evt) {
                        start = evt.getPoint();
                    }

                    public void mouseReleased(java.awt.event.MouseEvent evt) {
                        start = null;
                        end = null;
                    }
                });

                addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
                    public void mouseDragged(java.awt.event.MouseEvent evt) {
                        end = evt.getPoint();
                        repaint();
                    }
                });
            }

            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (start != null && end != null) {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setColor(currentColor);
                    g2d.setStroke(new BasicStroke(currentThickness));
                    g2d.drawLine(start.x, start.y, end.x, end.y);
                }
            }
        };

        // Створення меню
        JMenuBar menuBar = new JMenuBar();
        JMenu colorMenu = new JMenu("Колір");
        JMenuItem[] colorItems = {
                new JMenuItem("Чорний"),
                new JMenuItem("Червоний"),
                new JMenuItem("Синій"),
                new JMenuItem("Зелений")
        };
        for (JMenuItem item : colorItems) {
            item.addActionListener(this);
            colorMenu.add(item);
        }
        menuBar.add(colorMenu);

        JMenu thicknessMenu = new JMenu("Товщина лінії");
        JMenuItem[] thicknessItems = {
                new JMenuItem("Тонка"),
                new JMenuItem("Середня"),
                new JMenuItem("Товста")
        };
        for (JMenuItem item : thicknessItems) {
            item.addActionListener(this);
            thicknessMenu.add(item);
        }
        menuBar.add(thicknessMenu);

        setJMenuBar(menuBar);

        add(drawingPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DrawingApp());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "Чорний":
                currentColor = Color.BLACK;
                break;
            case "Червоний":
                currentColor = Color.RED;
                break;
            case "Синій":
                currentColor = Color.BLUE;
                break;
            case "Зелений":
                currentColor = Color.GREEN;
                break;
            case "Тонка":
                currentThickness = 3;
                break;
            case "Середня":
                currentThickness = 6;
                break;
            case "Товста":
                currentThickness = 9;
                break;
            default:
                break;
        }
    }
}
