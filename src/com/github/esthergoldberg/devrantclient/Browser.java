package com.github.esthergoldberg.devrantclient;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.util.ui.JBUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.Objects;

public class Browser extends JPanel {
    private BrowserView webView;
    private JButton btnBack;
    private JButton btnForward;
    private JButton btnReload;
    MyCookieStore cookieStore;
    CookieManager cookieManager;
    MyCookiePolicy cookiePolicy;

    public Browser(BrowserView webView) {

        StateService cookieStorageService = ServiceManager.getService(StateService.class);

        cookieStore = Objects.requireNonNull(cookieStorageService.getState()).getCookieStoreValue();
        cookiePolicy = new MyCookiePolicy();
        cookieManager = new CookieManager(cookieStore, cookiePolicy);
        CookieHandler.setDefault(cookieManager);

        this.webView = webView;
/*
        URI uri = URI.create("https://devrant.com");
        Map<String, java.util.List<String>> headers = new LinkedHashMap<String, java.util.List<String>>();
        headers.put("Set-Cookie", Arrays.asList("name=value"));
        try {
            java.net.CookieHandler.getDefault().put(uri,headers);
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        this.initView();
        this.initEvent();
    }

    private void initView() {
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 20, 0, 0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);

        btnBack = new JButton("<");
        btnBack.setEnabled(false);
        btnBack.setMaximumSize(new Dimension(40, 29));
        btnBack.setMinimumSize(new Dimension(40, 29));
        btnBack.setPreferredSize(new Dimension(40, 29));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = JBUI.insets(0, 0, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(btnBack, gbc);

        btnForward = new JButton(">");
        btnForward.setEnabled(false);
        btnForward.setMaximumSize(new Dimension(40, 29));
        btnForward.setMinimumSize(new Dimension(40, 29));
        btnForward.setPreferredSize(new Dimension(40, 29));
        btnForward.setMargin(JBUI.emptyInsets());
        gbc = new GridBagConstraints();
        gbc.insets = JBUI.insets(0, 0, 5, 5);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(btnForward, gbc);

        gbc = new GridBagConstraints();
        gbc.insets = JBUI.insets(0, 0, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 0;

        btnReload = new JButton("Reload");
        btnReload.setMaximumSize(new Dimension(50, 29));
        btnReload.setMinimumSize(new Dimension(50, 29));
        btnReload.setPreferredSize(new Dimension(50, 29));
        gbc = new GridBagConstraints();
        gbc.insets = JBUI.insetsBottom(5);
        gbc.gridx = 3;
        gbc.gridy = 0;
        add(btnReload, gbc);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(0, 0));
        panel.add(webView.getBrowser(), BorderLayout.CENTER);
        gbc = new GridBagConstraints();
        gbc.gridwidth = 4;
        gbc.insets = JBUI.insetsRight(5);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(panel, gbc);

    }

    private void initEvent() {
        webView.load("https://devrant.com");
        webView.urlChange(s -> {
            this.btnBack.setEnabled(webView.isPrev());
            this.btnForward.setEnabled(webView.isNext());
        });
        btnBack.addActionListener(e -> webView.back());
        btnForward.addActionListener(e -> webView.forward());
        btnReload.addActionListener(e -> webView.reload());

        if (Toolkit.getDefaultToolkit().areExtraMouseButtonsEnabled() && MouseInfo.getNumberOfButtons() > 3) {
            Toolkit.getDefaultToolkit().addAWTEventListener(event -> {
                if (event instanceof MouseEvent) {
                    MouseEvent mouseEvent = (MouseEvent) event;
                    if (mouseEvent.getID() == MouseEvent.MOUSE_RELEASED && mouseEvent.getButton() > 3) {
                        if (mouseEvent.getButton() == 4) {
                            webView.back();
                        }
                        else if (mouseEvent.getButton() == 5) {
                            webView.forward();
                        }
                    }
                }
            }, AWTEvent.MOUSE_EVENT_MASK);
        }

    }
}






