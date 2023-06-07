package com.kevincnzuk.bapool;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class PoolAdapter extends RecyclerView.Adapter<PoolAdapter.ViewHolder> {

    private static final String TAG = "PoolAdapter";

    private Context context;
    private List<StudentVO> list;

    public PoolAdapter (Context contextImport, List<StudentVO> listImport) {
        context = contextImport;
        list = listImport;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StudentVO vo = list.get(position);

        StringBuilder stars = new StringBuilder();
        for (int i = 0; i < vo.getRank(); i++) {
            stars.append("⭐");
        }

        String nameEn = vo.getNameEn().toLowerCase();
        Log.d(TAG, "onBindViewHolder: nameEn before: " + nameEn);
        nameEn = nameEn.replace(" ", "_");
        nameEn = nameEn.replace(" ", "_");
        nameEn = nameEn.replace("(", "");
        nameEn = nameEn.replace(")", "");

        Log.d(TAG, "onBindViewHolder: nameEn after: " + nameEn);

        holder.imageView.setImageResource(context.getResources()
                .getIdentifier(nameEn, "drawable", "com.kevincnzuk.bapool"));
        holder.tvName.setText(vo.getName());
        holder.tvNameEn.setText(vo.getNameEn());
        holder.tvStar.setText(stars.toString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ShapeableImageView imageView;
        MaterialTextView tvName;
        MaterialTextView tvNameEn;
        MaterialTextView tvStar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_student_avatar);
            tvName = itemView.findViewById(R.id.item_student_name);
            tvNameEn = itemView.findViewById(R.id.item_student_name_en);
            tvStar = itemView.findViewById(R.id.item_student_star);
        }
    }
}
