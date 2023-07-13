package Tugas_PBO_DIVA;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
public class Admin {
    //    private static ArrayList<Restaurant> restaurants = new ArrayList<>();
//    public static ArrayList<Menu> menus = new ArrayList<>();
    static  HashMap<String, Restaurant> restaurants = new HashMap<>();
    public static ArrayList<Order> orders = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    static void MenuAdmin(){
        System.out.println("======== MENU ADMIN ========");
        System.out.println("| 1. Lihat Restaurant       ");
        System.out.println("| 2. Tambah Restaurant      ");
        System.out.println("| 3. Hapus Restaurant       ");
        System.out.println("| 4. Ulang Login            ");
        System.out.println("| 5. Keluar                 ");
        System.out.println("============================");
    }

    //    add restaurant
    public static void addRestaurant() {
        System.out.print("Masukkan ID Restaurant : ");
        int id = scanner.nextInt();

        System.out.print("Masukkan Nama Restaurant : ");
        String nama = scanner.next();

        System.out.println("Masukkan Alamat Restaurant : ");
        String alamat = scanner.next();
        Restaurant restaurant = new Restaurant(id, nama, alamat);

        String inputMenu = "";
        while (!inputMenu.equals("selesai")){
            System.out.print("Masukkan/Tambah Menu (format: menuName|menuPrice) enter, lalu ketik \"selesai\" untuk selesai: ");
            inputMenu = scanner.next();
            if(!inputMenu.equals("selesai")){
                String[] menuData = inputMenu.split("\\|");
                restaurant.setMenus(new Menu(menuData[0], Integer.parseInt(menuData[1])));
            }
        }
        restaurants.put(nama,restaurant);
        System.out.println("Berhasil Menambahkan Restaurant!");
    }


    //    view retaurant
    public static void viewRestaurant(){
        System.out.println("============= RESTAURANT =============");
        for(String namaRestaurant : restaurants.keySet()){
            System.out.println("ID Restaurant : " + restaurants.get(namaRestaurant).getId()
                    + " || Nama : " + namaRestaurant + " || Alamat : " +restaurants.get(namaRestaurant).getAlamat());
            System.out.println("Menu : ");
            for (Menu menu : restaurants.get(namaRestaurant).getMenus()){
                System.out.println(menu.getNama() + " || " +menu.getHarga());
            }
        }
    }

    public static void viewMenu(){
        System.out.println("==== RESTAURANT ====");
        for(String namarestaurant : restaurants.keySet()) {
            System.out.println(namarestaurant + " || " + restaurants.get(namarestaurant).getAlamat());
        }
        System.out.print("Masukkan Nama Restaurant : ");
        String pilih_restaurant = scanner.next();
        if(!restaurants.containsKey(pilih_restaurant)){
            System.out.println("Menu Kosong!");
        }
        Restaurant res = restaurants.get(pilih_restaurant);
        Order newOrder = new Order(res);
        String menu = "";
        while(!menu.equals("2")){
            System.out.println("==== "+ res.getNama() + " ====");
            System.out.println("Menu : ");
            int nomorMenu = 1;
            for (Menu menu1 : res.getMenus()){
                System.out.println(nomorMenu + ". " + menu1.getNama() + " - " + menu1.getHarga());
                nomorMenu++;
            }
            System.out.print("Masukan Nomor Menu : ");
            int indexMenu = scanner.nextInt();
            System.out.print("Total beli : ");
            int jumlah_beli = scanner.nextInt();
            scanner.nextLine();

            newOrder.addMenu(res.getMenus().get(indexMenu - 1), jumlah_beli);

            System.out.print("Ingin tambah menu lagi?  (1 = iya / 2 = tidak) : ");
            menu = scanner.next();
        }
        System.out.print("Masukan jarak antar makanan ke lokasi (dalam km) : ");
        int jarak = scanner.nextInt();

        newOrder.setDistance(jarak);
        orders.add(newOrder);
        System.out.println("Order menu berhasil!");
    }

    public static void viewOrder(){
        System.out.println("===== ORDERS =====");
        for(int i = 0; i<orders.size(); i++){
            Order order = orders.get(i);
            System.out.println((i+1) + ". " +order.getRestaurant().getNama());
            System.out.println("Menu : ");
            for(Menu menu : order.getMenus().keySet()){
                System.out.println(menu.getNama() + " X " + order.getMenus().get(menu));
            }
            System.out.println("Ongkir 1.000.km || Jarak : "+order.getDistance()+" km");
            System.out.println("Total Bayar : "+order.getTotalPrice());
        }
    }

    public static void removeRestaurant(){
        for(String namaRestaurant : restaurants.keySet()){
            System.out.println("ID Restaurant : " + restaurants.get(namaRestaurant).getId()
                    + " || Nama : " + namaRestaurant + " || Alamat : " +restaurants.get(namaRestaurant).getAlamat());
        }
        System.out.println("==============================");
        System.out.println("Masukkan Nama Restaurant : ");
        String namaRestaurant = scanner.next();
        if(restaurants.containsKey(namaRestaurant)){
            restaurants.remove(namaRestaurant);
            System.out.println("Restaurant Berhasil Terhapus!");
        }else{
            System.out.println("Restaurant tidak ada!");
        }
    }
}
