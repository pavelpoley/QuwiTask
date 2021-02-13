package com.quwi.task.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.quwi.task.di.ViewModelKey;
import com.quwi.task.ui.auth.AuthViewModel;
import com.quwi.task.ui.common.ViewModelFactory;
import com.quwi.task.ui.details.ProjectDetailsViewModel;
import com.quwi.task.ui.projects.ProjectListViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
abstract public class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    abstract ViewModel bindAuthViewModel(AuthViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ProjectListViewModel.class)
    abstract ViewModel bindProjectListViewModel(ProjectListViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ProjectDetailsViewModel.class)
    abstract ViewModel bindProjectDetailsViewModel(ProjectDetailsViewModel viewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
