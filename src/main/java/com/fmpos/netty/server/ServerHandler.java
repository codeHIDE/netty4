package com.fmpos.netty.server;

import com.fmpos.netty.common.CustomHeartbeatHandler;
import com.fmpos.netty.domain.DeviceValue;
import com.fmpos.netty.domain.TypeData;

import io.netty.channel.ChannelHandlerContext;


public class ServerHandler extends CustomHeartbeatHandler {

    public ServerHandler() {
        super("server");
    }

    @Override
    protected void handleData(ChannelHandlerContext channelHandlerContext, Object msg) {
        DeviceValue deviceValue = (DeviceValue) msg;
        System.out.println("server 接收数据:"+deviceValue.toString());

        DeviceValue s = new DeviceValue();
        s.setType(TypeData.CUSTOME);
        s.setSpeed(0);
        s.setAngle(15);
        s.setSeatId(TypeData.SERVER_RESPONSE);
        channelHandlerContext.writeAndFlush(s);
        System.out.println("server 发送数据:"+s.toString());
    }

    @Override
    protected void handleReaderIdle(ChannelHandlerContext ctx) {
        super.handleReaderIdle(ctx);
        System.err.println("---client " + ctx.channel().remoteAddress().toString() + " reader timeout, close it---");
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(name+" exception"+cause.toString());
    }
}