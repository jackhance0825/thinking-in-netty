package com.jackhance.netty.message.fixlength;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

/**
 * fix length message handler
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
@ChannelHandler.Sharable
public class FixLengthHandler extends SimpleChannelInboundHandler<String> {
    private InternalLogger log = InternalLoggerFactory.getInstance(FixLengthHandler.class.getName());

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        log.error("receive message: {}", msg);

        ctx.writeAndFlush(msg);
    }
}
