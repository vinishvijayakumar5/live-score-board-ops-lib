package com.zyxcorp.libs.scoreboard.exception;

import lombok.Getter;

@Getter
public class MatchScoreBoardException extends Exception {

    private static final long serialVersionUID = 1L;

    private String contract;

    public MatchScoreBoardException(String message, String contract) {
        super(message);
        this.contract = contract;
    }

}
