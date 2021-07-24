package rpcDemo;

import org.apache.hadoop.ipc.VersionedProtocol;

public interface MyInterface extends VersionedProtocol {
    long versionID = 1L;
    int add(int num1, int num2);
    String findName(String studentId);
}
