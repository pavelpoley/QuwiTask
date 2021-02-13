package com.quwi.task.ui.details;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.quwi.task.R;
import com.quwi.task.data.network.model.request.UpdateRequest;
import com.quwi.task.databinding.ProjectDetailsFragmentBinding;
import com.quwi.task.domain.model.Project;
import com.quwi.task.ui.MainActivity;
import com.quwi.task.ui.common.BaseFragment;
import com.quwi.task.ui.common.response.Response;

import org.jetbrains.annotations.NotNull;

public class ProjectDetailsFragment extends BaseFragment {

    public static final String ARG_PROJECT_ITEM = "ARG_PROJECT_ITEM";
    public static final int SAVE_MENU_ID = 21;

    private ProjectDetailsFragmentBinding binding;
    private ProjectDetailsViewModel viewModel;
    private Project project;

    public static ProjectDetailsFragment newInstance(Project project) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_PROJECT_ITEM, project);
        ProjectDetailsFragment fragment = new ProjectDetailsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        viewModel = new ViewModelProvider(this, viewModelFactory)
                .get(ProjectDetailsViewModel.class);

        Bundle arguments = getArguments();
        if (arguments != null) {
            project = (Project) arguments.getSerializable(ARG_PROJECT_ITEM);
        } else {
            showErrorMessage();
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = ProjectDetailsFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        getMainActivity().showBackArrow();
        getMainActivity().setTitle(project.getName());
        binding.projectNameEditText.setText(project.getName());

        viewModel.getUpdateResponse().observe(getViewLifecycleOwner(), this::processUpdateResponse);
    }

    private void processUpdateResponse(Response response) {
        hideLoadingBar();
        switch (response.getStatus()) {
            case LOADING:
                showLoadingBar();
                break;
            case SUCCESS:
                goBack();
                break;
            case ERROR:
                showErrorMessage();
                break;
        }
    }

    private void goBack() {
        MainActivity mainActivity = getMainActivity();
        if (mainActivity != null) {
            mainActivity.updateProjectList();
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        MenuItem menuItem = menu.add(0, SAVE_MENU_ID, menu.size() + 1, "Save");
        menuItem.setIcon(R.drawable.ic_baseline_done_24);
        menuItem.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == SAVE_MENU_ID) {
            save();
        }
        return super.onOptionsItemSelected(item);
    }

    private void save() {
        String value = getValue(binding.projectNameEditText);
        if (TextUtils.isEmpty(value)) {
            Toast.makeText(requireContext(), "Project name cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        viewModel.updateName(project.getId(), new UpdateRequest(value));
    }

    @NotNull
    private String getValue(EditText editText) {
        if (editText.getText() == null) {
            return "";
        }
        return editText.getText().toString();
    }
}