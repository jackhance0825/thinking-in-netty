package com.jackhance.netty.pack.fixlength.codec;

import com.jackhance.netty.pack.fixlength.FixLengthConstant;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
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

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        // 此处还需处理padding的问题，这里先不展开
        return super.decode(ctx, in);
    }
}
