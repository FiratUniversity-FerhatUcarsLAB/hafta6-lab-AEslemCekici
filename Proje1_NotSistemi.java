/*
 * Ad Soyad: Ayşe Eslem ÇEKİCİ
 * Öğrenci No: 250541026
 * Proje 1 Öğrenci Not Değerlendirme Sistemi
 * Öğrencinin aldığı vize, final ve ödev notlarına göre ortalama, harf notunu hesaplayan, geçti kalma durumunu, bütünleme
 * hakkını ve onur listesinde olup olmadığını yazdıran program
 */

import java.util.Scanner;

public class NotSistemi{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("---- ÖĞRENCİ NOT SİSTEMİ ----");

        // Girdilerin alınması
        System.out.print("Vize Notu (0-100):");
        double vize = scanner.nextDouble();

        System.out.print("Final Notu (0-100):");
        double finalNotu = scanner.nextDouble();

        System.out.print("Ödev Notu (0-100):");
        double odev = scanner.nextDouble();

        // Hesaplamalar
        double ortalama =calculateAverage(vize, finalNotu, odev);
        boolean gectiMi = isPassingGrade(ortalama);
        char harfNotu = getLetterGrade(ortalama);
        boolean onurBelgesi = isHonorList(ortalama, vize, finalNotu, odev);
        boolean butunlemeHakki = hasRetakeRight(ortalama);

        // Çıktıların ekrana yazdırılması

        System.out.println("\n--- SONUÇ RAPORU ---");
        System.out.printf("Ortalama: %.2f\n", ortalama);
        System.out.println("Harf Notu: " + harfNotu);


        if (gectiMi) {
            System.out.println("Durum: GEÇTİ");
        } else {
            System.out.println("Durum: KALDI");
        }


        if (onurBelgesi) {
            System.out.println("Onur Belgesi: Kazandınız");
        } else {
            System.out.println("Onur Belgesi: Kazanamadınız");
        }


        if (butunlemeHakki) {
            System.out.println("Bütünleme: Hakkınız Var");
        } else {
            System.out.println("Bütünleme: Hakkınız Yok");
        }

        scanner.close();
    }

    public static double calculateAverage(double vize, double finalNotu, double odev) {
        return (vize * 0.30) + (finalNotu * 0.40) + (odev * 0.30);
    }

    // Geçme kontrolü
    public static boolean isPassingGrade(double ortalama) {
        return ortalama >= 50;
    }

    // Harf notu belirleme
    public static char getLetterGrade(double ortalama) {
        if (ortalama >= 90)
            return 'A';
        else if (ortalama >= 80)
            return 'B';
        else if (ortalama >= 70)
            return 'C';
        else if (ortalama >= 60)
            return 'D';
        else if (ortalama >= 50)
            return 'E';
        else
            return 'F';
    }

    // Onur listesi kontrolü
    public static boolean isHonorList(double ortalama,
                                      double vize,
                                      double finalNotu,
                                      double odev) {
        boolean notlarYuksek = (vize >= 70 && finalNotu >= 70 && odev >= 70);
        return (ortalama >= 85 && notlarYuksek);
    }

    // Bütünleme hakkı kontrolü
    public static boolean hasRetakeRight(double ortalama) {
        return (ortalama >= 40 && ortalama < 50);
    }
}
