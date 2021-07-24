package rpcDemo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.ProtocolSignature;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Server端
 */
public class MyInterfaceImpl implements MyInterface {
    @Override
    public int add(int num1, int num2) {
        System.out.println("num1 = " + num1 + " num2 = " + num2 );
        return num1 + num2;
    }

    private static Map<String, String> hashmap = new HashMap<>();
    static {
        hashmap.put("20210123456789", "心心");
    }

    @Override
    public String findName(String studentId) {
        return MyInterfaceImpl.hashmap.getOrDefault(studentId, null);
    }

    @Override
    public long getProtocolVersion(String s, long l) throws IOException {
        return MyInterface.versionID;
    }

    @Override
    public ProtocolSignature getProtocolSignature(String s, long l, int i) throws IOException {
        return new ProtocolSignature();
    }

    public static void main(String[] args) {
        RPC.Builder builder = new RPC.Builder(new Configuration());

        // server ip和port
        builder.setBindAddress("localhost");
        builder.setPort(12345);

        builder.setProtocol(MyInterface.class);
        builder.setInstance(new MyInterfaceImpl());

        try {
            RPC.Server server = builder.build();
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
