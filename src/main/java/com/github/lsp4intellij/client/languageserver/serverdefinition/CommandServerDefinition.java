package com.github.lsp4intellij.client.languageserver.serverdefinition;

import com.github.lsp4intellij.client.connection.ProcessStreamConnectionProvider;
import com.github.lsp4intellij.client.connection.StreamConnectionProvider;

import java.util.Arrays;

/**
 * A base trait for every command-line server definition
 */
public class CommandServerDefinition extends UserConfigurableServerDefinition {
    private static final CommandServerDefinition INSTANCE = new CommandServerDefinition();

    public static CommandServerDefinition getInstance() {
        return INSTANCE;
    }

    protected String[] command;

    protected CommandServerDefinition() {
        this.presentableTyp = "Command";
        this.typ = "command";
    }

    /**
     * Transforms an array of string into the corresponding UserConfigurableServerDefinition
     *
     * @param arr The array
     * @return The server definition
     */
    public UserConfigurableServerDefinition fromArray(String[] arr) {
        CommandServerDefinition raw = RawCommandServerDefinition.getInstance().fromArray(arr);
        if (raw == null) {
            return ExeLanguageServerDefinition.getInstance().fromArray(arr);
        } else {
            return raw;
        }
    }

    @Override
    public StreamConnectionProvider createConnectionProvider(String workingDir) {
        return new ProcessStreamConnectionProvider(Arrays.asList(command), workingDir);
    }
}
