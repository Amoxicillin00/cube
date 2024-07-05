package com.cube.cloud.core.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * redis缓存工具
 * @author Long
 * @date 2023-04-14 15:20
 */
@Component
public class RedisUtils {

    /**
     * 默认过期时长，单位：秒
     */
    public final static long DEFAULT_EXPIRE = 60 * 60 * 24;
    /**
     * 不设置过期时长
     */
    public final static long NOT_EXPIRE = -1;


    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ValueOperations<String, String> valueOperations;



    public StringRedisTemplate getRedisTemplate() {
        return this.redisTemplate;
    }

    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 设置指定 key 的值
     * @param key 键
     * @param value 值
     */
    public void set(String key, String value) {
        set(key, value, DEFAULT_EXPIRE);
    }

    /**
     * 设置指定 key 的值
     * @param key 键
     * @param value 值
     * @param expire 过期时长(-1不过期，单位：秒)
     */
    public void set(String key, String value, long expire) {
        redisTemplate.opsForValue().set(key, value);
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
    }

    /**
     * 设置缓存（默认过期时长）
     * @param key 键
     * @param value 值
     */
    public void set(String key, Object value) {
        set(key, value, DEFAULT_EXPIRE);
    }

    /**
     * 设置缓存（指定过期时长）
     * @param key 键
     * @param value 值
     * @param expire 过期时长(单位：秒)
     */
    public void set(String key, Object value, long expire) {
        valueOperations.set(key, toJson(value));
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
    }

    /**
     * 批量添加
     * @param maps 键值对
     */
    public void multiSet(Map<String, String> maps) {
        redisTemplate.opsForValue().multiSet(maps);
    }

    /**
     * 获取指定 key 的值
     * @param key 键
     * @return 值
     */
    public String get(String key) {
        return get(key, NOT_EXPIRE);
    }

