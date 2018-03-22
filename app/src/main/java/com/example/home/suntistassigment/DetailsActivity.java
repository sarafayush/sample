package com.example.home.suntistassigment;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView headerName, headerEmail;
    private EditText nameText, phoneText;
    private Button maleBtn, femaleBtn, submit;
    private String genderSelected ="";
    private ListView contactListview;
    private ArrayList<User> userItems;
    private UserAdapter adapter;
    private JSONObject response;
    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            checkFieldsForEmptyValues();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        headerName = (TextView) findViewById(R.id.name);
        headerEmail = (TextView) findViewById(R.id.email);
        nameText = (EditText) findViewById(R.id.inputName);
        phoneText = (EditText) findViewById(R.id.inputPhone);
        maleBtn = (Button) findViewById(R.id.maleButton);
        femaleBtn = (Button) findViewById(R.id.femaleButton);
        submit = (Button) findViewById(R.id.submit);
        contactListview = (ListView) findViewById(R.id.list);
        nameText.addTextChangedListener(mTextWatcher);
        phoneText.addTextChangedListener(mTextWatcher);
        userItems = new ArrayList<>();
         adapter = new UserAdapter(this, userItems);
        contactListview.setAdapter(adapter);
        maleBtn.setOnClickListener(this);
        femaleBtn.setOnClickListener(this);
        submit.setOnClickListener(this);
        submit.setEnabled(false);
        Intent intent = getIntent();
        String jsondata = intent.getStringExtra("userProfile");
        try {
            response = new JSONObject(jsondata);
            headerEmail.setText(response.get("email").toString());
            headerName.setText(response.get("name").toString());



        } catch(Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.maleButton:
                maleBtn.setBackground(ContextCompat.getDrawable(this,R.drawable.button_bg_redstroke));
                maleBtn.setTextColor(ContextCompat.getColor(this, R.color.whitecolor));
                femaleBtn.setBackground(ContextCompat.getDrawable(this,R.drawable.button_bg_stroke));
                femaleBtn.setTextColor(ContextCompat.getColor(this, R.color.greycolor));
                genderSelected = "M";
                checkFieldsForEmptyValues();
                break;
            case R.id.femaleButton:
                femaleBtn.setBackground(ContextCompat.getDrawable(this,R.drawable.button_bg_redstroke));
                femaleBtn.setTextColor(ContextCompat.getColor(this, R.color.whitecolor));
                maleBtn.setBackground(ContextCompat.getDrawable(this,R.drawable.button_bg_stroke));
                maleBtn.setTextColor(ContextCompat.getColor(this, R.color.greycolor));
                genderSelected = "F";
                checkFieldsForEmptyValues();
                break;
            case R.id.submit:
                User user = new User(nameText.getText().toString(), phoneText.getText().toString(),genderSelected);
                userItems.add(user);
                adapter.notifyDataSetChanged();
                nameText.setText("");
                phoneText.setText("");
                genderSelected = "";
                femaleBtn.setBackground(ContextCompat.getDrawable(this,R.drawable.button_bg_stroke));
                femaleBtn.setTextColor(ContextCompat.getColor(this, R.color.greycolor));
                maleBtn.setBackground(ContextCompat.getDrawable(this,R.drawable.button_bg_stroke));
                maleBtn.setTextColor(ContextCompat.getColor(this, R.color.greycolor));

        }

    }
    void checkFieldsForEmptyValues() {

        String s1 = nameText.getText().toString();
        String s2 = phoneText.getText().toString();

        if (s1.equals("") || s2.equals("") || genderSelected.equals("")) {
            submit.setEnabled(false);
        } else {
            submit.setEnabled(true);
        }
    }
}
