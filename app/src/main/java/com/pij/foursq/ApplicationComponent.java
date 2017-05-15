package com.pij.foursq;

import com.pij.foursq.interactor.BuildTypeModule;
import com.pij.foursq.net.NetModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * <p>Created on 08/04/2017.</p>
 * @author Pierrejean
 */
@Component(modules = {
        ApplicationModule.class,
        BuildTypeModule.class,
        AndroidSupportInjectionModule.class,
        BuildersModule.class,
        NetModule.class
})
@Singleton
interface ApplicationComponent {

    void inject(Application target);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        ApplicationComponent build();
    }
}
