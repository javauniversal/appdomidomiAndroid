package com.zonaapp.domidomi.implementation.createAccount;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.timqi.sectorprogressview.SectorProgressView;
import com.zonaapp.domidomi.R;
import com.zonaapp.domidomi.model.User;
import com.zonaapp.domidomi.util.PreferencesManager;

public class CreateAccountActivity extends AppCompatActivity {


    TextView txtNames;
    TextView txtLastName;
    TextView addressField1;
    TextView addressField2;
    TextView addressField3;
    LinearLayout layoutCreateAccount;
    LinearLayout layoutAddressType;
    TextView address;
    TextView txtPhone;
    EditText etAddresType;
    public String[] addressTypes = new String[]{"Avenida", "Avenida Calle", "Avenida Carrera", "Calle", "Carrera", "Circular", "Circunvalar",
            "Diagonal", "Manzana", "Transversal", "Vía"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        txtNames = (TextView) findViewById(R.id.txtNames);
        txtLastName = (TextView) findViewById(R.id.txtLastName);
        addressField1 = (TextView) findViewById(R.id.addressField1);
        addressField2 = (TextView) findViewById(R.id.addressField2);
        addressField3 = (TextView) findViewById(R.id.addressField3);
        etAddresType = (EditText) findViewById(R.id.etAddressType);
        layoutCreateAccount = (LinearLayout) findViewById(R.id.layoutCreateAccount);
        layoutAddressType = (LinearLayout) findViewById(R.id.layoutAddressType);
        txtPhone = (TextView) findViewById(R.id.txtPhone);

        addHandlers();
    }

    private void addHandlers() {

        layoutCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateFields();
            }


        });

        layoutAddressType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(CreateAccountActivity.this)
                        .items(addressTypes)
                        .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                etAddresType.setHint(text);
                                dialog.dismiss();
                                return true;
                            }
                        })
                        .show();
            }
        });



    }

    private void validateFields() {

        if (txtNames.getText().toString().equals("")){
            txtNames.setError("Tu nombre es requerido");
            return;
        }
        if (txtLastName.getText().toString().equals("")){
            txtLastName.setError("Tu apellido es requerido");
            return;
        }
        if (txtPhone.getText().toString().equals("")){
            txtPhone.setError("Tu teléfono es requerido");
            return;
        }

        if (addressField1.getText().toString().equals("") ||
                addressField2.getText().toString().equals("") ||
                addressField3.getText().toString().equals("")){

            return;

        }
        User userTosave =  new User();
        userTosave.setNames(txtNames.getText().toString());
        userTosave.setNames(txtLastName.getText().toString());
        userTosave.setNames(txtPhone.getText().toString());
        PreferencesManager.saveUser(userTosave, this);


    }
}
