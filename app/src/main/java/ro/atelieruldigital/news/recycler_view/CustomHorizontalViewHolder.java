package ro.atelieruldigital.news.recycler_view;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ro.atelieruldigital.news.R;

class CustomHorizontalViewHolder extends RecyclerView.ViewHolder {
    ImageView mImageView;
    TextView mTextViewTitle;
    TextView mTextViewDescription;
    Button mButtonFindMore;

    public CustomHorizontalViewHolder(@NonNull View itemView) {
        super(itemView);

        initView(itemView);
    }

    private void initView(View itemView) {
        mTextViewTitle = itemView.findViewById(R.id.text_view_title);
        mTextViewDescription = itemView.findViewById(R.id.text_view_description);
        mButtonFindMore = itemView.findViewById(R.id.button_find_more);
        mImageView = itemView.findViewById(R.id.image_view);
    }
}
