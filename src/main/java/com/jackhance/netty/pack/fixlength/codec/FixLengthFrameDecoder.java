package com.jackhance.netty.pack.fixlength.codec;

import com.jackhance.netty.pack.fixlength.FixLengthConstant;
import io.netty.handler.codec.FixedLengthFrameDecoder;

/**
 * fix length message frame decoder
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
public class FixLengthFrameDecoder extends FixedLengthFrameDecoder {

    public FixLengthFrameDecoder() {
        super(FixLengthConstant.LENGTH);
    }
}
