package com.target.targetcasestudy

import android.app.Activity
import android.app.Application
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import androidx.fragment.app.Fragment
import com.target.targetcasestudy.dagger.DaggerTargetComponent
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class TargetApplication : Application(), HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun activityInjector(): DispatchingAndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment> {
        return fragmentInjector
    }

    override fun onCreate() {
        super.onCreate()

        DaggerTargetComponent
            .builder()
            .application(this)
            .build()
            .inject(this)
    }
}