package com.bwie.myjgpush.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;

/**
 * 广播接受者
 */

public class MyRecevice extends BroadcastReceiver {
    // 声明接口变量,静态注册是因为广播是静态注册，其他类要调用时便不能进行实例化
   //static Connector mConnector;
    //加入有多个activity需要接收就要改变方法
    public static List<Connector> list = new ArrayList<>();
    public static void add(Connector connector){
        //MyRecevice.mConnector = connector;
        list.add(connector);
    }
    public static void remove(Connector connector){
        //MyRecevice.mConnector = connector;
        list.remove(connector);
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            System.out.println("收到了自定义消息。消息内容是：" + bundle.getString(JPushInterface.EXTRA_MESSAGE));
            // 自定义消息不会展示在通知栏，完全要开发者写代码去处理
            //mConnector.onRefresh(bundle.getString(JPushInterface.EXTRA_MESSAGE));
            for (Connector c:list){
                if (c != null) {
                    c.onRefresh(bundle.getString(JPushInterface.EXTRA_MESSAGE));
                }
            }
        }
        String action = intent.getAction();
        Log.d("TAG", "onReceive - " + action);
       /* Bundle bundle = intent.getExtras();
        Log.d("TAG", "onReceive - " + intent.getAction());
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            System.out.println("收到了自定义消息。消息内容是：" + bundle.getString(JPushInterface.EXTRA_MESSAGE));
            // 自定义消息不会展示在通知栏，完全要开发者写代码去处理
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            System.out.println("收到了通知");
            // 在这里可以做些统计，或者做些其他工作
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            System.out.println("用户点击打开了通知");
            // 在这里可以自己写代码去定义用户点击后的行为
            Intent i = new Intent(context, MainActivity.class);  //自定义打开的界面
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        } else {
            Log.d("TAG", "Unhandled intent - " + intent.getAction());
        }*/
    }
    //创建一个接口类
    public interface Connector{
        //定义一个抽象的方法
        void onRefresh(String str);
    }
}
