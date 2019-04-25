package client;

import java.net.Socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStreamReader;

/**
 * The interface responsible for communicating client requests to the remote
 * database server
 */
class Server {

    static List<String> data = new ArrayList<>();
    private static Socket socket = null;
    private static PrintWriter out = null;
    private static BufferedReader in = null;
    private static long timer = 0;

    public static List<String> getQuotes() {
        if (System.currentTimeMillis() - timer > 2000) // effectively cache data for two seconds
            request(new String[] { "get" });
        return data;
    }

    public static Boolean addQuote(String quote) {
        return request(new String[] {"add", quote});
    }

    public static Boolean editQuote(String targetQuote, String newQuote) {
        return request(new String[] { "edit", targetQuote, newQuote });
    }

    public static Boolean deleteQuote(String quote) {
        return request(new String[] { "delete", quote });
    }

    private static Boolean request(String...parameters) {
        
        if (!connect()) return false; // no connection could be established
        
        // send request
        for (String p : parameters) 
            out.println(p);
        try {
            socket.shutdownOutput();
            // request must have been successfully sent
            timer = System.currentTimeMillis();
            System.out.println("A server request accured with parameters: " + parameters);
            return saveResponse(); // if the response is saved, the request was successful

        } catch (IOException e) {
            e.printStackTrace();
            closeSocket();
            return false;
        }

    }

    private static Boolean saveResponse() {

        ArrayList<String> response = new ArrayList<>();
        String inputLine;
        try {
            while ((inputLine = in.readLine()) != null)
                response.add(inputLine);
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        closeSocket();

        Boolean success = response.get(1).equals("1"); // whether or not the request was successfully processed by the remote server
        if (success) data = response.subList(response.indexOf("data") + 1, response.size());
        return success;

    }

    private static boolean connect() {

        try {
            socket = new Socket("localhost", 65432);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            return socket.isConnected();

        } catch (IOException e) {
            e.printStackTrace();
            closeSocket();
            return false;
        }

    }

    private static void closeSocket() {
        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("Socket could not be gracefully closed!");
            e.printStackTrace();
        }
    }

}