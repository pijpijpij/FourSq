package com.pij.foursq.ui.search;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.pij.foursq.R;
import com.pij.foursq.ui.model.Place;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.subscriptions.CompositeSubscription;

/**
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
