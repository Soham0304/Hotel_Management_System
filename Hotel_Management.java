import java.sql.*;
import java.util.Scanner;

public class Hotel_Management {
    private static final String url="jdbc:mysql://localhost:3306/hotel_db";
    private static final String username="root";
    private static final String password="Soham@030904";
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try{
            Connection connection= DriverManager.getConnection(url, username, password);
            Statement statement= connection.createStatement();
            while (true){
                System.out.println();
                System.out.println("HOTEL MANAGEMENT SYSTEM");
                Scanner scanner= new Scanner(System.in);
                System.out.println("1. Reservation for Room");
                System.out.println("2. View Reservations");
                System.out.println("3. Get room number");
                System.out.println("4. Update Reservation");
                System.out.println("5. Delete Reservation");
                System.out.println("6. Exit");
                System.out.println("Choose an option: ");
                int choice= scanner.nextInt();
                switch (choice){
                    case 1:
                        reserveRoom(connection, scanner, statement);
                        break;
                    case 2:
                        viewReservation(connection, statement);
                        break;
                    case 3:
                        getRoomNumber(connection, scanner, statement);
                        break;
                    case 4:
                        updateReservation(connection, scanner, statement);
                        break;
                    case 5:
                        deleteReservation(connection, scanner, statement);
                        break;
                    case 6:
                        exit();
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice...");
                }

            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }
    private static void reserveRoom(Connection connection, Scanner scanner, Statement statement){
        try{
            System.out.println("Enter guest name: ");
            String guestName=scanner.nextLine();
            System.out.println("Enter room number: ");
            int roomNumber= scanner.nextInt();
            System.out.println("Enter contact number: ");
            String contactNumber=scanner.nextLine();

            String query = "INSERT INTO reservations (guest_name, room_number, contact_number) " +
                    "VALUES ('" + guestName + "', " + roomNumber + ", '" + contactNumber + "')";

            int affectedrows=statement.executeUpdate(query);
            if(affectedrows> 0){
                System.out.println("Rows added succesfully");
            }
            else{
                System.out.println("Insertion failed");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    private static void viewReservation(Connection connection, Statement statement) throws SQLException{
        String query="SELECT reservation_id, guest_name, room_number, contact_number, reservation_date FROM reservations";
        ResultSet rs=statement.executeQuery(query);
        while (rs.next()){
            int reservationId =rs.getInt("reservation_id");
            String guestName= rs.getString("guest_name");
            int roomNumber = rs.getInt("room_number");
            String contactNumber = rs.getString("contact_number");
            String reservationDate = rs.getTimestamp("reservation_date").toString();

            System.out.println("Your Reservation ID is: "+reservationId);
            System.out.println("The Guest Name is: "+guestName);
            System.out.println("Your room number is: "+roomNumber);
            System.out.println("Your contact details is: "+contactNumber);
            System.out.println("The reservation date is: "+reservationDate);

        }
    }

    private static void getRoomNumber(Connection connection, Scanner scanner, Statement statement){
        try{
            System.out.println("Enter reservation ID: ");
            int reservationId=scanner.nextInt();
            System.out.println("Enter guest name: ");
            String guestName=scanner.next();

            String sql="SELECT room_number FROM reservations"+"WHERE reservation_id = "+ reservationId+"AND guest_name= "+ guestName+"";

            try(ResultSet resultSet = statement.executeQuery(sql)){
                if(resultSet.next()){
                    int roomNumber = resultSet.getInt("room_number");
                    System.out.println("Room number for Reservation ID "+ reservationId + " and Guest "+ guestName+ "is: "+roomNumber);
                }
                else{
                    System.out.println("Reservation not found for the given ID and guest name.");
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    private static void updateReservation(Connection connection, Scanner scanner){
        try{
            System.out.println("Enter reservation ID to update: ");
            int reservationId = scanner.nextInt();
            scanner.nextLine();

            if()
        }
    }
}
