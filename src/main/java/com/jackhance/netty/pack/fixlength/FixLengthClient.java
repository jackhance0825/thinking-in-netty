package com.jackhance.netty.pack.fixlength;

import com.jackhance.netty.pack.fixlength.codec.*;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.internal.SystemPropertyUtil;

/**
 * fix length message client
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
public class FixLengthClient {
    private static final String HOST = SystemPropertyUtil.get("host", "127.0.0.1");
    private static final int PORT = SystemPropertyUtil.getInt("port", 8007);

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            FixLengthHandler handler = new FixLengthHandler();

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

                            p.addLast("frameDecoder", new FixLengthFrameDecoder());
                            p.addLast("frameEncoder", new FixLengthFrameEncoder());

                            p.addLast("protocolDecoder", new FixLengthProtocolDecoder());
                            p.addLast("protocolEncoder", new FixLengthProtocolEncoder());

                            p.addLast("handler", handler);
                        }
                    });

            // Start the client.
            ChannelFuture f = b.connect(HOST, PORT).sync();

            StringBuilder s = new StringBuilder(FixLengthConstant.LENGTH);
            for (int i = 0; i < FixLengthConstant.LENGTH; i++) {
                s.append((char)(97 + i));
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
