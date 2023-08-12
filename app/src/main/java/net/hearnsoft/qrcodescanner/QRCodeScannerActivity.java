package net.hearnsoft.qrcodescanner;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

public class QRCodeScannerActivity extends Activity {

    private final Intent intent = new Intent();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int option = initConfig();
        launchScanner(option);
    }

    private int initConfig(){
        SharedPreferences configs = getSharedPreferences("options", Context.MODE_PRIVATE);
        return configs.getInt("open_options",0);
    }

    private void launchScanner(int option){
        switch (option){
            case 1:
                openAlipayQrScanner(intent);
                finish();
                break;
            case 2:
                openWeChatQrScanner(intent);
                finish();
                break;
            case 3:
                openQQQrScanner(intent);
                finish();
                break;
            case 4:
                openGoogleLensScanner(intent);
                finish();
                break;
            case 5:
                launchDialog();
                break;
            case 0:
            default:
                finish();
                break;
        }
    }

    private void launchDialog(){
        int options = 0;
        new AlertDialog.Builder(this)
                .setTitle(R.string.app_name)
                .setCancelable(false)
                .setSingleChoiceItems(
                        getResources().getStringArray(R.array.app_title_list), options, (dialog, which) -> {
                            launchScanner(which+1);
                            finish();
                        })
                .setNegativeButton(R.string.dialog_cancel, (dialogInterface, i) -> finish())
                .show();
    }

    private void openAlipayQrScanner(Intent intent){
        try {
            intent.setClassName("com.eg.android.AlipayGphone","com.alipay.mobile.scan.as.main.MainCaptureActivity");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void openWeChatQrScanner(Intent intent){
        try {
            intent.setClassName("com.tencent.mm","com.tencent.mm.plugin.scanner.ui.BaseScanUI");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void openQQQrScanner(Intent intent){
        try {
            intent.setClassName("com.tencent.mobileqq","com.tencent.mobileqq.qrscan.activity.ScannerActivity");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void openGoogleLensScanner(Intent intent){
        try {
            intent.setClassName("com.google.android.gms","com.google.android.gms.mlkit.barcode.ui.PlatformBarcodeScanningActivityProxy");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
