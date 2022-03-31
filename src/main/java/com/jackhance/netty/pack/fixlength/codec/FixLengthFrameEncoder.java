package com.jackhance.netty.pack.fixlength.codec;

import com.jackhance.netty.pack.fixlength.FixLengthConstant;
import com.jackhance.netty.pack.fixlength.FixLengthHandler;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

import java.util.List;

/**
 * fix length message frame encoder
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
public class FixLengthFrameEncoder extends MessageToMessageEncoder<ByteBuf> {
    private static InternalLogger log = InternalLoggerFactory.getInstance(FixLengthHandler.class.getName());

    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        int length = msg.readableBytes();
        if (length > FixLengthConstant.LENGTH) {
            log.error("出现超出约定长度的数据包，len：{}， max：{}", length, FixLengthConstant.LENGTH);
            return;
        }

        ByteBuf dest = ctx.alloc().buffer(FixLengthConstant.LENGTH);
        dest.writeBytes(msg);
        out.add(dest);
    }
}
