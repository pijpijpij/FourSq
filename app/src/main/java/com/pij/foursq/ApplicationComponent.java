package com.pij.foursq;

import com.pij.foursq.interactor.BuildTypeModule;
import com.pij.foursq.net.NetConfigModule;
import com.pij.foursq.net.NetModule;

import javax.inject.Singleton;

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
        NetModule.class,
        NetConfigModule.class,
        ThreadingModule.class
})
@Singleton
interface ApplicationComponent {

    void inject(Application target);

    @Component.Builder
    interface Builder {

        Builder netConfig(NetConfigModule netConfig);

        // Not required
        Builder threadingConfig(ThreadingModule threading);

        ApplicationComponent build();
    }
}
