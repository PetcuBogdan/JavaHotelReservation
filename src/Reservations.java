public class Reservations{
    protected int idRez;
    protected String checkIn;
    protected String checkOut;
    protected int rooms;
    protected String hotel;
    protected int userID;

    @Override
    public String toString() {
        return "Reservations{" +
                "idRez=" + idRez +
                ", checkIn='" + checkIn + '\'' +
                ", checkOut='" + checkOut + '\'' +
                ", rooms=" + rooms +
                ", hotel='" + hotel + '\'' +
                ", userID=" + userID +
                '}';
    }

    public Reservations(int idRez, String checkIn, String checkOut, int rooms, String hotel, int userID) {
        this.idRez = idRez;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.rooms = rooms;
        this.hotel = hotel;
        this.userID = userID;
    }

    public int getIdRez() {
        return idRez;
    }

    public void setIdRez(int idRez) {
        this.idRez = idRez;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }
}