    /**
     * 获取指定 key 的值
     * @param key 键
     * @param expire 过期时长（-1不刷新过期时长，单位：秒）
     * @return 值
     */
    public String get(String key, long expire) {
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 获取缓存
     * @param key 键
     * @param clazz 类
     * @param <T> 类
     * @return 类
     */
    public <T> T get(String key, Class<T> clazz) {
        return get(key, clazz, NOT_EXPIRE);
    }

    /**
     * 获取缓存（刷新过期时长）
     * @param key 键
     * @param clazz 类
     * @param expire 时长
     * @param <T> 类
     * @return 类
     */
    public <T> T get(String key, Class<T> clazz, long expire) {
        String value = valueOperations.get(key);
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value == null ? null : fromJson(value, clazz);
    }

    /**
     * 批量获取
     * @param keys 集合
     * @return 值集合
     */
    public List<String> multiGet(Collection<String> keys) {
        return redisTemplate.opsForValue().multiGet(keys);
    }

    /**
     * 删除key
     * @param key 键
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 批量删除key
     * @param keys 键集合
     */
    public void delete(Collection<String> keys) {
        redisTemplate.delete(keys);
    }

    /**
     * 序列化key
     * @param key 键
     * @return byte
     */
    public byte[] dump(String key) {
        return redisTemplate.dump(key);
    }

    /**
     * 是否存在key
     * @param key 键
     * @return Boolean
     */
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 查找匹配的key
     * @param pattern 匹配字符
     * @return key集合
     */
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**
     * 设置过期时长
     * @param key 键
     * @param timeout 时长
     * @param unit TimeUnit
     * @return Boolean
     */
    public Boolean expire(String key, long timeout, TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }

    /**
     * 设置过期时间
     * @param key 键
     * @param date 过期时间
     * @return Boolean
     */
    public Boolean expireAt(String key, Date date) {
        return redisTemplate.expireAt(key, date);
    }

    /**
     * 移除 key 的过期时间，key 将持久保持
     * @param key 键
     * @return Boolean
     */
    public Boolean persist(String key) {
        return redisTemplate.persist(key);
    }

    /**
     * 返回 key 的剩余的过期时长
     * @param key 键
     * @param unit TimeUnit
     * @return 剩余的过期时长
     */
    public Long getExpire(String key, TimeUnit unit) {
        return redisTemplate.getExpire(key, unit);
    }

    /**
     * 返回 key 的剩余的过期时长
     * @param key 键
     * @return 剩余的过期时长
     */
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * 将当前数据库的 key 移动到给定的数据库 db 当中
     * @param key 键
     * @param dbIndex 数据库index
     * @return Boolean
     */
    public Boolean move(String key, int dbIndex) {
        return redisTemplate.move(key, dbIndex);
    }

    /**
     * 从当前数据库中随机返回一个 key
     * @return 键值
     */
    public String randomKey() {
        return redisTemplate.randomKey();
    }

    /**
     * 修改 key 的名称
     * @param oldKey 旧的键值
     * @param newKey 新的键值
     */
    public void rename(String oldKey, String newKey) {
        redisTemplate.rename(oldKey, newKey);
    }

    /**
     * 返回 key 所储存的值的类型
     * @param key 键
     * @return org.springframework.data.redis.connection.DataType
     */
    public DataType type(String key) {
        return redisTemplate.type(key);
    }

    /**
     * 仅当 newKey 不存在时，将 oldKey 改名为 newKey
     * @param oldKey 旧的键值
     * @param newKey 新的键值
     * @return Boolean
     */
    public Boolean renameIfAbsent(String oldKey, String newKey) {
        return redisTemplate.renameIfAbsent(oldKey, newKey);
    }

    /**
     * 同时设置一个或多个 key-value 对，当且仅当所有给定 key 都不存在
     * @param maps 键值对
     * @return 之前已经存在返回false, 不存在返回true
     */
    public boolean multiSetIfAbsent(Map<String, String> maps) {
        return redisTemplate.opsForValue().multiSetIfAbsent(maps);
    }

    /**
     * 返回 key 中字符串值的子字符
     * @param key 键
     * @param start 开始位置
     * @param end 结束位置
     * @return 字符串
     */
    public String getRange(String key, long start, long end) {
        return redisTemplate.opsForValue().get(key, start, end);
    }

    /**
     * 用 value 参数覆写给定 key 所储存的字符串值，从偏移量 offset 开始
     * @param key 键
     * @param value 值
     * @param offset 从指定位置开始覆写
     */
    public void setRange(String key, String value, long offset) {
        redisTemplate.opsForValue().set(key, value, offset);
    }

    /**
     * 将给定 key 的值设为 value ，并返回 key 的旧值(old value)
     * @param key 键
     * @param value 值
     * @return 值
     */
    public String getAndSet(String key, String value) {
        return redisTemplate.opsForValue().getAndSet(key, value);
    }

    /**
     * 对 key 所储存的字符串值，获取指定偏移量上的位(bit)
     * @param key 键
     * @param offset 偏移量
     * @return Boolean
     */
    public Boolean getBit(String key, long offset) {
        return redisTemplate.opsForValue().getBit(key, offset);
    }

    /**
     * 设置ASCII码, 字符串'a'的ASCII码是97, 转为二进制是'01100001', 此方法是将二进制第offset位值变为value
     * @param key 键
     * @param offset 偏移量
     * @param value 值
     * @return Boolean
     */
    public Boolean setBit(String key, long offset, boolean value) {
        return redisTemplate.opsForValue().setBit(key, offset, value);
    }

    /**
     * 将值 value 关联到 key ，并将 key 的过期时间设为 timeout
     * @param key 键
     * @param value 值
     * @param timeout 过期时长
     * @param unit 时间单位
     */
    public void setEx(String key, String value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    /**
     * 只有在 key 不存在时设置 key 的值
     * @param key 键
     * @param value 值
     * @return 之前已经存在返回false, 不存在返回true
     */
    public Boolean setIfAbsent(String key, String value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    /**
     * 获取字符串的长度
     * @param key 键
     * @return 字符串的长度
     */
    public Long size(String key) {
        return redisTemplate.opsForValue().size(key);
    }

    /**
     * 增加(自增长), 负数则为自减
     * @param key 键
     * @param increment 自增数
     * @return 自增数
     */
    public Long incrBy(String key, long increment) {
        return redisTemplate.opsForValue().increment(key, increment);
    }

    /**
     * 增加(自增长), 负数则为自减
     * @param key 键
     * @param increment 自增数
     * @return 自增数
     */
    public Double incrByFloat(String key, double increment) {
        return redisTemplate.opsForValue().increment(key, increment);
    }

    /**
     * 追加到末尾
     * @param key 键
     * @param value 值
     * @return Integer
     */
    public Integer append(String key, String value) {
        return redisTemplate.opsForValue().append(key, value);
    }

    /**
     * 获取存储在哈希表中指定字段的值
     * @param key 键
     * @param field 字段
     * @return Object
     */
    public Object getForHash(String key, String field) {
        return redisTemplate.opsForHash().get(key, field);
    }

    /**
     * 获取所有给定字段的值
     * @param key 键
     * @return 键值对
     */
    public Map<Object, Object> getAllForHash(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 获取所有给定字段的值
     * @param key 键
     * @param fields 字段集合
     * @return 对象集合
     */
    public List<Object> multiGetForHash(String key, Collection<Object> fields) {
        return redisTemplate.opsForHash().multiGet(key, fields);
    }

    /**
     * put
     * @param key 键
     * @param hashKey hashKey
     * @param value 值
     */
    public void putForHash(String key, String hashKey, String value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    /**
     * put
     * @param key 键
     * @param hashKey hashKey
     * @param value 值
     */
    public void putForHash(String key, String hashKey, Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    /**
     * putAll
     * @param key 键
     * @param maps 键值对
     */
    public void putAllForHash(String key, Map<String, String> maps) {
        redisTemplate.opsForHash().putAll(key, maps);
    }

    /**
     * 仅当hashKey不存在时才设置
     * @param key 键
     * @param hashKey hashKey
     * @param value 值
     * @return Boolean
     */
    public Boolean putIfAbsentForHash(String key, String hashKey, String value) {
        return redisTemplate.opsForHash().putIfAbsent(key, hashKey, value);
    }

    /**
     * 删除一个或多个哈希表字段
     * @param key 键
     * @param fields 字段
     * @return Long
     */
    public Long deleteForHash(String key, Object... fields) {
        return redisTemplate.opsForHash().delete(key, fields);
    }

    /**
     * 查看哈希表 key 中，指定的字段是否存在
     * @param key 键
     * @param field 字段
     * @return Boolean
     */
    public Boolean existsForHash(String key, String field) {
        return redisTemplate.opsForHash().hasKey(key, field);
    }

    /**
     * 为哈希表 key 中的指定字段的整数值加上增量 increment
     * @param key 键
     * @param field 字段
     * @param increment 增量
     * @return Long
     */
    public Long incrByForHash(String key, Object field, long increment) {
        return redisTemplate.opsForHash().increment(key, field, increment);
    }

    /**
     * 哈希表 key 中的指定字段的整数值加上增量 increment
     * @param key 键
     * @param field 值
     * @param increment 增量
     * @return Double
     */
    public Double incrByFloatForHash(String key, Object field, double increment) {
        return redisTemplate.opsForHash().increment(key, field, increment);
    }

    /**
     *  获取所有哈希表中的字段
     * @param key 键
     * @return 字段集合
     */
    public Set<Object> keysForHash(String key) {
        return redisTemplate.opsForHash().keys(key);
    }

    /**
     * 获取哈希表中字段的数量
     * @param key 键
     * @return Long
     */
    public Long sizeForHash(String key) {
        return redisTemplate.opsForHash().size(key);
    }

    /**
     * 获取哈希表中所有值
     * @param key 键
     * @return 值集合
     */
    public List<Object> valuesForHash(String key) {
        return redisTemplate.opsForHash().values(key);
    }

    /**
     * 迭代哈希表中的键值对
     * @param key 键
     * @param options 操作
     * @return Cursor
     */
    public Cursor<Map.Entry<Object, Object>> scanForHash(String key, ScanOptions options) {
        return redisTemplate.opsForHash().scan(key, options);
    }

    /**
     * 通过索引获取列表中的元素
     * @param key 键
     * @param index 索引
     * @return 元素
     */
    public String indexForList(String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    /**
     * 获取列表指定范围内的元素
     * @param key 键
     * @param start 开始位置, 0是开始位置
     * @param end 结束位置, -1返回所有
     * @return 元素集合
     */
    public List<String> rangeForList(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 存储list元素（头部开始）
     * @param key 键
     * @param value 元素
     * @return Long
     */
    public Long leftPushForList(String key, String value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 存储所有list元素（头部开始）
     * @param key 键
     * @param value 元素集合
     * @return Long
     */
    public Long leftPushAllForList(String key, String... value) {
        return redisTemplate.opsForList().leftPushAll(key, value);
    }

    /**
     * 存储所有list元素（头部开始）
     * @param key 键
     * @param value 元素集合
     * @return Long
     */
    public Long leftPushAllForList(String key, Collection<String> value) {
        return redisTemplate.opsForList().leftPushAll(key, value);
    }

    /**
     * 当list存在的时候才加入
     * @param key 键
     * @param value 元素
     * @return Long
     */
    public Long leftPushIfPresentForList(String key, String value) {
        return redisTemplate.opsForList().leftPushIfPresent(key, value);
    }

    /**
     * 如果pivot存在,再pivot前面添加
     * @param key 键
     * @param pivot pivot
     * @param value 元素
     * @return Long
     */
    public Long leftPushForList(String key, String pivot, String value) {
        return redisTemplate.opsForList().leftPush(key, pivot, value);
    }

    /**
     * 存在list元素（尾部开始）
     * @param key 键
     * @param value 值
     * @return Long
     */
    public Long rightPushForList(String key, String value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 存储所有list元素（尾部开始）
     * @param key 键
     * @param value 元素集合
     * @return Long
     */
    public Long rightPushAllForList(String key, String... value) {
        return redisTemplate.opsForList().rightPushAll(key, value);
    }

    /**
     * 存储所有list元素
     * @param key 键
     * @param value 元素集合
     * @return Long
     */
    public Long rightPushAllForList(String key, Collection<String> value) {
        return redisTemplate.opsForList().rightPushAll(key, value);
    }

    /**
     * 为已存在的列表添加值
     * @param key 键
     * @param value 值
     * @return Long
     */
    public Long rightPushIfPresentForList(String key, String value) {
        return redisTemplate.opsForList().rightPushIfPresent(key, value);
    }

    /**
     * 在pivot元素的右边添加值
     * @param key 键
     * @param pivot pivot
     * @param value 值
     * @return Long
     */
    public Long rightPushForList(String key, String pivot, String value) {
        return redisTemplate.opsForList().rightPush(key, pivot, value);
    }

    /**
     * 通过索引设置列表元素的值
     * @param key 键
     * @param index 索引
     * @param value 值
     */
    public void setForList(String key, long index, String value) {
        redisTemplate.opsForList().set(key, index, value);
    }

    /**
     * 移出并获取列表的第一个元素
     * @param key 键
     * @return 移除元素
     */
    public String leftPopForList(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 移除并获取列表的第一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
     * @param key 键
     * @param timeout 过期时长
     * @param unit 时间单位
     * @return 移除元素
     */
    public String leftPopForList(String key, long timeout, TimeUnit unit) {
        return redisTemplate.opsForList().leftPop(key, timeout, unit);
    }

    /**
     * 移除并获取列表最后一个元素
     * @param key 键
     * @return 移除元素
     */
    public String rightPopForList(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    /**
     * 移除并获取列表的最后一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
     * @param key 键
     * @param timeout 过期时长
     * @param unit 时间单位
     * @return 移除元素
     */
    public String rightPopForList(String key, long timeout, TimeUnit unit) {
        return redisTemplate.opsForList().rightPop(key, timeout, unit);
    }

    /**
     * 移除列表的最后一个元素，并将该元素添加到另一个列表并返回
     * @param sourceKey 源键
     * @param destinationKey 目标键
     * @return 移除元素
     */
    public String rightPopAndLeftPushForList(String sourceKey, String destinationKey) {
        return redisTemplate.opsForList().rightPopAndLeftPush(sourceKey,
                destinationKey);
    }

    /**
     * 从列表中弹出一个值，将弹出的元素插入到另外一个列表中并返回它；
     * 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
     * @param sourceKey 源键
     * @param destinationKey 目标键
     * @param timeout 过期时长
     * @param unit 时间单位
     * @return 移除元素
     */
    public String rightPopAndLeftPushForList(String sourceKey, String destinationKey,
                                        long timeout, TimeUnit unit) {
        return redisTemplate.opsForList().rightPopAndLeftPush(sourceKey,
                destinationKey, timeout, unit);
    }

    /**
     * 删除集合中值等于value得元素
     * @param key 键
     * @param index index=0, 删除所有值等于value的元素; index>0, 从头部开始删除第一个值等于value的元素; ndex<0, 从尾部开始删除第一个值等于value的元素;
     * @param value 值
     * @return Long
     */
    public Long removeForList(String key, long index, String value) {
        return redisTemplate.opsForList().remove(key, index, value);
    }

    /**
     * 裁剪list
     * @param key 键
     * @param start 开始位置
     * @param end 结束位置
     */
    public void trimForList(String key, long start, long end) {
        redisTemplate.opsForList().trim(key, start, end);
    }

    /**
     * 获取列表长度
     * @param key 键
     * @return 长度
     */
    public Long sizeForList(String key) {
        return redisTemplate.opsForList().size(key);
    }

    /**
     * set添加元素
     * @param key 键
     * @param values 元素集合
     * @return Long
     */
    public Long addForSet(String key, String... values) {
        return redisTemplate.opsForSet().add(key, values);
    }

    /**
     * set移除元素
     * @param key 键
     * @param values 元素集合
     * @return Long
     */
    public Long removeForSet(String key, Object... values) {
        return redisTemplate.opsForSet().remove(key, values);
    }

    /**
     * 移除并返回集合的一个随机元素
     * @param key 键
     * @return 随机元素
     */
    public String popForSet(String key) {
        return redisTemplate.opsForSet().pop(key);
    }

    /**
     * 将元素value从一个集合移到另一个集合
     * @param key 键
     * @param value 值
     * @param destKey 另一个集合键
     * @return Boolean
     */
    public Boolean moveForSet(String key, String value, String destKey) {
        return redisTemplate.opsForSet().move(key, value, destKey);
    }

    /**
     * 获取集合的大小
     * @param key 键
     * @return Long
     */
    public Long sizeForSet(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    /**
     * 判断集合是否包含value
     * @param key 键
     * @param value 值
     * @return Boolean
     */
    public Boolean isMemberForSet(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    /**
     * 获取两个集合的交集
     * @param key 键
     * @param otherKey 其他键
     * @return 元素集合
     */
    public Set<String> intersectForSet(String key, String otherKey) {
        return redisTemplate.opsForSet().intersect(key, otherKey);
    }

    /**
     * 获取key集合与多个集合的交集
     * @param key 键
     * @param otherKeys 其他键集合
     * @return 交集元素集合
     */
    public Set<String> intersectForSet(String key, Collection<String> otherKeys) {
        return redisTemplate.opsForSet().intersect(key, otherKeys);
    }

    /**
     * key集合与otherKey集合的交集存储到destKey集合中
     * @param key 键
     * @param otherKey 其他键
     * @param destKey 目标键
     * @return Long
     */
    public Long intersectAndStoreForSet(String key, String otherKey, String destKey) {
        return redisTemplate.opsForSet().intersectAndStore(key, otherKey,
                destKey);
    }

    /**
     * key集合与多个集合的交集存储到destKey集合中
     * @param key 键
     * @param otherKeys 其他键
     * @param destKey 目标键
     * @return Long
     */
    public Long intersectAndStoreForSet(String key, Collection<String> otherKeys,
                                   String destKey) {
        return redisTemplate.opsForSet().intersectAndStore(key, otherKeys,
                destKey);
    }

    /**
     * 获取两个集合的并集
     * @param key 键
     * @param otherKey 其他键
     * @return 并集元素集合
     */
    public Set<String> unionForSet(String key, String otherKey) {
        return redisTemplate.opsForSet().union(key, otherKey);
    }

    /**
     * 获取key集合与多个集合的并集
     * @param key 键
     * @param otherKeys 其他键集合
     * @return 并集元素集合
     */
    public Set<String> unionForSet(String key, Collection<String> otherKeys) {
        return redisTemplate.opsForSet().union(key, otherKeys);
    }

    /**
     * key集合与otherKey集合的并集存储到destKey中
     * @param key 键
     * @param otherKey 其他键
     * @param destKey 目标键
     * @return Long
     */
    public Long unionAndStoreForSet(String key, String otherKey, String destKey) {
        return redisTemplate.opsForSet().unionAndStore(key, otherKey, destKey);
    }

    /**
     * key集合与多个集合的并集存储到destKey中
     * @param key 键
     * @param otherKeys 其他键集合
     * @param destKey 目标键
     * @return Long
     */
    public Long unionAndStoreForSet(String key, Collection<String> otherKeys,
                               String destKey) {
        return redisTemplate.opsForSet().unionAndStore(key, otherKeys, destKey);
    }

    /**
     * 获取两个集合的差集
     * @param key 键
     * @param otherKey 其他元素
     * @return 差集元素集合
     */
    public Set<String> differenceForSet(String key, String otherKey) {
        return redisTemplate.opsForSet().difference(key, otherKey);
    }

    /**
     * 获取key集合与多个集合的差集
     * @param key 键
     * @param otherKeys 其他键集合
     * @return 差集元素集合
     */
    public Set<String> differenceForSet(String key, Collection<String> otherKeys) {
        return redisTemplate.opsForSet().difference(key, otherKeys);
    }

    /**
     *  key集合与otherKey集合的差集存储到destKey中
     * @param key 键
     * @param otherKey 其他键
     * @param destKey 目标键
     * @return Long
     */
    public Long differenceForSet(String key, String otherKey, String destKey) {
        return redisTemplate.opsForSet().differenceAndStore(key, otherKey,
                destKey);
    }

    /**
     * key集合与多个集合的差集存储到destKey中
     * @param key 键
     * @param otherKeys 其他键集合
     * @param destKey 目标键
     * @return Long
     */
    public Long differenceForSet(String key, Collection<String> otherKeys, String destKey) {
        return redisTemplate.opsForSet().differenceAndStore(key, otherKeys, destKey);
    }

    /**
     * 获取集合所有元素
     * @param key 键
     * @return 元素集合
     */
    public Set<String> membersForSet(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 随机获取集合中的一个元素
     * @param key 键
     * @return 随机元素
     */
    public String randomMemberForSet(String key) {
        return redisTemplate.opsForSet().randomMember(key);
    }

    /**
     * 随机获取集合中count个元素
     * @param key 键
     * @param count 个数
     * @return 随机元素集合
     */
    public List<String> randomMembersForSet(String key, long count) {
        return redisTemplate.opsForSet().randomMembers(key, count);
    }

    /**
     * 随机获取集合中count个元素并且去除重复的
     * @param key 键
     * @param count 个数
     * @return 随机元素集合
     */
    public Set<String> distinctRandomMembersForSet(String key, long count) {
        return redisTemplate.opsForSet().distinctRandomMembers(key, count);
    }

    /**
     * 迭代Set集合的值
     * @param key 键
     * @param options ScanOptions
     * @return Cursor
     */
    public Cursor<String> scanForSet(String key, ScanOptions options) {
        return redisTemplate.opsForSet().scan(key, options);
    }

    /**
     * 添加元素,有序集合是按照元素的score值由小到大排列
     * @param key 键
     * @param value 值
      * @param score score
     * @return Boolean
     */
    public Boolean addForZSet(String key, String value, double score) {
        return redisTemplate.opsForZSet().add(key, value, score);
    }

    /**
     * 添加元素
     * @param key 键
     * @param values 值
     * @return Long
     */
    public Long addForZSet(String key, Set<ZSetOperations.TypedTuple<String>> values) {
        return redisTemplate.opsForZSet().add(key, values);
    }

    /**
     * 移除元素
     * @param key 键
     * @param values 元素集合
     * @return Long
     */
    public Long removeForZSet(String key, Object... values) {
        return redisTemplate.opsForZSet().remove(key, values);
    }

    /**
     * 增加元素的score值，并返回增加后的值
     * @param key 键
     * @param value 值
     * @param delta delta
     * @return 增加后的值
     */
    public Double incrementScoreForZSet(String key, String value, double delta) {
        return redisTemplate.opsForZSet().incrementScore(key, value, delta);
    }

    /**
     * 返回元素在集合的排名,有序集合是按照元素的score值由小到大排列，0代表第一位
     * @param key 键
     * @param value 值
     * @return Long
     */
    public Long rankForZSet(String key, Object value) {
        return redisTemplate.opsForZSet().rank(key, value);
    }

    /**
     * 返回元素在集合的排名,按元素的score值由大到小排列
     * @param key 键
     * @param value 值
     * @return 元素在集合的排名
     */
    public Long reverseRankForZSet(String key, Object value) {
        return redisTemplate.opsForZSet().reverseRank(key, value);
    }

    /**
     * 获取集合的元素, 从小到大排序
     * @param key 键
     * @param start 开始位置
     * @param end 结束位置, -1查询所有
     * @return 元素集合
     */
    public Set<String> rangeForZSet(String key, long start, long end) {
        return redisTemplate.opsForZSet().range(key, start, end);
    }

    /**
     * 获取集合元素, 并且把score值也获取
     * @param key 键
     * @param start 开始位置
     * @param end 结束位置
     * @return 元素集合
     */
    public Set<ZSetOperations.TypedTuple<String>> rangeWithScoresForZSet(String key, long start, long end) {
        return redisTemplate.opsForZSet().rangeWithScores(key, start, end);
    }

    /**
     * 根据Score值查询集合元素
     * @param key 键
     * @param min 最小值
     * @param max 最大值
     * @return 元素集合
     */
    public Set<String> rangeByScoreForZSet(String key, double min, double max) {
        return redisTemplate.opsForZSet().rangeByScore(key, min, max);
    }

    /**
     * 根据Score值查询集合元素, 从小到大排序
     * @param key 键
     * @param min 最小值
     * @param max 最大值
     * @return 元素集合
     */
    public Set<ZSetOperations.TypedTuple<String>> angeByScoreWithScoresForZSet(String key,
                                                                          double min, double max) {
        return redisTemplate.opsForZSet().rangeByScoreWithScores(key, min, max);
    }

    /**
     * 根据Score值查询集合元素
     * @param key 键
     * @param min 最小值
     * @param max 最大值
     * @param start 开始位置
     * @param end 结束位置
     * @return 元素集合
     */
    public Set<ZSetOperations.TypedTuple<String>> rangeByScoreWithScoresForZSet(String key,
                                                                          double min, double max, long start, long end) {
        return redisTemplate.opsForZSet().rangeByScoreWithScores(key, min, max, start, end);
    }

    /**
     * 获取集合的元素, 从大到小排序
     * @param key 键
     * @param start 开始位置
     * @param end 结束位置
     * @return 元素集合
     */
    public Set<String> reverseRangeForZSet(String key, long start, long end) {
        return redisTemplate.opsForZSet().reverseRange(key, start, end);
    }

    /**
     * 获取集合的元素, 从大到小排序, 并返回score值
     * @param key 键
     * @param start 开始位置
     * @param end 结束位置
     * @return 元素集合
     */
    public Set<ZSetOperations.TypedTuple<String>> reverseRangeWithScoresForZSet(String key, long start, long end) {
        return redisTemplate.opsForZSet().reverseRangeWithScores(key, start, end);
    }

    /**
     * 根据Score值查询集合元素, 从大到小排序
     * @param key 键
     * @param min 最小值
     * @param max 最大值
     * @return 元素集合
     */
    public Set<String> reverseRangeByScoreForZSet(String key, double min, double max) {
        return redisTemplate.opsForZSet().reverseRangeByScore(key, min, max);
    }

    /**
     * 根据Score值查询集合元素, 从大到小排序
     * @param key 键
     * @param min 最小值
     * @param max 最大值
     * @return 元素集合
     */
    public Set<ZSetOperations.TypedTuple<String>> reverseRangeByScoreWithScoresForZSet(
            String key, double min, double max) {
        return redisTemplate.opsForZSet().reverseRangeByScoreWithScores(key, min, max);
    }

    /**
     * 根据Score值查询集合元素, 从大到小排序
     * @param key 键
     * @param min 最小值
     * @param max 最大值
     * @param start 开始位置
     * @param end 结束位置
     * @return 元素集合
     */
    public Set<String> reverseRangeByScoreForZSet(String key, double min,
                                            double max, long start, long end) {
        return redisTemplate.opsForZSet().reverseRangeByScore(key, min, max, start, end);
    }

    /**
     * 根据score值获取集合元素数量
     * @param key 键
     * @param min 最小值
     * @param max 最大值
     * @return 元素数量
     */
    public Long countForZSet(String key, double min, double max) {
        return redisTemplate.opsForZSet().count(key, min, max);
    }

    /**
     * 获取集合大小
     * @param key 键
     * @return 集合大小
     */
    public Long sizeForZSet(String key) {
        return redisTemplate.opsForZSet().size(key);
    }

    /**
     * 获取集合大小
     * @param key 键
     * @return 集合大小
     */
    public Long zCardForZSet(String key) {
        return redisTemplate.opsForZSet().zCard(key);
    }

    /**
     * 获取集合中value元素的score值
     * @param key 键
     * @param value 值
     * @return Double
     */
    public Double scoreForZSet(String key, Object value) {
        return redisTemplate.opsForZSet().score(key, value);
    }

    /**
     * 移除指定索引位置的成员
     * @param key 键
     * @param start 开始位置
     * @param end 结束位置
     * @return Long
     */
    public Long removeRangeForZSet(String key, long start, long end) {
        return redisTemplate.opsForZSet().removeRange(key, start, end);
    }

    /**
     * 根据指定的score值的范围来移除成员
     * @param key 键
     * @param min 最小值
     * @param max 最大值
     * @return Long
     */
    public Long removeRangeByScoreForZSet(String key, double min, double max) {
        return redisTemplate.opsForZSet().removeRangeByScore(key, min, max);
    }

    /**
     * 获取key和otherKey的并集并存储在destKey中
     * @param key 键
     * @param otherKey 其他键
     * @param destKey 目标键
     * @return Long
     */
    public Long unionAndStoreForZSet(String key, String otherKey, String destKey) {
        return redisTemplate.opsForZSet().unionAndStore(key, otherKey, destKey);
    }

    /**
     * 取key和多个otherKey的并集并存储在destKey中
     * @param key 键
     * @param otherKeys 其他键集合
     * @param destKey 目标键
     * @return Long
     */
    public Long unionAndStoreForZSet(String key, Collection<String> otherKeys, String destKey) {
        return redisTemplate.opsForZSet().unionAndStore(key, otherKeys, destKey);
    }

    /**
     * 取key和otherKey的交集并存储在destKey中
     * @param key 键
     * @param otherKey 其他键
     * @param destKey 目标键
     * @return Long
     */
    public Long intersectAndStoreForZSet(String key, String otherKey, String destKey) {
        return redisTemplate.opsForZSet().intersectAndStore(key, otherKey, destKey);
    }

    /**
     * 取key和多个otherKey的交集并存储在destKey中
     * @param key 键
     * @param otherKeys 其他键集合
     * @param destKey 目标键
     * @return Long
     */
    public Long intersectAndStoreForZSet(String key, Collection<String> otherKeys, String destKey) {
        return redisTemplate.opsForZSet().intersectAndStore(key, otherKeys, destKey);
    }

    /**
     * 迭代ZSet的值
     * @param key 键
     * @param options ScanOptions
     * @return Cursor
     */
    public Cursor<ZSetOperations.TypedTuple<String>> scanForZSet(String key, ScanOptions options) {
        return redisTemplate.opsForZSet().scan(key, options);
    }

    /**
     * JSON数据转成Object
     */
    public <T> T fromJson(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }

    /**
     * Object转成JSON数据
     */
    private static String toJson(Object object) {
        if (object instanceof Integer || object instanceof Long || object instanceof Float ||
                object instanceof Double || object instanceof Boolean || object instanceof String) {
            return String.valueOf(object);
        }
        return JSON.toJSONString(object);
    }
}
