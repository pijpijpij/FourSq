package com.pij.foursq.search;

import com.pij.dagger.ActivityScope;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * <p>Created on 08/04/2017.</p>
 * @author Pierrejean
 */

@ActivityScope
@Subcomponent(modules = SearchActivityModule.class)
public interface SearchActivitySubComponent extends AndroidInjector<SearchActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<SearchActivity> { }
}
