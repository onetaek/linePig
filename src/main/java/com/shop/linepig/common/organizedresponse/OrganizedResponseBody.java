package com.shop.linepig.common.organizedresponse;

import io.opencensus.trace.Link;

import java.util.HashMap;

public class OrganizedResponseBody {

    private boolean succeeded;
    private HashMap<String, Link> links;

    protected OrganizedResponseBody(boolean succeeded) {
        this.succeeded = succeeded;
        this.links = null;
    }

    private void initInnerLink() {
        if (this.links == null) {
            this.links = new HashMap<>();
        }
    }



}
