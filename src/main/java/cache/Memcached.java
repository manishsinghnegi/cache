package cache;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.text.NumberFormat;

import net.spy.memcached.MemcachedClient;

public class Memcached {
  private static final String KEY = "Person_Details";
  public static void main(String[] args) throws IOException, InterruptedException {
    MemcachedClient mcc = new MemcachedClient(new
        InetSocketAddress("localhost", 11211));
    Person person = new Person("Donald", "Trump", "donald.trump", 73);
    System.out.println("Connection to server sucessful");
    System.out.println("Data put into Cache - Name :"+ person.getName() + " Surname :"+person.getSurname());
    mcc.set(KEY,10, person);
    Person cachedPerson = (Person)mcc.get(KEY);
    System.out.println("Fetched Data From Cache - Name :"+ cachedPerson.getName() + " Surname :"+cachedPerson.getSurname());
    System.out.println("11 second wait for record to expire....");
    Thread.sleep(11000);
    if(mcc.get(KEY) == null){
      System.out.println("No Person Data for "+ KEY);
    }
    else{
      cachedPerson = (Person)mcc.get(KEY);
      System.out.println("After Sleep : Name :"+ cachedPerson.getName() + " Age :"+cachedPerson.getAge());
    }
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