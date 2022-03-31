package com.jackhance.netty.message.lengthfield;

import com.jackhance.netty.message.lengthfield.codec.LengthFieldFrameDecoder;
import com.jackhance.netty.message.lengthfield.codec.LengthFieldFrameEncoder;
import com.jackhance.netty.message.lengthfield.codec.LengthFieldProtocolDecoder;
import com.jackhance.netty.message.lengthfield.codec.LengthFieldProtocolEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.DefaultThreadFactory;
import io.netty.util.internal.SystemPropertyUtil;

/**
 * length field message server
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
public class LengthFieldServer {

    private static final int PORT = SystemPropertyUtil.getInt("port", 8007);

    public static void main(String[] args) {

        NioEventLoopGroup boss = new NioEventLoopGroup(1, new DefaultThreadFactory("boss"));
        NioEventLoopGroup worker = new NioEventLoopGroup(0, new DefaultThreadFactory("worker"));
        try {
            LengthFieldHandler handler = new LengthFieldHandler();

            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel ch) {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast("loggingHandler", new LoggingHandler(LogLevel.INFO));

                            p.addLast("frameDecoder", new LengthFieldFrameDecoder());
                            p.addLast("frameEncoder", new LengthFieldFrameEncoder());

                            p.addLast("protocolDecoder", new LengthFieldProtocolDecoder());
                            p.addLast("protocolEncoder", new LengthFieldProtocolEncoder());

                            p.addLast("handler", handler);
                        }
                    });

            ChannelFuture channelFuture = serverBootstrap.bind(PORT).sync();

            channelFuture.channel().closeFuture().sync();
        } catch (Throwable t) {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
