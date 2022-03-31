package com.jackhance.netty.pack.delimiter;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * 常量
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
public abstract class DelimiterConstant {

    public static final ByteBuf DELIMITER = ByteBufAllocator.DEFAULT.buffer(1).writeBytes(new byte[]{(byte)';'});

    public static final int MAX_LENGTH = 1024;
}
