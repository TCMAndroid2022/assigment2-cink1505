package cat.tecnocampus.mobileapps.marinismael.mobileapp.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import cat.tecnocampus.mobileapps.marinismael.mobileapp.R;
import cat.tecnocampus.mobileapps.marinismael.mobileapp.domain.User;

public class ShowResultAdapter extends RecyclerView.Adapter<ShowResultAdapter.ViewHolder> {
    private ArrayList<User> data;
    Context context ;
    public ShowResultAdapter(ArrayList<User> data,Context context) {
        this.data = data;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_collection_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = data.get(position);
        String test = context.getResources().getString(R.string.nameRanking)  + user.getName()+ context.getResources().getString(R.string.hasRanking) +Integer.toString(user.getPoints())+ context.getResources().getString(R.string.gameOver3);
        holder.text.setText( test);
        holder.position = position;
    }



    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public int position;
        public TextView text;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text =  itemView.findViewById(R.id.text_result);
            itemView.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceType")
                @Override
                public void onClick(View v) {
                    Log.d("_","_");
                }
            });
        }
    }
}