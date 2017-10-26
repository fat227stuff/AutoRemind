package com.mikeporet.autoremind;

import java.io.Serializable;

/**
 * Created by mikeporet on 10/18/17.
 */

public class Supply implements Serializable {

    private String title;
    private String subTitle;

    public Supply(String title, String subTitle) {
        this.title = title;
        this.subTitle = subTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
}
