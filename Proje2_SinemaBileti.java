/**
 * Ad Soyad: Ayşe Eslem ÇEKİCİ
 * Öğrenci No: 250541026
 * Proje 2 Sinema Bileti Fiyatlandırma Sistemi
 */

import java.util.Scanner;
public class SinemaBileti {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- SİNEMA BİLETİ SİSTEMİ ---");


        System.out.print("Gün (1-7, 1=Pzt, 7=Paz): ");
        int gun = scanner.nextInt();

        System.out.print("Saat (0-23): ");
        int saat = scanner.nextInt();

        System.out.print("Yaş: ");
        int yas = scanner.nextInt();

        System.out.println("Meslek (1=Öğrenci, 2=Öğretmen, 3=Diğer): ");
        int meslek = scanner.nextInt();

        System.out.println("Film Türü (1=2D, 2=3D, 3=IMAX, 4=4DX): ");
        int filmTuru = scanner.nextInt();

        // Hesaplamalar
        double finalFiyat = calculateFinalPrice(gun, saat, yas, meslek, filmTuru);

        // Fiş yazdırma
        generateTicketInfo(gun, saat, yas, meslek, filmTuru, finalFiyat);

        scanner.close();
    }

    // Hafta sonu kontrolü
    public static boolean isWeekend(int gun) {
        return (gun == 6 || gun == 7);
    }

    // Matine kontrolü
    public static boolean isMatinee(int saat) {
        return saat < 12;
    }

    // Temel fiyat hesaplama
    public static double calculateBasePrice(int gun, int saat) {
        boolean haftaSonu = isWeekend(gun);
        boolean matine = isMatinee(saat);

        if (haftaSonu) {
            if (matine) return 55.0;
            else return 85.0;
        } else {
            if (matine) return 45.0;
            else return 65.0;
        }
    }

    // İndirim hesaplama
    public static double calculateDiscount(int yas, int meslek, int gun) {
        double indirimOrani = 0.0;

        // Meslek İndirimleri
        switch (meslek) {
            case 1: // Öğrenci
                if (gun >= 1 && gun <= 4) indirimOrani = 0.20;
                else indirimOrani = 0.15;
                break;
            case 2: // Öğretmen
                if (gun == 3) indirimOrani = 0.35;
                break;
            case 3: // Diğer
                break;
        }

        // Eğer meslek indirimi uygulanmadıysa yaş indirimine bak
        if (indirimOrani == 0.0) {
            if (yas >= 65) indirimOrani = 0.30;
            else if (yas < 12) indirimOrani = 0.25;
        }
        return indirimOrani;
    }

    // Film türüne göre ekstra ücretler
    public static double getFormatExtra(int filmTuru) {
        switch (filmTuru) {
            case 1: return 0.0;  // 2D
            case 2: return 25.0; // 3D
            case 3: return 35.0; // IMAX
            case 4: return 50.0; // 4DX
            default: return 0.0;
        }
    }

    public static double calculateFinalPrice(int gun,
                                             int saat,
                                             int yas,
                                             int meslek,
                                             int filmTuru) {
        double temelFiyat = calculateBasePrice(gun, saat);
        double indirimOrani = calculateDiscount(yas, meslek, gun);
        double ekstraUcret = getFormatExtra(filmTuru);

        double indirimTutari = temelFiyat * indirimOrani;
        double araToplam = temelFiyat - indirimTutari;

        return araToplam + ekstraUcret;
    }

    // Bilet bilgisi yazdırma
    public static void generateTicketInfo(int gun,
                                          int saat,
                                          int yas,
                                          int meslek,
                                          int filmTuru,
                                          double fiyat) {
        System.out.println("       SİNEMA BİLETİ FİŞİ      ");
        System.out.println("###############################");

        String gunAdi =" ";
        switch(gun) {
            case 1: gunAdi="Pazartesi"; break;
            case 2: gunAdi="Salı"; break;
            case 3: gunAdi="Çarşamba"; break;
            case 4: gunAdi="Perşembe"; break;
            case 5: gunAdi="Cuma"; break;
            case 6: gunAdi="Cumartesi"; break;
            case 7: gunAdi="Pazar"; break;
        }

        System.out.println("Gün/Saat : " + gunAdi + " / " + saat +":00");

        String turAdi = " ";
        switch(filmTuru) {
            case 1: turAdi="2D"; break;
            case 2: turAdi="3D"; break;
            case 3: turAdi="IMAX"; break;
            case 4: turAdi="4DX"; break;
        }
        System.out.println("Format : " + turAdi);

        double temel = calculateBasePrice(gun, saat);
        double indirim = calculateDiscount(yas, meslek, gun);
        double ekstra = getFormatExtra(filmTuru);

        System.out.printf("Temel F. : %.2f TL\n", temel);
        if(indirim > 0) {
            System.out.printf("İndirim  : -%.2f TL (%%%d)\n", (temel*indirim), (int)(indirim*100));
        }
        System.out.printf("Ekstra : +%.2f TL\n", ekstra);
        System.out.println("-------------------------------");
        System.out.printf("TOPLAM : %.2f TL\n", fiyat);
        System.out.println("###############################");
    }
}
