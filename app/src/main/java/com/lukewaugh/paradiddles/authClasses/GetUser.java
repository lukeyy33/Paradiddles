package com.lukewaugh.paradiddles.authClasses;

/*
An interface to tell the Activity which
performs a Server request when the
request had completed.
*/
public interface GetUser {
    void processDone(User returnedUser);
}
