package com.pij.foursq;

import android.app.Activity;

import com.pij.foursq.search.SearchActivity;
import com.pij.foursq.search.SearchActivitySubComponent;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Required for automatic injection.
 * <p>Created on 08/04/2017.</p>
 * @author Pierrejean
 */
@Module
abstract class BuildersModule {

    @Binds
    @IntoMap
    @ActivityKey(SearchActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindSearchActivityInjectorFactory(
            SearchActivitySubComponent.Builder builder);

}
