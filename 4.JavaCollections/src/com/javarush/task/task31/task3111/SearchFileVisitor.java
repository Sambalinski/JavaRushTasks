package com.javarush.task.task31.task3111;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {
    private List<Path> foundFiles = new ArrayList<>();

    private String partOfName;
    private String partOfContent;
    private int minSize;
    private int maxSize;

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        byte[] content = Files.readAllBytes(file); // размер файла: content.length

        int count = 0;
        int check = 0;

        if (this.partOfName != null) count ++;
        if (this.partOfContent != null) count ++;
        if (this.maxSize != 0) count ++;
        if (this.minSize != 0) count ++;

        if (partOfName != null && file.getFileName().toString().contains(partOfName)) check ++;
        if (partOfContent != null && new String(content).contains(partOfContent)) check ++;
        if (maxSize != 0 && Files.size(file) < maxSize) check ++;
        if (minSize != 0 && Files.size(file) > minSize) check ++;

        if (count == check) foundFiles.add(file);

        return super.visitFile(file, attrs);
    }


    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;
    }

    public void setPartOfContent(String partOfContent) {
        this.partOfContent = partOfContent;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public List<Path> getFoundFiles() {
        return this.foundFiles;
    }
}
