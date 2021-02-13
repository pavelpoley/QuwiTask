package com.quwi.task.ui.auth;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.quwi.task.R;
import com.quwi.task.data.network.model.response.LoginResponse;
import com.quwi.task.databinding.AuthFragmentBinding;
import com.quwi.task.ui.MainActivity;
import com.quwi.task.ui.common.BaseFragment;
import com.quwi.task.ui.common.response.Response;

import org.jetbrains.annotations.NotNull;

public class AuthFragment extends BaseFragment {

    private AuthFragmentBinding binding;
    private AuthViewModel viewModel;

    public static AuthFragment newInstance() {
        return new AuthFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this, viewModelFactory)
                .get(AuthViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = AuthFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        getMainActivity().hideBackArrow();
        getMainActivity().setTitle(getResources().getString(R.string.app_name));

        binding.btnLogin.setOnClickListener(v -> tryToLogin());

        binding.tvForgotPassword.setOnClickListener(v ->
                Toast.makeText(requireContext(), "Not implemented in this task", Toast.LENGTH_SHORT).show());

        viewModel.getLoginLiveData()
                .observe(getViewLifecycleOwner(), this::processLoginResponse);
    }

    private void processLoginResponse(Response<LoginResponse> response) {
        hideLoadingBar();
        switch (response.getStatus()) {
            case LOADING:
                showLoadingBar();
                break;
            case SUCCESS:
                onLoginSuccess(response);
                break;
            case ERROR:
                //dirty hack but okay for this test task
                if (response.getError() != null
                        && response.getError().getMessage() != null
                        && response.getError().getMessage().equals("HTTP 417 Data Validation Failed.")) {
                    Toast.makeText(requireContext(), "Incorrect email or password.", Toast.LENGTH_SHORT).show();
                }
                showErrorMessage();
                break;
        }
    }

    private void onLoginSuccess(Response<LoginResponse> response) {
        LoginResponse data = response.getData();
        if (data == null) {
            showErrorMessage();
            return;
        }

        MainActivity mainActivity = getMainActivity();
        if (mainActivity != null) {
            mainActivity.navigateToProjectScreen();
        }
    }

    private void tryToLogin() {
        String email = getValue(binding.emailEditText);
        String password = getValue(binding.passwordEditText);

        if (isInputValid(email, password)) {
            viewModel.login(email, password);
        }
    }

    private boolean isInputValid(String email, String password) {
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(requireContext(), "Email cannot be blank", Toast.LENGTH_SHORT).show();
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(requireContext(), "Invalid email", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(requireContext(), "Invalid password", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    @NotNull
    private String getValue(EditText editText) {
        if (editText.getText() == null) {
            return "";
        }
        return editText.getText().toString();
    }
}