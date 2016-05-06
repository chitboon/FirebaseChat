package sg.edu.nyp.firebasechat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseListAdapter;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        // replace the url with your unique url created in your account
        final Firebase ref = new Firebase("https://popping-fire-1625.firebaseio.com/");

        ListView listView = (ListView)findViewById(R.id.listView);
        FirebaseListAdapter<Message> mAdapter = new FirebaseListAdapter<Message>(this, Message.class, android.R.layout.two_line_list_item,ref) {
            @Override
            protected void populateView(View view, Message msg, int posititon) {
                ((TextView)view.findViewById(android.R.id.text1)).setText(msg.getName());
                ((TextView)view.findViewById(android.R.id.text2)).setText(msg.getMessage());
            }
        };
        listView.setAdapter(mAdapter);

        final EditText mMessage = (EditText)findViewById(R.id.editText);
        findViewById(R.id.imageButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // message sent to the firebase database here
                ref.push().setValue(new Message("android", mMessage.getText().toString()));
                mMessage.setText("");
                // this is to hide the soft keyboard after the button is clicked.
                hideSoftKeyboard();
            }
        });
        // need to hide the keyboard, as by default the EditText field is in focus, and the keyboard will be visible
        hideSoftKeyboard();
    }

    public void hideSoftKeyboard() {
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

}
