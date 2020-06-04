package cache;


public class CacheService {

  private PersonCache personCache = new PersonCache();
  private static final String KEY = "Person_Details";

  public void runCache() throws Exception {

    Person person = new Person("Donald", "Trump", "donald.trump", 73);
    personCache.init();
    System.out.println("Data put into Cache - Name :"+ person.getName() + " Surname :"+person.getSurname());
    personCache.put(KEY, person);
    Person cachedPerson = personCache.get(KEY);

    System.out.println("Fetched Data From Cache - Name :"+ cachedPerson.getName() + " Surname :"+cachedPerson.getSurname());
    System.out.println("11 second wait for record to expire....");
    Thread.sleep(11000);
    if(personCache.get(KEY) == null){
      System.out.println("No Person Data for "+ KEY);
    }
    else{
      cachedPerson = personCache.get(KEY);
      System.out.println("After Sleep : Name :"+ cachedPerson.getName() + " Age :"+cachedPerson.getAge());
    }
  }

}