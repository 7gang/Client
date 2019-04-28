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

    static List<String> data = new ArrayList<>(); // the list to hold the data from the most recent successful "get" request
    private static Socket socket = null; // the current socket connection
    private static PrintWriter out = null; // the current output stream
    private static BufferedReader in = null; // the current input stream
    private static long timer = 0; // the most recent "get" request time stamp, used to avoid spamming the api endpoint

    /**
     * Retrieves the most recent dataset
     * @return the most recent dataset
     */
    public static List<String> getQuotes() {
        if (System.currentTimeMillis() - timer > 2000) // effectively cache data for two seconds
            request("get"); // send a new request if it's been more than two seconds since the last request went through
        return data;
    }

    /**
     * Add the specified quote String to the remote database
     * @param quote the quote String to be added
     * @return a Boolean value depending on the success of the network request
     */
    public static Boolean addQuote(String quote) {
        return request("add", quote);
    }

    /**
     * Edit the specified quote String at the remote database, changing it to the new quote String
     * @param targetQuote the quote String to be edited
     * @param newQuote the quote String to replace the targetQuote
     * @return a Boolean value depending on the success of the network request
     */
    public static Boolean editQuote(String targetQuote, String newQuote) {
        return request("edit", targetQuote, newQuote);
    }

    /**
     * Delete the specified quote String at the remote database
     * @param quote the quote String to be deleted
     * @return a Boolean value depending on the success of the network request
     */
    public static Boolean deleteQuote(String quote) {
        return request("delete", quote);
    }

    /**
     * Carry out a new network request by establishing a connection, submitting given parameters and saving the response code and dataset
     * @param parameters the variable amount of parameters to send over the network, where each entry is sent on it's own TCP line
     * @return a Boolean value depending on the success of the network request
     */
    private static Boolean request(String...parameters) {
        
        if (!connect()) return false; // no connection could be established
        
        // send request
        for (String p : parameters) 
            out.println(p); // send each parameter on it's own TCP output line
        try {
            socket.shutdownOutput(); // attempt to conclude the request
            // request must have been successfully sent if no errors have occured by now
            timer = System.currentTimeMillis(); // refresh the data cache timer
            System.out.println("A server request accured with parameters: " + parameters);
            return saveResponse(); // if the response is saved correctly, the request is successful

        } catch (IOException e) {
            // report the error and close the socket in case the request could not be sent
            e.printStackTrace();
            closeSocket();
            return false; // returning false indicating a failiure
        }

    }

    /**
     * Read and save a remote server response
     * @return a Boolean value depending on the success of the network request
     */
    private static Boolean saveResponse() {

        ArrayList<String> response = new ArrayList<>(); // ArrayList to hold each line of the server response
        String inputLine; // variable to hold a given line during iteration
        try {
            // append the input line to the reponse ArrayList as long as there are lines to read
            while ((inputLine = in.readLine()) != null)
                response.add(inputLine);
            // then close the socket
            socket.close();

        } catch (IOException e) {
            // if anything goes wrong the error is propogated to the caller
            e.printStackTrace();
            return false;
        }
        // close the socket, neglecting an ungraceful close
        closeSocket();

        // save the returned dataset from the response ArrayList if successful, then return the request success code
        Boolean success = response.get(1).equals("1"); // whether or not the request was successfully processed by the remote server
        if (success) data = response.subList(response.indexOf("data") + 1, response.size()); // neglect the parts of the response containing the success code
        return success;

    }

    /**
     * Establish a TCP connection to the remote server
     * @return a Boolean value depending on the success of the network request
     */
    private static boolean connect() {

        try {
            // populate various class variables
            socket = new Socket("localhost", 65432);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            return socket.isConnected(); // return whether or not the socket connection was established successfully

        } catch (IOException e) {
            // in case of an error, attempt to close the socket and propogate the error to the calling method
            e.printStackTrace();
            closeSocket();
            return false;
        }

    }

    /**
     * Close the TCP socket connection, disrigarding any errors that might occur as a result
     */
    private static void closeSocket() {
        try {
            socket.close(); // attempting close
        } catch (IOException e) {
            // in case of an error, simply inform the user without taking any further action
            System.out.println("Socket could not be gracefully closed!");
            e.printStackTrace();
        }
    }

}