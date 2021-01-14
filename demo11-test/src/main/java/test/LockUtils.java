package test;


/**
 * 分布式锁工具
 *
 * <p><code>
 *     //应用启动时，全局实始化一次
 *     RedisX redisX = new redisX(...);
 *     LockUtils.init(redisX);
 *
 *     //应用
 *     if(LockUtils.tryLock("cake-factor-api","create_order_user_12",3)){
 *         //业处处理代码
 *     }else{
 *         //提示不要重复提交
 *     }
 * </code></p>
 *
 * @author noear
 * @since 2.0
 * */
public class LockUtils {
    private static RedisX _redis_uni;
    public static void init(RedisX redisX){
        _redis_uni = redisX;
    }

    static {
        _redis_uni = new RedisX("redis.water.io:6379", "123456", 2);
    }

    /**
     * 尝试把 group_key 锁定给 inMaster
     *
     * @param group     分组
     * @param key       关键字
     * @param inSeconds 锁定时间
     * @param inMaster  锁持有人
     */
    public static boolean tryLock(String group, String key, int inSeconds, String inMaster) {
        String key2 = group + ".lk." + key;

        return _redis_uni.open1((ru) -> ru.key(key2).expire(inSeconds).lock(inMaster));
    }

    /**
     * 尝试把 group_key 锁定
     *
     * @param inSeconds 锁定时间
     */
    public static boolean tryLock(String group, String key, int inSeconds) {
        String key2 = group + ".lk." + key;

        return _redis_uni.open1((ru) -> ru.key(key2).expire(inSeconds).lock("_"));
    }

    /**
     * 尝试把 group_key 锁定 （3秒）
     */
    public static boolean tryLock(String group, String key) {
        return tryLock(group, key, 3);
    }

    /**
     * 检查 group_key 是否已被锁定
     */
    public static boolean isLocked(String group, String key) {
        String key2 = group + ".lk." + key;

        return _redis_uni.open1((ru) -> ru.key(key2).exists());
    }


    /**
     * 解锁 group_key
     */
    public static void unLock(String group, String key, String inMaster) {
        String key2 = group + ".lk." + key;

        _redis_uni.open0((ru) -> {
            if (inMaster == null || inMaster.equals(ru.key(key2).val())) {
                ru.key(key2).delete();
            }
        });
    }

    /**
     * 解锁 group_key
     */
    public static void unLock(String group, String key) {
        unLock(group, key, null);
    }
}
