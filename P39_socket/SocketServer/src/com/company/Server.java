package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    private static int serverPort = 5050;
    private static ServerSocket serverSocket;
    private static int count=0;//count the connection of client

    private static ArrayList clients = new ArrayList();

    public static void main(String[] args){
        try{
            serverSocket = new ServerSocket(serverPort);
            System.out.println("server is start.");
            System.out.println("Waiting for client connection");

            while(!serverSocket.isClosed())
            {
                waitNewClient();
            }
        }catch (IOException e)
        {
            System.out.println("Server socket Error");
        }

    }

    public static void waitNewClient(){
        try{
            Socket socket = serverSocket.accept();
            count++;
            System.out.println("現在使用者個數："+count);

            addNewClient(socket);
        }catch (IOException e){}
    }

    public static void addNewClient(final Socket socket) throws IOException{
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    clients.add(socket);

                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    while(socket.isConnected()){
                        String msg = bufferedReader.readLine();

                        if(msg == null)
                        {
                            System.out.println("Client Disconnected");
                            break;
                        }

                        System.out.println(msg);

                        castMsg(msg);
                    }
                }catch (IOException e)
                {
                    e.getStackTrace();
                }finally {
                    {
                        clients.remove(socket);
                        count--;
                        System.out.println("現在使用者個數："+count);
                    }
                }
            }
        });
        thread.start();
    }

    public static void castMsg(String Msg){
        Socket[] clientArrays = new Socket[clients.size()];

        clients.toArray(clientArrays);

        for(Socket socket:clientArrays){
            try{
                BufferedWriter bufferedWriter;
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                bufferedWriter.write(Msg+"\n");
                bufferedWriter.flush();
            }catch (IOException e){}
        }
    }
}

