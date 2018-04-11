package com.prosupport.lolchampions.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.prosupport.lolchampions.R;
import com.prosupport.lolchampions.data.Champion;
import com.prosupport.lolchampions.listeners.OnItemClickListener;

import java.text.Collator;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class ChampionsAdapter extends RecyclerView.Adapter<ChampionsAdapter.ViewHolder> {

    private List<Champion> mChampions;
    private OnItemClickListener<Champion> mOnItemClickListener;
    private List<Champion> mFilteredChampions;

    public ChampionsAdapter(List<Champion> champions) {
        mChampions = champions;
        if (champions != null) {
            mFilteredChampions = new ArrayList<>(champions);
        }
    }

    public void updateChampions(List<Champion> champions, OnItemClickListener<Champion> onItemClickListener) {
        mChampions = champions;
        if (champions != null) {
            mFilteredChampions = new ArrayList<>(champions);
        }
        mOnItemClickListener = onItemClickListener;
        notifyDataSetChanged();
    }

    public void searchFor(final String text) {
        if (text == null || text.length() == 0) {
            mFilteredChampions = new ArrayList<>(mChampions);
            notifyDataSetChanged();
            return;
        }
        final Collator insensitiveStringComparator = Collator.getInstance();
        final String searchText = removeDiacriticalMarks(text).toLowerCase();
        insensitiveStringComparator.setStrength(Collator.PRIMARY);
        mFilteredChampions = new ArrayList<>();
        for (Champion champion : mChampions) {
            if (removeDiacriticalMarks(champion.name.toLowerCase()).contains(searchText)
                    || removeDiacriticalMarks(champion.title.toLowerCase()).contains(searchText)) {
                mFilteredChampions.add(champion);
            }
        }
        notifyDataSetChanged();
    }

    public static String removeDiacriticalMarks(String string) {
        return Normalizer.normalize(string, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_champion, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mFilteredChampions.get(position), mOnItemClickListener);
    }

    @Override
    public int getItemCount() {
        return mFilteredChampions == null ? 0 : mFilteredChampions.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView avatarImageView;
        public TextView nameTextView;
        public TextView titleTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            avatarImageView = itemView.findViewById(R.id.avatarImageView);
            nameTextView = itemView.findViewById(R.id.nameTxt);
            titleTextView = itemView.findViewById(R.id.titleTxt);
        }

        public void bind(final Champion champion, final OnItemClickListener<Champion> onItemClickListener) {
            RequestOptions options = new RequestOptions();
            options.placeholder(R.drawable.placeholder);
            options.error(R.drawable.placeholder);
            options.centerCrop();
            Glide.with(avatarImageView.getContext()).load(champion.getDefaultSkinImage()).apply(options).into(avatarImageView);
            nameTextView.setText(champion.name);
            titleTextView.setText(champion.title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(getAdapterPosition(), champion);
                    }
                }
            });
        }
    }
}
