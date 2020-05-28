package cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.cache.CacheLoader;

@Component
public class PersonCacheLoader extends CacheLoader<String,Person>{
	
	@Autowired
	PersonSerializer personSerializer = new PersonSerializer();
	
	public Person load(String key) throws Exception {
		return personSerializer.deserialize(key);
	}

	public void putRecord(String key, Person person){
		personSerializer.serialize(key, person);
	}
}
