package com.turnPoint.pil.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

    @Builder
    @Getter
    @Setter
    public class SupervisorWrapper {

        public Supervisor supervisor;

        public String message;

    }


