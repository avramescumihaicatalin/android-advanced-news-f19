package ro.atelieruldigital.news.recycler_view;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ro.atelieruldigital.news.R;

class CustomVerticalViewHolder extends RecyclerView.ViewHolder {
    final TextView mTextView;
    final RecyclerView mHorizontalRecyclerView;

    public CustomVerticalViewHolder(@NonNull View itemView) {
        super(itemView);

        mTextView = itemView.findViewById(R.id.category_text_view);
        mHorizontalRecyclerView = itemView.findViewById(R.id.horizontal_recycler_view);
    }
}
