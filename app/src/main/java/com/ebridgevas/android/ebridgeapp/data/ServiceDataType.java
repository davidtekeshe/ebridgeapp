package com.ebridgevas.android.ebridgeapp.data;

public enum ServiceDataType {
    ROOT((byte)0),
    YELLOW_PAGE_ENTRY((byte)1),
    SERVICE_LIST_ENTRY((byte)2),
    SERVICE_MENU_ENTRY((byte)3),
    BALANCE_ENQUIRY_ENTRY((byte)4);

    private Byte mId;

    ServiceDataType (Byte id) {
        mId = id;
    }

    public Byte getId() {
        return mId;
    }

    public void setId(Byte id) {
        mId = id;
    }
}
