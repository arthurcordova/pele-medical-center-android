package com.mobway.pelemedicalcenter.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by arthur.stapassoli on 13/12/2017.
 */

public class ArriveOrderResponse {

    @SerializedName("idAgenda")
    public Integer id;
    @SerializedName("vagasPrimeiro")
    public Integer vacanciesFirstRound;
    @SerializedName("vagasSegundo")
    public Integer vacanciesSecondRound;

    public ArriveOrderResponse() {
    }

    public ArriveOrderResponse(Integer id, Integer vacanciesFirstRound, Integer vacanciesSecondRound) {
        this.id = id;
        this.vacanciesFirstRound = vacanciesFirstRound;
        this.vacanciesSecondRound = vacanciesSecondRound;
    }
}
