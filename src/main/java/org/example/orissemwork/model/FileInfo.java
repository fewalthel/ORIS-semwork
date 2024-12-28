package org.example.orissemwork.model;

public class FileInfo {
    private Integer id;
    private String originalFileName;
    private String storageFileName;
    private Long size;
    private String type;

    public FileInfo(Integer id, String originalFileName, String storageFileName, Long size, String type) {
        this.id = id;
        this.originalFileName = originalFileName;
        this.storageFileName = storageFileName;
        this.size = size;
        this.type = type;
    }

    public String getOriginalFileName() { return originalFileName; }
    public String getStorageFileName() { return storageFileName; }
    public Long getSize() { return size; }
    public String getType() { return type; }

}