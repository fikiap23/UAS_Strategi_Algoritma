import java.util.ArrayList;
import java.util.List;

/**
 * Sebuah program untuk mencari titik pemberhentian yang disarankan untuk
 * perjalanan sepeda Toni.
 */
public class ToniBersepeda {

    /**
     * Menemukan titik pemberhentian yang disarankan untuk perjalanan sepeda Toni.
     *
     * @param args argumen baris perintah (tidak digunakan)
     */
    public static void main(String[] args) {
        int[] warungs = { 10, 25, 30, 40, 50, 75, 80, 110, 130 };
        int totalJarak = 140;
        int maksJarakIstirahat = 30;

        List<Integer> pemberhentianTerbaik = new ArrayList<>();
        int jumlahPemberhentianMin = Integer.MAX_VALUE;

        int subset = 1 << warungs.length;

        // Menggunakan metode pembangkitan subset dengan representasi bit
        for (int i = 0; i < subset; i++) {
            List<Integer> pemberhentianSaatIni = new ArrayList<>();
            int jarakSaatIni = 0;
            int jumlahPemberhentianSaatIni = 0;
            boolean valid = true;

            // Memeriksa setiap bit dalam representasi biner untuk menentukan subset
            for (int j = 0; j < warungs.length; j++) {
                if ((i & (1 << j)) != 0) {
                    pemberhentianSaatIni.add(warungs[j]);
                }
            }

            // Menambahkan totalJarak sebagai pemberhentian terakhir
            pemberhentianSaatIni.add(totalJarak);

            // Memeriksa jarak antara pemberhentian saat ini
            for (int j = 0; j < pemberhentianSaatIni.size(); j++) {
                int jarak = pemberhentianSaatIni.get(j) - jarakSaatIni;

                // Memeriksa apakah jarak melebihi batas maksimum jarak istirahat
                if (jarak > maksJarakIstirahat) {
                    valid = false;
                    break;
                }

                jarakSaatIni = pemberhentianSaatIni.get(j);
                jumlahPemberhentianSaatIni++;
            }

            // Memperbarui titik pemberhentian terbaik jika memenuhi kriteria
            if (valid && jumlahPemberhentianSaatIni < jumlahPemberhentianMin) {
                pemberhentianSaatIni.remove(pemberhentianSaatIni.size() - 1);

                pemberhentianTerbaik = pemberhentianSaatIni;
                jumlahPemberhentianMin = jumlahPemberhentianSaatIni;
            }
        }

        System.out.println("Tempat pemberhentian yang disarankan:");
        for (int pemberhentian : pemberhentianTerbaik) {
            System.out.println(pemberhentian + " km");
        }
    }
}
