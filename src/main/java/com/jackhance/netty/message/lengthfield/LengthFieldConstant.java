package com.jackhance.netty.message.lengthfield;

/**
 * 常量
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
public abstract class LengthFieldConstant {

    /**
     * 长度标识长度
     */
    public static final int LENGTH_FIELD_LENGTH = 2;

    /**
     * 最大帧长度
     */
    public static final int MAX_FRAME_SIZE = 4 * 1024;
}
