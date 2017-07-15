package nettyTest.timerServerTest.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;



/**
 * Created by 98384 on 2017/7/15.
 */
public class TimeClient {

    public void connect(int port, String host) throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();

        try{
            Bootstrap sb = new Bootstrap();
            sb.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>(){
                        public void initChannel(SocketChannel ch) throws Exception{
                            ch.pipeline().addLast(new TimeClientHandler());
                        }
                    });

            ChannelFuture f = sb.connect(host, port).sync();

            f.channel().closeFuture().sync();

        }finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws  Exception{
        int port = 8080;
        if(args != null && args.length > 0){
            try{
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e){
                System.out.println(e);
            }
        }

        new TimeClient().connect(port, "127.0.0.1");
    }
}
