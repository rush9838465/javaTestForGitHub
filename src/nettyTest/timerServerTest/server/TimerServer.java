package nettyTest.timerServerTest.server;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;


/**
 * Created by 98384 on 2017/7/14.
 */
public class TimerServer {
    public  void bind(int port) throws Exception{
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        System.out.println("Timer server running");
        try{
            ServerBootstrap sb = new ServerBootstrap();
            sb.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChildChannelHandler())
                    .option(ChannelOption.SO_BACKLOG, 1024);
            ChannelFuture f = sb.bind(port).sync();
            f.channel().closeFuture().sync();
            System.out.println("Timer server STOP");
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    private class ChildChannelHandler extends ChannelInitializer<SocketChannel>{
        protected void initChannel(SocketChannel arg0) throws Exception{
            System.out.println("Timer server initChannel");
            arg0.pipeline().addLast(new TimeServerHandler());

        }
    }

    public static void main(String[] args) throws Exception{
        int port = 8080;

        if(args != null && args.length > 0){
            try{
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e){
                System.out.println(e);
            }
        }

        new TimerServer().bind(port);
    }
}
