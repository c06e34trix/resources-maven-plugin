package org.codematrix.mojo;

import org.apache.maven.plugins.resources.CopyResourcesMojo;

import java.io.Serializable;

/**
 * @author zhangcs
 * @since 1.0
 */
public class Rename implements Serializable {

    /**
     * The source file name that needs to be renamed. The path where the source file resides
     * is the output path configured for this plug-in.
     *
     * @see CopyResourcesMojo#getOutputDirectory()
     */
    private String source;

    /**
     * The new file name you want to name
     */
    private String destination;

    /**
     * Whether to overwrite the output file directly when it already exists
     */
    private boolean overwrite = true;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public boolean isOverwrite() {
        return overwrite;
    }

    public void setOverwrite(boolean overwrite) {
        this.overwrite = overwrite;
    }
}
