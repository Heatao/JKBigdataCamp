package rpcDemo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class MyRPCClient {
    public static void main(String[] args) {
        try {
            MyInterface proxy = RPC.getProxy(MyInterface.class, 1L, new InetSocketAddress(InetAddress.getByName("localhost"), 4896), new Configuration());
            System.out.println(proxy.findName("20210000000000"));
            System.out.println(proxy.findName("20210123456789"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
