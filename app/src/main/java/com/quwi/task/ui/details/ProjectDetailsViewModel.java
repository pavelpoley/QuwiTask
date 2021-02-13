package com.quwi.task.ui.details;

import com.quwi.task.data.network.model.request.UpdateRequest;
import com.quwi.task.data.repository.ClientRepository;
import com.quwi.task.ui.common.BaseViewModel;
import com.quwi.task.ui.common.response.ResponseLiveData;

import javax.inject.Inject;

public class ProjectDetailsViewModel extends BaseViewModel {

    private final ClientRepository clientRepository;
    private final ResponseLiveData updateResponse = new ResponseLiveData();

    @Inject
    public ProjectDetailsViewModel(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void updateName(int id, UpdateRequest request) {
        subscribe(clientRepository.update(id, request), updateResponse);
    }

    public ResponseLiveData getUpdateResponse() {
        return updateResponse;
    }
}