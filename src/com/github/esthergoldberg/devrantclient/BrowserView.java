package com.github.esthergoldberg.devrantclient;

import javax.swing.*;
import java.util.function.Consumer;

public interface BrowserView {

    JComponent getBrowser();

    void load(String url);

    void reload();

    void urlChange(Consumer<String> consumer);

    void back();

    void forward();

    boolean isNext();

    boolean isPrev();


}
