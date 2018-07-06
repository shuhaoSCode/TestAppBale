package com.l.getchannel;

import android.content.Context;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class GetComment {

    /**
     * 读取comment区域数据
     *
     * @param context
     * @return
     */
    private static String readApk(Context context) {
        // 获取文件路径
        File file = new File(context.getPackageCodePath());
        byte[] bytes = null;
        RandomAccessFile accessFile = null;
        try {
            accessFile = new RandomAccessFile(file, "r");
            long index = accessFile.length();

            bytes = new byte[2];
            // 获取comment文件的位置
            index = index - bytes.length;
            accessFile.seek(index);
            // 获取comment中写入数据的大小byte类型
            accessFile.readFully(bytes);
            // 将byte转换成大小
            int contentLength = stream2Short(bytes, 0);
            // 创建byte[]数据大小来存储写入的数据
            bytes = new byte[contentLength];
            index = index - bytes.length;
            accessFile.seek(index);
            // 读取数据
            accessFile.readFully(bytes);
            return new String(bytes, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (accessFile != null) {
                try {
                    accessFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 字节数组转换成short
     *
     * @param stream
     * @param offset
     * @return
     */
    private static short stream2Short(byte[] stream, int offset) {
        ByteBuffer buffer = ByteBuffer.allocate(2);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.put(stream[offset]);
        buffer.put(stream[offset + 1]);
        return buffer.getShort(0);
    }
}
