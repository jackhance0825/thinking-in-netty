package com.jackhance.netty.pack.delimiter.codec;

import com.jackhance.netty.pack.delimiter.DelimiterConstant;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;

/**
 * delimiter message frame decoder
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
public class DelimiterFrameDecoder extends DelimiterBasedFrameDecoder {


    public DelimiterFrameDecoder() {
        super(DelimiterConstant.MAX_LENGTH, true, DelimiterConstant.DELIMITER);
    }
}
