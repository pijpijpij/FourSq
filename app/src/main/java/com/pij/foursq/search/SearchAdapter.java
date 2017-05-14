package com.pij.foursq.search;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pij.foursq.model.Place;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.apache.commons.collections4.IterableUtils.toList;

/**
 * @author Pierrejean
 */
class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder> {

    private final int itemLayout;
    @NonNull
    private List<Place> values = emptyList();

    SearchAdapter(@LayoutRes int itemLayout) {
        this.itemLayout = itemLayout;
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SearchViewHolder holder, int position) {
        Place item = values.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    /**
     * Animates the insertions/ addition of items.
     */
    @SuppressWarnings("WeakerAccess")
    public void setItems(List<Place> items) {
        List<Place> oldValues = values;
        values = toList(items);
        DiffUtil.DiffResult diff = DiffUtil.calculateDiff(new PlaceDiffCallback(oldValues, items));
        diff.dispatchUpdatesTo(this);
    }

    private static class PlaceDiffCallback extends DiffUtil.Callback {

        private final List<Place> oldValues;
        private final List<Place> newValues;

        PlaceDiffCallback(List<Place> oldValues, List<Place> newValues) {
            this.oldValues = oldValues;
            this.newValues = newValues;
        }

        @Override
        public int getOldListSize() {
            return oldValues.size();
        }

        @Override
        public int getNewListSize() {
            return newValues.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            String oldItemId = getItemId(oldItemPosition, oldValues);
            String newItemId = getItemId(newItemPosition, newValues);
            return oldItemId.equals(newItemId);
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            Place oldItem = oldValues.get(oldItemPosition);
            Place newItem = newValues.get(newItemPosition);
            // TODO code that
            return false;
        }

        private String getItemId(int position, List<Place> values) {
            return values.get(position).id();
        }
    }
}
