package cat.tecnocampus.mobileapps.marinismael.mobileapp.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;

import cat.tecnocampus.mobileapps.marinismael.mobileapp.R;
import cat.tecnocampus.mobileapps.marinismael.mobileapp.domain.User;
import cat.tecnocampus.mobileapps.marinismael.mobileapp.view.ShowResultAdapter;

public class ShowResultActivity extends AppCompatActivity {
    private ArrayList<User> users;
    private SharedPreferences prefs;
    private RecyclerView collectionView;
    private ShowResultAdapter showResultAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_result);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.main_color)));
        prefs = getSharedPreferences("GamePreferences", Context.MODE_PRIVATE);
        users= new ArrayList<>();
        int contador = prefs.getInt("contador",-1);
        for(int i=0;i<=contador;i++){
            users.add(new Gson().fromJson(prefs.getString(Integer.toString(i),null), User.class));
        }

        collectionView = findViewById(R.id.collection_view);
        RecyclerView.LayoutManager layoutManager =  new LinearLayoutManager(this);
        collectionView.setLayoutManager(layoutManager);
        showResultAdapter = new ShowResultAdapter(users,this);
        collectionView.setAdapter(showResultAdapter);
    }

    @Override
    public  boolean onSupportNavigateUp(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.sure_back)
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

    public void btn_clear_all(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.sure_clear)
                .setPositiveButton(R.string.btn_yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id){
                        users= new ArrayList<>();
                        SharedPreferences.Editor editor=prefs.edit();
                        editor.clear();
                        editor.commit();
                        finish();
                    }
                })
                .setNegativeButton(R.string.btn_no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        builder.create().show();

    }
}
