package com.jackhance.netty.message.lengthfield.codec;

import com.jackhance.netty.message.lengthfield.LengthFieldConstant;
import io.netty.handler.codec.LengthFieldPrepender;

/**
 * length field message frame encoder
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
public class LengthFieldFrameEncoder extends LengthFieldPrepender {

    public LengthFieldFrameEncoder() {
        super(LengthFieldConstant.LENGTH_FIELD_LENGTH);
    }
}
