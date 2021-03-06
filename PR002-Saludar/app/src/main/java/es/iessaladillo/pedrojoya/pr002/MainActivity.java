package es.iessaladillo.pedrojoya.pr002;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Toast;

import es.iessaladillo.pedrojoya.pr002.utils.KeyboardUtils;

public class MainActivity extends AppCompatActivity implements OnClickListener,
        OnCheckedChangeListener {

    private EditText txtName;
    private CheckBox chkPolite;
    @SuppressWarnings("FieldCanBeLocal")
    private Button btnGreet;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        txtName = findViewById(R.id.txtName);
        chkPolite = findViewById(R.id.chkPolite);
        btnGreet = findViewById(R.id.btnGreet);

        chkPolite.setOnCheckedChangeListener(this);
        btnGreet.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnGreet) {
            greet();

        }
    }

    private void greet() {
        String message = getString(R.string.main_activity_good_morning);
        if (chkPolite.isChecked()) {
            message = message + getString(R.string.main_activity_nice_to_meet_you);
        }
        message += " " + txtName.getText();
        KeyboardUtils.hideKeyboard(chkPolite);
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        chkPolite.setText(isChecked ? getString(R.string.main_activity_polite_mode) : getString(
                R.string.main_activity_impolite_mode));
    }

}
