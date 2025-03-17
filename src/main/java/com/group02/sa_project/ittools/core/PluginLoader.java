package com.group02.sa_project.ittools.core;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class PluginLoader {
    private final List<String> pluginRoutes = new ArrayList<>();

    public void registerPlugin(String route) {
        pluginRoutes.add(route);
    }

    public List<String> getAllPlugins() {
        return pluginRoutes;
    }
}
