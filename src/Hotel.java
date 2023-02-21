import javax.swing.*;
import java.util.Set;

public class Hotel {
    protected int id;
    protected String name;
    protected String adress;
    protected int star;
    protected float price;
    protected String image;
    protected int rooms;
    protected String facilities;
    protected String description;



    public Hotel(int id, String name, String adress, int star, float price, String image, String facilities, int rooms, String description) {
        this.id = id;
        this.name = name;
        this.adress = adress;
        this.star = star;
        this.price = price;
        this.image = image;
        this.rooms = rooms;
        this.facilities = facilities;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", adress='" + adress + '\'' +
                ", star=" + star +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", rooms=" + rooms +
                ", facilities='" + facilities + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getAdress() {
        return adress;
    }

    public int getStar() {
        return star;
    }

    public float getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public int getRooms() {
        return rooms;
    }

    public String getFacilities() {
        return facilities;
    }
}
