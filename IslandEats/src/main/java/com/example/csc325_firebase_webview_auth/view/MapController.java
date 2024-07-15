package com.example.csc325_firebase_webview_auth.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;

public class MapController {
    @FXML
    WebView webView;
    @FXML
    Button forumSwitch;


    @FXML
    public void initialize() {
        WebEngine engine = webView.getEngine();
        URL url = getClass().getResource("/files/Map.html");
        assert url != null;
        engine.load(url.toExternalForm());
    }

    @FXML
    public void forum() {
        WebEngine engine = webView.getEngine();
        engine.load("https://www.reddit.com");
    }
}
