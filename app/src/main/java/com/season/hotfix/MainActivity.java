package com.season.hotfix;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.season.hotfix.core.HotFixDirHelper;
import com.season.hotfix.core.HotFixHelper;

public class MainActivity extends AppCompatActivity {

    TextView mResultView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mResultView = (TextView) findViewById(R.id.tv_result);

        findViewById(R.id.btn_method_invoke).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                methodInvoke();
            }
        });

        findViewById(R.id.btn_hotfix).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hotFix();
            }
        });

    }

    void methodInvoke(){
        TestClass testClass = new TestClass();
        mResultView.setText(testClass.getTestMessage());
    }

    void hotFix(){
        String assetsName = "TestClassFixV2.dex";
        HotFixDirHelper.getInstance(MainActivity.this).copyAssets(assetsName, false);
        boolean result = HotFixHelper.hotFix(MainActivity.this, HotFixDirHelper.getInstance(MainActivity.this).getHotFixDexFile(assetsName));
        Toast.makeText(MainActivity.this, result ? "修复成功" : "修复失败", Toast.LENGTH_SHORT).show();
    }

}
