package com.jackhance.netty.pack.lengthfield;

import com.jackhance.netty.pack.lengthfield.codec.LengthFieldFrameDecoder;
import com.jackhance.netty.pack.lengthfield.codec.LengthFieldFrameEncoder;
import com.jackhance.netty.pack.lengthfield.codec.LengthFieldProtocolDecoder;
import com.jackhance.netty.pack.lengthfield.codec.LengthFieldProtocolEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.internal.SystemPropertyUtil;

/**
 * length field message client
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
public class LengthFieldClient {
    private static final String HOST = SystemPropertyUtil.get("host", "127.0.0.1");
    private static final int PORT = SystemPropertyUtil.getInt("port", 8007);

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            LengthFieldHandler handler = new LengthFieldHandler();

            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10 * 1000)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(new LoggingHandler(LogLevel.INFO));

                            p.addLast("frameDecoder", new LengthFieldFrameDecoder());
                            p.addLast("frameEncoder", new LengthFieldFrameEncoder());

                            p.addLast("protocolDecoder", new LengthFieldProtocolDecoder());
                            p.addLast("protocolEncoder", new LengthFieldProtocolEncoder());

                            p.addLast("handler", handler);
                        }
                    });

            // Start the client.
            ChannelFuture f = b.connect(HOST, PORT).sync();

            f.channel().writeAndFlush("Hello World!!!!");

            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        } finally {
            // Shut down the event loop to terminate all threads.
            group.shutdownGracefully();
        }
    }
}
