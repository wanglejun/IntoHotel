package com.intohotel.utils;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.intohotel.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

/**
 * Created by wanglejun on 15/7/11.
 */
public class BluetoothSocketUtil {
    //    private final int UUID =  0xFAF1;
    private Context context;
    private BluetoothSocket socket = null;
    private BluetoothDevice device = null;
    private BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    //连接线程
    private ClientThread clientThread;
    //接受线程
    private ReadThread readThread;
    private Timer timer;
    //上行请求标识
    private int id;
    private Handler handler;
    public BluetoothSocketUtil(Context context,Handler handler) {
        this.context = context;
        this.handler = handler;
    }

    private Handler showMessageHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            ToastUtil.show(context, String.valueOf(msg.obj));
        }

    };

    /**
     * 连接
     * @param macAddress
     */
    public void connection(String macAddress){
        if (!"".equals(macAddress)) {
            device = bluetoothAdapter.getRemoteDevice(macAddress);
            clientThread = new ClientThread();
            clientThread.start();
            timer = new Timer();
        } else {
            ToastUtil.show(context, "macAddress is null");
        }
    }

    //开启客户端连接
    private class ClientThread extends Thread {
        public void run() {
            try {
                //创建一个Socket连接：只需要服务器在注册时的UUID号
                socket = device.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
                //连接
                sendHandlerMessage(showMessageHandler,"请稍候，正在连接仪表",0,ResponseStatus.OK);

                socket.connect();

                sendHandlerMessage(showMessageHandler, "连接仪表成功", 0,ResponseStatus.OK);
                //启动接受数据
                readThread = new ReadThread();
                readThread.start();

                //开启心跳任务 定时向服务端发送心跳消息
//                timer.schedule(new HearbeatTask(),30000);
                sendHandlerMessage(handler, "", R.id.getConnection, ResponseStatus.OK);

            } catch (IOException e) {
                Log.e("connect", "", e);
                sendHandlerMessage(showMessageHandler, "连接仪表失败！断开连接重新试一试", 0,ResponseStatus.OK);
            }
        }
    }

    ;

    //读取数据
    private class ReadThread extends Thread {
        public void run() {

            byte[] buffer = new byte[1024];
            int bytes;
            InputStream mmInStream = null;

            try {
                mmInStream = socket.getInputStream();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            while (true) {
                try {
                    // Read from the InputStream
                    if ((bytes = mmInStream.read(buffer)) > 0) {
                        byte[] buf_data = new byte[bytes];
                        for (int i = 0; i < bytes; i++) {
                            buf_data[i] = buffer[i];
                        }
                        String s = new String(buf_data);
                        sendHandlerMessage(handler,s,id,ResponseStatus.OK);
                    }

                } catch (IOException e) {
                    try {
                        mmInStream.close();
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    sendHandlerMessage(handler,"读取数据失败",id,ResponseStatus.ERR);
                    break;
                }
            }
        }
    }

    //发送数据
    private void sendMessage(int id,String msg) {
        if (socket == null) {
           ToastUtil.show(context,"没有连接");
            return;
        }
        this.id = id;
        try {
            OutputStream os = socket.getOutputStream();
            os.write(msg.getBytes());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            sendHandlerMessage(handler, "请求失败", id, ResponseStatus.ERR);
        }
    }

    //心跳任务
    private class HearbeatTask extends TimerTask{
        @Override
        public void run() {

        }
    }

    private class TimingTask extends TimerTask{
        @Override
        public void run() {

        }
    }
    /**
     * 断开连接
     */
    private void disconnect() {
        new Thread() {
            public void run() {
                if (clientThread != null) {
                    clientThread.interrupt();
                    clientThread = null;
                }
                if (readThread != null) {
                    readThread.interrupt();
                    readThread = null;
                }
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    socket = null;
                }
            }

            ;
        }.start();

    }

    /**
     * 发送handler消息
     * @param obj
     * @param what
     */
    private void sendHandlerMessage(Handler handler,Object obj,int what,int status){
        Message msg = new Message();
        msg.obj = obj;
        msg.what = what;
        msg.arg1 = status;
        handler.sendMessage(msg);
    }
}
