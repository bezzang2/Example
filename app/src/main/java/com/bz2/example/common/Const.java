package com.bz2.example.common;

public interface Const {
    interface Permission{
        int REQUEST_PERMISSION           = 1001;
    }

    interface Url{
        String FILEUPLOAD_URL            = "/cmm/fileUpload.do";
        String LOGOUT_MAIN_URL            = "/man/moveMain.do";
        String LOGIN_MAIN_URL            = "/hom/moveAfterMain.do";
    }
}
