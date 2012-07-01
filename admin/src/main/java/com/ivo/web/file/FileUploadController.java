package com.ivo.web.file;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@RequestScoped
public class FileUploadController {

    private UploadedFile file;

    public UploadedFile getFile() {
        System.out.println("Get");
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
        System.out.println("Set");
    }

    public void upload() {
        System.out.println("Upload");
        if(file != null) {
            FacesMessage msg = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage("fileUploadMessages", msg);
        }
    }

    public void handleFileUpload(FileUploadEvent event) {
		FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage("fileUploadMessages", msg);
                System.out.println("Event");
	}
}