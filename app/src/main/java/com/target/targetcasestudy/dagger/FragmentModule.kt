package com.target.targetcasestudy.dagger

import com.target.targetcasestudy.ui.fragment.DealListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector()
    abstract fun dealListFragment(): DealListFragment
}