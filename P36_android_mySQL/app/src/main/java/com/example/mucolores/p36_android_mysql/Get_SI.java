package com.example.mucolores.p36_android_mysql;
//SI == Server Information
public class Get_SI {

    private static String Server_IP = "192.168.43.213";
    public String GET_SERVER_URL()
    {
        String link = "http://" + Server_IP + "/android/";
        return link;
    }
    public void Set_Server_IP(String ip)
    {
        Server_IP = ip;
    }
    public String Get_server_IP()
    {
        return Server_IP;
    }

}
