package cat.tecnocampus.mobileapps.marinismael.mobileapp.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import cat.tecnocampus.mobileapps.marinismael.mobileapp.R;
import cat.tecnocampus.mobileapps.marinismael.mobileapp.domain.Words;
import cat.tecnocampus.mobileapps.marinismael.mobileapp.domain.User;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences prefs;
    private EditText etUserName, etNumberCoubles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.main_color)));
        etUserName = findViewById(R.id.nameHint);
        etNumberCoubles = findViewById(R.id.couplesHint);
        prefs = getSharedPreferences("GamePreferences", Context.MODE_PRIVATE);

    }


    public void startGame(View view) {
        User user;
        Words word;
        String et_UserName = etUserName.getText().toString();
        boolean isOk = true;

        if ("".equals(et_UserName)) {
            isOk = false;
            etUserName.setError(getString(R.string.error_empty));
        }

        if (isOk) {
            user = new User(et_UserName);
            word = new Words();
            Intent intent = new Intent(this, GameOnActivity.class);
            intent.putExtra("myWord", new Gson().toJson(word));
            intent.putExtra("myUser", new Gson().toJson(user));
            startActivityForResult(intent, 1);

        }
    }

    public void ranking(View view) {
        int contador = prefs.getInt("contador", -1);
        Log.d("contador", Integer.toString(contador));
        if (contador == -1) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(getString(R.string.error_empty))

                    .setPositiveButton(R.string.btn_yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
            builder.create().show();
        } else {
            Intent intent = new Intent(this, ShowResultActivity.class);
            startActivityForResult(intent, 2);


        }
    }

    @Override
    public  boolean onSupportNavigateUp(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.sure_back_app)
                .setPositiveButton(R.string.btn_yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id){
                        finish();
                    }
                })
                .setNegativeButton(R.string.btn_no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        builder.create().show();
        return true;
    }
}