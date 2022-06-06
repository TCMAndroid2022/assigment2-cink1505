package cat.tecnocampus.mobileapps.marinismael.mobileapp.activity;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import cat.tecnocampus.mobileapps.marinismael.mobileapp.R;
import cat.tecnocampus.mobileapps.marinismael.mobileapp.domain.Words;
import cat.tecnocampus.mobileapps.marinismael.mobileapp.domain.User;
import cat.tecnocampus.mobileapps.marinismael.mobileapp.persistence.SharePoint;
import cat.tecnocampus.mobileapps.marinismael.mobileapp.view.WordAdapter;

public class GameOnActivity extends AppCompatActivity implements SharePoint {

    private RecyclerView collectionView;
    private WordAdapter wordAdapter;
    private Words data;
    private User user;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_on_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.main_color)));

        prefs = getSharedPreferences("GamePreferences",Context.MODE_PRIVATE);

        collectionView = findViewById(R.id.collection_view);
        RecyclerView.LayoutManager layoutManager =  new LinearLayoutManager(this);
        collectionView.setLayoutManager(layoutManager);

        Bundle main_data = getIntent().getExtras();
        data = new Gson().fromJson(main_data.getString("myWord"), Words.class);
        user = new Gson().fromJson(main_data.getString("myUser"), User.class);
        wordAdapter = new WordAdapter(data.getWord(), this);

        collectionView.setAdapter(wordAdapter);
        int sizeOfTable = data.getWord().length;
        int cont = (int) Math.sqrt(sizeOfTable);


        collectionView.setLayoutManager(new GridLayoutManager(this, cont));
        ((TextView)findViewById(R.id.et_userName)).setText(getString(R.string.userName)+user.getName());



    }
    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String ItemName = intent.getStringExtra("item");
            String qty = intent.getStringExtra("quantity");
            Toast.makeText(GameOnActivity.this,ItemName +" "+qty ,Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void gameOver(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.gameOver)+user.getName()+getString(R.string.gameOver2)+Integer.toString(user.getPoints())+getString(R.string.gameOver3)+getString(R.string.gameOver4))

                .setPositiveButton(R.string.btn_yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id){
                        int contador = prefs.getInt("contador",-1);
                        contador++;
                        SharedPreferences.Editor editor=prefs.edit();
                        editor.putInt("contador",contador);
                        editor.putString(Integer.toString(contador), new Gson().toJson(user));
                        editor.commit();
                        finish();
                    }
                });
                builder.create().show();

    }
    @Override
    public void sendPoints(int points) {
        user.setPoints(points);
        ((TextView)findViewById(R.id.et_userName)).setText(getString(R.string.userName)+user.getName()+getString(R.string.pointsText)+user.getPoints());


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

}

