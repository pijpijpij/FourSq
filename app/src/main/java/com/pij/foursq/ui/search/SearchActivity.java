package com.pij.foursq.ui.search;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.pij.foursq.R;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerAppCompatActivity;
import rx.Observable;
import rx.subscriptions.CompositeSubscription;

import static com.jakewharton.rxbinding.widget.RxTextView.textChanges;
import static org.apache.commons.lang3.StringUtils.isBlank;

public class SearchActivity extends DaggerAppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.input)
    EditText input;
    @BindView(R.id.empty)
    TextView empty;
    @BindView(R.id.search_list)
    RecyclerView list;
    @BindString(R.string.search_no_search)
    String noSearch;

    @Inject
    SearchViewModel viewModel;
    private Unbinder unbinder;
    private CompositeSubscription subscriptions = new CompositeSubscription();
    private SearchAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        unbinder = ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        // Not injected since we want to keep references to layout in the same place
        adapter = new SearchAdapter(R.layout.search_list_item);
        list.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        Observable<ResultEvent> places = viewModel.places().publish().refCount();
        subscriptions.addAll(
                // To the View Model
                textChanges(input).map(Object::toString).subscribe(viewModel::search, this::notifyError),

                // From the view Model
                places.subscribe(this::onResult, this::notifyError));
        super.onStart();
    }

    @Override
    protected void onStop() {
        subscriptions.clear();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        adapter = null;
        super.onDestroy();
    }

    public void notifyError(Throwable error) {
        error.printStackTrace();
        Snackbar.make(input, "Error: " + error, Snackbar.LENGTH_LONG)
                .setAction(R.string.snackbar_show_error, null)
                .show();
    }

    private void onResult(ResultEvent result) {
        if (result.inProgress()) {
            list.setVisibility(View.INVISIBLE);
            empty.setText(R.string.search_in_progress);
            empty.setVisibility(View.VISIBLE);
        } else {
            if (result.places().isEmpty()) {
                String emptyText = calculateEmptyStringForName(result.name());
                empty.setText(emptyText);
                empty.setVisibility(View.VISIBLE);
            } else {
                empty.setVisibility(View.INVISIBLE);
            }

            if (result.errorMessage() != null) {
                //noinspection ConstantConditions
                Snackbar.make(list, result.errorMessage(), Snackbar.LENGTH_LONG).show();
            }
            adapter.setItems(result.places());
            list.setVisibility(View.VISIBLE);
        }
    }

    @NonNull
    private String calculateEmptyStringForName(String name) {
        return isBlank(name) ? noSearch : getString(R.string.search_not_found, name);
    }
}
