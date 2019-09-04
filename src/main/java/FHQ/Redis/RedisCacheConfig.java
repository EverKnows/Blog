package FHQ.Redis;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.lang.reflect.Method;

@Configuration
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport {

        private volatile JedisConnectionFactory mJedisConnectionFactory;
        private volatile RedisTemplate<Object, Object> mRedisTemplate;
        private volatile RedisCacheManager mRedisCacheManager;

        public RedisCacheConfig() {
            super();
        }

        public RedisCacheConfig(JedisConnectionFactory mJedisConnectionFactory, RedisTemplate<Object, Object> mRedisTemplate, RedisCacheManager mRedisCacheManager) {
            super();
            this.mJedisConnectionFactory = mJedisConnectionFactory;
            this.mRedisTemplate = mRedisTemplate;
            this.mRedisCacheManager = mRedisCacheManager;
        }

        public JedisConnectionFactory redisConnectionFactory() {
            return mJedisConnectionFactory;
        }

        public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory cf) {
            return mRedisTemplate;
        }

        public CacheManager cacheManager(RedisTemplate<?, ?> redisTemplate) {
            return mRedisCacheManager;
        }

    @Override
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName()+"_");
                sb.append(method.getName()+"_");
                for (Object obj : params) {
                    sb.append(obj.toString()+",");
                }
                return sb.toString();
            }
        };
    }
}