package cache;

public class InMemoryCacheDemo {
  private static CacheService cacheService = new CacheService();

  public static void main(String args[]) throws Exception {
    cacheService.runCache();
  }
}
