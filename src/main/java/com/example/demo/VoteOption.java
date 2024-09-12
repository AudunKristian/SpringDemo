package com.example.demo;

// VoteOption class
public class VoteOption {
    private String caption;
    private int presentationOrder;

    // Constructor
    public VoteOption(String caption, int presentationOrder) {
        this.caption = caption;
        this.presentationOrder = presentationOrder;
    }

    // Getters and setters
    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public int getPresentationOrder() {
        return presentationOrder;
    }

    public void setPresentationOrder(int presentationOrder) {
        this.presentationOrder = presentationOrder;
    }
}