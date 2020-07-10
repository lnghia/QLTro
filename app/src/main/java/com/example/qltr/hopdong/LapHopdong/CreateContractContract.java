package com.example.qltr.hopdong.LapHopdong;

import com.example.Models.Contract.Contract;

public interface CreateContractContract {
    interface View{
        void createSuccess();
        void createFailure(String message);
    }

    interface Presenter{
        void createContract(String token, int index, Contract contract);
    }
}
