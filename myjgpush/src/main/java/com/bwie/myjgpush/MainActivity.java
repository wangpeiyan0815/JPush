package com.bwie.myjgpush;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.bwie.myjgpush.receiver.MyRecevice;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;

public class MainActivity extends AppCompatActivity implements MyRecevice.Connector{

    private ListView lv;
    List<String> list = new ArrayList<>();
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv);
        btn = (Button) findViewById(R.id.btn);
        MyRecevice.add(this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MainActivity.this,TwoActivity.class);
                startActivity(in);
            }
        });
        /*//设置别名
        JPushInterface.setAlias(this, "text", new TagAliasCallback() {
            @Override
            public void gotResult(int i, String s, Set<String> set) {
                //返回值为零代表别名设置成功
                Log.i("TAG", "gotResult: "+i);
            }
        });*/
        //设置标签，类似于两名用户共同订阅了游戏类，A有订阅了娱乐类，b有订阅了体育类，
        //这时进行推送，属于A的娱乐接受，单独属于B的进行接收，在次发送共同的都能进行接收。
        /*Set<String> set = new HashSet<>();
        set.add("yule");
        set.add("youxi");
        JPushInterface.setTags(this, set, new TagAliasCallback() {
            @Override
            public void gotResult(int i, String s, Set<String> set) {
                Log.i("TAG", "setTags: "+i);
            }
        });*/
        Switch swit = (Switch) findViewById(R.id.swit);
        swit.setChecked(true);
        swit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    // 点击恢复按钮后，极光推送服务会恢复正常工作
                    JPushInterface.resumePush(getApplicationContext());
                } else {
                    // 点击停止按钮后，极光推送服务会被停止掉
                    JPushInterface.stopPush(getApplicationContext());
                }
            }
        });
    }

    @Override
    public void onRefresh(String str) {
        Toast.makeText(this, "收到一个推送消息！"+str, Toast.LENGTH_SHORT).show();
        //进行listView
        list.add(str);
        lv.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyRecevice.remove(this);
    }
}
