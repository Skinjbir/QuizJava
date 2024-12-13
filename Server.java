import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;

import common.Question;
import db.DBConnector;

/**
 * The Server class represents a quiz server that listens for client connections,
 * handles client requests, and manages the quiz process.
 */
public class Server {
    private static final int PORT = 12345;

    /**
     * The main method to start the quiz server.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is running on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress());
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/**
 * The ClientHandler class handles communication with a connected client,
 * processes quiz questions, and calculates the client's score.
 */
class ClientHandler implements Runnable {
    private Socket socket;
    private ObjectOutputStream out;
    private BufferedReader in;

    /**
     * Constructs a ClientHandler for the specified client socket.
     *
     * @param socket the client socket
     */
    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    /**
     * The run method to handle client communication and quiz processing.
     */
    @Override
    public void run() {
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            List<Question> questions = fetchQuestionsFromDatabase();

            int score = 0;
            for (Question question : questions) {
                out.writeObject(question);
                out.flush();
                String clientAnswer = in.readLine();
                if (Integer.parseInt(clientAnswer) == question.getCorrectOption()) {
                    score++;
                }
            }

            out.writeObject("Quiz terminé ! Votre score est : " + score + "/10");
            out.flush();

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Fetches quiz questions from the database.
     *
     * @return a list of quiz questions
     * @throws SQLException if a database access error occurs
     */
    private List<Question> fetchQuestionsFromDatabase() throws SQLException {
        List<Question> questions = new ArrayList<>();
        Connection connection = DBConnector.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM questions");

        while (resultSet.next()) {
            questions.add(new Question(
                    resultSet.getInt("id"),
                    resultSet.getString("question_text"),
                    resultSet.getString("option1"),
                    resultSet.getString("option2"),
                    resultSet.getString("option3"),
                    resultSet.getString("option4"),
                    resultSet.getInt("correct_option")
            ));
        }
        connection.close();
        return questions;
    }
}