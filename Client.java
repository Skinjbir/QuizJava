import common.Question;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.*;
import java.net.*;

/**
 * The Client class represents a quiz client that connects to a server,
 * receives questions, and displays them in a GUI.
 */
public class Client {
    private Socket socket;
    private ObjectInputStream in;
    private PrintWriter out;
    private JFrame frame;
    private JTextArea questionArea;
    private JRadioButton[] options;
    private ButtonGroup optionsGroup;
    private JButton nextButton;

    /**
     * Constructs a Client object and connects to the specified server.
     *
     * @param serverAddress the address of the server
     * @param serverPort the port of the server
     * @throws IOException if an I/O error occurs when creating the socket
     */
    public Client(String serverAddress, int serverPort) throws IOException {
        socket = new Socket(serverAddress, serverPort);
        in = new ObjectInputStream(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream(), true);
        setupGUI();
    }

    /**
     * Sets up the graphical user interface for the quiz client.
     */
    private void setupGUI() {
        frame = new JFrame("Quiz Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500); // Increased frame size
        frame.setLayout(new BorderLayout());

        questionArea = new JTextArea(5, 40);
        questionArea.setEditable(false);
        questionArea.setFont(new Font("Arial", Font.BOLD, 16));
        questionArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        frame.add(new JScrollPane(questionArea), BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel();
        options = new JRadioButton[4];
        optionsGroup = new ButtonGroup();
        optionsPanel.setLayout(new GridLayout(4, 1, 10, 10));
        optionsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            options[i].setFont(new Font("Arial", Font.PLAIN, 14));
            optionsGroup.add(options[i]);
            optionsPanel.add(options[i]);
        }
        frame.add(optionsPanel, BorderLayout.CENTER);

        nextButton = new JButton("Next");
        nextButton.setFont(new Font("Arial", Font.BOLD, 14));
        nextButton.setBackground(new Color(70, 130, 180));
        nextButton.setForeground(Color.WHITE);
        nextButton.addActionListener(e -> sendAnswer());
        frame.add(nextButton, BorderLayout.SOUTH);

        frame.setVisible(true);
        receiveQuestion();
    }

    /**
     * Receives a question from the server and updates the UI.
     */

    private void receiveQuestion() {
        try {
            Object obj = in.readObject();
            if (obj instanceof String && ((String) obj).startsWith("Quiz terminé")) {

                String finalMessage = (String) obj;
                JOptionPane.showMessageDialog(frame, finalMessage);
                socket.close();
                frame.dispose();
                return;
            }

            if (obj instanceof Question) {
                Question question = (Question) obj;
                updateUIWithQuestion(question);
            } else {
                JOptionPane.showMessageDialog(frame, "Question format incorrect. Veuillez réessayer.");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the UI with the given question.
     *
     * @param question the question to display
     */
    private void updateUIWithQuestion(Question question) {
        questionArea.setText(question.getQuestionText());
        String[] optionsText = question.getOptions();
        for (int i = 0; i < optionsText.length; i++) {
            options[i].setText(optionsText[i]);
        }
        optionsGroup.clearSelection();
    }

    /**
     * Sends the selected answer to the server.
     */
    private void sendAnswer() {
        for (int i = 0; i < 4; i++) {
            if (options[i].isSelected()) {
                out.println(i + 1);
                receiveQuestion();
                return;
            }
        }
        JOptionPane.showMessageDialog(frame, "Veuillez sélectionner une réponse !");
    }

    /**
     * The main method to start the quiz client.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new Client("localhost", 12345);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}