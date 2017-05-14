package com.pij.foursq;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.squareup.leakcanary.LeakCanary;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasDispatchingActivityInjector;
import dagger.android.support.HasDispatchingSupportFragmentInjector;

/**
 * <p>Created on 08/04/2017.</p>
 * @author Pierrejean
 */

public class Application extends android.app.Application
        implements HasDispatchingActivityInjector, HasDispatchingSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingActivityInjector;

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingFragmentInjector;

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);

    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        if (dispatchingActivityInjector == null) {
            injectSelf();
        }
        return dispatchingActivityInjector;
    }

    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        if (dispatchingFragmentInjector == null) {
            injectSelf();
        }
        return dispatchingFragmentInjector;
    }

    public void setComponent(ApplicationComponent newValue) {
        component = newValue;
    }

    private void injectSelf() {
        if (component == null) {
            setComponent(defaultComponent());
            component.inject(this);
        }
    }

    private ApplicationComponent defaultComponent() {
        return DaggerApplicationComponent.builder().application(this).build();
    }
}