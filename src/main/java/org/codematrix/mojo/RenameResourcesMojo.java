package org.codematrix.mojo;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.resources.CopyResourcesMojo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangcs
 * @since 1.0
 */
@Mojo(name = "copy-rename-resources", threadSafe = true)
@SuppressWarnings("unused")
public class RenameResourcesMojo extends CopyResourcesMojo {

    /**
     * The list of resources we want to rename.
     */
    @Parameter
    private List<Rename> renames = new ArrayList<>();

    @Override
    public void execute() throws MojoExecutionException {
        super.execute();

        if (renames.isEmpty()) {
            return;
        }
        getLog().debug("execute renames");

        for (Rename name : renames) {
            String source = name.getSource();
            String destination = name.getDestination();
            getLog().debug("rename `" + source + "` to `" + destination + "`");

            File input = new File(getOutputDirectory(), source);
            if (!input.exists()) {
                throw new MojoExecutionException("Rename failed, `" + input.getAbsolutePath() + "` does not exist.");
            }
            if (!input.isFile()) {
                throw new MojoExecutionException("Rename failed, `" + input.getAbsolutePath() + "` is not a file.");
            }

            if (destination == null || destination.isEmpty()) {
                throw new MojoExecutionException("Rename failed, `" + destination + "`  is not a valid file name.");
            }

            File output = new File(getOutputDirectory(), destination);
            if (!name.isOverwrite() && output.exists()) {
                throw new MojoExecutionException("Rename failed, `" + output.getAbsolutePath() + "` already exists.");
            }

            try {
                Files.move(input.toPath(), output.toPath());
            } catch (IOException e) {
                getLog().warn(e.getMessage());
                throw new MojoExecutionException(e);
            }
        }
    }
}
