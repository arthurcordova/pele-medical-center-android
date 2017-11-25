package com.mobway.pelemedicalcenter.adapters;

/**
 * Created by acstapassoli on 24/11/17.
 */

public class SelectableItem {

    private boolean isSelected = false;

    public SelectableItem(String item, boolean isSelected) {
        this.isSelected = isSelected;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
