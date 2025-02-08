package com.manu.BookHub.API.model;

import lombok.Getter;

@Getter
public enum StateOfLoan {
    ACTIVE("active"),
    FINISHED("finished");

    private final String value;

    StateOfLoan(String value) {
        this.value = value;
    }
}
