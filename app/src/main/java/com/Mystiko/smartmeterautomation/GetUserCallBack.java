package com.Mystiko.smartmeterautomation;
//to know which activities perform when server request completes
interface GetUserCallBack {

    public abstract void done(User returnedUser);

}
