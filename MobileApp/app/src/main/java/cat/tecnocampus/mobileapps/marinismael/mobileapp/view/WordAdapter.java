package cat.tecnocampus.mobileapps.marinismael.mobileapp.view;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import cat.tecnocampus.mobileapps.marinismael.mobileapp.R;
import cat.tecnocampus.mobileapps.marinismael.mobileapp.domain.Letter;
import cat.tecnocampus.mobileapps.marinismael.mobileapp.persistence.SharePoint;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.ViewHolder> {
    private Letter[] data;
    private Letter cardOpenned = null;
    private View closingView, lastView;
    private int lastPosition, userPoint = 0;
    private SharePoint sharePoint;


    public WordAdapter(Letter[] data, SharePoint sharePoint) {
        this.data = data;
        this.sharePoint = sharePoint;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_collection_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        sharePoint.sendPoints(userPoint);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Letter word = data[position];
        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public int position;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceType")
                @Override
                public void onClick(View v) {
                    if (lastView != null && closingView != null) {
                        changeTheView(closingView, false, false);
                        changeTheView(lastView, false, false);
                        cardOpenned = null;
                        lastView = null;
                        closingView = null;
                    }

                    if (cardOpenned == null) {
                        cardOpenned = data[position];
                        changeTheView(v, true, false);
                        lastView = v;
                        lastPosition = position;
                    } else if (lastPosition != position) {
                        closingView = v;
                        if (cardOpenned.getType().equals(data[position].getType())) {
                            userPoint += 20;
                            sharePoint.sendPoints(userPoint);
                            changeTheView(v, false, false);
                            changeTheView(lastView, false, false);


                            notifyDataSetChanged();
                        } else {
                            userPoint -= 5;
                            sharePoint.sendPoints(userPoint);
                            changeTheView(v, true, true);
                        }
                    } else {
                        changeTheView(v, false, false);
                        cardOpenned = null;
                    }
                    if (data.length == 0) {
                        sharePoint.gameOver();
                    }
                }
            });
        }

        private void changeTheView(View v, boolean turnOn, boolean isRed) {
            if (turnOn) {
                if (isRed) {
                    ((TextView) v.findViewById(R.id.card_type)).setTextColor(Color.RED);
                    ((TextView) v.findViewById(R.id.card_type)).setBackgroundColor(Color.WHITE);
                    ((TextView) v.findViewById(R.id.card_type)).setText(data[position].getType());
                } else {
                    ((TextView) v.findViewById(R.id.card_type)).setTextColor(Color.GREEN);
                    ((TextView) v.findViewById(R.id.card_type)).setBackgroundColor(Color.WHITE);
                    ((TextView) v.findViewById(R.id.card_type)).setText(data[position].getType());
                }
            } else {
                ((TextView) v.findViewById(R.id.card_type)).setTextColor(Color.WHITE);
                ((TextView) v.findViewById(R.id.card_type)).setBackgroundColor(Color.BLACK);
                ((TextView) v.findViewById(R.id.card_type)).setText(R.string.nothing);
            }
        }
    }
}