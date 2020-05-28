package cache;

import com.google.common.cache.LoadingCache;

public class CacheService {

  private PersonCache personCache = new PersonCache();
  private static final String KEY = "Person Details";
  //private PersonCacheLoader cacheLoader = new PersonCacheLoader();

  public void runCache() throws Exception {

    Person person = new Person("Manish", "Negi", "manish.negi", 30);
    personCache.init();
    personCache.put(KEY, person);
    Person cachedPerson = personCache.get(KEY);

    System.out.println("Before Sleep : Name :"+ cachedPerson.getName() + " Age :"+cachedPerson.getAge());
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
