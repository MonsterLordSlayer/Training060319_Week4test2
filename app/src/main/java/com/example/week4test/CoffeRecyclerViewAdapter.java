package com.example.week4test;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.week4test.ItemFragment.OnListFragmentInteractionListener;
import com.example.week4test.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class CoffeRecyclerViewAdapter extends RecyclerView.Adapter<CoffeRecyclerViewAdapter.ViewHolder> {

    private final List<Coffe> coffes;


    public CoffeRecyclerViewAdapter(List<Coffe> items) {
        coffes=items;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.coffe = coffes.get(position);
        holder.tvname.setText(holder.coffe.getName());
        holder.tvdes.setText(holder.coffe.getDesc());
        Glide.with(holder.ivcoffe).load(holder.coffe.getImageUrl()).into(holder.ivcoffe);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return coffes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tvname;
        public final TextView tvdes;
        public final ImageView ivcoffe;
        public Coffe coffe;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvname = (TextView) view.findViewById(R.id.tvname);
            tvdes = (TextView) view.findViewById(R.id.tvdescription);
            ivcoffe=(ImageView) view.findViewById(R.id.ivImage);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvname.getText() + "'";
        }
    }
}
