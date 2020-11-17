package com.pig4cloud.pigx.device.generator.xinchuan;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * Socket服务端
 *
 * @author lz
 */

@Slf4j
public class TcpServer {

    private static byte[] req = {(byte) 0x40, (byte) 0x40, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x03, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x83, (byte) 0x23, (byte) 0x23};

    private static class ClientHandler implements Runnable {

        private Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }
        @Override
        public void run() {
            try {
                //获取Socket的输出流，用来向客户端发送数据
                OutputStream ops = socket.getOutputStream();

                //封装输入流（接收客户端的流）
                BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
                DataInputStream dis = new DataInputStream(bis);

                byte[] bytes = new byte[1]; // 一次读取一个byte
                String ret = "";
                while (dis.read(bytes) != -1) {
                    ret += bytesToHexString(bytes) + " ";
                    if (dis.available() == 0) { //一个请求
                        log.info(socket.getRemoteSocketAddress() + ":" + ret);
                        //信传正确数据
                        if (ret.trim().length() == 104) {
                            ops.write(req);
                                //todo
                                BeanUtils.beanUtils.busData.procesData(ret);
                            //数据出错
                        } else {
                            log.error("接收数据出错(长度有误)!");
                        }
                        ret = "";
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                log.info(socket.getRemoteSocketAddress() + " 与服务端断开连接!");
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    private static int corePoolSize = Runtime.getRuntime().availableProcessors();

    private static ThreadFactory nameFactory = new ThreadFactoryBuilder()
            .setNameFormat("pool-%d").build();

    private static ExecutorService pool = new ThreadPoolExecutor(corePoolSize, 200,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1024), nameFactory, new ThreadPoolExecutor.AbortPolicy());


    public void runServer() {
        ServerSocket server = null;
        try {
            server = new ServerSocket(5000);
            while (true) {
                log.info("*********************** 等待客户端连接 ***********************");
                Socket socket = server.accept();
                log.info("连接客户端地址：" + socket.getRemoteSocketAddress());
                ClientHandler handler = new ClientHandler(socket);
                pool.execute(handler);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * byte[]数组转换为16进制的字符串
     *
     * @param bytes 要转换的字节数组
     * @return 转换后的结果
     */
    public static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }


//    public static void main(String[] args) throws Exception {
//        new TcpServer().run();
//    }
}
