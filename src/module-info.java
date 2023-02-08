module FXML.Example {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires java.logging;
    requires java.prefs;
    requires java.desktop;
    requires javafx.media;
    opens sample;
    exports sample;
}