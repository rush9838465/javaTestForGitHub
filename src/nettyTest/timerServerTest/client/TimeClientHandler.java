package nettyTest.timerServerTest.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by 98384 on 2017/7/15.
 */
public class TimeClientHandler extends SimpleChannelInboundHandler<Object> {

    private static final Logger logger = Logger
            .getLogger(TimeClientHandler.class.getName());

    private ByteBuf firstMessage;

    public TimeClientHandler(){
        byte[] req = "QUERY TIME ORDER".getBytes();

        firstMessage = Unpooled.buffer(req.length);

        firstMessage.writeBytes(req);
        System.out.println("Clinet Init");
    }

    public void channelActive(ChannelHandlerContext ctx){
        ctx.writeAndFlush(firstMessage);
        System.out.println("Clinet channelActive");
    }

    public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception{
        ByteBuf buf = (ByteBuf) msg;

        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, "UTF-8");
        System.out.println("Now is : " + body);

        try{
            Thread.sleep(1000);
        }catch (Exception e){
            System.out.println(e);
        }
        req = "QUERY TIME ORDER".getBytes();

        firstMessage = Unpooled.buffer(req.length);

        firstMessage.writeBytes(req);
        ctx.writeAndFlush(firstMessage);
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        logger.warning("Unexpected exception form downstream : " + cause.getMessage());
        ctx.close();
    }

}
