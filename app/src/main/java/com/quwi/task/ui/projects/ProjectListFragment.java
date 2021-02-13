package com.quwi.task.ui.projects;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.quwi.task.data.network.model.response.ProjectListResponse;
import com.quwi.task.databinding.ProjectListFragmentBinding;
import com.quwi.task.domain.Mapper;
import com.quwi.task.domain.model.Project;
import com.quwi.task.ui.MainActivity;
import com.quwi.task.ui.common.BaseFragment;
import com.quwi.task.ui.common.response.Response;

import java.util.List;

public class ProjectListFragment extends BaseFragment {

    private ProjectListFragmentBinding binding;
    private ProjectListViewModel viewModel;
    private final Adapter adapter = new Adapter();
    private boolean shouldRefresh = false;

    public static ProjectListFragment newInstance() {
        return new ProjectListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this, viewModelFactory)
                .get(ProjectListViewModel.class);
        viewModel.loadData();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = ProjectListFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        getMainActivity().hideBackArrow();
        getMainActivity().setTitle("Projects");
        initRecycleView();

        viewModel.getProjectListLiveData()
                .observe(getViewLifecycleOwner(), this::processProjectListResponse);

        if (shouldRefresh) {
            viewModel.loadData();
            shouldRefresh = false;
        }
    }

    private void initRecycleView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        binding.recycleView.setLayoutManager(layoutManager);
        binding.recycleView.setAdapter(adapter);
        binding.recycleView.addItemDecoration(new DividerItemDecoration(requireContext(),
                layoutManager.getOrientation()));
        adapter.setCallback(project -> {
            MainActivity activity = getMainActivity();
            if (activity != null) {
                activity.navigateToDetailsScreen(project);
            }
        });
    }

    private void processProjectListResponse(Response<ProjectListResponse> response) {
        hideLoadingBar();
        switch (response.getStatus()) {
            case LOADING:
                showLoadingBar();
                break;
            case SUCCESS:
                bindUI(response);
                break;
            case ERROR:
                showErrorMessage();
                break;
        }
    }

    private void bindUI(Response<ProjectListResponse> response) {
        ProjectListResponse data = response.getData();
        if (data == null) {
            showErrorMessage();
            return;
        }

        List<Project> projects = Mapper.toDomain(data.getProjects());
        adapter.setList(projects);
    }

    public void setShouldRefresh(boolean shouldRefresh) {
        this.shouldRefresh = shouldRefresh;
    }
}