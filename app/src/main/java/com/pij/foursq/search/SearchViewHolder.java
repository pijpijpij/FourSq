package com.pij.foursq.search;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.pij.foursq.R;
import com.pij.foursq.model.Place;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.subscriptions.CompositeSubscription;

/**
 * <p>Created on 06/04/2017.</p>
 * @author Pierrejean
 */
class SearchViewHolder extends RecyclerView.ViewHolder {

    private final CompositeSubscription subscription = new CompositeSubscription();
    @BindView(R.id.label)
    TextView label;
    private String id;

    @SuppressWarnings("WeakerAccess")
    public SearchViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    @Override
    public String toString() {
        return super.toString() + " '" + id + "'";
    }

    @SuppressWarnings("WeakerAccess")
    public void setItem(Place item) {
        id = item.id();
        label.setText(item.name());
    }

}
