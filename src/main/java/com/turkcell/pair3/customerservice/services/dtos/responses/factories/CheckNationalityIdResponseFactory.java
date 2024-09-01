package com.turkcell.pair3.customerservice.services.dtos.responses.factories;

import com.turkcell.pair3.customerservice.services.dtos.responses.CheckNationalityIdResponse;

public class CheckNationalityIdResponseFactory {
    public static CheckNationalityIdResponse createWithIfAlreadyExists(boolean alreadyExists) {
        CheckNationalityIdResponse checkNationalityIdResponse = new CheckNationalityIdResponse();
        checkNationalityIdResponse.setAlreadyExist(alreadyExists);
        return checkNationalityIdResponse;
    }
}
