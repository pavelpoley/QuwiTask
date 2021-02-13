package com.quwi.task.ui.projects;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.quwi.task.App;
import com.quwi.task.R;
import com.quwi.task.databinding.ListItemProjectBinding;
import com.quwi.task.domain.model.Project;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private final List<Project> list = new ArrayList<>();
    @Nullable
    private Callback callback;

    public interface Callback {
        void onItemClick(Project project);
    }

    public void setList(List<Project> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemProjectBinding binding = ListItemProjectBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding, binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Project project = list.get(position);
        if (project != null) {
            holder.bind(project);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setCallback(@Nullable Callback callback) {
        this.callback = callback;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final ListItemProjectBinding binding;

        public ViewHolder(ListItemProjectBinding binding, @NonNull View itemView) {
            super(itemView);
            this.binding = binding;
        }
        void bind(Project project) {
            binding.projectNameTextView.setText(project.getName());

            Glide.with(App.getInstance())
                    .load(project.getLogUrl())
                    .placeholder(R.drawable.img_placeholder)
                    .into(binding.avatarImage);

            itemView.setOnClickListener(v -> {
                if (callback != null) {
                    callback.onItemClick(project);
                }
            });
        }

    }
}
