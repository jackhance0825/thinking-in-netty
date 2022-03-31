package com.jackhance.netty.message.lengthfield.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * length field message protocol decoder
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
public class LengthFieldProtocolDecoder extends MessageToMessageDecoder<ByteBuf> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        byte[] data = new byte[msg.readableBytes()];
        msg.readBytes(data);
        out.add(new String(data, StandardCharsets.UTF_8));
    }
}
