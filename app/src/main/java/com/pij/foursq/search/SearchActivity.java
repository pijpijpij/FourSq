package com.pij.foursq.search;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.pij.foursq.R;

import javax.inject.Inject;

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
        //TODO add a dialog to display detail of exception stack.
        Snackbar.make(input, "Error: " + error, Snackbar.LENGTH_LONG)
                .setAction(R.string.snackbar_show_error, null)
                .show();
    }

    private void onResult(ResultEvent result) {
        if (result.inProgress()) {
            list.setVisibility(View.INVISIBLE);
            empty.setText(R.string.search_in_progress);
            empty.setVisibility(View.VISIBLE);
        } else if (result.errorMessage() != null) {
            list.setVisibility(View.INVISIBLE);
            empty.setText(result.errorMessage());
            empty.setVisibility(View.VISIBLE);
        } else {
            adapter.setItems(result.places());
            list.setVisibility(View.VISIBLE);

            if (result.places().isEmpty()) {
                String emptyText = isBlank(result.name())
                                   ? getString(R.string.search_no_search)
                                   : getString(R.string.search_not_found, result.name());
                empty.setText(emptyText);
                empty.setVisibility(View.VISIBLE);
            } else {
                empty.setVisibility(View.INVISIBLE);
            }
        }
    }
}
