package nettyTest.smartHomeProtoclTest.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Arrays;

/**
 * Created by 98384 on 2017/7/16.
 */
public class ServerHandler extends SimpleChannelInboundHandler<Object>{

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception{
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        System.out.println(Arrays.toString(req));

//        ctx.write(buf);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception{
        ctx.flush();
        System.out.println("channelReadComplete" );
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        System.out.println("exceptionCaught" );
        ctx.close();
    }

    @Override

    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("channelActive" );
        ctx.writeAndFlush("HELLO: Type the path of the file to retrieve.\n");

    }

}
