package com.fmpos.netty.code;



import java.util.List;

import org.msgpack.MessagePack;

import com.fmpos.netty.domain.DeviceValue;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

public class MsgPackDecode extends MessageToMessageDecoder<ByteBuf> {


    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        final byte[] array;
        final int length=msg.readableBytes();
        array=new byte[length];
        msg.getBytes(msg.readerIndex(), array,0,length);
        MessagePack msgpack=new MessagePack();
        out.add(msgpack.read(array, DeviceValue.class));
    }

}