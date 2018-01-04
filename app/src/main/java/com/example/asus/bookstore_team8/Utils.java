package com.example.asus.bookstore_team8;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.text.format.Formatter;
import android.util.Log;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

import static android.content.Context.WIFI_SERVICE;

/**
 * Created by yxong on 28/12/2017.
 */

public class Utils {

    public final static String IP = getIP();

    private static String getIP(){
        /*WifiManager wm = (WifiManager) mContext.getSystemService(WIFI_SERVICE);
        int ip = wm.getConnectionInfo().getIpAddress();

        //convert 4-byte IPv4-address to string
        IP = String.format("%d.%d.%d.%d", (ip & 0xff), (ip >> 8 & 0xff), (ip >> 16 & 0xff),
                (ip >> 24 & 0xff));*/

        String ip = null;
        try {
            ip = InetAddress.getByName("DESKTOP-3D5MKAS").getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        Log.e("IP",ip);
        return ip;
    }

    private static String getComputerName()
    {
        Map<String, String> env = System.getenv();
        if (env.containsKey("COMPUTERNAME"))
            return env.get("COMPUTERNAME");
        else if (env.containsKey("HOSTNAME"))
            return env.get("HOSTNAME");
        else
            return "Unknown Computer";
    }
}

