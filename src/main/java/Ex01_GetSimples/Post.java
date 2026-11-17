package Ex01_GetSimples;

public class Post {
    private long id;
    private String name;
    private String description;

    public long getId()
        { return id; }

    public void setId(long id)
        { this.id = id; }

    public void setName(String name)
        { this.name = name; }

    public void setDescription(String description)
        { this.description = Post.this.description; }

    @Override
    public String toString() {
        return "Post {" +
                "id: " + id + ", " +
                "name: " + name + ", " +
                "description: " + description +
                '}';
    }
}
