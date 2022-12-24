package net.hearnsoft.qrcodescanner;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    RadioGroup mGroup;
    RadioButton mRadioBtnAlipay;
    RadioButton mRadioBtnWeChat;
    RadioButton mRadioBtnQQ;
    RadioButton mRadioBtnGoogle;
    RadioButton mRadioBtnDialog;
    RadioButton mRadioBtnNone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mGroup = findViewById(R.id.scanner_group);
        mRadioBtnAlipay = findViewById(R.id.alipay);
        mRadioBtnWeChat = findViewById(R.id.wechat);
        mRadioBtnQQ = findViewById(R.id.qq);
        mRadioBtnGoogle = findViewById(R.id.lens);
        mRadioBtnDialog = findViewById(R.id.always);
        mRadioBtnNone = findViewById(R.id.none_option);
        initView();
        mGroup.setOnCheckedChangeListener(new OptionChange());
    }

    private void initView(){
        SharedPreferences sp = getSharedPreferences("options", Context.MODE_PRIVATE);
        int option = sp.getInt("open_options",0);
        switch (option){
            case 1:
                mRadioBtnAlipay.setChecked(true);
                break;
            case 2:
                mRadioBtnWeChat.setChecked(true);
                break;
            case 3:
                mRadioBtnQQ.setChecked(true);
                break;
            case 4:
                mRadioBtnGoogle.setChecked(true);
                break;
            case 5:
                mRadioBtnDialog.setChecked(true);
                break;
            case 0:
            default:
                mRadioBtnNone.setChecked(true);
                break;
        }

    }

    private class OptionChange implements RadioGroup.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (mRadioBtnAlipay.isChecked()){
                saveOptions(1);
            } else if (mRadioBtnWeChat.isChecked()){
                saveOptions(2);
            } else if (mRadioBtnQQ.isChecked()){
                saveOptions(3);
            } else if (mRadioBtnGoogle.isChecked()){
                saveOptions(4);
            } else if (mRadioBtnDialog.isChecked()){
                saveOptions(5);
            } else if (mRadioBtnNone.isChecked()){
                saveOptions(0);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveOptions(int options){
        SharedPreferences sp = getSharedPreferences("options", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("open_options", options);
        editor.apply();
    }

}
