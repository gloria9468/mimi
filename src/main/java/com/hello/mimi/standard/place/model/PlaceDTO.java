package com.hello.mimi.standard.place.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PlaceDTO {
    private String title;
    private String roadAddress;
    private String mapx;
    private String mapy;

    @Override
    public String toString() {
        return "PlaceDTO{" +
                "title='" + title + '\'' +
                ", roadAddress='" + roadAddress + '\'' +
                ", mapx='" + mapx + '\'' +
                ", mapy='" + mapy + '\'' +
                '}';
    }
}
