package com.quwi.task.ui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.quwi.task.R;
import com.quwi.task.data.repository.AuthRepository;
import com.quwi.task.domain.model.Project;
import com.quwi.task.ui.auth.AuthFragment;
import com.quwi.task.ui.details.ProjectDetailsFragment;
import com.quwi.task.ui.projects.ProjectListFragment;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    @Inject
    AuthRepository authRepository;

    private View loadingBar;
    private View fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadingBar = findViewById(R.id.progress_bar);
        fragmentContainer = findViewById(R.id.container);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());

        if (savedInstanceState == null) {
            if (authRepository.isAuthorized()) {
                navigateToProjectScreen();
            } else {
                replaceFragment(AuthFragment.newInstance(), false);
            }
        }
    }

    private void replaceFragment(Fragment fragment, boolean backstack) {
        String simpleName = fragment.getClass().getSimpleName();
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment, simpleName);

        if (backstack) {
            transaction.addToBackStack(simpleName);
        }

        transaction.commit();
    }

    public void navigateToProjectScreen() {
        replaceFragment(ProjectListFragment.newInstance(), false);
    }

    public void navigateToDetailsScreen(Project project) {
        replaceFragment(ProjectDetailsFragment.newInstance(project), true);
    }

    public void showLoadingBar() {
        loadingBar.setVisibility(View.VISIBLE);
        fragmentContainer.setVisibility(View.GONE);
    }

    public void hideLoadingBar() {
        loadingBar.setVisibility(View.GONE);
        fragmentContainer.setVisibility(View.VISIBLE);
    }

    public void showBackArrow() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    public void hideBackArrow() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }

    public void updateProjectList() {
        ProjectListFragment fragment = (ProjectListFragment) getSupportFragmentManager()
                .findFragmentByTag(ProjectListFragment.class.getSimpleName());

        if (fragment != null) {
            fragment.setShouldRefresh(true);
        }

        onBackPressed();
    }
}