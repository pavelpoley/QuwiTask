package com.quwi.task.ui.projects;

import com.quwi.task.data.repository.ClientRepository;
import com.quwi.task.ui.common.BaseViewModel;
import com.quwi.task.ui.common.response.ResponseLiveData;

import javax.inject.Inject;

public class ProjectListViewModel extends BaseViewModel {

    private final ClientRepository clientRepository;
    private final ResponseLiveData projectListLiveData = new ResponseLiveData();

    @Inject
    public ProjectListViewModel(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void loadData() {
        subscribe(
                clientRepository.getProjectList(), projectListLiveData
        );
    }

    public ResponseLiveData getProjectListLiveData() {
        return projectListLiveData;
    }
}