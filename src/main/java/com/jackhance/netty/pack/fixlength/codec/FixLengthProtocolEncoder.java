package com.jackhance.netty.pack.fixlength.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * fix length message protocol encoder
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
public class FixLengthProtocolEncoder extends MessageToMessageEncoder<String> {

    @Override
    protected void encode(ChannelHandlerContext ctx, String msg, List<Object> out) throws Exception {
        byte[] bs = msg.getBytes(StandardCharsets.UTF_8);
        ByteBuf byteBuf = ctx.alloc().buffer(bs.length).writeBytes(bs);
        out.add(byteBuf);
    }
}
