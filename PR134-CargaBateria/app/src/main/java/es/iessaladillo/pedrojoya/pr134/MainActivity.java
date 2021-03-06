package es.iessaladillo.pedrojoya.pr134;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private TextView lblLevel;
    private ProgressBar pbLevel;

    private BatteryBroadcastReceiver batteryBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        batteryBroadcastReceiver = new BatteryBroadcastReceiver();
    }

    private void initViews() {
        lblLevel = findViewById(R.id.lblLevel);
        pbLevel = findViewById(R.id.pbLevel);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(batteryBroadcastReceiver);
    }

    private class BatteryBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            boolean loading =
                    status == BatteryManager.BATTERY_STATUS_CHARGING ||
                            status == BatteryManager.BATTERY_STATUS_FULL;
            int plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
            boolean pluggedUsb = plugged == BatteryManager.BATTERY_PLUGGED_USB;
            boolean pluggedAc = plugged == BatteryManager.BATTERY_PLUGGED_AC;
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            @SuppressWarnings("unused")
            float porcentage = level / (float)scale;
            StringBuilder sb = new StringBuilder();
            if (loading) {
                sb.append(getString(R.string.main_activity_loading)).append(" ");
                if (pluggedUsb) {
                    sb.append(getString(R.string.main_activity_plugged_usb)).append(" ");
                }
                else if (pluggedAc) {
                    sb.append(getString(R.string.main_activity_plugged_ac)).append(" ");
                }
            }
            else {
                sb.append(getString(R.string.activity_main_lblLevel)).append(" ");
            }
            sb.append("(").append(level).append("%)");
            lblLevel.setText(sb.toString());
            pbLevel.setProgress(level);
        }

    }

}
