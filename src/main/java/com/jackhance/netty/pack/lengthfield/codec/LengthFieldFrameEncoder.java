package com.jackhance.netty.pack.lengthfield.codec;

import com.jackhance.netty.pack.lengthfield.LengthFieldConstant;
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
