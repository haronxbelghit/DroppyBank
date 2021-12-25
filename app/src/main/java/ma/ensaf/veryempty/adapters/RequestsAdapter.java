package ma.ensaf.veryempty.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ma.ensaf.veryempty.R;
import ma.ensaf.veryempty.databinding.ItemRequestsBinding;
import ma.ensaf.veryempty.models.CUsers;
import ma.ensaf.veryempty.models.Users;

public class RequestsAdapter extends RecyclerView.Adapter<RequestsAdapter.ViewHolder> {

    private List<CUsers> filtered_items;

    private Context ctx;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position, CUsers obj);
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ItemRequestsBinding binding;
        ViewHolder(final ItemRequestsBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RequestsAdapter(Context ctx, List<CUsers> items) {
        this.ctx = ctx;
        filtered_items = items;
    }

    public void setUsersList(List<CUsers> items) {
        // then update the items
        filtered_items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemRequestsBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_requests, parent, false);
        return new ViewHolder(binding);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final CUsers obj = filtered_items.get(position);

        // set the views
        holder.binding.userNameTextView.setText(obj.getName());
        holder.binding.userImageView.setImageResource(obj.getImage());
        holder.binding.userLocationTextView.setText(obj.getLocation());
        holder.binding.userPhoneNumberTextView.setText(obj.getPhoneNumber());
        holder.binding.bloodGroupTextView.setText(obj.getBloodGroup());

        // click listeners
        holder.binding.lytParent.setOnClickListener(view -> {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(view, position, obj);
            }
        });

        // Here you apply the animation when the view is bound
        setAnimation(holder.itemView, position);
    }

    /**
     * Here is the key method to apply the animation
     */
    private int lastPosition = -1;
    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(ctx, R.anim.slide_left_in);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return filtered_items.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}

