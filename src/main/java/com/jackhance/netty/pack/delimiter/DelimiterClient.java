package com.jackhance.netty.pack.delimiter;

import com.jackhance.netty.pack.delimiter.codec.DelimiterFrameDecoder;
import com.jackhance.netty.pack.delimiter.codec.DelimiterFrameEncoder;
import com.jackhance.netty.pack.delimiter.codec.DelimiterProtocolDecoder;
import com.jackhance.netty.pack.delimiter.codec.DelimiterProtocolEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.internal.SystemPropertyUtil;

/**
 * delimiter message client
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
public class DelimiterClient {
    private static final String HOST = SystemPropertyUtil.get("host", "127.0.0.1");
    private static final int PORT = SystemPropertyUtil.getInt("port", 8007);

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            DelimiterHandler handler = new DelimiterHandler();

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

                            p.addLast("frameDecoder", new DelimiterFrameDecoder());
                            p.addLast("frameEncoder", new DelimiterFrameEncoder());

                            p.addLast("protocolDecoder", new DelimiterProtocolDecoder());
                            p.addLast("protocolEncoder", new DelimiterProtocolEncoder());

                            p.addLast("handler", handler);
                        }
                    });

            // Start the client.
            ChannelFuture f = b.connect(HOST, PORT).sync();

            StringBuilder s = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                s.append((char) (97 + i));
            }

            f.channel().writeAndFlush(s.toString());

            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        } finally {
            // Shut down the event loop to terminate all threads.
            group.shutdownGracefully();
        }
    }
}
