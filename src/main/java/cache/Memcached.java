package cache;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.text.NumberFormat;

import net.spy.memcached.MemcachedClient;

public class Memcached {
  public static void main(String[] args) throws IOException {
    //Connecting to Redis server on localhost
    //Jedis jedis = new Jedis("localhost");

    MemcachedClient mcc = new MemcachedClient(new
        InetSocketAddress("localhost", 11211));
    System.out.println("Connection to server sucessfully");
    mcc.set("hi",9, "hey");
    System.out.println(mcc.get("hi"));
    Runtime runtime = Runtime.getRuntime();

    NumberFormat format = NumberFormat.getInstance();

    StringBuilder sb = new StringBuilder();
    long maxMemory = runtime.maxMemory();
    long allocatedMemory = runtime.totalMemory();
    long freeMemory = runtime.freeMemory();

    sb.append("free memory: " + format.format(freeMemory / 1073741824) + "<br/>");
    sb.append("allocated memory: " + format.format(allocatedMemory / 1073741824) + "<br/>");
    sb.append("max memory: " + format.format(maxMemory / 1073741824) + "<br/>");
    sb.append("total free memory: " + format.format((freeMemory + (maxMemory - allocatedMemory)) / 1073741824) + "<br/>");

    System.out.println("Memory : "+sb.toString());
    //check whether server is running or not
    // System.out.println("Server is running: "+jedis.ping());
  }
}