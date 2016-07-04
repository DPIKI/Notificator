package dpiki.notificator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dpiki.notificator.Receivers.BootReceiver;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BootReceiver.initAlarmManager(this);
    }
}
