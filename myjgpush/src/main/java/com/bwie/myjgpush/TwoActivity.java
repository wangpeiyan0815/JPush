package com.bwie.myjgpush;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.bwie.myjgpush.receiver.MyRecevice;

/**
 * Created by dell on 2017/1/11.
 */

public class TwoActivity extends Activity implements MyRecevice.Connector{

    private TextView two_textTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.twoactivity);
        two_textTv = (TextView) findViewById(R.id.two_textTv);
        MyRecevice.add(this);
    }

    @Override
    public void onRefresh(String str) {
        Log.i("TAG", "onRefresh:2号 "+str);
        two_textTv.setText(str);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyRecevice.remove(this);
    }
}
