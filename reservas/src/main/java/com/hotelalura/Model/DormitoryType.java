package com.hotelalura.Model;

public class DormitoryType {
    public enum RoomType {
        SINGLE("Single room"),
        KING("King room"),
        SUITE("Suites"),
        PRESIDENTIAL("Presidential Suites");

        private final String displayName;

        RoomType(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }
}
