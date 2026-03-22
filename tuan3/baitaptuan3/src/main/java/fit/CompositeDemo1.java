package fit;

import java.util.ArrayList;
import java.util.List;

// Component
interface FileSystemComponent {
    void display(String indent);
}

// Leaf - File
class FileLeaf implements FileSystemComponent {
    private String name;

    public FileLeaf(String name) {
        this.name = name;
    }

    @Override
    public void display(String indent) {
        System.out.println(indent + "- File: " + name);
    }
}

// Composite - Folder
class FolderComposite implements FileSystemComponent {
    private String name;
    private List<FileSystemComponent> children = new ArrayList<>();

    public FolderComposite(String name) {
        this.name = name;
    }

    public void add(FileSystemComponent component) {
        children.add(component);
    }

    public void remove(FileSystemComponent component) {
        children.remove(component);
    }

    @Override
    public void display(String indent) {
        System.out.println(indent + "+ Folder: " + name);
        for (FileSystemComponent child : children) {
            child.display(indent + "   ");
        }
    }
}

public class CompositeDemo1 {
    public static void main(String[] args) {

        // Root folder
        FolderComposite root = new FolderComposite("Root");

        // Files
        FileLeaf file1 = new FileLeaf("file1.txt");
        FileLeaf file2 = new FileLeaf("file2.txt");

        // Sub folder A
        FolderComposite folderA = new FolderComposite("FolderA");
        FileLeaf file3 = new FileLeaf("file3.txt");

        // Sub folder B
        FolderComposite folderB = new FolderComposite("FolderB");
        FileLeaf file4 = new FileLeaf("file4.txt");

        // Build tree
        folderB.add(file4);
        folderA.add(file3);
        folderA.add(folderB);

        root.add(file1);
        root.add(file2);
        root.add(folderA);

        // Display tree
        root.display("");
    }
}