package de.hsmannheim.bilderrahmen.filesync;

import java.util.List;

/**
 * DTO for synchronizing uploads and deletions from Google Drive. The message contains a id-list of deleted and created files.
 */
public class FileChanges {

    private List<String> deletedFiles;
    private List<String> createdFiles;


    public FileChanges build(List<String> deletedFiles, List<String> createFiles){
        this.deletedFiles = deletedFiles;
        this.createdFiles = createFiles;
        return this;
    }

    public List<String> getDeletedFiles() {
        return deletedFiles;
    }

    public void setDeletedFiles(List<String> deletedFiles) {
        this.deletedFiles = deletedFiles;
    }

    public List<String> getCreatedFiles() {
        return createdFiles;
    }

    public void setCreatedFiles(List<String> createdFiles) {
        this.createdFiles = createdFiles;
    }


}
