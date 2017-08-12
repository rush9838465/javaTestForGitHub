package nettyTest.timerServerTest.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;

/**
 * Created by 98384 on 2017/7/15.
 */
public class TimeServerHandler extends SimpleChannelInboundHandler<Object> {

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception{
        ByteBuf buf = (ByteBuf) msg;
        byte[] req;
        if(buf.hasArray())
        {
            req = buf.array();
        }
        else{
            req = new byte[buf.readableBytes()];
        }
        req[4] = 0;
        buf.readBytes(req);
        String body = new String(req, "UTF-8");
        System.out.println("The time server receive order : " + body);

        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new java.util.Date(System.currentTimeMillis()).toString() : "BAD ORDER";
        System.out.println(currentTime);
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.write(resp);
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
