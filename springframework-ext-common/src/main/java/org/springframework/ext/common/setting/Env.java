package org.springframework.ext.common.setting;

/**
 * 环境枚举
 * User: only
 * Date: 2014-1-20
 * Time: 下午5:07
 */
public enum Env {
    /** 本地开发环境 */
    DEV("dev"),
    /** 日常测试环境 */
    DAILY("daily"),
    /** 标准测试环境 */
    TEST("test"),
    /** alpha测试环境 */
    ALPHA("alpha"),
    /** beta测试环境 */
    BETA("beta"),
    /** gamma测试环境 */
    GAMMA("gamma"),
    /** 预发环境 */
    PRE("pre"),
    /** sandbox沙箱环境 */
    SANDBOX("sandbox"),
    /** 生产（线上）环境 */
    PRODUCT("prod");
    /** 环境系统参数名 */
    public static final String ENV_NAME = "context.env";
    /** 环境名称 */
    private String env;

    /**
     * 构造函数
     *
     * @param env 环境名称
     */
    private Env(String env) {
        this.env = env;
    }

    /**
     * 工厂方法
     *
     * @param env 环境名称
     * @return 环境枚举
     */
    public static Env instance(String env) {
        Env[] values = Env.values();
        for (Env each : values) {
            if (each.getEnv().equals(env)) {
                return each;
            }
        }
        return DEV;
    }

    /**
     * 是否现网环境：idc，beta，pre
     *
     * @return 是否现网环境
     */
    public boolean isOnline() {
        return isProd() || isPre() || isSandbox();
    }

    /**
     * 是否线下环境：daily，dev
     *
     * @return 是否线下环境
     */
    public boolean isOffline() {
        return isDaily() || isTest() || isDev() || isAlpha() || isBeta() || isGamma();
    }

    /**
     * 判断是否dev环境
     *
     * @return 是否dev环境
     */
    public boolean isDev() {
        return this == DEV;
    }

    /**
     * 判断是否daily环境
     *
     * @return 是否daily环境
     */
    public boolean isDaily() {
        return this == DAILY;
    }

    /**
     * 判断是否测试环境
     *
     * @return 是否测试环境
     */
    public boolean isTest() {
        return this == TEST;
    }

    /**
     * 判断是否pre环境
     *
     * @return 是否pre环境
     */
    public boolean isPre() {
        return this == PRE;
    }

    /**
     * 判断是否alpha环境
     *
     * @return 是否alpha环境
     */
    public boolean isAlpha() {
        return this == ALPHA;
    }

    /**
     * 判断是否beta环境
     *
     * @return 是否beta环境
     */
    public boolean isBeta() {
        return this == BETA;
    }

    /**
     * 判断是否gamma环境
     *
     * @return 是否gamma环境
     */
    public boolean isGamma() {
        return this == GAMMA;
    }

    /**
     * 判断是否sandbox环境
     *
     * @return 是否sandbox环境
     */
    public boolean isSandbox() {
        return this == SANDBOX;
    }

    /**
     * 判断是否生产环境
     *
     * @return 是否生产环境
     */
    public boolean isProd() {
        return this == PRODUCT;
    }

    /**
     * 取环境名称
     *
     * @return 环境名称
     */
    public String getEnv() {
        return env;
    }

}
