package Main;

import javax.swing.*;
import java.awt.*;

public class TextDrawer extends JFrame {

    public TextDrawer(String title, String text) {
        // Window setup
        this.setTitle(title);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Only closes this window
        this.getContentPane().setBackground(Color.BLUE);
        this.setLayout(new BorderLayout(10, 10));

        // Create a styled text area
        JTextArea textArea = new JTextArea();
        textArea.setText(text);
        textArea.setFont(new Font("Consolas", Font.PLAIN, 22));
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setForeground(new Color(240, 240, 240)); // soft white
        textArea.setBackground(new Color(20, 20, 20)); // dark gray
        textArea.setMargin(new Insets(20, 40, 20, 40)); // padding around text

        // Add scrollbars with custom look
        JScrollPane scrollPane = new JScrollPane(
                textArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Optional title label at top
        JLabel header = new JLabel("📘 Journal", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI Semibold", Font.BOLD, 28));
        header.setForeground(new Color(0, 200, 255)); // light blue accent
        header.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));

        this.add(header, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);

        this.setVisible(true);
    }

    // // Example to test
    // public static void main(String[] args) {
    //     String longText = "Sensor Readings Log\n\n"
    //             + "Temperature: 25°C\n"
    //             + "Pressure: 101.3 kPa\n"
    //             + "Humidity: 60%\n"
    //             + "-------------------------\n"
    //             + "These are simulated readings from the monitoring system. "
    //             + "If this were a live environment, data would update dynamically. "
    //             + "The text automatically wraps and can be scrolled for longer logs. "
    //             + "Margins and spacing have been improved for readability and professional display.";
    //     new TextDrawer(longText);
    // }
}
