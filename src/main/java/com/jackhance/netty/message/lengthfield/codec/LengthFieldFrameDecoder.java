package com.jackhance.netty.message.lengthfield.codec;

import com.jackhance.netty.message.lengthfield.LengthFieldConstant;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * length field message frame decoder
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
public class LengthFieldFrameDecoder extends LengthFieldBasedFrameDecoder {
    public LengthFieldFrameDecoder() {
        super(LengthFieldConstant.MAX_FRAME_SIZE, 0, LengthFieldConstant.LENGTH_FIELD_LENGTH,
                0, LengthFieldConstant.LENGTH_FIELD_LENGTH);
    }
}
