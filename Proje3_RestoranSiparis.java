/**
 * Ad Soyad: Ayşe Eslem ÇEKİCİ
 * Öğrenci No: 250541026
 * Proje 3 Akıllı Restoran Sipariş Sistemi
 */

import java.util.Scanner;
public class RestoranSiparis {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- RESTORAN SİPARİŞ ---");

        System.out.println("1-Tavuk(85) 2-Kebap(120) 3-Levrek(110) 4-Mantı(65) 0-Yok");
        System.out.print("Ana Yemek Seçimi: ");
        int anaYemek = scanner.nextInt();

        System.out.println("1-Çorba(25) 2-Humus(45) 3-Börek(55) 0-Yok");
        System.out.print("Başlangıç Seçimi: ");
        int baslangic = scanner.nextInt();

        System.out.println("1-Kola(15) 2-Ayran(12) 3-M.Suyu(35) 4-Limonata(25) 0-Yok");
        System.out.print("İçecek Seçimi: ");
        int icecek = scanner.nextInt();

        System.out.println("1-Künefe(65) 2-Baklava(55) 3-Sütlaç(35) 0-Yok");
        System.out.print("Tatlı Seçimi: ");
        int tatli = scanner.nextInt();

        // Ek Bilgiler
        System.out.print("Saat (0-23): ");
        int saat = scanner.nextInt();
        System.out.print("Gün (1-7): "); // Pzt-Cum hafta içi
        int gun = scanner.nextInt();
        System.out.print("Öğrenci misiniz? (true/false): ");
        boolean ogrenciMi = scanner.nextBoolean();

        // Fiyatları getirme
        double fiyatAna = getMainDishPrice(anaYemek);
        double fiyatBas = getAppetizerPrice(baslangic);
        double fiyatIcecek = getDrinkPrice(icecek);
        double fiyatTatli = getDessertPrice(tatli);

        // Happy Hour Kontrolü
        if (isHappyHour(saat)) {
            // İçecek %20 indirimli
            fiyatIcecek = fiyatIcecek * 0.80;
        }

        double araToplam = fiyatAna + fiyatBas + fiyatIcecek + fiyatTatli;

        // İndirimleri Hesaplama
        boolean comboVar = isComboOrder(anaYemek, icecek, tatli);
        boolean haftaIci = (gun >= 1 && gun <= 5);
        boolean ogrenciIndirimi = (ogrenciMi && haftaIci);

        double toplamIndirim = calculateDiscount(araToplam, comboVar, ogrenciIndirimi, saat);

        double indirimliTutar = araToplam - toplamIndirim;
        double bahsis = calculateServiceTip(indirimliTutar);
        double genelToplam = indirimliTutar + bahsis;

        // Fiş Yazdırma
        System.out.println("\n--- HESAP DETAYI ---");
        System.out.printf("Ara Toplam: %.2f TL\n", araToplam);

        if (isHappyHour(saat) && getDrinkPrice(icecek) > 0) {
            System.out.println("* Happy Hour içecek indirimi uygulandı.");
        }

        if (toplamIndirim > 0) {
            System.out.printf("Toplam İndirim: -%.2f TL\n", toplamIndirim);
            if (comboVar) System.out.println("  (Combo Menü: %15)");
            if (araToplam > 200) System.out.println("  (200 TL Üzeri: %10)");
            if (ogrenciIndirimi) System.out.println("  (Öğrenci: %10)");
        }

        System.out.printf("Ödenecek Tutar: %.2f TL\n", indirimliTutar);
        System.out.printf("Önerilen Bahşiş: %.2f TL\n", bahsis);
        System.out.println("");
        System.out.printf("GENEL TOPLAM: %.2f TL\n", genelToplam);

        scanner.close();
    }

    // Ana Yemek Fiyatları
    public static double getMainDishPrice(int secim) {
        switch (secim) {
            case 1: return 85.0;
            case 2: return 120.0;
            case 3: return 110.0;
            case 4: return 65.0;
            default: return 0.0;
        }
    }

    // Başlangıç Fiyatları
    public static double getAppetizerPrice(int secim) {
        switch (secim) {
            case 1: return 25.0;
            case 2: return 45.0;
            case 3: return 55.0;
            default: return 0.0;
        }
    }

    // İçecek Fiyatları
    public static double getDrinkPrice(int secim) {
        switch (secim) {
            case 1: return 15.0;
            case 2: return 12.0;
            case 3: return 35.0;
            case 4: return 25.0;
            default: return 0.0;
        }
    }

    // Tatlı Fiyatları
    public static double getDessertPrice(int secim) {
        switch (secim) {
            case 1: return 65.0;
            case 2: return 55.0;
            case 3: return 35.0;
            default: return 0.0;
        }
    }

    // Combo Kontrolü
    public static boolean isComboOrder(int anaVar, int icecekVar, int tatliVar) {
        return (anaVar > 0 && icecekVar > 0 && tatliVar > 0);
    }

    // Happy Hour Kontrolü
    public static boolean isHappyHour(int saat) {
        return (saat >= 14 && saat <= 17);
    }

    // Genel İndirim Hesaplama
    public static double calculateDiscount(double tutar, boolean combo, boolean ogrenci, int saat) {
        double indirimTutari = 0.0;

        // Combo Menü İndirimi (%15)
        if (combo) {
            indirimTutari += tutar * 0.15;
        }

        // 200 TL Üzeri İndirim (%10)

        if (tutar > 200) {
            indirimTutari += tutar * 0.10;
        }

        // Öğrenci İndirimi (%10)
        if (ogrenci) {
            indirimTutari += tutar * 0.10;
        }
        return indirimTutari;
    }

    // Bahşiş Hesaplama (%10)
    public static double calculateServiceTip(double tutar) {
        return tutar * 0.10;
    }
}
