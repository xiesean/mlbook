package org.sean.csj;

public interface AdCallback {
    enum Status {
        SUCCESS, ERROR, FAILED,CLOSE
    }

    void onResult(Status status);
}
