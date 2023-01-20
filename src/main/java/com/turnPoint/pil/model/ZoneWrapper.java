package com.turnPoint.pil.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ZoneWrapper {

    public Zone zone;

    public String message;

}
