package com.ebridgevas.android.ebridgeapp.model;

public enum MessageType {
    ROOT((byte)4, "root"),
    YELLOW_PAGE_ENTRY((byte)2, "yellow-page"),
    SERVICE_LIST_ENTRY((byte)0, "service-list"),
    SERVICE_MENU_ENTRY((byte)4, "serice-menu"),
    BALANCE_ENQUIRY_ENTRY((byte)3, "balance-enquiry"),
    CHAT_ENTRY((byte)1, "chat");

    private Byte id;
    private String description;

    MessageType(Byte id, String description) {
        this.id = id;
        this.description = description;
    }

    public Byte getId() {
        return id;
    }

    public void setId(Byte id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static MessageType getById(byte id) {
        for ( MessageType nodeType : values()) {
            if (nodeType.id == id) {
                return nodeType;
            }
        }
        return null;
    }

    public static MessageType getByDescription(String description) {
        for ( MessageType messageType : values()) {
            if (messageType.description.equalsIgnoreCase(description)) {
                return messageType;
            }
        }
        return null;
    }
}
