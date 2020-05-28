package cache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;

@Component
public class PersonCache {

	LoadingCache<String, Person> cache;

	PersonCacheLoader loader = new PersonCacheLoader();

	PersonRemovalListener listener = new PersonRemovalListener();
	
	public void init(){
		cache = CacheBuilder.newBuilder().
				maximumSize(100).
				expireAfterAccess(10, TimeUnit.SECONDS).
				removalListener(listener).
				build(loader);
	}

	public Person get(String key){
		Person person;
		try{
			person = cache.get(key);
		}catch(Exception ex){
			return null;
		}
		return person;
	}

	public void put(String key, Person person){
		cache.put(key, person);
	}
}
