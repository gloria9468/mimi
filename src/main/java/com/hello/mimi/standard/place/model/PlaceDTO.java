package com.hello.mimi.standard.place.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PlaceDTO {

    private Long placeId;
    private String placeTitle;
    private String roadAddress;
    private String mapx;
    private String mapy;

    @Override
    public String toString() {
        return "PlaceDTO{" +
                "placeId='" + placeId + '\'' +
                "placeTitle='" + placeTitle + '\'' +
                ", roadAddress='" + roadAddress + '\'' +
                ", mapx='" + mapx + '\'' +
                ", mapy='" + mapy + '\'' +
                '}';
    }
}
