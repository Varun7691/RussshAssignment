package varun.com.russshassignment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import varun.com.russshassignment.database.DBHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText mFirstName, mLastName, mMobileNumber, mEmailAddress, mPassword;
    Button mSave;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirstName = (EditText) findViewById(R.id.first_name);
        mLastName = (EditText) findViewById(R.id.last_name);
        mMobileNumber = (EditText) findViewById(R.id.mobile_number);
        mEmailAddress = (EditText) findViewById(R.id.email_address);
        mPassword = (EditText) findViewById(R.id.password);
        mSave = (Button) findViewById(R.id.save);

        dbHelper = new DBHelper(MainActivity.this);

        mSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.save) {
            String firstNameStr = mFirstName.getText().toString();
            String lastNameStr = mLastName.getText().toString();
            String mobileNumberStr = mMobileNumber.getText().toString();
            String emailAddressStr = mEmailAddress.getText().toString();
            String passwordStr = mPassword.getText().toString();

            if (firstNameStr.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter First Name", Toast.LENGTH_SHORT).show();
                return;
            }

            if (lastNameStr.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter Last Name", Toast.LENGTH_SHORT).show();
                return;
            }

            if (mobileNumberStr.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter Mobile Number", Toast.LENGTH_SHORT).show();
                return;
            }

            if (emailAddressStr.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter Email Address", Toast.LENGTH_SHORT).show();
                return;
            }

//            if (!emailAddressStr.matches(emailPattern)) {
//                Toast.makeText(MainActivity.this, "Please enter a valid Email Address", Toast.LENGTH_SHORT).show();
//                return;
//            }

            if (passwordStr.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter Password", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean isInsert = dbHelper.insertUser(firstNameStr, lastNameStr, mobileNumberStr, emailAddressStr, passwordStr);
            if (isInsert) {
                Toast.makeText(MainActivity.this, "User Registered", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        }
    }
}