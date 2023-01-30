package com.example.google_billing;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.google_billing.Database.room_data_modal;

import java.util.ArrayList;
import java.util.List;

public class UserRVAdapter extends RecyclerView.Adapter<UserRVAdapter.ViewHolder> {

    // variable for our array list and context.
    private ArrayList<room_data_modal> userModalArrayList;
    private Context context;

    // creating a constructor.
    public UserRVAdapter(List<room_data_modal> userModalArrayList, Context context) {
        this.userModalArrayList = (ArrayList<room_data_modal>) userModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflating our layout file on below line.
        View view = LayoutInflater.from(context).inflate(R.layout.user_rv_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        // getting data from our array list in our modal class.
        room_data_modal userModal = userModalArrayList.get(position);

        // on below line we are setting data to our text view.
        holder.authorNameTV.setText(userModal.getAuthor_Name());
        holder.contentTV.setText('"' + userModal.getQuote() + '"');
        //  Log.d("pos", String.valueOf(position));

        if ((position) % 3 == 0) {


            holder.ll.setBackgroundColor(Color.parseColor("#D27C7C"));
        } else if ((position) % 3 == 1) {

            holder.ll.setBackgroundColor(Color.parseColor("#CDEBAB"));
        } else if ((position) % 3 == 2) {

            holder.ll.setBackgroundColor(Color.parseColor("#647AC6"));

        }


    }

    @Override
    public int getItemCount() {
        // returning the size of array list.
        return userModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // creating a variable for our text view and image view.
        private TextView authorNameTV, contentTV;
        CardView card;
        LinearLayout ll;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // initializing our variables.
            authorNameTV = itemView.findViewById(R.id.idTVAuthorName);
            contentTV = itemView.findViewById(R.id.idTVContent);
            card = itemView.findViewById(R.id.card);
            ll = itemView.findViewById(R.id.ll);

            Typeface customfont = Typeface.createFromAsset(context.getAssets(), "fonts/custfont3.ttf");
            contentTV.setTypeface(customfont);


        }
    }
}

