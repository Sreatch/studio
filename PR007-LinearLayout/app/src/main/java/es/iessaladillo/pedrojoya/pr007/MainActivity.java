package es.iessaladillo.pedrojoya.pr007;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity {

    private TextView lblUsername;
    private EditText txtUsername;
    private TextView lblPassword;
    private EditText txtPassword;
    private Button btnAccept;
    private Button btnCancel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        lblUsername = findViewById(R.id.lblUsername);
        lblPassword = findViewById(R.id.lblPassword);
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        btnAccept = findViewById(R.id.btnAccept);
        btnCancel = findViewById(R.id.btnCancel);

        btnAccept.setOnClickListener(v -> signIn());
        btnCancel.setOnClickListener(v -> resetViews());
        setChangeTypefaceOnFocusListener(lblUsername, txtUsername);
        setChangeTypefaceOnFocusListener(lblPassword, txtPassword);
        setChangeVisibilityTextWatcher(lblUsername, txtUsername);
        setChangeVisibilityTextWatcher(lblPassword, txtPassword);
        // Initial check.
        checkIsValidForm();
        setTypefaceDependingOnFocus(lblUsername, true);
        setTextViewVisibility(txtPassword, lblPassword);
        setTextViewVisibility(txtUsername, lblUsername);
    }

    private void signIn() {
        Toast.makeText(this,
                getString(R.string.main_activity_signing_in, txtUsername.getText().toString()),
                Toast.LENGTH_SHORT).show();
    }

    private void resetViews() {
        txtUsername.setText("");
        txtPassword.setText("");
    }

    private void setChangeVisibilityTextWatcher(TextView lbl, EditText txt) {
        txt.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                setTextViewVisibility(txt, lbl);
                checkIsValidForm();
            }
        });
    }

    private void setChangeTypefaceOnFocusListener(TextView lbl, EditText txt) {
        txt.setOnFocusChangeListener((v, hasFocus) -> setTypefaceDependingOnFocus(lbl, hasFocus));
    }

    private void checkIsValidForm() {
        btnAccept.setEnabled(isValidForm());
    }

    private boolean isValidForm() {
        return !TextUtils.isEmpty(txtUsername.getText().toString()) && !TextUtils.isEmpty(
                txtPassword.getText().toString());
    }

    private void setTextViewVisibility(EditText txt, TextView lbl) {
        lbl.setVisibility(
                TextUtils.isEmpty(txt.getText().toString()) ? View.INVISIBLE : View.VISIBLE);
    }

    private void setTypefaceDependingOnFocus(TextView lbl, boolean hasFocus) {
        lbl.setTypeface(hasFocus ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT);
    }

}
