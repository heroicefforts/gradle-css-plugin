package com.eriwen.gradle.css.tasks;

import java.io.File;

import org.gradle.api.internal.project.ProjectInternal;

import com.asual.lesscss.LessException;

public class LessCompilationException extends RuntimeException {
	private static final long serialVersionUID = 1;
	
	private final File file;
    private final ProjectInternal project;

    public LessCompilationException(Throwable cause, File file, ProjectInternal project) {
        super(cause);
        this.file = file;
        this.project = project;
    }

    public String getMessage() {
        String path = project.relativePath(file);
        String context;
        if(this.getCause() instanceof LessException)
        	context = "Less compilation error at " + path + ':' + ((LessException)this.getCause()).getLine();
        else
        	context = "Less compilation error in file " + path;

        return context + System.getProperty("line.separator") + '|' + getCause().getMessage();
    }
    
}
