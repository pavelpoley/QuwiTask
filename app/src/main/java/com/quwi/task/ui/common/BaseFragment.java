package com.quwi.task.ui.common;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import com.quwi.task.ui.MainActivity;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public abstract class BaseFragment extends DaggerFragment {

    @Inject
    public ViewModelProvider.Factory viewModelFactory;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    protected void showLoadingBar() {
        MainActivity activity = getMainActivity();
        if (activity != null) {
            activity.showLoadingBar();
        }
    }

    protected void hideLoadingBar() {
        MainActivity activity = getMainActivity();
        if (activity != null) {
            activity.hideLoadingBar();
        }
    }

    @Nullable
    protected MainActivity getMainActivity() {
        FragmentActivity activity = getActivity();
        if (activity instanceof MainActivity) {
            return (MainActivity) activity;
        }
        return null;
    }

    protected void showErrorMessage() {
        Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
    }
}
