package com.jackhance.netty.pack.delimiter;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

/**
 * delimiter message handler
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
@ChannelHandler.Sharable
public class DelimiterHandler extends SimpleChannelInboundHandler<String> {
    private InternalLogger log = InternalLoggerFactory.getInstance(DelimiterHandler.class.getName());

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        log.error("receive message: {}", msg);

        ctx.writeAndFlush(msg);
    }
}
