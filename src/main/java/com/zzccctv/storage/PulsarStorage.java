package com.zzccctv.storage;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.components.State;

import java.util.*;

@State(name = "Pulsaride", storages = {@Storage("Pulsaride.xml")})
public class PulsarStorage implements PersistentStateComponent<PulsarStorage> {

    public Map<String, Map<String, String>> envNames = new HashMap<>();

    private PulsarStorage() {
    }

    @Override
    public PulsarStorage getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull PulsarStorage state) {
        XmlSerializerUtil.copyBean(state, this);
    }

    public void addEnvName(Map<String, String> c) {
        envNames.put(c.get("envName"), c);
    }

    public void removeEnvName(String c) {
        envNames.remove(c);
    }

    private static final PulsarStorage pulsarStorage = ServiceManager.getService(PulsarStorage.class);

    public static PulsarStorage getInstance() {
        return pulsarStorage;
    }
}