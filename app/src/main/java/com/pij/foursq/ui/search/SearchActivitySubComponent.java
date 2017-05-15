package com.pij.foursq.ui.search;

import com.pij.dagger.ActivityScope;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * @author Pierrejean
 */

@ActivityScope
@Subcomponent(modules = SearchActivityModule.class)
public interface SearchActivitySubComponent extends AndroidInjector<SearchActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<SearchActivity> { }
}
